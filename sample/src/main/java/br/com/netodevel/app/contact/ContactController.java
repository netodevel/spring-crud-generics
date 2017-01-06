package br.com.netodevel.app.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.netodevel.generics.AbstractControllerCrud;
import br.com.netodevel.generics.CrudService;

@Controller
@RequestMapping(ContactController.BASE_URL)
public class ContactController extends AbstractControllerCrud<Contact, Integer> {

	static final String BASE_URL = "contacts";

	@Autowired
	private ContactService contactService;
	
	@Override
	public CrudService<Contact, Integer> service() {
		return contactService;
	}
	
	@Override
	public Class<Contact> getClazz() {
		return Contact.class;
	}
	
	@Override
	public String getBaseURL() {
		return BASE_URL;
	}
	
}
