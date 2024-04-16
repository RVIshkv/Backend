package com.tarasov.ktebackend.controllers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;
import java.util.UUID;

@Data
@XmlRootElement(name = "RegisterSaleRequestDto")
public class RegisterSaleRequestDto {
    @Schema(type = "string", example = "7057279f-354c-45c1-983b-c4542613e5e8")
    private UUID clientUuid;
    @Schema(type = "object" )
    private Map<UUID, Integer> productUuidsWithQuantities;
    @Schema(type = "integer", example = "1")
    private Long price;
}
