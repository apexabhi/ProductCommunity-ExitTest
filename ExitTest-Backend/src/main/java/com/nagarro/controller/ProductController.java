//Product API
package com.nagarro.controller;
//importing packages
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.nagarro.dto.ProductDto;
import com.nagarro.entity.Product;
import com.nagarro.service.ProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService service;
	//to insert new product in database
	@PostMapping
	public Product registerProduct(@RequestParam("file") MultipartFile file,
			@RequestParam("name") String name,
			@RequestParam("description") String description,@RequestParam("brand") String brand,@RequestParam("price") double price,@RequestParam("productCode") String productCode) throws Exception {
		Product pObj = null;
		pObj = service.insertProduct(file,name,description,brand,price,productCode);
		return pObj;
	}
	//to get list of all products
	@GetMapping
	public ResponseEntity<List<ProductDto>> getAllProudct(){
		return ResponseEntity.ok().body(this.service.getProducts());
	}
	//to search products based on one parameter
	@RequestMapping("/search/1/{parameter}")
    public ResponseEntity<List<ProductDto>> getProductByOne(@PathVariable("parameter") String parameter) throws Exception {
        return ResponseEntity.ok().body(service.fetchProductByParameter(parameter));
    }
	//to search products based on two parameter
	@RequestMapping("/search/2/{p1}/{p2}")
    public ResponseEntity<List<ProductDto>> getProductByTwo(@PathVariable("p1") String p1,@PathVariable("p2") String p2) throws Exception {
		return ResponseEntity.ok().body(service.fetchProductByTwoParameter(p1,p2));
    }
	//to search products based on three parameter
	@RequestMapping("/search/3/{p1}/{p2}/{p3}")
    public ResponseEntity<List<ProductDto>> getProductByThree(@PathVariable("p1") String p1,@PathVariable("p2") String p2,@PathVariable("p3") String p3) throws Exception {
        return ResponseEntity.ok().body(service.fetchProductByThreeParameter(p1,p2,p3));
    }
	//to get details of product
	@RequestMapping("/detail/{parameter}")
    public ResponseEntity<ProductDto> getProductDetail(@PathVariable("parameter") String parameter) throws Exception {
        return ResponseEntity.ok().body(service.getProductDetail(parameter));
    }


}
