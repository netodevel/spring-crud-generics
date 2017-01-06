package br.com.netodevel.generics;

import java.io.Serializable;
import java.util.List;

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
	public E save(E entity) {
		return getRepository().save(entity);
	}

	@Override
	public void delete(E entity) {
		getRepository().delete(entity);
	}

}
