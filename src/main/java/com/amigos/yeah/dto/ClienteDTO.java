package com.amigos.yeah.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.amigos.yeah.domain.Cliente;
import com.amigos.yeah.services.validations.ClientUpdate;

import org.hibernate.validator.constraints.Length;

@ClientUpdate
public class ClienteDTO implements Serializable {
    
    private Integer id; 
    
    @NotEmpty(message = "Não pode ser vazio")
    @Length(min=5, max=200, message = "Tamanho deve ser minimo de 5 e máximo de 80")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Email obrigatório")
    private String email;

    // private ClienteDTO() {}

    public ClienteDTO(Cliente obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.email = obj.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}
