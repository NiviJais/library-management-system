package com.example.librarymanagementsystem.RequestDtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyPhoneNumberRequest {

    private Integer studentId;
    private String newPhnNo;
}
