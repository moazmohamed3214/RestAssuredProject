package Books;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.*;
import pojoClasses.Book;
import java.io.File;
import java.io.IOException;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.Assert.assertEquals;
import static Books.updateBook.updateBookId;
public class deleteBook {
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1/";
    }

    @Description("delete book by id")
    @Test
    public void deleteSinglebook() {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .get("Books/" + updateBookId)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        response.prettyPrint();
        assertEquals(response.jsonPath().get("id").toString(), Integer.toString(10));

    }
}
