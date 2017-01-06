package br.com.netodevel.generics;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudService<E, idEntity extends Serializable> {

	public List<E> findAll();
	
	public E findOne(idEntity id);
	
	public E save(E entity);

	public void delete(E entity);
	
	public JpaRepository<E, idEntity> getRepository();
	
}
