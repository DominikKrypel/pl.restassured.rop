package pl.javastart.restassured.test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.javastart.main.pojo.Category;
import pl.javastart.main.pojo.Pet;
import pl.javastart.main.pojo.Tag;

import java.util.Collections;

import static io.restassured.RestAssured.given;

public class BasicHttpMethodsTests {
    @BeforeClass
    public void setupConfiguriation(){
        RestAssured.baseURI = "https://swaggerpetstore.przyklady.javastart.pl";
        RestAssured.basePath = "v2";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType("application/json").build();
        RestAssured.responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).build();
    }


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

        given().body(pet)
                .when().post("pet");
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

        given().body(user)
                .when().post("user");


        given().log().all()
                .pathParam("username", "Alfa")
                .when().get("user/{username}");
    }

    @Test
    public void givenExistingPetIdWhenGetPetThenReturnPetTest() {
        given().log().method().log().uri()
                .pathParam("petId", 1)
                .when().get("pet/{petId}");

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

        given().body(pet).contentType("application/json")
                .when().post("pet");

        pet.setName("hellfire");

        given().body(pet).contentType("application/json")
                .when().put("pet");
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

        given().body(pet)                 .when().post("pet");

        given().pathParam("id", 445)
                .when().delete("pet/{id}");
        }
}
