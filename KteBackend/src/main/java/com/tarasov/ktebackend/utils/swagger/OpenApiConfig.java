package com.tarasov.ktebackend.utils.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "KTE-Labs",
                description = "Тестовое задание для взятия меня на работу", version = "1.0.0",
                contact = @Contact(
                        name = "Тарасов Александр",
                        email = "keeper83tarasov@yandex.ru"
                )
        )
)
public class OpenApiConfig {
}
