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
public class createBook {
    static int bookId;
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1/";
    }


    @Description("create book")
    @Test
    public void createBook() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Book book = mapper.readValue(new File("./src/resourses/bookData.json"), Book.class);
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(book)
                        .when()
                        .post("Books")
                        .then()
                        .statusCode(200)
                        .body("id",equalTo(5))
                        .body("title", equalTo("Rest assured"))
                        .body("description", equalTo("Rest assured from zero to hero"))
                        .body("pageCount", equalTo(44))
                        .body("excerpt", equalTo("Rest assured from zero to hero"))
                        .body("publishDate", containsString("2025-07-26"))
                        .extract()
                        .response();
        response.prettyPrint();
        bookId=response.jsonPath().getInt("id");


    }
}