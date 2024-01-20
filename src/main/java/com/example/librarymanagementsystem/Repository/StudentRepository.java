package com.example.librarymanagementsystem.Repository;

import com.example.librarymanagementsystem.Entites.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
