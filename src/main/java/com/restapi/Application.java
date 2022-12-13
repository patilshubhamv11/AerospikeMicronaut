package com.restapi;

import io.micronaut.context.ApplicationContext;
import io.micronaut.openapi.annotation.OpenAPIInclude;
import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.logging.LogManager;
import java.util.logging.Logger;

@SecurityRequirement(name="Authorization")
@SecurityScheme(
        name = "Authorization",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
@OpenAPIDefinition(
        info = @Info(
                title = "aerospike",
                version = "0.0",
                description = "shubham API",
                license = @License(name = "Apache 2.0", url = "https://shubham.bar"),
                contact = @Contact(url = "https://gigantic-server.com", name = "shubham patil", email = "patilshubham063@gmail.com")
        )
)
@OpenAPIInclude(
        classes = {io.micronaut.security.endpoints.LoginController.class, io.micronaut.security.endpoints.LogoutController.class},
        tags = @Tag(name = "Authentication")
)

public class Application {
    private static  final Logger LOGGER = LogManager.getLogManager().getLogger(Application.class.getName());
    private static ApplicationContext context;
    public static void main(String[] args) {
        context=Micronaut.run(Application.class, args);
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }
}