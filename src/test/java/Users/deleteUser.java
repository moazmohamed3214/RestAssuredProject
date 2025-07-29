package Users;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.Assert.*;
import org.testng.annotations.*;

import static Users.updateUser.updateUserid;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
public class deleteUser {
    @BeforeClass
    public void setUp()
    {
        RestAssured.baseURI="https://fakerestapi.azurewebsites.net/api/v1/";
    }

    @Description("delete user by ID")
    @Test
    public void getUsers()
    {
        Response response=
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .delete("Users/"+updateUserid)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        response.prettyPrint();





    }
}
