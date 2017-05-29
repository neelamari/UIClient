package com.nttdata.UIClient.web;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.UIClient.model.Catalog;
import com.nttdata.UIClient.model.Message;
import com.nttdata.UIClient.service.CatalogService;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

	@Autowired
	private CatalogService service;
	
	@Autowired
	private ObjectMapper mapper;

    @RequestMapping(method = RequestMethod.GET)
	public String findAll( Model model) {
        model.addAttribute("catalog", service.findAll());
        model.addAttribute("newCatalog", new Catalog());
        return "catalog";
    }
	
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestParam Long id, Catalog catalog) {
        service.update(id, catalog);
        return "redirect:/catalog";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String delete(@RequestParam Long id) {
        service.delete(id);
        return "redirect:/catalog";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("newCatalog") Catalog catalog) {
        service.create(catalog);
        return "redirect:/catalog";
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public String handleClientError(HttpClientErrorException ex, Model model) throws IOException {
        Message message = mapper.readValue(ex.getResponseBodyAsByteArray(), Message.class);
        model.addAttribute("error", message.getMessage());
        return findAll(model);
    }
    
}
