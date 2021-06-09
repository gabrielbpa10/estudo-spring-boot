package br.com.alura.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.dto.TopicoDTO;
import br.com.alura.forum.dto.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

@RestController 
@RequestMapping("/topicos")
public class TopicosController {
    
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDTO> getListTopicos(String nomeCurso, String categoriaCurso){
        List<Topico> topicos = null;
        if(nomeCurso != null && categoriaCurso != null){
            topicos = topicoRepository.carregarPorNomeDoCurso(nomeCurso,categoriaCurso);
        } else
            topicos = topicoRepository.findAll();

        return TopicoDTO.converter(topicos);
    }

    @PostMapping
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriBuilder){
        Topico topico = topicoForm.converter(cursoRepository);
        topicoRepository.save(topico);
        
        // Sempre no retorno da mensagem, sempre dizer o novo header (location) 
        // e representação do corpo da mensagem
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }
}
