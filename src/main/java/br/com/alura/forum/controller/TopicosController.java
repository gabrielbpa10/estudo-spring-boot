package br.com.alura.forum.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.dto.TopicoDTO;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicosController {
    
    @RequestMapping("/topicos")
    public List<TopicoDTO> getListTopicos(){
        Topico topico = new Topico(
            "Como instalar Spring Boot na minha máquina",
            "Não consigo instalar o framework na minha máquina", 
            new Curso("Spring Boot (Java)","Java")
            );
        
            return TopicoDTO.converter(Arrays.asList(topico));
    }
}
