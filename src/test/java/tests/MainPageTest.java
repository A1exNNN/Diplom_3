package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.MainPage;
import utils.TestConstants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Epic("Тесты главной страницы")
public class MainPageTest {
    // Использую ClassRule для настройки WebDriver
    @ClassRule
    public static DriverRule driverRule = new DriverRule();  // Используем ClassRule
    // Получаю WebDriver через DriverRule
    private WebDriver driver = driverRule.getDriver();  // Получаем WebDriver через DriverRule

    @Test
    @DisplayName("Проверка перехода на страницу входа в аккаунт по клику кнопки 'Войти' на главной странице")
    public void testClickLoginButton() {
        MainPage mainPage = new MainPage(driver);
        // Переход на главную страницу
        driver.get(TestConstants.STELLAR_BURGERS_URL);
        // Клик на кнопку "Войти"
        mainPage.clickLoginButton();
        // Проверка, что открылась страница входа
        assertEquals(TestConstants.LOGIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Проверка перехода на страницу входа в аккаунт по клику кнопки 'Личный кабинет' на главной странице")
    public void testClickPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        // Перехожу на главную страницу
        driver.get(TestConstants.STELLAR_BURGERS_URL);
        // Клик по кнопке "Личный кабинет"
        mainPage.clickPersonalAccountButton();
        // Проверяю, что открылась страница входа в личный кабинет
        assertEquals(TestConstants.LOGIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Проверка видимости раздела конструктора - 'Булки'")
    public void testClickBunsSection() {
        MainPage mainPage = new MainPage(driver);
        // Перехожу на главную страницу
        driver.get(TestConstants.STELLAR_BURGERS_URL);
        // Клик по наименованию раздела "Соусы"
        mainPage.clickSaucesSection();
        // Клик по наименованию раздела "Булки"
        mainPage.clickBunsSection();
        // Проверяю видимость элемента
        assertTrue(mainPage.isBunsHeaderDisplayed());
    }

    @Test
    @DisplayName("Проверка видимости раздела конструктора - 'Соусы'")
    public void testClickSaucesSection() {
        MainPage mainPage = new MainPage(driver);
        // Перехожу на главную страницу
        driver.get(TestConstants.STELLAR_BURGERS_URL);
        // Клик по наименованию раздела "Соусы"
        mainPage.clickSaucesSection();
        // Проверяю видимость элемента
        assertTrue(mainPage.isSaucesHeaderDisplayed());
    }

    @Test
    @DisplayName("Проверка видимости раздела конструктора - 'Начинки'")
    public void testClickFillingsSection() {
        MainPage mainPage = new MainPage(driver);
        // Перехожу на главную страницу
        driver.get(TestConstants.STELLAR_BURGERS_URL);
        // Клик по наименованию раздела "Начинки"
        mainPage.clickFillingsSection();
        // Проверяю видимость элемента
        assertTrue(mainPage.isFillingsHeaderDisplayed());
    }
}