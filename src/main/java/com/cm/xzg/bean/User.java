package com.cm.xzg.bean;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    private String parentName;

    private String childName;

    private String idCardNumber;

    private String phoneNumber;

    private LocalDateTime regeisteDate;

    private Integer shopType;


}
