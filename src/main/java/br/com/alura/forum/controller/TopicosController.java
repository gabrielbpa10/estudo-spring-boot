package br.com.alura.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;

import java.util.Arrays;
import java.util.List;

@Controller
public class TopicosController {
    
    @RequestMapping("/topicos")
    @ResponseBody
    public List<Topico> getListTopicos(){
        Topico topico = new Topico(
            "Como instalar Spring Boot na minha máquina",
            "Não consigo instalar o framework na minha máquina", 
            new Curso("Spring Boot (Java)","Java")
            );
        
            return Arrays.asList(topico);
    }
}
