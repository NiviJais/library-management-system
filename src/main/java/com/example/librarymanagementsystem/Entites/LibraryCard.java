package com.example.librarymanagementsystem.Entites;


import com.example.librarymanagementsystem.Enums.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "library_card")
@Getter
@Setter@AllArgsConstructor
@NoArgsConstructor
public class LibraryCard {
    public static final Integer MAX_NO_OF_ALLOWED_BOOKS = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;

    //This is annotation for mysql to understand custom data type
    //and store it as a string format inside the DB
    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;

    private int NumOfBooksIssued;

    // library card have foreign key because it is child table
    @JoinColumn
    @OneToOne
    private Student student;

    @OneToMany(mappedBy = "libraryCard", cascade = CascadeType.ALL)
    public List<Transaction> transactionList = new ArrayList<>();

}
