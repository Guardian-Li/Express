package express.guyuxiao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import express.guyuxiao.dao.BillMapper;
import express.guyuxiao.pojo.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.StyledEditorKit;
import java.util.List;

@Controller
@RequestMapping("/main")
public class MainController {
    @Autowired
    BillMapper billMapper;
    @RequestMapping("/addBill")
    public String addBill(
            @RequestParam("sendName")String sendName,
            @RequestParam("sendPhoneNumber")String sendPhoneNumber,
            @RequestParam("sendLocal")String sendLocal,
            @RequestParam("recName")String recName,
            @RequestParam("recPhoneNumber")String recPhoneNumber,
            @RequestParam("recLocal")String recLocal,
            Model model){
        Bill bill = new Bill();
        if(sendName.isEmpty() || sendPhoneNumber.isEmpty() || sendLocal.isEmpty() ||
        recName.isEmpty()|| recPhoneNumber.isEmpty() || recLocal.isEmpty()){
            model.addAttribute("msg","信息填写不完整");
            return "main";
        }else{
            bill.setSendName(sendName);
            bill.setSendPhoneNumber(sendPhoneNumber);
            bill.setSendLocal(sendLocal);
            bill.setRecName(recName);
            bill.setRecPhoneNumber(recPhoneNumber);
            bill.setRecLocal(recLocal);
            Integer id = billMapper.selectCount(new QueryWrapper<>())+1000;
            bill.setId(id+1);
            if(billMapper.insert(bill) == 1)
                model.addAttribute("msg","插入成功，订单号为："+id);
        }

        return "main";

    }
    @RequestMapping("/search")
    public String search(@RequestParam("message")String message,
                         Model model){
        Boolean isNumber=true;
        QueryWrapper<Bill> queryWrapper = new QueryWrapper<>();
        List<Bill> billList;
        for(int i=0; i<message.length(); ++i){
            if(!Character.isDigit(message.charAt(i))){
                isNumber=false;
            }
        }
        System.out.println(isNumber);
        if(isNumber){
            if(message.length()==11){
                billList=billMapper.selectList(queryWrapper.eq("send_phone_number",message).or().eq("rec_phone_number",message));
            }else{
                Integer id = Integer.parseInt(message);
                billList =billMapper.selectList(queryWrapper.eq("id",id));
            }

        }else{
            billList=billMapper.selectList(queryWrapper.like("rec_name",message).or().like("send_name",message));
        }

        if(billList.isEmpty()==false)
        {
            model.addAttribute("bills",billList);
            model.addAttribute("searchMsg","共 "+billList.size()+" 条搜索结果");
        }
        else
            model.addAttribute("searchMsg","无搜索结果");
        return "main";

    }
    @GetMapping("/detail/{id}")
    public String billDetail(@PathVariable("id")Integer id ,Model model){
       // Integer Id = Integer.parseInt(id);
       // QueryWrapper<Bill> queryWrapper = new QueryWrapper<>();
        model.addAttribute("bill",billMapper.selectById(id));

        return "detail";
    }

    @GetMapping("/delete/{id}")
    public String billDelete(@PathVariable("id")Integer id){
        billMapper.deleteById(id);
        return "main";
    }


}
