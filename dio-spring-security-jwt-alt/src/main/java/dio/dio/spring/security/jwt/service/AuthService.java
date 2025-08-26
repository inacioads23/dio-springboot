package dio.dio.spring.security.jwt.service;

import java.util.Date;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dio.dio.spring.security.jwt.dtos.Login;
import dio.dio.spring.security.jwt.dtos.Sessao;
import dio.dio.spring.security.jwt.model.User;
import dio.dio.spring.security.jwt.repository.UserRepository;
import dio.dio.spring.security.jwt.security.JWTCreator;
import dio.dio.spring.security.jwt.security.JWTObject;
import dio.dio.spring.security.jwt.security.SecurityConfig;

@Service
public class AuthService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    
    public AuthService (UserRepository repository, PasswordEncoder encoder) {
    	this.repository = repository;
    	this.encoder = encoder;
    }

    public Sessao login(Login login) {
        User user = repository.findByUsername(login.getUsername());
        if (user != null) {
            boolean passwordOk = encoder.matches(login.getPassword(), user.getPassword());
            if (!passwordOk) {
                throw new RuntimeException("Senha inválida para o login: " + login.getUsername());
            }

            Sessao sessao = new Sessao();
            sessao.setLogin(user.getUsername());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION));
            jwtObject.setRoles(user.getRoles());

            sessao.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
            return sessao;
        } else {
            throw new RuntimeException("Erro ao tentar fazer login");
        }
    }
}

