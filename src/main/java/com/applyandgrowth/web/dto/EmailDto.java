package com.applyandgrowth.web.dto;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;


@Data
public class EmailDto {

    @NotEmpty
    @Email
    private String emailFrom;
    @NotEmpty
    @NotEmpty(message = "Email should not be empty")
    private String emailTo;
    @NotEmpty
    private String subject;
    @NotEmpty
    private String text;

}