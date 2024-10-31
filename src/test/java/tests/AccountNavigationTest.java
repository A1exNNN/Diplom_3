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
import pageobjects.MainPage;
import pageobjects.ProfilePage;
import utils.EnvConfig;
import utils.TestConstants;
import utils.UserApi;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

@Epic("Тесты навигации по учетной записи")
public class AccountNavigationTest {

    @ClassRule
    public static DriverRule driverRule = new DriverRule();
    private WebDriver driver = driverRule.getDriver();

    @Before
    public void setUp() {
        // Создаю тестового пользователя
        UserApi.createTestUser();
        // Авторизуюсь со страницы авторизации
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.login(UserApi.email, UserApi.password);

        // Жду переход на главную страницу после авторизации
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT));
        wait.until(ExpectedConditions.urlToBe(TestConstants.STELLAR_BURGERS_URL));
    }

    @After
    public void tearDown() {
        // Удаляю тестового пользователя
        UserApi.deleteUser(UserApi.token);
    }

    @Test
    @DisplayName("Проверка перехода на главную страницу по кнопке 'Конструктор' из личного кабинета")
    public void navigateToConstructorFromProfile() {
        ProfilePage profilePage = new ProfilePage(driver);

        // Перехожу в личный кабинет
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        // Клик по кнопке "Конструктор"
        profilePage.clickConstructorButton();

        // Проверяю переход на главную страницу
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT));
        wait.until(ExpectedConditions.urlToBe(TestConstants.STELLAR_BURGERS_URL));
        assertEquals(TestConstants.STELLAR_BURGERS_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Проверка перехода на главную страницу по клику на логотип Stellar Burgers из личного кабинета")
    public void navigateToMainPageFromProfileByLogo() {
        ProfilePage profilePage = new ProfilePage(driver);

        // Перехожу в личный кабинет
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        // Клик по логотипу Stellar Burgers
        profilePage.clickLogo();

        // Проверяю переход на главную страницу
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT));
        wait.until(ExpectedConditions.urlToBe(TestConstants.STELLAR_BURGERS_URL));
        assertEquals(TestConstants.STELLAR_BURGERS_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Тест на выход из аккаунта через личный кабинет")
    public void testLogoutFromProfile() {
        ProfilePage profilePage = new ProfilePage(driver);
        MainPage mainPage = new MainPage(driver);

        // Перехожу в личный кабинет
        mainPage.clickPersonalAccountButton();

        // Кликаю по кнопке "Выйти"
        profilePage.clickLogoutButton();

        // Жду переход на страницу авторизации
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT));
        wait.until(ExpectedConditions.urlToBe(TestConstants.LOGIN_PAGE_URL));

        // Проверяю что нахожусь на странице авторизации
        assertEquals(TestConstants.LOGIN_PAGE_URL, driver.getCurrentUrl());
    }
}