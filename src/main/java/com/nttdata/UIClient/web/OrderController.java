package com.nttdata.UIClient.web;

import java.io.IOException;
import java.util.Map;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.nttdata.UIClient.model.Orders;
import com.nttdata.UIClient.model.Message;
import com.nttdata.UIClient.service.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {

	public static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService service;
	
	@Autowired
	private ObjectMapper mapper;

    @RequestMapping(method = RequestMethod.GET)
	public String findAll( Model model) {
        model.addAttribute("orders", service.findAll());
        model.addAttribute("newOrders", new Orders());
        return "orders";
    }
	
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestParam String _id, Orders orders) {
    		orders.set_id(_id);
    		//orders.set_id("ObjectId(" + _id.toString() + ")");
        logger.info("Response _id{}: ", _id);
        logger.info("Response orders{}: ", orders.toString());
        try {
	        //String orderStr = "ObjectId(" + mapper.writeValueAsString(orders) + ")";
        		String orderStr = mapper.writeValueAsString(orders);
	        logger.info("Response orders object {}: ", orderStr);
	        //service.update(_id, orders);
        }
        catch (Exception e){
	        logger.info("Exception parsing orders object {}: ", e.toString());        	
        }
        service.update(_id, orders);
        return "redirect:/orders";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String delete(@RequestParam String _id) {
        service.delete(_id);
        return "redirect:/orders";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("newOrder") Orders orders) {
        service.create(orders);
        return "redirect:/orders";
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public String handleClientError(HttpClientErrorException ex, Model model) throws IOException {
        Message message = mapper.readValue(ex.getResponseBodyAsByteArray(), Message.class);
        model.addAttribute("error", message.getMessage());
        return findAll(model);
    }
    
}
