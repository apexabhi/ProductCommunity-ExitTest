package com.nagarro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nagarro.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	 
	@Query("SELECT p FROM Product p WHERE p.name = ?1 OR p.productCode = ?1 OR p.brand=?1")
	public List<Product> findByOneParameter(String parameter);
	@Query("SELECT p FROM Product p WHERE (p.name = ?1 AND p.productCode = ?2)OR (p.name = ?1 AND p.brand = ?2) OR (p.productCode = ?1 AND p.brand = ?2) OR (p.name = ?2 AND p.productCode = ?1)OR (p.name = ?2 AND p.brand = ?1) OR (p.productCode = ?2 AND p.brand = ?1)")
	public List<Product> findByTwoParameter(String p1,String p2);
	@Query("SELECT p FROM Product p WHERE (p.name = ?1 AND p.productCode = ?2 AND p.brand=?3)")
	public List<Product> findByThreeParameter(String p1,String p2,String p3);
	@Query("SELECT p FROM Product p WHERE p.name = ?1 OR p.productCode = ?1 OR p.brand=?1")
	public Product getProductDetail(String parameter);
	

}