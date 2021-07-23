package br.com.alura.forum.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alura.forum.dto.AtualizacaoTopicoForm;
import br.com.alura.forum.dto.TopicoDTO;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.TopicoRepository;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public void salvar(Topico topico) {
        topicoRepository.save(topico);
    }

    public Page<TopicoDTO> listarTudo(Pageable paginacao) {
        Page<Topico> topicos = topicoRepository.findAll(paginacao);

        return topicos.map(topico -> new TopicoDTO(topico));
    }

    public Page<TopicoDTO> listarPorCurso(String nomeCurso, Pageable paginacao) {
        Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);

        return topicos.map(topico -> new TopicoDTO(topico));
    }

    public Optional<Topico> buscarPorId(Long id) {
        return topicoRepository.findById(id);
    }

    public void deletar(Topico topico) {
        topicoRepository.delete(topico);
    }

    public Topico atualizar(Long id, AtualizacaoTopicoForm form) {
        Topico topico = topicoRepository.getOne(id);
        topico.setTitulo(form.getTitulo());
        topico.setMensagem(form.getMensagem());

        return topico;
    }
}