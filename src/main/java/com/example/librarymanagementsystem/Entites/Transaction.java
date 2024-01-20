package com.example.librarymanagementsystem.Entites;

import com.example.librarymanagementsystem.Enums.TransactionType;
import com.example.librarymanagementsystem.Enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;


import java.sql.Date;

@Entity
@Table(name = "transactions")
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String transactionId;

    private TransactionStatus transactionStatus;

    @CreatedDate
    private Date CreatedOn;

    private  int fineAmount;

    private TransactionType transactionType;

    @JoinColumn
    @ManyToOne
    private LibraryCard libraryCard;

    @JoinColumn
    @ManyToOne
    private Books book;

}
