package com.nagarro.mappers;
import com.nagarro.dto.*;
import com.nagarro.entity.Product;

import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface ProductMapper {
	public ProductDto productToProductDto(Product p);
}
