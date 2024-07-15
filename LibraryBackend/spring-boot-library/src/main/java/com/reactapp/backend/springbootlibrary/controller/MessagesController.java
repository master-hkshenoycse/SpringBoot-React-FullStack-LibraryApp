package com.reactapp.backend.springbootlibrary.controller;


import com.reactapp.backend.springbootlibrary.entity.Message;
import com.reactapp.backend.springbootlibrary.requestmodels.AdminQuestionRequest;
import com.reactapp.backend.springbootlibrary.service.MessagesService;
import com.reactapp.backend.springbootlibrary.utils.ExtractJWT;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("https://localhost:3000")
@RequestMapping("/api/messages")
public class MessagesController {

    private MessagesService messagesService;

    @PostMapping("/secure/add/message")
    public void postMessage(@RequestHeader(value="Authorization") String token,
                            @RequestBody Message messageRequest){

        String userEmail= ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        messagesService.postMessage(messageRequest,userEmail);


    }

    @PutMapping("/secure/admnin/message")
    public void putMessage(@RequestHeader(value="Authorization") String token,
                            @RequestBody AdminQuestionRequest adminQuestionRequest) throws Exception{

        String userEmail= ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        String admin=ExtractJWT.payloadJWTExtraction(token,"\"userType\"");

        if(admin==null || !admin.equals("admin")){
            throw new Exception("Admin page only");
        }
        messagesService.putMessage(adminQuestionRequest,userEmail);


    }
}
