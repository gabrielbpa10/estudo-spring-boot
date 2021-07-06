package br.com.alura.forum.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.repository.CursoRepository;
import io.jsonwebtoken.lang.Assert;

@DataJpaTest
public class CursoTeste {

    @Autowired
    private CursoRepository cursoRepository;

    @Test
    public void buscarCursoPorNomeDoCurso(){
        String nomeCurso = "HTML 5";
        Curso curso = cursoRepository.findByNome(nomeCurso);
        Assert.notNull(curso);
    }

}
