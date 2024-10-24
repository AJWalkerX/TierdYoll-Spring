package com.ajwalker.utility;

import com.ajwalker.entity.Product;
import com.ajwalker.repository.ProductRepository;
import com.ajwalker.utility.enums.EBrand;
import com.ajwalker.utility.enums.ECategory;
import com.ajwalker.utility.enums.EColor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DataInitializer implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final Random random = new Random();

    public DataInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        // 100 adet ürün ekle
//        for (int i = 0; i < 100; i++) {
//            Product product = createRandomProduct(i);
//            productRepository.save(product);
//        }
    }

    private Product createRandomProduct(int index) {
        return Product.builder()
                .name("Product " + index)
                .productCode("PROD-" + index)
                .price((long) (random.nextInt(1000) + 1)) // 1-1000 arasında rastgele fiyat
                .userId((long) (random.nextInt(10) + 1)) // 1-10 arasında rastgele kullanıcı ID
                .stock(random.nextInt(100) + 1) // 1-100 arasında rastgele stok
                .category(ECategory.values()[random.nextInt(ECategory.values().length)]) // Rastgele kategori
                .brand(EBrand.values()[random.nextInt(EBrand.values().length)]) // Rastgele marka
                .rating(Math.round((random.nextDouble() * 5) * 100.0) / 100.0) // 0.00 - 5.00 arasında rastgele değerlendirme
                .color(EColor.values()[random.nextInt(EColor.values().length)]) // Rastgele renk
                .build();
    }
}
