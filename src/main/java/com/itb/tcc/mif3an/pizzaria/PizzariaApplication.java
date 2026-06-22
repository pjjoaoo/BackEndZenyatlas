package com.itb.tcc.mif3an.pizzaria;

import com.itb.tcc.mif3an.pizzaria.auth.AuthenticationService;
import com.itb.tcc.mif3an.pizzaria.dto.auth.RegisterRequest;
import com.itb.tcc.mif3an.pizzaria.model.entity.Cliente;
import com.itb.tcc.mif3an.pizzaria.model.enums.TipoUsuario;
import com.itb.tcc.mif3an.pizzaria.model.repository.ClienteRepository;
import com.itb.tcc.mif3an.pizzaria.model.services.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PizzariaApplication {

    private final UsuarioService usuarioService;
    private final AuthenticationService authenticationService;

    public PizzariaApplication(UsuarioService usuarioService, AuthenticationService authenticationService) {
        this.usuarioService = usuarioService;
        this.authenticationService = authenticationService;
    }
	public static void main(String[] args) {
		SpringApplication.run(PizzariaApplication.class, args);


        System.out.println("API Rodando bbzão");
	}

    @Bean
    CommandLineRunner run () {
         return args -> {
             try {
             String emailAdmin = "admin@zenyatlas.com";
             usuarioService.findByEmail(emailAdmin);
             System.out.println("Admin ja cadastrado");
         } catch (Exception e){
                 authenticationService.register(new RegisterRequest("Administrador", "admin@zenyatlas.com", "12345678", TipoUsuario.ADMIN));
             }
         };
    }
}
