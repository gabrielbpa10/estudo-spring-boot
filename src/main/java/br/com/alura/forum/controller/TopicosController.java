package br.com.alura.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.dto.AtualizacaoTopicoForm;
import br.com.alura.forum.dto.TopicoDTO;
import br.com.alura.forum.dto.TopicoDetalhesDTO;
import br.com.alura.forum.dto.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import br.com.alura.forum.service.TopicoService;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoService topicoService;

    /*
     * @GetMapping
     * 
     * @Cacheable(value = "listaDeTopicos") public Page<TopicoDTO>
     * listar(@RequestParam(required = false) String nomeCurso,
     * 
     * @PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size =
     * 10) Pageable paginacao) {
     * 
     * if (nomeCurso == null) { Page<Topico> topicos =
     * topicoService.listarTudo(paginacao); return TopicoDTO.converter(topicos); }
     * else { Page<Topico> topicos = topicoService.listarPorCurso(nomeCurso,
     * paginacao); if (topicos.getContent().isEmpty()) {
     * 
     * return Page.empty(paginacao); } return TopicoDTO.converter(topicos); } }
     */
    @GetMapping
    public ResponseEntity<Page<TopicoDTO>> listar(@RequestParam(required = false) String nomeCurso,
            @PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {

        if (nomeCurso != null) {
            Page<TopicoDTO> topicosPaginados = topicoService.listarPorCurso(nomeCurso, paginacao);
            if (topicosPaginados.hasContent()) {
                return ResponseEntity.ok(topicosPaginados);
            }
        } else {
            Page<TopicoDTO> topicos = topicoService.listarTudo(paginacao);
            return ResponseEntity.ok(topicos);
        }
        return ResponseEntity.notFound().header("Pesquisa","Sem resposta").build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDetalhesDTO> detalhar(@PathVariable Long id) {
        Optional<Topico> topico = topicoService.buscarPorId(id);
        if (topico.isPresent())
            return new ResponseEntity<TopicoDetalhesDTO>(new TopicoDetalhesDTO(topico.get()), HttpStatus.OK);

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm topicoForm,
            UriComponentsBuilder uriBuilder) {
        Topico topico = topicoForm.converter(cursoRepository);
        topicoService.salvar(topico);

        // Sempre no retorno da mensagem, sempre dizer o novo header (location)
        // e representação do corpo da mensagem
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

    @DeleteMapping("{id}")
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        Optional<Topico> topico = topicoService.buscarPorId(id);
        if (topico.isPresent()) {
            topicoRepository.deleteById(id);
            return new ResponseEntity<TopicoDTO>(new TopicoDTO(topico.get()), HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form) {
        Optional<Topico> topicoOptional = topicoService.buscarPorId(id);
        if (topicoOptional.isPresent()) {
            Topico topico = topicoService.atualizar(id, form);
            return new ResponseEntity<TopicoDTO>(new TopicoDTO(topico), HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }
}
