package com.nagarro.service;
//importing packages
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.nagarro.dto.ProductDto;
import com.nagarro.entity.Product;
import com.nagarro.exceptions.ProductAlreadyExistException;
import com.nagarro.exceptions.ProductNotFoundException;
import com.nagarro.mappers.ProductMapper;
import com.nagarro.repository.ProductRepository;
import com.nagarro.service.serviceinterface.ProductServiceInterface;

@Service
public class ProductService implements ProductServiceInterface{
	@Autowired
	ProductRepository  repo;
	@Autowired
	ProductMapper map;
	@Override
	public Product insertProduct(MultipartFile file, String name, String description,String brand,double price,String productCode) throws IOException{
		// TODO Auto-generated method stub
		Product p1=repo.getProductDetail(productCode);
		if(Objects.nonNull(p1)) {
			throw new ProductAlreadyExistException("Product already exists");
		}
		else {
		Product p=new Product();
		p.setBrand(brand);
		p.setDescription(description);
		p.setName(name);
		p.setImage(file.getBytes());
		p.setPrice(price);
		p.setProductCode(productCode);
		return repo.save(p);
		}
	}
	@Override
	public List<ProductDto> getProducts() {
		ArrayList<Product> p=(ArrayList<Product>) this.repo.findAll();
		if(p.isEmpty()==false) {
			ArrayList<ProductDto> p1=new ArrayList<>();
			for(Product product:p) {
				p1.add(map.productToProductDto(product));
			}
			return p1;
		}
		else {
			throw new ProductNotFoundException("No product available!");
		}
	}

	@Override
	public List<ProductDto> fetchProductByParameter(String parameter) {
		// TODO Auto-generated method stub
		ArrayList<Product> p=(ArrayList<Product>) this.repo.findByOneParameter(parameter);
		if(p.isEmpty()==false) {
			ArrayList<ProductDto> p1=new ArrayList<>();
			for(Product product:p) {
				p1.add(map.productToProductDto(product));
			}
			return p1;
		}
		else {
			throw new ProductNotFoundException("No product available!");
		}
	}

	@Override
	public List<ProductDto> fetchProductByTwoParameter(String p1, String p2) {
		// TODO Auto-generated method stub
		ArrayList<Product> p=(ArrayList<Product>)this.repo.findByTwoParameter(p1, p2);
		if(p.isEmpty()==false) {
			ArrayList<ProductDto> p3=new ArrayList<>();
			for(Product product:p) {
				p3.add(map.productToProductDto(product));
			}
			return p3;
		}
		else {
			throw new ProductNotFoundException("No product available!");
		} 
	}

	@Override
	public List<ProductDto> fetchProductByThreeParameter(String p1, String p2, String p3) {
		// TODO Auto-generated method stub
		ArrayList<Product> p=(ArrayList<Product>)this.repo.findByThreeParameter(p1, p2, p3);
		if(p.isEmpty()==false) {
			ArrayList<ProductDto> p4=new ArrayList<>();
			for(Product product:p) {
				p4.add(map.productToProductDto(product));
			}
			return p4;
		}
		else {
			throw new ProductNotFoundException("No product available!");
		} 
	}
	@Override
	public ProductDto getProductDetail(String s) {
		// TODO Auto-generated method stub
		Product p=repo.getProductDetail(s);
		if(Objects.nonNull(p)) {
			ProductDto p1=map.productToProductDto(p);
			return p1;
		}
		else {
			throw new ProductNotFoundException("No product available!");
		}
		
		
	}
	



}
