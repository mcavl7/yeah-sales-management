package com.amigos.yeah.domain.enums;

// Evitar ao máximo de mexer nessa classe
// Estrutura responsável por garantir a integridade dos códigos de cada tipo enumerado
// Antes de alterar qualquer coisa aqui falar com Mateus Cavalcanti 
public enum TipoCliente {
    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "Pessoa Jurídica");

    private int cod;
    private String descricao;

    private TipoCliente(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoCliente toEnum(Integer cod) {
        if (cod == null) {
            return null;
        } 

        // Percorre o tipo enumerado
        for (TipoCliente x : TipoCliente.values()) {
            
            if (cod.equals(x.getCod())) {
                return x;
            }

        }

        throw new IllegalArgumentException("Id Inválido: " + cod);

    }
}
