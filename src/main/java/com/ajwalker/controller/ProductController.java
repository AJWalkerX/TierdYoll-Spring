package com.ajwalker.controller;

import com.ajwalker.dto.request.AddProductRequestDto;
import com.ajwalker.dto.request.DeleteProductRequestDto;
import com.ajwalker.dto.request.UpdateProductRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.entity.BaseEntity;
import com.ajwalker.entity.Product;
import com.ajwalker.service.ProductService;
import com.ajwalker.views.VwProduct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/dev/product")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;
	
	@PostMapping("/v1/dev/product/add-product")
	public ResponseEntity<BaseResponse<Boolean>> addProduct(@RequestBody @Valid AddProductRequestDto dto) {
		productService.addProduct(dto);
		return ResponseEntity.ok(
				BaseResponse.<Boolean>builder()
						.code(200)
						.data(true)
						.message("Add product success")
						.success(true)
				            .build()
		);
	}
	
	@GetMapping("/v1/dev/product/get-all-products")
	public ResponseEntity<BaseResponse<List<VwProduct>>> getAllProducts(){
		return ResponseEntity.ok(BaseResponse.<List<VwProduct>>builder()
				                         .success(true)
				                         .code(200)
				                         .message("All products success")
				                         .data(productService.getAllProducts())
		                                     .build());
	}
	
	@PutMapping("/v1/dev/product/update-product")
	private ResponseEntity<BaseResponse<Boolean>> updateProduct(@RequestBody @Valid UpdateProductRequestDto dto) {
		productService.updateProduct(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
				                         .success(true)
				                         .code(200)
				                         .message("Product updated")
				                         .data(true)
		                                     .build());
	}
	
	@PostMapping("/v1/dev/product/delete-product")
	private ResponseEntity<BaseResponse<Boolean>> deleteProduct(@RequestBody @Valid DeleteProductRequestDto dto){
		productService.deleteProduct(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
				                         .success(true)
				                         .code(200)
				                         .message("Product deleted")
				                         .data(true)
		                                     .build());
	}
	
	
}