package dio.dio.spring.security.jwt.service;

import dio.dio.spring.security.jwt.model.User;
import dio.dio.spring.security.jwt.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private final UserRepository repository;    
    private final PasswordEncoder encoder;
    
    public UserService(UserRepository repository, PasswordEncoder encoder) {
    	this.repository = repository;
    	this.encoder = encoder;
    }
    
    public void createUser(User user){
        String pass = user.getPassword(); // recebe a senha        
        user.setPassword(encoder.encode(pass)); // criptografa antes de salvar no banco
        repository.save(user); // salva já com senha criptografada
    }
}
