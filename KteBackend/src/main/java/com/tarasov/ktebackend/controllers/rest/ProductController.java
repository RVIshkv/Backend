package com.tarasov.ktebackend.controllers.rest;

import com.tarasov.ktebackend.controllers.dto.GetProductInfoResponseDto;
import com.tarasov.ktebackend.controllers.dto.GetProductResponseDto;
import com.tarasov.ktebackend.controllers.dto.SetProductRatingRequestDto;
import com.tarasov.ktebackend.controllers.dto.basic.BasicResponseDto;
import com.tarasov.ktebackend.entity.Product;
import com.tarasov.ktebackend.service.ProductService;
import com.tarasov.ktebackend.utils.mapper.DtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.tarasov.ktebackend.utils.Validations.checkNotNull;
import static com.tarasov.ktebackend.utils.Validations.checkUuid;

@RestController
@RequestMapping("/api2/product")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Товары", description = "Методы для работы с товарами")
public class ProductController {
    private final ProductService productService;
    private final DtoMapper dtoMapper;

    @Operation(summary = "Список товаров")
    @GetMapping("/list")
    public GetProductResponseDto getProducts() {
        List<Product> products = productService.getProducts();
        return GetProductResponseDto.builder()
                .products(products.stream()
                        .map(dtoMapper::productToDto)
                        .collect(Collectors.toList())
                ).build();
    }

    @Operation(summary = "Получение дополнительной информации о товаре")
    @GetMapping("/{uuid}")
    public GetProductInfoResponseDto getProductInfoById(
            @Parameter(description = "ID продукта CTR ", example = "f87dddaa-423e-47f0-8505-09db74cb7447")
            @PathVariable("uuid") UUID productUuid) {
        checkNotNull(productUuid, "productUuid");
        checkUuid(productUuid, "productUuid");

        return productService.getProductInfoByIdWithInformation(productUuid);
    }

    @Operation(summary = "Оценка товара")
    @PostMapping("/rating")
    public BasicResponseDto setProductRating(@RequestBody SetProductRatingRequestDto request) {
        checkNotNull(request.getClientUuid(), "clientUuid");
        checkUuid(request.getClientUuid(), "clientUuid");
        checkNotNull(request.getProductUuid(), "productUuid");
        checkUuid(request.getProductUuid(), "productUuid");

        productService.setProductRating(request.getClientUuid(), request.getProductUuid(), request.getRating());

        return new BasicResponseDto();
    }
}
