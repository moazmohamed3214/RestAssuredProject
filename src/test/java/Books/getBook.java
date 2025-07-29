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
import static Books.createBook.bookId;

public class getBook {
    @BeforeClass
    public void setUp()
    {
        RestAssured.baseURI="https://fakerestapi.azurewebsites.net/api/v1/";
    }
    @Description("get book by id")
    @Test
    public void getSinglebook()
    {
        Response response=
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .get("Books/"+bookId)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        response.prettyPrint();
        assertEquals(response.jsonPath().get("id").toString(),Integer.toString(5));
        System.out.println(bookId);


    }

}
