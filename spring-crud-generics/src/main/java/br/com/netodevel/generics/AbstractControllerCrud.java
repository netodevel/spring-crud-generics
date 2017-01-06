package br.com.netodevel.generics;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public abstract class AbstractControllerCrud <T, ID extends Serializable> implements CrudController<T, ID> {

	private static final String MSG_SUCESS_INSERT = "Inserido com sucesso.";
	private static final String MSG_SUCESS_UPDATE = "Alterado com sucesso.";
	private static final String MSG_SUCESS_DELETE = "Deletado com sucesso.";
	private static final String MSG_ERROR = "Erro ao executar operação. Por favor contate o administrador do sistema.";
	
	protected Class<T> clazz;
	protected String baseURL;
	
	@GetMapping("/index")
	public String index(Model model) {
		List<T> all = service().findAll();
		model.addAttribute("list" + getClazz().getSimpleName(), all);
		return getBaseURL() + "/index";
	}

	@GetMapping(value = "/new")
	public String create(Model model, @ModelAttribute T entity) {
		model.addAttribute(getClazz().getSimpleName(), entity);
		return getBaseURL() + "/form";
	}
	
	@PostMapping(value = "/new")
	public String create(@Valid @ModelAttribute T entity, BindingResult result, RedirectAttributes redirectAttributes) {
		try {
			service().save(entity);
			redirectAttributes.addFlashAttribute("sucesso", MSG_SUCESS_INSERT);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		return "redirect:/" + getBaseURL() + "/new";
	}
	
	
	@GetMapping("{id}/update")
	public String update(Model model, @PathVariable("id") ID idEntity) {
		try {
			if (idEntity != null) {
				T entity = service().findOne(idEntity);
				model.addAttribute(getClazz().getSimpleName(), entity);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return getBaseURL() + "/form";
	}
	
	@PutMapping(value = "/update")
	public String update(@Valid @ModelAttribute T entity, BindingResult result, RedirectAttributes redirectAttributes) {
		try {
			service().save(entity);
			redirectAttributes.addFlashAttribute("sucesso", MSG_SUCESS_UPDATE);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		return "redirect:/" + getBaseURL() + "/new";
	}
	
	@DeleteMapping("{id}/delete")
	public String delete(@PathVariable("id") ID idEntity, RedirectAttributes redirectAttributes) {
		try {
			if (idEntity != null) {
				T entity = service().findOne(idEntity);
				service().delete(entity);
				redirectAttributes.addFlashAttribute("sucesso", MSG_SUCESS_DELETE);
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			throw new ServiceException(e.getMessage());
		}
		return "redirect:/" + getBaseURL() + "/index";
	}
	
	public Class<T> getClazz() {
		return clazz;
	}
	
	public String getBaseURL() {
		return clazz.getSimpleName().toLowerCase();
	}
}
