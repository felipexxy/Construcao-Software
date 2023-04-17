package com.applyandgrowth.models;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class PasswordResetToken {
    
    private static final int EXPIRATION_MINUTES = 60 * 24; // 24 horas
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;
    
    private String token;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataExpiracao;
    
    // Construtor padrão
    public PasswordResetToken() {}
    
    // Construtor com usuário
    public PasswordResetToken(User usuario) {
        this.usuario = usuario;
        this.token = gerarTokenAleatorio();
        this.dataExpiracao = calcularDataExpiracao(EXPIRATION_MINUTES);
    }
    
    // Getters e Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(Date dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }
    
    // Métodos auxiliares
    
    private String gerarTokenAleatorio() {
        // Implemente aqui a lógica para gerar um token aleatório
        // Uma forma simples é usar a classe SecureRandom do Java
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        return new String(Base64.getEncoder().encode(bytes));
    }
    
    private Date calcularDataExpiracao(int minutos) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, minutos);
        return cal.getTime();
    }
}
