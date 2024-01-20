package com.example.librarymanagementsystem.Entites;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    private String name;

    private String branch;

    private double cgpa;

    private String phoneNo;

    /*
        mappedBy contains the value of variable name : Foreign Key variable name in child table
    */
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private LibraryCard libraryCard;
}
