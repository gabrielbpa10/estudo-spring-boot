package br.com.alura.forum.config.validacao;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ErroDeFormularioDto {

    private String campo;
    private String erro;

    public ErroDeFormularioDto(String campo, String erro){
        this.campo = campo;
        this.erro = erro;
    }
}
