package helper;

import config.AmadeusToken;
import config.Conf;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class TokenPreparation {

    public AmadeusToken signIn() {
        String clientId = System.getenv("CLIENT_ID");
        String clientSecret = System.getenv("CLIENT_SECRET");
        var result = given()
                .baseUri(Conf.core().getAmadeusUrl())
                .contentType(ContentType.URLENC)
                .formParam("client_id", clientId)
                .formParam("client_secret", clientSecret)
                .formParam("grant_type", "client_credentials")
                .post("/v1/security/oauth2/token").jsonPath();

        String accessToken = result.getString("access_token");
        long expiresIn = result.getLong("expires_in");

        Allure.step("Generated amadeus token: " + accessToken);

        return new AmadeusToken(accessToken, expiresIn, result.prettyPrint());
    }
}
