package pl.javastart.restassured.test;

import org.testng.annotations.Test;
import pl.javastart.main.pojo.User;

import static io.restassured.RestAssured.given;

public class UserUpdateTests {
    @Test
    public void givenCorrectUserDataWhenFirstNameLastNameAreUpdatedThenUserDataIsUpdatedTest() {
        User user = new User();
        user.setId(445);
        user.setUsername("firstuser");
        user.setFirstName("Krzysztof");
        user.setLastName("Kowalski");
        user.setEmail("krzysztof@test.com");
        user.setPassword("password");
        user.setPhone("+123456789");
        user.setUserStatus(123);

        given().log().all()
                .contentType("application/json")
                .body(user)
                .when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/user")
                .then().log().all().statusCode(200);

        user.setFirstName("Adam");
        user.setLastName("Malinowski");

        given().log().all()
                .contentType("application/json")
                .pathParam("username", user.getUsername())
                .body(user)
                .when().put("https://swaggerpetstore.przyklady.javastart.pl/v2/user/{username}")
                .then().log().all().statusCode(200);

        given().log().all()
                .contentType("application/json")
                .pathParam("username", user.getUsername())
                .when().get("https://swaggerpetstore.przyklady.javastart.pl/v2/user/{username}")
                .then().log().all().statusCode(200);
    }

}
