package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.TestConstants;

public class ForgotPasswordPage {
    private WebDriver driver;

    // Локатор для поля ввода Email
    private By emailField = By.name("email");
    // Локатор для кнопки "Восстановить"
    private By recoverButton = By.xpath("//button[contains(@class, 'button_button_type_primary')]");
    // Локатор для кнопки "Войти"
    private By loginLink = By.xpath("//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываю страницу 'Восстановление пароля'")
    public void open() {
        driver.get(TestConstants.FORGOT_PASSWORD_PAGE_URL);  // URL страницы добавляем в TestConstants
    }

    @Step("Ввожу Email для восстановления пароля")
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Клик по кнопке 'Восстановить'")
    public void clickRecoverButton() {
        driver.findElement(recoverButton).click();
    }

    @Step("Переход на страницу 'Вход' по клику кнопки 'Войти'")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}