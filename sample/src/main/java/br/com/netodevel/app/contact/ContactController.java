package br.com.netodevel.app.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.netodevel.generics.AbstractControllerCrud;

@Controller
@RequestMapping("/contacts")
public class ContactController extends AbstractControllerCrud<Contact, Integer> {

	@Autowired
	private ContactRepository contactRepository;
	
	@Override
	public JpaRepository<Contact, Integer> getRepository() {
		return contactRepository;
	}
	
	@Override
	public String getPathHtmlFiles() {
		return "contact";
	}
	
	@Override
	public String[] getMsgActions() {
		String[] msgs = {"Contact save.", "Error"};
		return msgs;
	}
	
	@Override
	public Class<Contact> getClazz() {
		return Contact.class;
	}
	
	@Override
	public String getBaseURL() {
		return "contacts";
	}
	
}
