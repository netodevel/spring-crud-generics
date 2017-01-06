package br.com.netodevel.app.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.netodevel.generics.AbstractService;

@Service
@Transactional(readOnly = true)
public class ContactService extends AbstractService<Contact, Integer>{

	@Autowired
	private ContactRepository contactRepository;
	
	@Override
	public JpaRepository<Contact, Integer> getRepository() {
		return contactRepository;
	}

}
