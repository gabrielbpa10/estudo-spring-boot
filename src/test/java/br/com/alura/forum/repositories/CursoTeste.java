package br.com.alura.forum.repositories;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.repository.CursoRepository;
import io.jsonwebtoken.lang.Assert;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CursoTeste {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void buscarCursoPorNomeDoCurso(){
        Curso curso = new Curso();
        curso.setNome("HTML 5");
        curso.setCategoria("Web");
        entityManager.persist(curso);

        String nomeCurso = "HTML 5";
        curso = cursoRepository.findByNome(nomeCurso);
        Assert.notNull(curso);
    }

}
