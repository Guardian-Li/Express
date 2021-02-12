package express.guyuxiao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mysql.cj.util.StringUtils;
import express.guyuxiao.dao.UserMapper;
import express.guyuxiao.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/index")
    public String indexLogin(@RequestParam("Username")String Username,
                             @RequestParam("Password")String Password,
                             @RequestParam("verify")String verify,
                             Model model,
                             HttpSession session){
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        User user = userMapper.selectOne(queryWrapper.eq("username",Username));
        if(Username==null || Password==null){
            model.addAttribute("msg","请输入完整用户信息");
            return "index";
        }
        if(user == null){
            model.addAttribute("msg","找不到用户");
            return "index";
        }else{
            if(user.getPassword().equals(Password)){
                if(StringUtils.isNullOrEmpty(verify)){
                    model.addAttribute("msg","请输入验证码");
                    return "index";
                }else{
                    String kaptchaCode = session.getAttribute("verifyCode") + "";
                    if (StringUtils.isNullOrEmpty(kaptchaCode) || verify.equals(kaptchaCode)) {

                        session.setAttribute("loginUser","user");

                        return  "redirect:/main";
                    }else{
                        model.addAttribute("msg","验证码不正确");
                        return "index";
                    }
                }


            }else{
                model.addAttribute("msg","密码错误");
                return "index";
            }
        }
    }

    @RequestMapping("/signUp")
    public String signUp(@RequestParam("Username")String Username,
                         @RequestParam("Password")String Password,
                         @RequestParam("rePassword")String rePassword,
                         Model model){
        if(Username==null || Password==null || rePassword ==null){
            model.addAttribute("msg","请输入完整用户信息");
            return "signUp";
        }else if( rePassword.equals(Password) == false){
            model.addAttribute("msg","两次密码不一致");
            return "signUp";
        }else{
            User user=new User(Username,Password);
            userMapper.insert(user);
            model.addAttribute("msg","注册成功");
            return "index";
        }
    }
    @RequestMapping("/sign")
    public String sign(){
        return "signUp";
    }
}
