package Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import excelData.readExcelData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.json.simple.JSONObject;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static excelData.readExcelData.*;
public class createUser {
    static int userId;
    String path="./src/test/java/excelData/userDataExcel.xlsx";
    String sheetName="sheet1";

    @BeforeClass
    public void setUp()
    {
        RestAssured.baseURI="https://fakerestapi.azurewebsites.net/api/v1/";
    }
    @Description("create user")
    @Test
    public void createUser()throws IOException
    {
        readExcelData user=new readExcelData(path,sheetName);
        user.getCellData();
        JSONObject data=new JSONObject();
        data.put("id",user.id);
        data.put("userName",user.userName);
        data.put("password",user.password);

        Response response=
                given()
                        .contentType(ContentType.JSON)
                        .body(data)
                        .when()
                        .post("Users")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        response.prettyPrint();
        assertEquals(response.jsonPath().get("id").toString(), user.id);
        assertEquals(response.jsonPath().get("userName").toString(), user.userName);
        assertEquals(response.jsonPath().get("password").toString(), user.password);
         userId = response.jsonPath().getInt("id");





    }
}
