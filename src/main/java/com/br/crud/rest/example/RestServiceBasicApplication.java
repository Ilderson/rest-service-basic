package com.br.crud.rest.example;

import java.util.Date;
import java.util.stream.LongStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.br.crud.rest.example.usuario.Usuario;
import com.br.crud.rest.example.usuario.UsuarioRepository;

@SpringBootApplication
@EnableScheduling
public class RestServiceBasicApplication {

	public static void main(String[] args) {
		String curDir = System.getProperty("user.dir");
		System.out.println(curDir);
		SpringApplication.run(RestServiceBasicApplication.class, args);
	}

	@Bean
    CommandLineRunner init(UsuarioRepository repository) {
        return args -> {
            repository.deleteAll();
            LongStream.range(1, 11)
                    .mapToObj(i -> {
                        Usuario c = new Usuario();
                        c.setBirthDate("11/11/2011");
                        c.setCompanyId(5);
                        c.setEmail("userCompany" + c.getCompanyId() + "@email.com");
                        c.setDataNiver(new Date());
                        return c;
                    })
                    .map(v -> repository.save(v))
                    .forEach(System.out::println);
        };
    }
	
}
