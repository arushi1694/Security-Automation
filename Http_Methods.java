import com.github.dzieciou.testing.curl.CurlLoggingRestAssuredConfigFactory;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class SecureMeth {

    SoftAssert softAssert = new SoftAssert();

    @Test
    public void WebMeth()
    {
        RestAssured.baseURI = "http://testphp.vulnweb.com/";
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
        validateGateway(resp);
    }

    public void validateGateway(Response respo)
    {
        String stri = respo.header("Allow");
        System.out.println(stri);
        //String serverType = res.header("Server");
        softAssert.assertEquals(stri, "GET");
        softAssert.assertEquals(stri, "POST");
        softAssert.assertEquals(stri, "HEAD");
        softAssert.assertEquals(stri, "PUT");
        softAssert.assertEquals(stri, "PATCH");

        System.out.println("The website is allowed through safe gateway");
    }
}
