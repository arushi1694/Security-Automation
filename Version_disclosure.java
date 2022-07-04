import com.github.dzieciou.testing.curl.CurlLoggingRestAssuredConfigFactory;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Dictionary;
import java.util.Hashtable;

import static io.restassured.RestAssured.given;

public class servDis {

    SoftAssert softAssert = new SoftAssert();

    @Test
    public void versionDis(){

        RestAssured.baseURI = "https://cms-qa.tv.yo-digital.com";
        RestAssuredConfig config = CurlLoggingRestAssuredConfigFactory.createConfig();
        RequestSpecification httpRequest = given().config(config).param("sentry_key", "c1c13041393d4227b418253fa7bb03fa", "sentry_version", "7");
        Response resp = httpRequest.request(Method.GET,"epg/channel");
        Response res = given()
                .when()
                .headers("Content-Type","application/json")
                .queryParams("sentry_key","c1c13041393d4227b418253fa7bb03fa")
                .queryParams("sentry_version","7")
                .get("api/users");

        System.out.println(resp.getBody().asString());
        System.out.println("getStatusCode " + resp.getHeaders());
        System.out.println("getStatusCode " + resp.getStatusCode());
        System.out.println("contentType " + resp.contentType());
        //System.out.println(response.prettyPrint());
        validateServ(resp);
    }
    public void validateServ(Response respon)
    {
        String stri = respon.header("Server");
        System.out.println(stri);
        //String serverType = res.header("Server");
        Assert.assertEquals(stri, "nginx", "It did not match");
        System.out.println("The server version is secure for HU-Qa_Natco");

    }

}
