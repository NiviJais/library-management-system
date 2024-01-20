package com.example.librarymanagementsystem.Services;

import com.example.librarymanagementsystem.Entites.LibraryCard;
import com.example.librarymanagementsystem.Entites.Student;
import com.example.librarymanagementsystem.Enums.CardStatus;
import com.example.librarymanagementsystem.Repository.CardRepository;
import com.example.librarymanagementsystem.Repository.StudentRepository;
import com.example.librarymanagementsystem.RequestDtos.AssociateCardStudentRequest;
import org.apache.tomcat.jni.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private StudentRepository studentRepository;

    public String getFreshCard(){
        LibraryCard newCard = new LibraryCard();
        newCard.setCardStatus(CardStatus.NEW);
        newCard.setNumOfBooksIssued(0);

        LibraryCard savedCard = cardRepository.save(newCard);

        return "new Card  with card No " + savedCard.getCardId() + " has been generated";
    }

    public String associateCardAndStudent(AssociateCardStudentRequest associateCardStudentRequest)throws Exception{

        Integer cardId = associateCardStudentRequest.getCardId();
        Integer studentId = associateCardStudentRequest.getStudentId();

        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardId);

        if(optionalLibraryCard.isEmpty()){
            throw new Exception("Invalid cardId entered");
        }

        LibraryCard libraryCard = optionalLibraryCard.get();

        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        if(optionalStudent.isEmpty()){
            throw new Exception("No Student with the given Id exist in the system");
        }

        Student student = optionalStudent.get();

        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setStudent(student);
        libraryCard.setNumOfBooksIssued(0);

        cardRepository.save(libraryCard);
        return "card with cardId "+ cardId + " Student with StudentId " + studentId + "are associated";
    }
}
