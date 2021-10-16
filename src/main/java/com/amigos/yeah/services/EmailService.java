package com.amigos.yeah.services;

import com.amigos.yeah.domain.Pedido;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmation(Pedido obj);

    void sendEmail(SimpleMailMessage msg);

}
