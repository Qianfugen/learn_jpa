package cn.qianfg.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "通知返回结果")
public class BaseResult extends BaseDto {

    public final static String SUCCESS = "000000";

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "结果码，000000表示正常，其他不正常")
    private String code;

    @ApiModelProperty(value = "异常消息，已国际化")
    private String message;

    public BaseResult() {
        this("000000");
    }

    public BaseResult(String code) {
        this.code = code;
    }

    public BaseResult(Exception exception) {

    }

    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESS.equals(code);
    }
}
