package com.ajwalker.controller;

import com.ajwalker.dto.request.AddProductRequestDto;
import com.ajwalker.dto.request.DeleteProductRequestDto;
import com.ajwalker.dto.request.ProductFilterDto;
import com.ajwalker.dto.request.UpdateProductRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.entity.BaseEntity;
import com.ajwalker.entity.Product;
import com.ajwalker.service.ProductService;
import com.ajwalker.views.VwProduct;
import com.ajwalker.views.VwProductDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.ajwalker.constant.RestApis.*;

@RestController
@RequestMapping("/v1/dev/product")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;
	
	@PostMapping(value ="/v1/dev/product/add-product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<BaseResponse<Boolean>> addProduct(@ModelAttribute @Valid AddProductRequestDto dto) throws IOException {
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
	
	@GetMapping("/v1/dev/product/get-product-details")
	public  ResponseEntity<BaseResponse<VwProductDetails>> getProductDetails(String productCode){
		return ResponseEntity.ok(BaseResponse.<VwProductDetails>builder()
				                         .success(true)
				                         .code(200)
				                         .message("Product details success")
				                         .data(productService.getProductDetails(productCode).get())
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

	@PostMapping(FILTER)
	public ResponseEntity<BaseResponse<List<Product>>> filterProducts(@RequestBody @Valid ProductFilterDto filterDto) {
		return ResponseEntity.ok(BaseResponse.<List<Product>>builder()
				.success(true)
				.code(200)
				.message("Product filtered")
				.data(productService.filterProducts(filterDto))
				.build());
	}
	
}