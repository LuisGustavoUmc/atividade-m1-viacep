package com.example.crud.service;

import com.example.crud.domain.address.Address;
import com.example.crud.domain.product.Product;
import com.example.crud.domain.product.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ViaCepService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final ProductRepository productRepository;

    @Autowired
    public ViaCepService(RestTemplate restTemplate, ObjectMapper objectMapper, ProductRepository productRepository) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.productRepository = productRepository;
    }

    public String searchCityByCep(String cep) {
        String url = "https://viacep.com.br/ws/{cep}/json/";

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("cep", cep);

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, uriVariables);

        try {
            Address address = objectMapper.readValue(response.getBody(), Address.class);
            return address.getLocalidade();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isCityMatchingDistributionCenter(String cep, String productId) {
        String cityFromCep = searchCityByCep(cep);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        String productCity = product.getDistributionCenter();
        return cityFromCep.trim().equalsIgnoreCase(productCity.trim());
    }
}
