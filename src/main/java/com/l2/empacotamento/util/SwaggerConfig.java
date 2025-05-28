package com.l2.empacotamento.util;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Empacotamento API",
        version = "1.0",
        description = "API para empacotamento de pedidos e produtos em caixas"
))
public class SwaggerConfig {}

