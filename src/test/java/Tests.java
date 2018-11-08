import com.google.gson.JsonObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Tests {

    @Test
    public void firstTest() {

        given().
                header("Content-Type","application/json").
                header("X-Ninja-Token","0xnofxo6fy2p05pxkz2aicmdooubuqdr").
        when().
                get("http://79.137.68.21/api/v1/clients/116").
        then().
                assertThat().
                statusCode(200).
        and().
                body("data.name",equalTo("Tester-01"));
    }


    @DataProvider(name = "postData")
    public static Object[][] postData() {
        return new Object[][] {
                {"AutoTester1","Krakow"},
                {"AutoTester2","Warszawa"}
        };
    }

    @Test(dataProvider = "postData")
    public void secTest(String name,String city) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("city",city);

        given().
                header("Content-Type","application/json").
                header("X-Ninja-Token","0xnofxo6fy2p05pxkz2aicmdooubuqdr").
                body(jsonObject.toString()).
        when().
                post("http://79.137.68.21/api/v1/clients").
        then().
                assertThat().
                statusCode(200).
        and().
                body("data.name",equalTo(name));


    }
}
