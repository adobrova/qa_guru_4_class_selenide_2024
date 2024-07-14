package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ExampleForJUnitSearchTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1980x1080";
        baseUrl = "https://github.com/";
        Configuration.pageLoadStrategy = "eager";
        //Configuration.holdBrowserOpen = true;
    }

    @Test
    void shouldFindCodeForJUnit() {
        open(baseUrl);
        $("[class~='header-search-button']").click();
        $("[name='query-builder-test']").setValue("Selenide").pressEnter();
        $$("[data-testid=results-list] .search-title").first().click();
        $("#wiki-tab").click();
        $(".markdown-body ul").shouldHave(text("Soft assertions"));
        $(".markdown-body ul").$(byText("Soft assertions")).click();
        $("#wiki-body").shouldHave(text("@ExtendWith({SoftAssertsExtension.class})\n"
                + "class Tests {\n" + "@Test\n" + "void test() {\n"
                + "Configuration.assertionMode = SOFT;\n"
                + "open(\"page.html\");\n" + "$(\"#first\").should(visible).click();\n"
                + "$(\"#second\").should(visible).click();\n"));
    }
}