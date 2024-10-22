package com.ajwalker.service;

import com.ajwalker.dto.request.AddProductRequestDto;
import com.ajwalker.dto.request.DeleteProductRequestDto;
import com.ajwalker.dto.request.UpdateProductRequestDto;
import com.ajwalker.entity.Product;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.TierdYolException;
import com.ajwalker.mapper.ProductMapper;
import com.ajwalker.repository.ProductRepository;
import com.ajwalker.repository.UserRepository;
import com.ajwalker.utility.enums.EUserStatus;
import com.ajwalker.views.VwProduct;
import com.ajwalker.views.VwProductDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
	private final UserService userService;
	
	public void addProduct(AddProductRequestDto dto) {
		EUserStatus userStatus = userService.findUserStatusByUserId(dto.userId());
		if (userStatus !=EUserStatus.ADMIN && userStatus !=EUserStatus.SELLER){
			throw new TierdYolException(ErrorType.INVALID_STATUS);
		}
		productRepository.save(ProductMapper.INSTANCE.fromAddProductRequestDto(dto));
	}
	
	public List<VwProduct> getAllProducts() {
		return productRepository.findAllProducts();
	}
	
	public void updateProduct(UpdateProductRequestDto dto) {
		Optional<Product> productId = productRepository.findById(dto.id());
		if (productId.isEmpty()){
			throw new TierdYolException(ErrorType.NOT_FOUNT_PRODUCT);
		}
		productRepository.save(ProductMapper.INSTANCE.fromUpdateProductRequestDto(dto,productId.get()));
	}
	
	public void deleteProduct( DeleteProductRequestDto dto) {
		EUserStatus userStatus = userService.findUserStatusByUserId(dto.userId());
		if (userStatus !=EUserStatus.ADMIN && userStatus !=EUserStatus.SELLER){
			throw new TierdYolException(ErrorType.INVALID_STATUS);
		}
		Optional<Product> productId = productRepository.findById(dto.id());
		if (productId.isEmpty()){
			throw new TierdYolException(ErrorType.NOT_FOUNT_PRODUCT);
		}
		productRepository.save(ProductMapper.INSTANCE.fromDeleteProductRequestDto(dto,productId.get()));
		
	}
	
	public Optional<VwProductDetails> getProductDetails(String productCode) {
		Optional<VwProductDetails> byProductCode = productRepository.findByProductCode(productCode);
		if (byProductCode.isEmpty()){
			throw new TierdYolException(ErrorType.NOT_FOUNT_PRODUCT_CODE);
		}
		return byProductCode;
	}
}