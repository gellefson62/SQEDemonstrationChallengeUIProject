package com.sample.test.demo.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.sample.test.demo.TestBase;
import java.util.concurrent.TimeUnit;
import java.util.Set;

import com.sample.test.demo.constants.PizzaToppings;
import com.sample.test.demo.constants.PizzaTypes;

import static org.testng.Assert.assertTrue;

public class DemoTest extends TestBase {

    Select pizza1 = null;
    WebElement pizza1Toppings1 = null;
    WebElement pizza1Toppings2 = null;
    WebElement pizza1Quantity = null;
    WebElement pizza1Cost = null;
    WebElement radioCreditCard = null;
    WebElement radioCash = null;
    WebElement custName = null;
    WebElement email = null;
    WebElement phone = null;
    WebElement placeOrderButton = null;
    WebElement resetButton = null;
    WebElement dialog = null;
    WebElement dialogText = null;
    public static final String noNameError 	= "Missing name";
    public static final String noPhoneError	= "Missing phone number";

    @BeforeClass
    public void setUp() {
        // WebElement pizza1  = driver.findElement(By.id("pizza1Pizza"));
        pizza1  = new Select(driver.findElement(By.id("pizza1Pizza")));

        pizza1Toppings1   = driver.findElement(By.xpath("//div[@id='pizza1']//select[@class='toppings1']"));
        pizza1Toppings2   = driver.findElement(By.xpath("//div[@id='pizza1']//select[@class='toppings2']"));
        pizza1Quantity    = driver.findElement(By.id("pizza1Qty"));
        pizza1Cost        = driver.findElement(By.id("pizza1Cost"));

        radioCreditCard   = driver.findElement(By.id("ccpayment"));
        radioCash         = driver.findElement(By.id("cashpayment"));

        custName          = driver.findElement(By.id("name"));
        email             = driver.findElement(By.id("email"));
        phone             = driver.findElement(By.id("phone"));

        placeOrderButton  = driver.findElement(By.id("placeOrder"));
        resetButton       = driver.findElement(By.id("reset"));

        dialog            = driver.findElement(By.id("dialog"));
        //dialogText        xpath     "//div[@id='dialog']/p")
        dialogText        = driver.findElement(By.xpath("//div[@id='dialog']/p"));
    }

    @AfterClass
    public void tearDown() {
        try {
            driver.quit();
        } catch (Exception e) {
        }
    }

    @Test
    public void demoTest() throws InterruptedException {

        System.out.println("Starting Pizza test");
        System.out.println("Case 1 success, select Small 6 Slices - no toppings");
        // case1_success();
        pizza_success(PizzaTypes.SMALL_NOTOPPINGS, null, null, 2, "Hungry Joe", null, "602-430-5544", true);

        // dialog.click();
        // System.out.println("finished test 1, did popup close?");
        // TimeUnit.SECONDS.sleep(7);
        resetButton.click();

        System.out.println("Case 2 success, select Medium 8 Slices - 2 toppings");
        // case2_success();
        pizza_success(PizzaTypes.MEDIUM_TWOTOPPINGS, PizzaToppings.OLIVES, PizzaToppings.MUSHROOMS, 1, "Guy Pizza", "pizzag@hungry.com", "602-220-5544", false);
        resetButton.click();

        System.out.println("Case 3 fail, no name entered");
        pizza_fail(PizzaTypes.SMALL_ONETOPPINGS, PizzaToppings.SALAMI, null, 1, null,"pizzag@hungry.com", "602-220-5544", true);
        resetButton.click();

        System.out.println("Case 4 fail, no phone entered");
        pizza_fail(PizzaTypes.MEDIUM_TWOTOPPINGS, PizzaToppings.OLIVES, PizzaToppings.MUSHROOMS, 1, "no Phone", null, null, false);

        System.out.println("Goodbye Pizza test");
        driver.quit();
    }

    private void pizza_success(PizzaTypes pie,  PizzaToppings topping1, PizzaToppings topping2, int qty, String customer, String custEmail, String custPhone, Boolean cc) throws InterruptedException {
    // private void pizza_test(PizzaTypes pie,  PizzaToppings topping1, PizzaToppings topping2, int qty, String customer, String custEmail, String custPhone, Boolean cc) throws InterruptedException {

        pizza1.selectByValue(pie.getDisplayName());
        if (topping1 != null) {
            pizza1Toppings1.sendKeys(topping1.getDisplayName());
        }
        if (topping2 != null) {
            pizza1Toppings2.sendKeys(topping2.getDisplayName());
        }
        double localCost = pie.getCost();
        double localTotal = localCost * qty;

        pizza1Quantity.clear();
        pizza1Quantity.sendKeys(String.valueOf(qty));

        custName.clear();
        custName.sendKeys(customer);
        if (custEmail != null) {
            email.clear();
            email.sendKeys(custEmail);
        }
        phone.clear();
        phone.sendKeys(custPhone);
        if (cc) {
            radioCreditCard.click();
        } else {
            radioCash.click();
        }
        String calcCost = pizza1Cost.getAttribute("value");
        String myCost = String.valueOf(localTotal);
        Boolean correctCost = myCost.contains(calcCost);
        assertTrue(correctCost, "Wrong value calculated");

        placeOrderButton.click();

        TimeUnit.SECONDS.sleep(1);

        String resText = getBoxText(dialogText);
        System.out.println(" The text is : " + resText);

        Boolean correctPizza = resText.contains(pie.getDisplayName());
        assertTrue(correctPizza, "Wrong pizza displayed in popup");
        Boolean correctPopupCost = resText.contains(myCost);
        assertTrue(correctPopupCost, "Wrong value displayed in popup");


    }

    private void pizza_fail(PizzaTypes pie,  PizzaToppings topping1, PizzaToppings topping2, int qty, String customer, String custEmail, String custPhone, Boolean cc) throws InterruptedException {

        Boolean noName = false;
        Boolean noPhone = false;
        Boolean correctError = false;

        pizza1.selectByValue(pie.getDisplayName());
        if (topping1 != null) {
            pizza1Toppings1.sendKeys(topping1.getDisplayName());
        }
        if (topping2 != null) {
            pizza1Toppings2.sendKeys(topping2.getDisplayName());
        }
        double localCost = pie.getCost();
        double localTotal = localCost * qty;

        System.out.println("fail case calculated cost ");

        pizza1Quantity.clear();
        pizza1Quantity.sendKeys(String.valueOf(qty));

        if (customer != null) {
            custName.sendKeys(customer);
        } else {
            noName = true;
        }

        if (custEmail != null) {
            email.sendKeys(custEmail);
        }
        if (custPhone != null) {
            phone.sendKeys(custPhone);
        } else {
            noPhone = true;
        }

        if (cc) {
            radioCreditCard.click();
        } else {
            radioCash.click();
        }
        String calcCost = pizza1Cost.getAttribute("value");
        String myCost = String.valueOf(localTotal);
        Boolean correctCost = myCost.contains(calcCost);
        assertTrue(correctCost, "Wrong value calculated");

        placeOrderButton.click();

        TimeUnit.SECONDS.sleep(1);

        String resText = getBoxText(dialogText);
        System.out.println(" The text is : " + resText);

        if (noName) {
            correctError = resText.contains(noNameError);
            assertTrue(correctError, noNameError+" in Error popup");
        }
        if (noPhone) {
            correctError = resText.contains(noPhoneError);
            assertTrue(correctError, noPhoneError+" in Error popup");
        }

    }


    private String getBoxText(WebElement dialogText) {
        String popupText = null;
        popupText = dialogText.getText();
        if (popupText.length() == 0) {
            popupText = dialogText.getAttribute("innerText");
        }
        if (popupText.isEmpty()) {
            popupText = "NO TEXT FOUND";
        }
        return popupText.trim();
    }

}
