package br.com.netodevel.app.contact;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.netodevel.generics.AbstractModel;

@Entity
@Table(name = "contact")
public class Contact extends AbstractModel {

	private static final long serialVersionUID = 1L;
	
	private String mail;
	
	private String message;

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
