
package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ExampleForJUnitSearchWithCommentsTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1980x1080";
        baseUrl = "https://github.com/";
        Configuration.pageLoadStrategy = "eager";
        //Configuration.holdBrowserOpen = true;
    }

    @Test
    void shouldFindCodeForJUnit() {
        //Откройте страницу Selenide в Github
        open(baseUrl);

        //тест повторяет действия пользователя - переход на страницу через поиск
        $("[class~='header-search-button']").click();
        $("[name='query-builder-test']").setValue("Selenide").pressEnter();
        $$("[data-testid=results-list] .search-title").first().click();

        //Перейдите в раздел Wiki проекта
        $("#wiki-tab").click();

        //Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $(".markdown-body ul").shouldHave(text("Soft assertions"));

        //Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5
        $(".markdown-body ul").$(byText("Soft assertions")).click();

        $("#wiki-body").shouldHave(text("@ExtendWith({SoftAssertsExtension.class})\n"
                + "class Tests {\n" + "@Test\n" + "void test() {\n"
                + "Configuration.assertionMode = SOFT;\n"
                + "open(\"page.html\");\n" + "$(\"#first\").should(visible).click();\n"
                + "$(\"#second\").should(visible).click();\n"));
    }


}