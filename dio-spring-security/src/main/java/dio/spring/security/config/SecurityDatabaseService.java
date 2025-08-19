package dio.spring.security.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dio.spring.security.model.User;
import dio.spring.security.repository.UserRepository;

@Service //Adicionada essa anotação
public class SecurityDatabaseService implements UserDetailsService{
	
	private final UserRepository userRepository;
	
	public SecurityDatabaseService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public UserDetails loadUserByUsername(String username) {
		User userEntity = userRepository.findByUsername(username);
		if(userEntity == null) {
			throw new UsernameNotFoundException("Usuario nao encontrado" + username);
		}
		
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		userEntity.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		});
		
		UserDetails user = new org.springframework.security.core.userdetails.User(userEntity.getUsername(),
				userEntity.getPassword(),
				authorities);
		return user;
	}

}
