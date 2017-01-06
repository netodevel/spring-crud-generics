package br.com.netodevel.generics;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public abstract class AbstractControllerCrud <T, ID extends Serializable> extends AbstractService<T, ID> {
	
	protected JpaRepository<T, ID> repository;
	protected Class<T> clazz;
	protected String pathHTMLFiles;
	protected String[] msgsActions;
	protected String baseURL;
 
	@GetMapping("/index")
	public String index(Model model) {
		List<T> all = findAll();
		model.addAttribute("list" + getClazz().getSimpleName(), all);
		return getPathHtmlFiles() + "/index/index";
	}

	@GetMapping(value = "/new")
	public String create(Model model, @ModelAttribute T entity) {
		model.addAttribute(getClazz().getSimpleName(), entity);
		return getPathHtmlFiles() + "/new/form";
	}
	
	@PostMapping(value = "/new")
	public String create(@Valid @ModelAttribute T entity, BindingResult result, RedirectAttributes redirectAttributes) {
		try {
			save(entity);
			redirectAttributes.addFlashAttribute("sucesso", getMsgActions()[0]);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", getMsgActions()[1]);
			e.printStackTrace();
		}
		return "redirect:/" + getBaseURL() + "/new";
	}
	
	
	@GetMapping("{id}/update")
	public String update(Model model, @PathVariable("id") ID idEntity) {
		try {
			if (idEntity != null) {
				T entity = findOne(idEntity);
				model.addAttribute(getClazz().getSimpleName(), entity);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return getPathHtmlFiles() + "/new/form";
	}
	
	@PutMapping(value = "/update")
	public String update(@Valid @ModelAttribute T entity, BindingResult result, RedirectAttributes redirectAttributes) {
		try {
			save(entity);
			redirectAttributes.addFlashAttribute("sucesso", getMsgActions()[0]);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", getMsgActions()[1]);
			e.printStackTrace();
		}
		return "redirect:" + getBaseURL() + "/new";
	}
	
	@DeleteMapping("{id}/delete")
	public String delete(@PathVariable("id") ID idEntity, RedirectAttributes redirectAttributes) {
		try {
			if (idEntity != null) {
				T entity = findOne(idEntity);
				delete(entity);
				redirectAttributes.addFlashAttribute("sucesso", getMsgActions()[0]);
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", getMsgActions()[1]);
			throw new ServiceException(e.getMessage());
		}
		return "redirect:/" + getBaseURL();
	}
	
	public JpaRepository<T, ID> getRepository() { 
		return repository;
	}
	
	public Class<T> getClazz() {
		return clazz;
	}
	
	public String getPathHtmlFiles() {
		return pathHTMLFiles;
	}
	
	public String[] getMsgActions() {
		return msgsActions;
	}
	
	public String getBaseURL() {
		return baseURL;
	}
}
