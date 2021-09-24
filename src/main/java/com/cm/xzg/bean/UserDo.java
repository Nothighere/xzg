package com.cm.xzg.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDo {

    @NotNull(message = "家长姓名不能为空")
    @Size(max = 50)
    private String parentName;

    @NotNull(message = "孩子姓名不能为空")
    @Size(max = 50)
    private String childName;

    @NotNull(message = "身份证号不能为空")
    @Size(max = 50)
    private String idCardNumber;

    @NotNull(message = "电话号码不能为空")
    private String phoneNumber;

    @NotNull(message = "参加时间不能为空")
    private int regeisteDate;

}
