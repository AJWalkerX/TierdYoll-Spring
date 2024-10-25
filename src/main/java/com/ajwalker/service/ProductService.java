package com.ajwalker.service;

import com.ajwalker.dto.request.AddProductRequestDto;
import com.ajwalker.dto.request.DeleteProductRequestDto;
import com.ajwalker.dto.request.ProductFilterDto;
import com.ajwalker.dto.request.UpdateProductRequestDto;
import com.ajwalker.entity.FilterCriteria;
import com.ajwalker.entity.Product;
import com.ajwalker.entity.ProductSpecification;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.TierdYolException;
import com.ajwalker.mapper.ProductMapper;
import com.ajwalker.repository.ProductRepository;
import com.ajwalker.utility.enums.EUserStatus;
import com.ajwalker.views.VwProduct;
import com.ajwalker.views.VwProductDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserService userService;
    private final PhotoUploadService photoUploadService;

    public void addProduct(AddProductRequestDto dto) throws IOException {
        EUserStatus userStatus = userService.findUserStatusByUserId(dto.userId());
        if (userStatus != EUserStatus.ADMIN && userStatus != EUserStatus.SELLER) {
            throw new TierdYolException(ErrorType.INVALID_STATUS);
        }

        Product product = productRepository.save(ProductMapper.INSTANCE.fromAddProductRequestDto(dto));
        String url = photoUploadService.uploadPhoto(dto.file());
        photoUploadService.save(url, product.getId());
    }

    public List<VwProduct> getAllProducts() {
        return productRepository.findAllProducts();
    }

    public void updateProduct(UpdateProductRequestDto dto) {
        Optional<Product> productId = productRepository.findById(dto.id());
        if (productId.isEmpty()) {
            throw new TierdYolException(ErrorType.NOT_FOUNT_PRODUCT);
        }
        productRepository.save(ProductMapper.INSTANCE.fromUpdateProductRequestDto(dto, productId.get()));
    }

    public void deleteProduct(DeleteProductRequestDto dto) {
        EUserStatus userStatus = userService.findUserStatusByUserId(dto.userId());
        if (userStatus != EUserStatus.ADMIN && userStatus != EUserStatus.SELLER) {
            throw new TierdYolException(ErrorType.INVALID_STATUS);
        }
        Optional<Product> productId = productRepository.findById(dto.id());
        if (productId.isEmpty()) {
            throw new TierdYolException(ErrorType.NOT_FOUNT_PRODUCT);
        }
        productRepository.save(ProductMapper.INSTANCE.fromDeleteProductRequestDto(dto, productId.get()));

    }

    public Optional<VwProductDetails> getProductDetails(String productCode) {
        Optional<VwProductDetails> byProductCode = productRepository.findByProductCode(productCode);
        if (byProductCode.isEmpty()) {
            throw new TierdYolException(ErrorType.NOT_FOUNT_PRODUCT_CODE);
        }
        return byProductCode;
    }

    public Optional<Product> findByProductId(Long id) {

        Optional<Product> optionalProduct = productRepository.findById(id);

        return optionalProduct;
    }


    public List<Product> filterProducts(ProductFilterDto filterDto) {
        List<Specification<Product>> specs = new ArrayList<>();

        if (filterDto.category() != null)
            specs.add(new ProductSpecification(new FilterCriteria("category", ":", filterDto.category())));
        if (filterDto.brand() != null)
            specs.add(new ProductSpecification(new FilterCriteria("brand", ":", filterDto.brand())));
        if (filterDto.maxPrice() != null)
            specs.add(new ProductSpecification(new FilterCriteria("price", ">", filterDto.minPrice())));
        if (filterDto.maxPrice() != null)
            specs.add(new ProductSpecification(new FilterCriteria("price", "<", filterDto.maxPrice())));
        if (filterDto.minRating() != null)
            specs.add(new ProductSpecification(new FilterCriteria("rating", ">", filterDto.minRating())));
        if (filterDto.color() != null)
            specs.add(new ProductSpecification(new FilterCriteria("color", ":", filterDto.color())));

        Specification<Product> finalSpec = specs.stream()
                .reduce(Specification::and)
                .orElse(null);

        // Kullanıcıdan gelen sıralama bilgilerini kullanarak Sort nesnesi oluştur
        Sort sort = Sort.by(
                filterDto.sortDirection() != null ? Sort.Direction.valueOf(filterDto.sortDirection().getDirection()) : Sort.Direction.ASC,
                filterDto.sortBy() != null ? filterDto.sortBy().getField() : "id" // Varsayılan sıralama alanı
        );

        return productRepository.findAll(finalSpec, sort);
    }

}