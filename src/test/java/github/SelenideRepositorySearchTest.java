package github;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class SelenideRepositorySearchTest {

    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize = "1980x1080";
        Configuration.baseUrl = "https://github.com/";
        Configuration.pageLoadStrategy = "eager";
       // Configuration.holdBrowserOpen = true;
    }

    @Test

    void shouldFindSelenideRepositoryAtTheTop(){
        //ARRANGE подготовка
        //открыть главную страницу
        open(baseUrl);

        //ACT действие
        //ввести в поле поиска selenide и нажать Enter
        $("[class~='header-search-button']").click();
        $("[name='query-builder-test']").setValue("Selenide").pressEnter();

        //кликнуть на первый репозиторий из списка найденных
        $$("[data-testid=results-list] .search-title").first().click();

        //ASSERT проверки
        //проверка: заголовок selenide/selenide
        $("#repository-container-header").shouldHave(Condition.text("selenide / selenide"));

    }
}
