package br.com.alura.forum.controllers;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TopicosTeste {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deve_devolver_ok_em_buscar_topico_pelo_id() throws Exception{
        URI uri = new URI("/topicos");

        mockMvc.perform(MockMvcRequestBuilders
        .get(uri)
        .pathInfo("/1")
        ).andExpect(MockMvcResultMatchers
        .status()
        .isOk());
    }
}
