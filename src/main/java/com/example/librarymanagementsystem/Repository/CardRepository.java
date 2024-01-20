package com.example.librarymanagementsystem.Repository;

import com.example.librarymanagementsystem.Entites.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<LibraryCard, Integer> {
}
