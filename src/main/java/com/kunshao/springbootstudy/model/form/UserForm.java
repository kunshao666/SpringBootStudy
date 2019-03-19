package com.kunshao.springbootstudy.model.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel
public class UserForm {
    @ApiModelProperty(value = "用户ID",required = true)
    @NotNull(message = "id不能为空!")
//    @Size(min = 1,message = "用户id不能小于 1 !")
    private Long id;

    @ApiModelProperty(value = "标签ID")
    @NotNull(message = "标签id不能为空!")
//    @Size(min = 1,message = "id不能小于 1 !")
    private Long tagId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }
}
