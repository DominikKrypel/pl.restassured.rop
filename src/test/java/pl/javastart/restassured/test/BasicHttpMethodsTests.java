package pl.javastart.restassured.test;

import org.testng.annotations.Test;
import pl.javastart.main.pojo.Category;
import pl.javastart.main.pojo.Pet;
import pl.javastart.main.pojo.Tag;

import java.util.Collections;

import static io.restassured.RestAssured.given;

public class BasicHttpMethodsTests {
    @Test
    public void givenPetWhenPostPetThenPetIsCreatedTest(){


        Category category = new Category();
        category.setId(1);
        category.setName("Wladzia");

        Tag tag = new Tag();
        tag.setId(88);
        tag.setName("Klendra");

        Pet pet = new Pet();
        pet.setId(34);
        pet.setCategory(category);
        pet.setPhotoUrls(Collections.singletonList("http://photos.com/dog1.jpg"));
        pet.setTags(Collections.singletonList(tag));
        pet.setStatus("available");

        given().log().all().body(pet).contentType("application/json")
                .when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
                .then().log().all().statusCode(200);
    }

    @Test
    public void createUser() {
        String user = "{\"id\": 445,\n" +
                "  \"username\": \"Alfa\",\n" +
                "  \"firstName\": \"Dominik\",\n" +
                "  \"lastName\": \"Dominikalski\",\n" +
                "  \"email\": \"Dominik@test.com\",\n" +
                "  \"password\": \"password\",\n" +
                "  \"phone\": \"+123456789\",\n" +
                "  \"userStatus\": 1}";

        given().log().all().body(user).contentType("application/json")
                .when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/user")
                .then().log().all().statusCode(200);


        given().log().all()
                .pathParam("username", "Alfa")
                .when().get("https://swaggerpetstore.przyklady.javastart.pl/v2/user/{username}")
                .then().log().all().statusCode(200);
    }

    @Test
    public void givenExistingPetIdWhenGetPetThenReturnPetTest() {
        given().log().method().log().uri()
                .pathParam("petId", 1)
                .when().get("https://swaggerpetstore.przyklady.javastart.pl/v2/pet/{petId}")
                .then().log().all().statusCode(200);

    }

    @Test
    public void givenExistingPetWhenUpdatePetNameThenPetIsChangedTest(){

        Category category = new Category();
        category.setId(1);
        category.setName("dogs");

        Tag tag = new Tag();
        tag.setId(88);
        tag.setName("KilerekAldi");

        Pet pet = new Pet();
        pet.setId(123);
        pet.setCategory(category);
        pet.setPhotoUrls(Collections.singletonList("http://photos.com/dog1.jpg"));
        pet.setTags(Collections.singletonList(tag));
        pet.setStatus("available");

        given().log().all().body(pet).contentType("application/json")
                .when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
                .then().log().all().statusCode(200);

        pet.setName("hellfire");

        given().log().all().body(pet).contentType("application/json")
                .when().put("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
                .then().log().all().statusCode(200);
        }

        @Test
    public void givenExistingPetIdWhenDeletingPetThenIsDeletedTest() {

            Category category = new Category();
            category.setId(1);
            category.setName("dogs");

            Tag tag = new Tag();
            tag.setId(1);
            tag.setName("dedimek");

            Pet pet = new Pet();
            pet.setId(445);
            pet.setCategory(category);
            pet.setPhotoUrls(Collections.singletonList("http://photos.com/dog1.jpg"));
            pet.setTags(Collections.singletonList(tag));
            pet.setStatus("available");

        given().log().all().body(pet).contentType("application/json")
                 .when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
                 .then().log().all().statusCode(200);

        given().log().all().contentType("application/json").pathParam("id", 445)
                .when().delete("https://swaggerpetstore.przyklady.javastart.pl/v2/pet/{id}")
                .then().log().all().statusCode(200);
        }
}
