package com.example.librarymanagementsystem.Services;

import com.example.librarymanagementsystem.Entites.Books;
import com.example.librarymanagementsystem.Entites.LibraryCard;
import com.example.librarymanagementsystem.Entites.Transaction;
import com.example.librarymanagementsystem.Enums.TransactionType;
import com.example.librarymanagementsystem.Enums.TransactionStatus;
import com.example.librarymanagementsystem.Exceptions.BookNotAvailableException;
import com.example.librarymanagementsystem.Exceptions.BookNotFoundException;
import com.example.librarymanagementsystem.Exceptions.CardNotFoundException;
import com.example.librarymanagementsystem.Exceptions.MaxLimitReachedException;
import com.example.librarymanagementsystem.Repository.BookRepository;
import com.example.librarymanagementsystem.Repository.CardRepository;
import com.example.librarymanagementsystem.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;

    public String issueBook(Integer cardId, Integer bookId)throws Exception{

        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.ISSUED);
        transaction.setTransactionStatus(TransactionStatus.ONGOING);

        // Get the book and card entity from DB

        Optional<Books> bookOptional = bookRepository.findById(bookId);

        if(bookOptional.isEmpty()){
            throw new BookNotFoundException("Book Id entered is Invalid");
        }

        Optional<LibraryCard> libraryCardOptional = cardRepository.findById(cardId);

        if(libraryCardOptional.isEmpty()){
            throw  new CardNotFoundException("Card Id is invalid");
        }

        Books book = bookOptional.get();
        LibraryCard card = libraryCardOptional.get();

        // 2. Validate book and card entity variable

        //Check for Availability
        if(book.getIsAvailable()==Boolean.FALSE){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transaction = transactionRepository.save(transaction);
            throw new BookNotAvailableException("Book with book ID is not available. Transaction "+transaction.getTransactionId());
        }

        //Check for Max Book issued
        if(card.getNumOfBooksIssued()>=LibraryCard.MAX_NO_OF_ALLOWED_BOOKS){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transaction = transactionRepository.save(transaction);
            throw new MaxLimitReachedException("You have reached the max limit of issed books" +
                    "please return a book in order to issue new " +
                    "Transaction Id"+transaction.getTransactionId());
        }


        //If you have reached that means all validations are OK

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        //3. update the card and the book status
        book.setIsAvailable(Boolean.FALSE);
        card.setNumOfBooksIssued(card.getNumOfBooksIssued()+1);

        transaction.setBook(book);
        transaction.setLibraryCard(card);

        //Save the child as it will cascade to both of the Parents
        transaction = transactionRepository.save(transaction);

        return "The transaction with Id"+transaction.getTransactionId()+" has been saved to the DB";
    }
}
