package controllers.amadeus;

import config.Conf;
import controllers.BaseController;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import verifycators.AmadeusVerification;

public class AmadeusController extends BaseController<AmadeusController> {
    private final String baseUri = Conf.core().getAmadeusUrl();
    private final String basePath = Conf.core().getCheckInLink();

    @Step("Get check-in links by airlineCode={airlineCode}")
    public AmadeusVerification checkInLink(String airlineCode) {
        Response response = baseClient(baseUri, basePath)
                .queryParam("airlineCode", airlineCode)
                .get("/checkin-links");
        return new AmadeusVerification(response);
    }
}
