package br.com.netodevel.generics;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractService<E, idEntity extends Serializable> implements CrudService<E, idEntity> {

	@Override
	public List<E> findAll() {
		return getRepository().findAll();
	}

	@Override
	public E findOne(idEntity id) {
		return getRepository().findOne(id);
	}

	@Override
	@Transactional(readOnly = false)
	public E save(E entity) {
		return getRepository().save(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(E entity) {
		getRepository().delete(entity);
	}

}
