package com.nttdata.UIClient.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.nttdata.UIClient.model.Orders;

@Service
public class OrderService {
	
	@Value("${orders.service.url}")
	private String ordersUrl;
	@Value("${orders.service.url}/{id}")
	private String ordersId;
	
	@Autowired
	private RestTemplate restTemplate;

	public List<Orders> findAll() {
		return Arrays.stream(restTemplate.getForObject(ordersUrl, Orders[].class)).collect(Collectors.toList());
	}

    public Orders update(String _id, Orders orders) {
        return restTemplate.exchange(ordersId, HttpMethod.PUT,new HttpEntity<>(orders), Orders.class, _id).getBody();
    }

    public void delete(String _id) {
        restTemplate.delete(ordersId, _id);
    }

    public Orders create(Orders orders) {
        return restTemplate.postForObject(ordersUrl, orders, Orders.class);
    }
    
}