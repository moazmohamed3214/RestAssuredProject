package Books;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.*;
import pojoClasses.updateBookPojo;
import java.io.File;
import java.io.IOException;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.Assert.assertEquals;

public class updateBook {
    static int updateBookId;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1/";
    }


    @Description("update book")
    @Test
    public void updateBook() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        updateBookPojo book = mapper.readValue(new File("./src/resourses/updateBookData.json"), updateBookPojo.class);
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(book)
                        .when()
                        .post("Books")
                        .then()
                        .statusCode(200)
                        .body("id", equalTo(book.getId()))
                        .body("title", equalTo(book.getTitle()))
                        .body("description", equalTo(book.getDescription()))
                        .body("pageCount", equalTo(book.getPageCount()))
                        .body("excerpt", equalTo(book.getExcerpt()))
                        .body("publishDate", containsString("2025-07-26"))
                        .extract()
                        .response();
        response.prettyPrint();
        updateBookId = response.jsonPath().getInt("id");

    }
}
