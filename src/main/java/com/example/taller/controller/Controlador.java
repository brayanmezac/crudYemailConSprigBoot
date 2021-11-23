package com.example.taller.controller;


import com.example.taller.bda.IClientebda;
import com.example.taller.variables.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Map;

@Controller
@SessionAttributes("cliente")
public class Controlador {

	@Autowired
	private IClientebda clienteBda;

	@RequestMapping(value = { "/listar", "resultado" }, method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Clientes");
		model.addAttribute("cliente", clienteBda.findAll());
		return "listar";

	}

	@RequestMapping(value = { "/form", "/", "index"})
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario Cliente");

		return "form";

	}
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable (value="id") Long id, Map<String, Object> model) {
		Cliente cliente = null;
		
		if(id>0) {
			cliente= clienteBda.findOne(id);
		}else {
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");
		return "form";
	}
	
	@RequestMapping(value ="/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status)
	{
		if (result.hasErrors()) {
			model.addAttribute("titulo","Formulario Cliente");
				return "form";
		}
		
		clienteBda.saved(cliente);
		status.setComplete();
		return "redirect:/listar";
	}

	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable (value="id") Long id) 
	{
		if(id>0) {
		clienteBda.delete(id);
		
	}
		return "redirect:/listar";
	}	
	
	
	@RequestMapping(value = {"/listarExcel"}, method = RequestMethod.GET)
	public String listarExcel(Model model) {
		model.addAttribute("cliente", clienteBda.findAll());
		return "listarExcel";
	}
	
}
