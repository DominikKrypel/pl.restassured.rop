package pl.javastart.restassured.test;

import org.testng.annotations.Test;
import pl.javastart.main.pojo.Category;
import pl.javastart.main.pojo.Pet;
import pl.javastart.main.pojo.Tag;
import pl.javastart.main.pojo.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertTrue;

public class CreateAndFindAnimals {
    @Test
    public void givenExistingPetWithStatusSoldWhenGetPetWithSoldStatusThenPetWithStatusIsReturnedTest() {

    Category category = new Category();

    {
        category.setId(1);
        category.setName("dogs");

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("dogs-category");

        Pet pet = new Pet();
        pet.setId(777);
        pet.setCategory(category);
        pet.setPhotoUrls(Collections.singletonList("http://photos.com/dog1.jpg"));
        pet.setTags(Collections.singletonList(tag));
        pet.setStatus("sold");

        given().log().all().body(pet).contentType("application/json")
                .when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
                .then().log().all().statusCode(200);

        Pet[] pets = given().queryParam("Status", "sold").contentType("application/json")
                .when().get("https://swaggerpetstore.przyklady.javastart.pl/v2/pet/findByStatus")
                .then().log().all().statusCode(200).extract().as(Pet[].class);
        System.out.println("Lista zwierzaczków " + Arrays.asList(pets));
        assertTrue("List of pets", Arrays.asList(pets).size() > 0);
        }
    }
}
