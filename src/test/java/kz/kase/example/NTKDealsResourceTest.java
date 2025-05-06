package kz.kase.example;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class NTKDealsResourceTest {
   @Test
   void testDealsEndpoint() {
      given().when().get("/ntk/deals").then().statusCode(200);
   }
}
