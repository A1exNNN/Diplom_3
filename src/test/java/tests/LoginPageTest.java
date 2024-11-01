package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.LoginPage;
import utils.EnvConfig;
import utils.TestConstants;
import utils.UserApi;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

@Epic("Тесты страницы авторизации")
public class LoginPageTest {

    @ClassRule
    // Использую ClassRule для настройки WebDriver
    public static DriverRule driverRule = new DriverRule();
    // Получаю WebDriver через DriverRule
    private WebDriver driver = driverRule.getDriver();

    // Создаю тестового пользователя перед каждым тестом
    @Before
    public void setUp() {
        UserApi.createTestUser();
    }

    // Удаляю тестового пользователя после выполнения теста
    @After
    public void tearDown() {
        UserApi.deleteUser(UserApi.token);
    }

    @Test
    @DisplayName("Успешная авторизация и переход на главную страницу")
    public void testSuccessfulLogin() {
        // Создаю страницу авторизации
        LoginPage loginPage = new LoginPage(driver);
        // Открываю страницу авторизации
        loginPage.openLoginPage();
        // Выполняю авторизацию
        loginPage.login(UserApi.email, UserApi.password);
        // Добавляю явное ожидание перехода на главную страницу
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT));
        wait.until(ExpectedConditions.urlToBe(TestConstants.STELLAR_BURGERS_URL));
        // Проверяю переход на главную страницу
        assertEquals(TestConstants.STELLAR_BURGERS_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Проверка отсутствия авторизации при вводе неправильного пароля. пользователь остается на странице авторизации")
    public void testLoginWithInvalidPassword() {
        // Создаю страницу авторизации
        LoginPage loginPage = new LoginPage(driver);
        // Открываю страницу авторизации
        driver.get(TestConstants.LOGIN_PAGE_URL);
        // Ввожу правильный email и неправильный пароль
        loginPage.enterEmail(UserApi.email);
        loginPage.enterPassword(TestConstants.WRONG_PASSWORD);
        loginPage.clickLoginButton();
        // Проверяю, что остался на странице авторизации
        assertEquals(TestConstants.LOGIN_PAGE_URL, driver.getCurrentUrl());
    }
}