package com.nagarro.service.serviceinterface;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nagarro.dto.ProductDto;
import com.nagarro.entity.Product;

public interface ProductServiceInterface {
	public Product insertProduct(MultipartFile file, String name, String description,String brand,double price,String productCode)throws IOException;
	public List<ProductDto> fetchProductByParameter(String parameter);
	public List<ProductDto> fetchProductByTwoParameter(String p1,String p2);
	public List<ProductDto> fetchProductByThreeParameter(String p1,String p2,String p3);
	public List<ProductDto> getProducts();
	public ProductDto getProductDetail(String s);
}
