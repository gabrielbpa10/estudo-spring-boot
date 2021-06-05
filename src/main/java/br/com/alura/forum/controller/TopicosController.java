package br.com.alura.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.dto.TopicoDTO;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.TopicoInterface;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicosController {
    
    @Autowired
    private TopicoInterface topicoInterface;

    @RequestMapping("/topicos")
    public List<TopicoDTO> getListTopicos(String nomeCurso, String categoriaCurso){
        List<Topico> topicos = null;
        if(nomeCurso != null && categoriaCurso != null){
            topicos = topicoInterface.carregarPorNomeDoCurso(nomeCurso,categoriaCurso);
        } else
            topicos = topicoInterface.findAll();

        return TopicoDTO.converter(topicos);
    }
}
