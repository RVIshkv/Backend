package com.tarasov.ktebackend.controllers.rest;

import com.tarasov.ktebackend.controllers.dto.GetFinalPriceRequestDto;
import com.tarasov.ktebackend.controllers.dto.GetFinalPriceResponseDto;
import com.tarasov.ktebackend.controllers.dto.GetStatisticRequestDto;
import com.tarasov.ktebackend.controllers.dto.GetStatisticResponseDto;
import com.tarasov.ktebackend.controllers.dto.RegisterSaleRequestDto;
import com.tarasov.ktebackend.controllers.dto.basic.BasicResponseDto;
import com.tarasov.ktebackend.service.SaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.tarasov.ktebackend.utils.Validations.checkNotNegativeLong;
import static com.tarasov.ktebackend.utils.Validations.checkNotNull;
import static com.tarasov.ktebackend.utils.Validations.checkNotNullOrEmptyMap;
import static com.tarasov.ktebackend.utils.Validations.checkTwoParametrsNotNull;
import static com.tarasov.ktebackend.utils.Validations.checkUuid;

@RestController
@RequestMapping("/api2/sale")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Продажи", description = "Методы для работы с продажами")
public class SaleController {
    private final SaleService saleService;

    @Operation(summary = "Запрос итоговой стоимости")
    @PostMapping("/price")
    public GetFinalPriceResponseDto getFinalPrice(@RequestBody GetFinalPriceRequestDto request) {
        checkNotNull(request.getClientUuid(), "clientUuid");
        checkUuid(request.getClientUuid(), "clientUuid");
        checkNotNullOrEmptyMap(request.getProductUuidsWithQuantities(), "productUuidsWithQuantities");

        Long finalPrice = saleService.getFinalPrice(request.getClientUuid(), request.getProductUuidsWithQuantities());
        return GetFinalPriceResponseDto.builder().finalPrice(finalPrice).build();
    }

    @Operation(summary = "Регистрация продажи")
    @PostMapping("/register")
    public BasicResponseDto registerSale(@RequestBody RegisterSaleRequestDto request) {
        checkNotNull(request.getClientUuid(), "clientUuid");
        checkUuid(request.getClientUuid(), "clientUuid");
        checkNotNegativeLong(request.getPrice(), "price");
        checkNotNullOrEmptyMap(request.getProductUuidsWithQuantities(), "productUuidsWithQuantities");

        saleService.registerSale(request.getClientUuid(), request.getProductUuidsWithQuantities(), request.getPrice());

        return new BasicResponseDto();
    }

    @Operation(summary = "Получение статистики")
    @PostMapping("/statistic")
    @Parameter(description = "ID продукта скрыт, если нужно - проверочный ID ", example = "d5739692-d5ec-4fdb-bb1b-bfafb15fd269")
    public GetStatisticResponseDto getStatistic(@RequestBody GetStatisticRequestDto request) {
        checkTwoParametrsNotNull(request.getClientUuid(), request.getProductUuid(), "clientUuid and productUuid");
        if (request.getClientUuid() != null) {
            checkUuid(request.getClientUuid(), "clientUuid");
        }
        if (request.getProductUuid() != null) {
            checkUuid(request.getProductUuid(), "productUuid");
        }

        return saleService.getStatistic(request.getClientUuid(), request.getProductUuid());
    }

}