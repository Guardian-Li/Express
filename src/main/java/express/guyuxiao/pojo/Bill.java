package express.guyuxiao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    private Integer id;
    private String sendName;
    private String sendPhoneNumber;
    private String sendLocal;
    private String recName;
    private String recPhoneNumber;
    private String recLocal;
}
