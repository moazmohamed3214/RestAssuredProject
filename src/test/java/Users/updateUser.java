package Users;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class updateUser {
    static int updateUserid;
    @DataProvider(name = "postData")
    public Object[][] dataPost()
    {
        Object [][] data=new Object[1][3];
        data[0][0]= "5";
        data[0][1]="assem mohamed";
        data[0][2]="assem123";
        return data;

    }
    @BeforeClass
    public void setUp()
    {
        RestAssured.baseURI="https://fakerestapi.azurewebsites.net/api/v1/";
    }

    @Description("update user")
    @Test(dataProvider = "postData")
    public void getUsers(String id,String userName,String password)throws IOException
    {
        JSONObject data=new JSONObject();
        data.put("id",id);
        data.put("userName",userName);
        data.put("password",password);
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
        assertEquals(response.jsonPath().get("id").toString(), id);
        assertEquals(response.jsonPath().get("userName").toString(), userName);
        assertEquals(response.jsonPath().get("password").toString(), password);
        updateUserid=response.jsonPath().getInt("id");






    }
}
