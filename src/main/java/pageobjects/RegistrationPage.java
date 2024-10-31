package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.TestConstants;

public class RegistrationPage {
    private WebDriver driver;

    // Локаторы для элементов страницы регистрации
    // Локатор поля Имя
    private By nameField = By.xpath("//input[@name='name']");
    // Локатор поля Email
    private By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    // Локатор поля Пароль
    private By passwordField = By.xpath("//input[@type='password']");
    // Локатор кнопки "Зарегистрироваться"
    private By registerButton = By.xpath("//button[contains(text(),'Зарегистрироваться')]");
    // Локатор кнопки "Войти" для перехода на страницу входа
    private By loginLink = By.xpath("//a[text()='Войти']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие страницы 'Регистрация'")
    public void open() {
        driver.get(TestConstants.REGISTER_PAGE_URL);  // URL страницы регистрации из TestConstants
    }

    @Step("Ввод имени")
    public void enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Ввод Email")
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Переход на страницу авторизации через кнопку 'Войти'")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}