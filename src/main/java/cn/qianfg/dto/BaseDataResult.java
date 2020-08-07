package cn.qianfg.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "返回数据类封装")
public class BaseDataResult<T> extends BaseResult {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "返回数据")
    private T data;

    public BaseDataResult() {
        super("000000");
    }

    public BaseDataResult(T data){
        super("000000");
        this.data = data;
    }
}
