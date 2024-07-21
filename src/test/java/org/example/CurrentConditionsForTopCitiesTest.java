package org.example;

import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Epic("Тестирование проекта accuweather.com")
@Feature("Тестирование API Currentconditions API")
public class CurrentConditionsForTopCitiesTest extends AccuweatherAbstractTest {
    @Test
    @DisplayName("Тест CurrentConditionsForTopCitiesTest - топ 50 лучших городов мира" )
    @Description("Тест CurrentConditionsForTopCitiesTest - получение данных о топ 50 лучших городов мира")
    @Link("https://developer.accuweather.com/accuweather-location-api/apis")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Вызов метода получения данных о топ 50 лучших городов мира")
    @Owner("Яшина Джана")
    void testLocationTemperatureTopCities() {
        String response = given()
                .when()
                .get(getBaseUrl() + "/currentconditions/v1/topcities/50")
                .then()
                .statusCode(200) // Проверка кода ответа 200
                .extract()
                .response()
                .asString();

        JsonPath jsonPath = new JsonPath(response);

        Assertions.assertEquals(50, jsonPath.getList("").size());
    }
}
