package express.guyuxiao.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import express.guyuxiao.pojo.User;
import jdk.jfr.Registered;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@Registered
public interface UserMapper extends BaseMapper<User> {
}
