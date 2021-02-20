package express.guyuxiao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String sendName;
    private String sendPhoneNumber;
    private String sendLocal;
    private String recName;
    private String recPhoneNumber;
    private String recLocal;
}
