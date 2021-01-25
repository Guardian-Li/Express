package express.guyuxiao.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import express.guyuxiao.pojo.Bill;
import jdk.jfr.Registered;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
@Registered
public interface BillMapper extends BaseMapper<Bill> {

}
