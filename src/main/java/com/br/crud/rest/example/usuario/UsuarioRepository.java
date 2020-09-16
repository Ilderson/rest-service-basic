package com.br.crud.rest.example.usuario;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	Iterable<Usuario> findByEmail(String email);
	Iterable<Usuario> findByCompanyId(Integer idCompany);
	

}
