package com.amigos.yeah.services;

import java.sql.Date;

import com.amigos.yeah.domain.Pedido;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

public abstract class AbstractEmailService implements EmailService {
    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmation(Pedido obj) {
        SimpleMailMessage sm = prepareSimpleMailMessaFromPedido(obj);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessaFromPedido(Pedido obj) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getCliente().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Pedido Confirmado" + obj.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(obj.toString());

        return sm;
    }
}
