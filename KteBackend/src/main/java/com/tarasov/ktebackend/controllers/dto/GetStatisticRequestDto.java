package com.tarasov.ktebackend.controllers.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@XmlRootElement(name = "GetStatisticRequestDto")
public class GetStatisticRequestDto {
    @Schema(type = "string", example = " 7057279f-354c-45c1-983b-c4542613e5e8")
    private UUID clientUuid;
    @Hidden
    @Schema(type = "string", example = "d5739692-d5ec-4fdb-bb1b-bfafb15fd269")
    private UUID productUuid;

}
