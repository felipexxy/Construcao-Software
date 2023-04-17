package com.applyandgrowth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import com.applyandgrowth.enums.StatusEmail;
import com.applyandgrowth.models.Email;
import com.applyandgrowth.repository.EmailRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;


    @Autowired
    private JavaMailSender emailSender;

    @Transactional
    public Email sendEmail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        email.setSendDateEmail(LocalDateTime.now());
        try{
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            emailSender.send(message);
            email.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e){
            email.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(email);
    }
}
    public Page<Email> findAll(Pageable pageable) {
        return  emailRepository.findAll(pageable);
    }

    public Optional<Email> findById(UUID emailId) {
        return emailRepository.findById(emailId);
    }
}