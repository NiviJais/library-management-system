package com.example.librarymanagementsystem.Repository;

import com.example.librarymanagementsystem.Entites.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
