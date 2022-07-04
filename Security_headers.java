import Utils.ConstantUtil;
import Utils.enums.AppKeyEnum;
import com.github.dzieciou.testing.curl.CurlLoggingRestAssuredConfigFactory;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.restassured.matcher.RestAssuredMatchers.*;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class securityheaders {

    SoftAssert softAssert = new SoftAssert();

    @Test
    public void OptionsCase() {

        baseURI = "https://gateway.dev.tv.yo-digital.com/at-qa-bifrost/";
        RestAssuredConfig config = CurlLoggingRestAssuredConfigFactory.createConfig();
        RequestSpecification httpRequest = RestAssured.given().config(config).param("page", "2");
        Response resp = httpRequest.request(Method.GET,"epg/channel");
        Response res = given()
                .when()
                .headers("Content-Type","application/json")
                .queryParams("page","2")
                .get("api/users");

        System.out.println(res.getBody().asString());
        System.out.println("getStatusCode " + resp.getHeaders());
        System.out.println("getStatusCode " + resp.getStatusCode());
        System.out.println("contentType " + resp.contentType());
        //System.out.println(response.prettyPrint());
        validateHUHeader(resp);
        //validateHeaderTwo(response);
    }
     /*public Map<String, String> getHeaders() {
        Map<String, String> setHeaders = new ConcurrentHashMap<>();
        setHeaders.put("X-Content-Type-Options", "nosniff");
        setHeaders.put("Strict-Transport-Security", "max-age=31536000;includeSubdomains");
        setHeaders.put("X-XSS-Protection", "1; mode=block");
        setHeaders.put("X-Frame-Options", "deny");
        return setHeaders;
    }*/
    public void validateHUHeader(Response res) {
        String s = res.header("X-Content-Type-Options");
        System.out.println(s);
        softAssert.assertEquals(s, "nosniff");
        System.out.println("The site is secure for X-Content-Type-Options");

        String st = res.header("Strict-Transport-Security");
        System.out.println(st);
        softAssert.assertEquals(st, "max-age=31536000;includeSubdomains");
        System.out.println("The site is secure for Strict-Transport-Security");

        String str = res.header("X-XSS-Protection");
        System.out.println(str);
        softAssert.assertEquals(str, "1; mode=block");
        System.out.println("The site is secure for X-XSS-Protection");

        String strs = res.header("X-Frame-Options");
        System.out.println(strs);
        softAssert.assertEquals(strs, "deny");
        System.out.println("The site is secure for X-Frame-Options");
    }
}
