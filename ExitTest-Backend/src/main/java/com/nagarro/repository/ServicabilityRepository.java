package com.nagarro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.entity.Servicability;


public interface ServicabilityRepository extends JpaRepository<Servicability, Integer>{

	Servicability findByProductProductCodeAndPincode(String productcode,int pincode);
}
