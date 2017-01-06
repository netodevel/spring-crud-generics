package br.com.netodevel.generics;

import java.io.Serializable;

public interface CrudController<E, ID extends Serializable>  {

	public CrudService<E, ID> service();
	
}
