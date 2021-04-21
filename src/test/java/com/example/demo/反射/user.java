package com.example.demo.反射;

/**
 * @Author XZQ
 * @Date 2021/4/13 11:31
 **/
@CreatValidator(param = {"notBlank|notBlank","mapConvert|sex|1:男,2:女|性别错误"})
public class user {

    @Convert(title = "姓名")
    @UseValidator(names={"notNull","notBlack"})
    private String name;

    @Convert(title = "性别")
    @UseValidator(names={"sex"})
    private String sex;

}
