package com.example.librarymanagementsystem.RequestDtos;

import com.example.librarymanagementsystem.Entites.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssociateCardStudentRequest {

    private Integer studentId;

    private Integer cardId;
}
