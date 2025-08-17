package dio.aula;

import dio.aula.model.User;
import dio.aula.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartApp implements CommandLineRunner {
	// @Autowired
	private UserRepository repository;

	public StartApp(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(String... args) throws Exception {
		//insertUser(); // PARA INSERIR - Descomentar!!
		//List<User> users = repository.findByNameContaining("GABRIEL");
		List<User> users = repository.filtrarPorNome("GABRIEL");
		for (User u : users) {
			System.out.println(u);
		}
	}

	private void insertUser() {
		User user = new User();
		user.setName("JOSE INACIO");
		user.setUsername("jose");
		user.setPassword("inacio");
		repository.save(user);

		for (User u : repository.findAll()) {
			System.out.println(u);
		}
	}
}
