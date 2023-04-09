package com.renegade.ecommerce.service;

import com.renegade.ecommerce.dto.ProductDTO;
import com.renegade.ecommerce.model.Category;
import com.renegade.ecommerce.model.Product;
import com.renegade.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void addProduct(ProductDTO productDTO, Category category) {
        Product product = getProductFromDto(productDTO, category);
        productRepository.save(product);
    }

    public static Product getProductFromDto(ProductDTO productDTO, Category category) {
        Product product = new Product();
        product.setCategory(category);
        product.setDescription(productDTO.getDescription());
        product.setImageURL(productDTO.getImageURL());
        product.setPrice(productDTO.getPrice());
        product.setName(productDTO.getName());
        return product;
    }
}
