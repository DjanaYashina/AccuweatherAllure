package org.example;

import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Epic("Тестирование проекта accuweather.com")
@Feature("Тестирование API Weather API")
public class OneDayofDailyForecastsTest extends AccuweatherAbstractTest{

    @Test
    @DisplayName("Тест OneDayofDailyForecastsTest - поиск погоды на день" )
    @Description("Тест OneDayofDailyForecastsTest - получение данных о погоде на день")
    @Link("https://developer.accuweather.com/accuweather-location-api/apis")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Вызов метода получения погоды на день")
    @Owner("Яшина Джана")
    void testOneDay() {

        String result = given().queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/forecasts/v1/daily/1day/294021")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .response()
                .body().asString();

        Assertions.assertTrue(result.contains("DailyForecasts"));
        Assertions.assertTrue(result.contains("Headline"));;
    }
}