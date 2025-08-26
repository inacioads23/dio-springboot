package dio.dio.spring.security.jwt.controller;

import dio.dio.spring.security.jwt.dtos.Login;
import dio.dio.spring.security.jwt.dtos.Sessao;
import dio.dio.spring.security.jwt.service.AuthService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class LoginController {
	private final AuthService authService;
    
    public LoginController (AuthService authService) {
    	this.authService = authService;
    }

    @PostMapping("/login")
    public Sessao logar(@RequestBody Login login) {
        return authService.login(login);
    }
}
