package org.example;

import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Epic("Тестирование проекта accuweather.com")
@Feature("Тестирование API Weather API")
public class GovernmentIssuedStormsByYearTest extends AccuweatherAbstractTest{
    @Test
    @DisplayName("Тест GovernmentIssuedStormsByYearTest - информация о штормах, выпущенная правительством за указанный год" )
    @Description("Тест GovernmentIssuedStormsByYearTest - поиск информации о штормах, выпущенной правительством за указанный год")
    @Link("https://developer.accuweather.com/accuweather-location-api/apis")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Получение информации о штормах, выпущенной правительством за 2023 год")
    @Owner("Яшина Джана")
    void testStormErrorResponses() {
        String response = given()
                .when()
                .get(getBaseUrl() + "/tropical/v1/gov/storms/2023")
                .then()
                .statusCode(401) // Проверка кода ответа 401
                .extract()
                .response()
                .asString();

        JsonPath jsonPath = new JsonPath(response);

        Assertions.assertEquals("Unauthorized", jsonPath.getString("Code"));
        Assertions.assertEquals("Api Authorization failed", jsonPath.getString("Message"));
    }
}
