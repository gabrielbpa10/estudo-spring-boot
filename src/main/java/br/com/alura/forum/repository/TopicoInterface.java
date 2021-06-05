package br.com.alura.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.forum.modelo.Topico;

public interface TopicoInterface extends JpaRepository<Topico,Long> {
    
    @Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso AND t.curso.categoria =:categoriaCurso")
    List<Topico> carregarPorNomeDoCurso(@Param("nomeCurso") String nomeCurso, @Param("categoriaCurso") String categoriaCurso);
}
