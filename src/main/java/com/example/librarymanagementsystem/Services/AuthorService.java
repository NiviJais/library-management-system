package com.example.librarymanagementsystem.Services;

import com.example.librarymanagementsystem.Entites.Author;
import com.example.librarymanagementsystem.Repository.AuthorRepository;
import com.example.librarymanagementsystem.RequestDtos.AddAuthorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private MailSender mailSender;
    public String addAuthor(@RequestBody AddAuthorRequest addAuthorRequest){

        Author authorEntity = new Author(addAuthorRequest.getAuthorName(),addAuthorRequest.getAuthorAge(),addAuthorRequest.getEmailId());

        Author newAuthor = authorRepository.save(authorEntity);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("Hi "+newAuthor.getAuthorName()+" !");

        message.setFrom("springacciojob@gmail.com");
        message.setTo(newAuthor.getEmailId());


        message.setText("You have been successfully Registered on our portal !" +
                "Looking forward for adding more books ");

        mailSender.send(message);

        return "Author with authorId "+ newAuthor.getAuthorId() +" has been saved into DB";
    }
}
