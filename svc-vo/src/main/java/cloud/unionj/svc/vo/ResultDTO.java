package cloud.unionj.svc.vo;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("code")
    private Integer code;
    @JsonProperty("msg")
    private String msg;
    @JsonProperty("data")
    private T data;

}
