package com.applyandgrowth.models;

import com.applyandgrowth.enums.StatusEmail;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;



@Entity
public class Email implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String text;
    private StatusEmail statusEmail;


    public String getEmailFrom() {
        return emailFrom;
    }
    public String getEmailTo() {
        return emailTo;
    }
    public String getSubject() {
        return subject;
    }
    public String getText() {
        return text;
    }
    public void setStatusEmail(StatusEmail sent) {
        this.statusEmail = sent;
    }
}