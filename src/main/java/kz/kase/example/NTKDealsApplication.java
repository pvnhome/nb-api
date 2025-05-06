package kz.kase.example;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import jakarta.ws.rs.core.Application;

//@formatter:off
@OpenAPIDefinition(
    info = @Info(
        title="NTK Deals API",
        description = "Программные интерфейсы НБ для передачи информации о сделках с NTK в виде REST-сервиса.",
        version = "1.0.0",
        contact = @Contact(
            name = "NTK Deals API Support",
            url = "https://www.nationalbank.kz",
            email = "support@nationalbank.kz")
        )
)
//@formatter:on
public class NTKDealsApplication extends Application {
}
