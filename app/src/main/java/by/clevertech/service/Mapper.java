package by.clevertech.service;

import by.clevertech.dao.entity.Product;
import by.clevertech.service.dto.ProductDto;

public class Mapper {

	public ProductDto productToDto(Product product) {
		ProductDto productDto = new ProductDto();
		productDto.setId(product.getId());
		productDto.setName(product.getName());
		productDto.setPrice(product.getPrice());
		productDto.setDiscount(product.isDiscount());
		return productDto;
	}

	public Product productToEntity(ProductDto dto) {
		Product product = new Product();
		product.setId(dto.getId());
		product.setName(product.getName());
		product.setPrice(dto.getPrice());
		product.setDiscount(dto.isDiscount());
		return product;
	}

}
