package br.com.alura.forum.dto;

import java.time.LocalDateTime;

import br.com.alura.forum.modelo.Resposta;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RespostaDTO {
    
    private Long id;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;

    public RespostaDTO(Resposta resposta){
        this.id = resposta.getId();
        this.mensagem = resposta.getMensagem();
        this.dataCriacao = resposta.getDataCriacao();
        this.nomeAutor = resposta.getAutor().getNome();
    }

}
