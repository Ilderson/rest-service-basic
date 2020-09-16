package com.br.crud.rest.example.usuario;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.crud.rest.example.usuario.Validacoes.ValidaRegras;

@RestController
@RequestMapping({"/usuarios"})
public class UsuarioController {
	private UsuarioRepository userRepository;
	
	public UsuarioController(UsuarioRepository userRepo) {
		this.userRepository = userRepo;
	}
	
	@GetMapping
	public Iterable<Usuario> findAll(){
		return userRepository.findAll();
	}
	
	@GetMapping(path = {"/findById"})
	public ResponseEntity<Usuario> findById(@RequestParam(value="userId") final long id){
	   return userRepository.findById(id)
	           .map(record -> ResponseEntity.ok().body(record))
	           .orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping(path = {"/findByEmail"})
	public Iterable<Usuario> findByEmail(@RequestParam(value="email") final String email){
		return userRepository.findByEmail(email);
	}
	
	@GetMapping(path = {"/findByCompanyId"})
	public Iterable<Usuario> findByCompanyId(@RequestParam(value="companyId") final Integer companyId){
		return userRepository.findByCompanyId(companyId);
	}
	
	//@PostMapping
	//public Usuario create(@Valid @RequestBody Usuario newUser){
	//	ValidaRegras.searchEmailAlready(newUser);
	//	return userRepository.save(newUser);
	//}
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Usuario newUser){
		boolean ret = ValidaRegras.searchEmailAlready(newUser);
		if (ret)
	        return new ResponseEntity<>("Email already exist for companyId", HttpStatus.BAD_REQUEST);
		
		
		Usuario usr = userRepository.save(newUser);
		if (usr != null )
			return ResponseEntity.ok().body(usr);
		else		
		  return ResponseEntity.notFound().build();	
	}
	
	
	@PutMapping(value="/{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id,
			                        @Valid @RequestBody Usuario userUpd) {
		
		return userRepository.findById(id)
	           .map(record -> {
	               record.setCompanyId(userUpd.getCompanyId());
	               record.setEmail(userUpd.getEmail());
	               record.setBirthDate(userUpd.getBirthDate());
	               record.setDataNiver(userUpd.getDataNiver());
	               Usuario updated = userRepository.save(record);
	               return ResponseEntity.ok().body(updated);
	           }).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity<?> delete(@PathVariable long id) {
		return userRepository.findById(id)
	           .map(record -> {
	               userRepository.deleteById(id);
	               return ResponseEntity.ok().build();
	           }).orElse(ResponseEntity.notFound().build());
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
}


