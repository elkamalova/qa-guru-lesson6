import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LambdaStepsTest {
    private static final String BASE_URL = "https://github.com";
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final String ISSUE_NAME = "Listeners NamedBy";

    @Test
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", (s) -> {
            s.parameter("URL", BASE_URL);
            open(BASE_URL);
        });
        step("Ищем репозиторий " + REPOSITORY, (s) -> {
            s.parameter("Repository", REPOSITORY);
            $(".header-search-input").click();
            $(".header-search-input").setValue(REPOSITORY).submit();
        });
        step("Переходим в репозиторий " + REPOSITORY, (s) -> {
            s.parameter("Repository", REPOSITORY);
            $(By.linkText(REPOSITORY)).click();
        });
        step("Открываем таб Issues в репозитории", () -> {
            $(".js-repo-nav").$(byText("Issues")).click();
        });
        step("Проверяем, что Issue c именем " + ISSUE_NAME + " существует", (s) -> {
            s.parameter("Название", ISSUE_NAME);
            $(withText(ISSUE_NAME)).should(Condition.exist);

        });
    }
}
