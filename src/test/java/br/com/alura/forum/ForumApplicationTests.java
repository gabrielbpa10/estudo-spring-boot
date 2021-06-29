package br.com.alura.forum;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ForumApplicationTests {

	@Autowired
    private DataSource dataSource;

	@Test
	void contextLoads() {
		System.out.println("O nome do Data Source -> " + dataSource.getClass().getName());
	}

}
