package Users;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.Assert.*;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

public class GetUsers {
    @BeforeClass
    public void setUp()
    {
        RestAssured.baseURI="https://fakerestapi.azurewebsites.net/api/v1/";
    }
    @Description("return all users")
    @Test
    public void getUsers()
    {
        Response response=
        given()
        .when()
        .get("Users")
        .then()
        .statusCode(200)
        .extract()
        .response();
        response.prettyPrint();
        assertEquals(response.getStatusCode(),200);
        assertEquals(response.jsonPath().get("id[0]").toString(),Integer.toString(1));
        assertEquals(response.jsonPath().get("userName[0]").toString(),"User 1");

    }

}
