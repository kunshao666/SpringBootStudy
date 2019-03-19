package com.kunshao.springbootstudy.controller;

import com.kunshao.springbootstudy.exception.TestException;
import com.kunshao.springbootstudy.model.dto.UserDto;
import com.kunshao.springbootstudy.model.form.UserForm;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Api(tags = {"User 接口"})
@RestController
public class HelloController {

    @ApiOperation("获取 Hello World !")
    @ApiResponses({@ApiResponse(code = 200,message = "成功"),
                   @ApiResponse(code = 400,message = "访问失败"),
                   @ApiResponse(code = 404,message = "错误请求"),
                   @ApiResponse(code = 500,message = "服务器异常")})
    @GetMapping("/hello")
    public String getHello(){
        return "Hello World!";
    }


    @ApiOperation("获取用户信息")
    @ApiResponses({@ApiResponse(code = 200,message = "成功"),
            @ApiResponse(code = 400,message = "访问失败"),
            @ApiResponse(code = 404,message = "错误请求"),
            @ApiResponse(code = 500,message = "服务器异常")})
    @PostMapping(value = "/user/detail")
    public UserDto getUser(@ApiParam(hidden = true)HttpSession session,
                           @Valid @RequestBody UserForm userForm){

        //模拟回传数据
        UserDto userDto = new UserDto();
        userDto.setId(userForm.getId());
        userDto.setAge(20);
        userDto.setName("王坤");
        return userDto;
    }

    @ApiOperation("测试异常拦截器")
    @GetMapping("/test/exception")
    public String getException(){
        throw new TestException(500,"服务器异常");
    }
}
