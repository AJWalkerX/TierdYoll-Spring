package com.ajwalker.service;

import com.ajwalker.dto.request.AddProductRequestDto;
import com.ajwalker.dto.request.DeleteProductRequestDto;
import com.ajwalker.dto.request.ProductFilterDto;
import com.ajwalker.dto.request.UpdateProductRequestDto;
import com.ajwalker.dto.response.AllProductResponseDto;
import com.ajwalker.entity.Category;
import com.ajwalker.entity.Product;
import com.ajwalker.entity.ProductSpecification;
import com.ajwalker.exception.ErrorType;
import com.ajwalker.exception.TierdYolException;
import com.ajwalker.mapper.ProductMapper;
import com.ajwalker.repository.ProductRepository;
import com.ajwalker.utility.enums.EUserStatus;
import com.ajwalker.views.VwCategory;
import com.ajwalker.views.VwProduct;
import com.ajwalker.views.VwProductDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserService userService;
    private final PhotoUploadService photoUploadService;
    private final CategoryService categoryService;


    public void addProduct(AddProductRequestDto dto) throws IOException {
        Category category = categoryService.getOrCreateCategory(dto.mainCategory(), dto.subCategory(), dto.productType());
        if (category == null) {
            throw new TierdYolException(ErrorType.CATEGORY_NOTFOUND_ERROR);
        }
        EUserStatus userStatus = userService.findUserStatusByUserId(dto.userId());
        if (userStatus != EUserStatus.ADMIN && userStatus != EUserStatus.SELLER) {
            throw new TierdYolException(ErrorType.INVALID_STATUS);
        }

        Product product = ProductMapper.INSTANCE.fromAddProductRequestDto(dto);
        product.setCategoryId(category.getId());
        productRepository.save(product);
        String url = photoUploadService.uploadPhoto(dto.file());
        photoUploadService.save(url, product.getId());
    }

    public List<AllProductResponseDto> getAllProducts() {
        List <Product> productList = productRepository.findTop100ByOrderByIdDesc();
        List<Long> categoryIds = productList.stream().map(Product::getCategoryId).toList();
        Map<Long,VwCategory> categoryMap = categoryService.findAllByIds(categoryIds);
        List<AllProductResponseDto> result = new ArrayList<>();
        productList.forEach(c -> {
            VwCategory vwCategory = categoryMap.get(c.getCategoryId());
            AllProductResponseDto newDto = ProductMapper.INSTANCE.fromProductAndCategory(c,vwCategory.getMainCategoryType(), vwCategory.getSubCategoryType(), vwCategory.getProductType());
            result.add(newDto);
        });
        return result;
    }

    public void updateProduct(UpdateProductRequestDto dto) {
        Optional<Product> productId = productRepository.findOptionalById(dto.id());
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
        Optional<Product> productId = productRepository.findOptionalById(dto.id());
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

        Optional<Product> optionalProduct = productRepository.findOptionalById(id);

        return optionalProduct;
    }

    public Page<Product> filterProducts(List<ProductFilterDto> filterDTOList) {
        // Varsayılan boyut (her sayfada gösterilecek ürün sayısı)
        int DEFAULT_SIZE = 10;

        // Filtrelerin geçerliliğini kontrol et
        for (ProductFilterDto filter : filterDTOList) {
            if (filter.operation() == null) {
                throw new IllegalArgumentException("Filter operation is required for column: " + filter.columnName());
            }
        }

        // Specification oluştur
        Specification<Product> spec = ProductSpecification.filterBy(filterDTOList);

        // Toplam eleman sayısını hesapla
        long totalElements = productRepository.count(spec);

        // Toplam sayfa sayısını hesapla
        int totalPages = (int) Math.ceil((double) totalElements / DEFAULT_SIZE);

        // Eğer toplam sayfa sayısı 0 ise, bunu kontrol et
        if (totalPages == 0) {
            return new PageImpl<>(new ArrayList<>(), PageRequest.of(0, DEFAULT_SIZE), 0);
        }

        // Varsayılan olarak 1. sayfayı ayarlıyoruz
        int currentPage = 1;

        // Pageable nesnesini oluştur
        Pageable pageable = PageRequest.of(currentPage - 1, DEFAULT_SIZE, Sort.by("id").descending());

        // Ürünleri filtrele ve sayfalı olarak döndür
        return productRepository.findAll(spec, pageable);
    }




}