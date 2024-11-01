package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.MainPage;
import utils.TestConstants;

import static org.junit.Assert.assertEquals;

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

    // переделал тесты на переход к разделам булки, соусы, начинки
    @Test
    @DisplayName("Переход к разделу 'Булки'")
    public void testClickBunsSection() {
        MainPage mainPage = new MainPage(driver);
        // Перехожу на главную страницу
        driver.get(TestConstants.STELLAR_BURGERS_URL);
        Assert.assertTrue("Переход на секцию 'Булки' не работает",
                mainPage.clickBunsSection());
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    public void testClickSaucesSection() {
        MainPage mainPage = new MainPage(driver);
        // Перехожу на главную страницу
        driver.get(TestConstants.STELLAR_BURGERS_URL);
        Assert.assertTrue("Переход на секцию 'Соусы' не работает",
                mainPage.clickSaucesSection());
    }


    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    public void testClickFillingsSection() {
        MainPage mainPage = new MainPage(driver);
        // Перехожу на главную страницу
        driver.get(TestConstants.STELLAR_BURGERS_URL);
        Assert.assertTrue("Переход на секцию 'Начинки' не работает",
                mainPage.clickFillingsSection());
    }
}

// переделал тесты на переход к разделам булки, соусы, начинки