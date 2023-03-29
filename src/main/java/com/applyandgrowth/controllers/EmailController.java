package com.applyandgrowth.controllers;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailController {
    @Autowired
    private JavaMailSender javaMailSender;

}
