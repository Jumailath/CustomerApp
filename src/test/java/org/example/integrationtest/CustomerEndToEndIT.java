package org.example.integrationtest;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WireMockTest(httpPort = 8080)
public class CustomerEndToEndIT {
    @LocalServerPort
    private int port;

    @Test
    void shouldImportDataSuccessfully() {

        given().port(port)
                .post("/customer_app/v1/customers/import")
                .then()
                .assertThat()
                .statusCode(201);
    }
}
