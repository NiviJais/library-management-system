package com.example.librarymanagementsystem.Controllers;

import com.example.librarymanagementsystem.RequestDtos.AssociateCardStudentRequest;
import com.example.librarymanagementsystem.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @PostMapping("generateACard")
    public String addCard(){
        String result = cardService.getFreshCard();
        return result;
    }

    @PutMapping("/associateCardAndStudent")
    public ResponseEntity associateCardAndStudent(@RequestParam AssociateCardStudentRequest associateCardStudentRequest){

        try{
            String result = cardService.associateCardAndStudent(associateCardStudentRequest);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
