package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.RegistrationPage;
import utils.EnvConfig;
import utils.TestConstants;
import utils.UserApi;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

@Epic("Тесты страницы регистрации")
public class RegistrationPageTest {

    // Инициализирую драйвер через DriverRule
    private WebDriver driver;
    private DriverRule driverRule = new DriverRule();

    private String registeredEmail;
    private String registeredPassword;

    // Запускаю драйвера перед каждым тестом
    @Before
    public void setUp() {
        driverRule.before();
        driver = driverRule.getDriver();
    }

    // Удаляю пользователя и закрываю драйвер после теста
    @After
    public void tearDown() {
        if (registeredEmail != null && registeredPassword != null) {
            // Получаю токен
            String token = UserApi.getToken(registeredEmail, registeredPassword);
            // Удаляю пользователя
            UserApi.deleteUser(token);
        }
        // Закрываю драйвер после теста
        driverRule.after();
    }

    @Test
    @DisplayName("Проверка успешной регистрации пользователя")
    public void testSuccessfulRegistration() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        // Перехожу на страницу регистрации
        driver.get(TestConstants.REGISTER_PAGE_URL);

        // Генерирую уникальный email и пароль
        registeredEmail = TestConstants.TEST_EMAIL_PREFIX + System.currentTimeMillis() + TestConstants.TEST_EMAIL_DOMAIN;
        registeredPassword = TestConstants.VALID_PASSWORD_123;

        // Заполняю данные для регистрации и кликаю по кнопке Зарегистрироваться
        registrationPage.enterName(TestConstants.TEST_USER_NAME);
        registrationPage.enterEmail(registeredEmail);
        registrationPage.enterPassword(registeredPassword);
        registrationPage.clickRegisterButton();

        // Явное ожидание перехода на страницу логина
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT));
        wait.until(ExpectedConditions.urlToBe(TestConstants.LOGIN_PAGE_URL));

        // Проверяю, что после успешной регистрации происходит переход в личный кабинет
        assertEquals(TestConstants.LOGIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Проверка ошибки при попытке регистрации с коротким (невалидным) паролем")
    public void testRegistrationWithInvalidPassword() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        // Перехожу на страницу регистрации
        driver.get(TestConstants.REGISTER_PAGE_URL);
        // Ввожу имя пользователя
        registrationPage.enterName(TestConstants.TEST_USER_NAME);
        // Ввожу сгенерированный email
        registrationPage.enterEmail(TestConstants.TEST_EMAIL_PREFIX + System.currentTimeMillis() + TestConstants.TEST_EMAIL_DOMAIN);
        // Ввожу короткий пароль
        registrationPage.enterPassword(TestConstants.SHORT_INVALID_PASSWORD);
        registrationPage.clickRegisterButton();
        // Проверяю, что остался на странице регистрации
        assertEquals(TestConstants.REGISTER_PAGE_URL, driver.getCurrentUrl());
    }
}