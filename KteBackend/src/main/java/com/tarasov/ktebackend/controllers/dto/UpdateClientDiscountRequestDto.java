package com.tarasov.ktebackend.controllers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "UpdateClientDiscountRequestDto")
public class UpdateClientDiscountRequestDto {
    @Schema(type = "integer", example = "5")
    private Integer discountFirst;
    @Schema(type = "integer", example = "10")
    private Integer discountSecond;
}
