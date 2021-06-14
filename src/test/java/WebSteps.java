import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class WebSteps {
    @Step("Открываем главную старницу")
    public void openMainPage(String url) {
        open(url);
    }

    @Step("Ищем репозиторий {repository}")
    public void searchForRepository(String repository) {
        $(".header-search-input").click();
        $(".header-search-input").setValue(repository).submit();
    }

    @Step("Переходим в репозиторий {repository}")
    public void goToRepository(String repository) {
        $(By.linkText(repository)).click();
    }

    @Step("Открываем таб Issues в репозитории")
    public void openIssueTab() {
        $(withText("Issues")).click();
    }

    @Step("Проверяем что Issue c именем {issueName} существует")
    public void shouldSeeIssueWithName(String issueName) {
        $(withText(issueName)).should(Condition.exist);

    }
}
