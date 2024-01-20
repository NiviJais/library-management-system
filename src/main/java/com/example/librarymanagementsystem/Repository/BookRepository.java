package com.example.librarymanagementsystem.Repository;

import com.example.librarymanagementsystem.Entites.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Books, Integer> {

}
