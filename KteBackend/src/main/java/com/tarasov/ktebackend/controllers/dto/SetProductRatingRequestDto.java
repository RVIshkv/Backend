package com.tarasov.ktebackend.controllers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@Data
@XmlRootElement(name = "SetProductRatingRequestDto")
public class SetProductRatingRequestDto {
    @Schema(type = "string", example = "7057279f-354c-45c1-983b-c4542613e5e8")
    private UUID clientUuid;
    @Schema(type = "string", example = "f87dddaa-423e-47f0-8505-09db74cb7447")
    private UUID productUuid;
    @Schema(type = "integer", example = "3")
    private Integer rating;
}
