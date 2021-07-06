package br.com.alura.forum.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.alura.forum.dto.AtualizacaoTopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.TopicoRepository;

@Component
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public void salvar(Topico topico){
        topicoRepository.save(topico);
    }

    public Page<Topico> listarTudo(Pageable paginacao){
        return topicoRepository.findAll(paginacao);
    }

    public Page<Topico> listarPorCurso(String nomeCurso, Pageable paginacao){
        return topicoRepository.findByCursoNome(nomeCurso, paginacao);
    }

    public Optional<Topico> buscarPorId(Long id){
        return topicoRepository.findById(id);
    }

    public void deletar(Topico topico){
        topicoRepository.delete(topico);
    }

    public Topico atualizar(Long id, AtualizacaoTopicoForm form){
        Topico topico = topicoRepository.getOne(id);
        topico.setTitulo(form.getTitulo());
        topico.setMensagem(form.getMensagem());

        return topico;
    }
}