package br.com.alura.forum.dto;

import lombok.Getter;

@Getter
public class TokenDTO {
    
    private String token;
    private String tipo;

    public TokenDTO(String toke, String tipo){
        this.token = toke;
        this.tipo = tipo;
    }
}
