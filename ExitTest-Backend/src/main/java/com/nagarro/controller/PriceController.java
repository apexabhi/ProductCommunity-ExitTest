//Get Price API 
package com.nagarro.controller;
//importing packages
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nagarro.dto.ProductDto;
import com.nagarro.service.ProductService;

@RestController
@CrossOrigin
@RequestMapping("/prices")
public class PriceController {
	@Autowired
	ProductService service;
	//to get prices of all products
	@GetMapping
	public List<Double> getAllProduct(){
		List<ProductDto> temp= this.service.getProducts();
		List<Double> prices=new ArrayList<Double>();
		for(ProductDto p:temp) {
			prices.add(p.getPrice());
		}
		return prices;
	}
	//to get price of specific product
	@RequestMapping("/{productCode}")
    public ResponseEntity<Double> getProductPrice(@PathVariable("productCode") String productCode) throws Exception {
        ProductDto p= service.getProductDetail(productCode);
        return ResponseEntity.ok().body(p.getPrice());
    }
}
