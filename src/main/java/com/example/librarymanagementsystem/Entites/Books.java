package com.example.librarymanagementsystem.Entites;


import com.example.librarymanagementsystem.Enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(unique = true)
    private String bookName;

    @Enumerated(value = EnumType.STRING)
    private Genre bookGenre;

    private int noOfPages;

    private int price;
    private Date publishDate;

    private Boolean isAvailable;

    @JoinColumn(referencedColumnName = "emailId")
    @ManyToOne
    private Author author;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    public List<Transaction> transactionList = new ArrayList<>();

    public Books(String bookName, Genre bookGenre, int noOfPages, int price, Date publishDate) {
        this.bookName = bookName;
        this.bookGenre = bookGenre;
        this.noOfPages = noOfPages;
        this.price = price;
        this.publishDate = publishDate;
    }
}
