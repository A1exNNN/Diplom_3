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
import pageobjects.ForgotPasswordPage;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.RegistrationPage;
import utils.EnvConfig;
import utils.TestConstants;
import utils.UserApi;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

@Epic("Тесты на проверку входа в аккаунт")
public class AccountAccessTest {

    @ClassRule
    public static DriverRule driverRule = new DriverRule();
    private WebDriver driver = driverRule.getDriver();

    @Before
    public void setUp() {
        // Создание тестового пользователя
        UserApi.createTestUser();
    }

    @After
    public void tearDown() {
        // Удаление тестового пользователя
        UserApi.deleteUser(UserApi.token);
    }

    @Test
    @DisplayName("Проверка входа в аккаунт через кнопку 'Войти в аккаунт' на главной странице")
    public void accessAccountThroughLoginButtonOnMainPage() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        // Открываю главную страницу
        mainPage.open();

        // Перехожу на страницу авторизации через кнопку "Войти в аккаунт" на главной странице
        mainPage.clickLoginButton();

        // Авторизуюсь
        loginPage.login(UserApi.email, UserApi.password);

        // Проверяю переход на главную страницу
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT));
        wait.until(ExpectedConditions.urlToBe(TestConstants.STELLAR_BURGERS_URL));
        assertEquals(TestConstants.STELLAR_BURGERS_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Проверка входа в личный кабинет через кнопку 'Личный кабинет' на главной странице")
    public void accessAccountThroughPersonalAccountButtonOnMainPage() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        // Открываю главную страницу
        mainPage.open();

        // Перехожу на страницу авторизации через кнопку "Личный кабинет" на главной странице
        mainPage.clickPersonalAccountButton();

        // Авторизуюсь
        loginPage.login(UserApi.email, UserApi.password);

        // Проверяю переход на главную страницу
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT));
        wait.until(ExpectedConditions.urlToBe(TestConstants.STELLAR_BURGERS_URL));
        assertEquals(TestConstants.STELLAR_BURGERS_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Проверка перехода в личный кабинет авторизованным пользователем по кнопке 'Личный кабинет'")
    public void accessAccountFromMainPage() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        // Открываю страницу авторизации и авторизуюсь
        loginPage.openLoginPage();
        loginPage.login(UserApi.email, UserApi.password);

        // Жду переход на главную страницу после авторизации
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT));
        wait.until(ExpectedConditions.urlToBe(TestConstants.STELLAR_BURGERS_URL));

        // Перехожу в профиль по кнопке "Личный кабинет"
        mainPage.clickPersonalAccountButton();

        // Проверяю переход в личный кабинет
        wait.until(ExpectedConditions.urlToBe(TestConstants.PERSONAL_ACCOUNT_URL));
        assertEquals(TestConstants.PERSONAL_ACCOUNT_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Проверка входа в аккаунт через кнопку 'Войти' на странице регистрации")
    public void accessAccountFromRegistrationPage() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        // Открываю страницу регистрации и перехожу на страницу авторизации
        registrationPage.open();
        registrationPage.clickLoginLink();

        // Авторизуюсь
        loginPage.login(UserApi.email, UserApi.password);

        // Проверяю переход на главную страницу
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT));
        wait.until(ExpectedConditions.urlToBe(TestConstants.STELLAR_BURGERS_URL));
        assertEquals(TestConstants.STELLAR_BURGERS_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Проверка входа в аккаунт через кнопку 'Войти' на странице восстановления пароля")
    public void accessAccountFromForgotPasswordPage() {
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        // Открываю страницу восстановления пароля и перехожу на страницу авторизации
        forgotPasswordPage.open();
        forgotPasswordPage.clickLoginLink();

        // Авторизуюсь
        loginPage.login(UserApi.email, UserApi.password);

        // Проверяю переход на главную страницу
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT));
        wait.until(ExpectedConditions.urlToBe(TestConstants.STELLAR_BURGERS_URL));
        assertEquals(TestConstants.STELLAR_BURGERS_URL, driver.getCurrentUrl());
    }
}