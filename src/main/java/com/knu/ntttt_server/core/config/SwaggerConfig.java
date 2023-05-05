package com.knu.ntttt_server.core.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@OpenAPIDefinition(
        info = @Info(title = "NTTTT API 명세서",
                description = "블록체인 기반 아이돌 토큰 서비스 API 명세서",
                version = "v1"))
@Configuration
public class SwaggerConfig implements WebMvcConfigurer {
}
