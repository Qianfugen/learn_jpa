package cn.qianfg.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel(description = "所有与前端交互数据的父类")
public abstract class BaseDto implements Serializable {
    private static final long serialVersionUID = 1L;
}
