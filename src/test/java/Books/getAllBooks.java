package Books;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
public class getAllBooks {
    @BeforeClass
    public void setUp()
    {
        RestAssured.baseURI="https://fakerestapi.azurewebsites.net/api/v1/";
    }
    @Description("return all books")
    @Test
    public void getAllBooks()
    {
        Response response=
                given()
                        .when()
                        .get("Books")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        response.prettyPrint();
        assertEquals(response.jsonPath().get("id[0]").toString(),Integer.toString(1));
        assertEquals(response.jsonPath().get("title[0]").toString(),"Book 1");

    }
}
