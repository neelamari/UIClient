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
import com.nttdata.UIClient.model.Catalog;

@Service
public class CatalogService {
	
	@Value("${catalog.service.url}")
	private String catalogurl;
	@Value("${catalog.service.url}/{id}")
	private String catalogid;
	
	@Autowired
	private RestTemplate restTemplate;

	public List<Catalog> findAll() {
		return Arrays.stream(restTemplate.getForObject(catalogurl, Catalog[].class)).collect(Collectors.toList());
	}

    public Catalog update(Long id, Catalog catalog) {
        return restTemplate.exchange(catalogid, HttpMethod.PUT,new HttpEntity<>(catalog), Catalog.class, id).getBody();
    }

    public void delete(Long id) {
        restTemplate.delete(catalogid, id);
    }

    public Catalog create(Catalog catalog) {
        return restTemplate.postForObject(catalogurl, catalog, Catalog.class);
    }
	    
}