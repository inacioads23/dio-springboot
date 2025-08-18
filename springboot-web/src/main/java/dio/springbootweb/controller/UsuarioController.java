package dio.springbootweb.controller;

// Diferença entre @Controller e @RestController
// @Controller - Retorna views (páginas HTML ou JSP) -> Aplicações web com interface
// @RestController - Retorna dados (JSON ou XML) diretamente -> APIs REST (backend puro)

import dio.springbootweb.model.Usuario;
import dio.springbootweb.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios") // Usado quando começo repetir prefixo "usuario"
public class UsuarioController {
    //@Autowired
    private UsuarioRepository repository;
    
    public UsuarioController(UsuarioRepository repository) {
    	this.repository = repository;
    }

    @PostMapping() // ou [@PostMapping("/")]
    public void post(@RequestBody Usuario usuario){
        repository.save(usuario);
    }
    
    @PutMapping() // ou [@PutMapping("/")]
    public void put(@RequestBody Usuario usuario){
        repository.update(usuario);
    }
    
    @GetMapping() // ou [@GetMapping("/")]
    public List<Usuario> getAll(){
        return repository.listAll();
    }  
    
    @GetMapping("/{id}")
    public Usuario getOne(@PathVariable("id") Integer id){
        return repository.finById(id);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        repository.remove(id);
    }
}
