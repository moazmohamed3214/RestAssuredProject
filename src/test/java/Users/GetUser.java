package Users;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.*;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static Users.createUser.userId;
public class GetUser {
    @BeforeClass
    public void setUp()
    {
        RestAssured.baseURI="https://fakerestapi.azurewebsites.net/api/v1/";
    }

    @Description("get user by ID")
    @Test
    public void getUsers()
    {
        Response response=
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .get("Users/"+userId)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        response.prettyPrint();





    }
}
