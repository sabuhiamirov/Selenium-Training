package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {

    @Test
    public void loginTest(){

        System.out.println("<<*Starting loginTest*>>");

    // Create driver
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        WebDriver driver= new ChromeDriver() ;

        //sleep for 3 seconds
        sleep(3000);

        //maximize browser window
        driver.manage().window().maximize();

    // open test page
        String url="https://the-internet.herokuapp.com/login";
        driver.get(url);
        System.out.println("<<*Page is opened*>>");

    //enter username
        WebElement username=driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");

    //enter password
        WebElement password=driver.findElement(By.name("password"));
        password.sendKeys("SuperSecretPassword!");

    //click login button
        WebElement loginBtn=driver.findElement(By.tagName("button"));
        loginBtn.click();
        sleep(3000);

    //verifications:
    //new url
        String expectedUrl="https://the-internet.herokuapp.com/secure";
        String actualUrl=driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl,"Actual page url is not the same as expected");


    //logount button visible
        WebElement logoutBtn=driver.findElement(By.xpath("//a[@class='button secondary radius']"));
        Assert.assertTrue(logoutBtn.isDisplayed(),"Log out button is not visible");

    //succesful login message
        WebElement successMessage=driver.findElement(By.cssSelector("#flash"));
        String expectedMessage ="You logged into a secure area!";
        String actualMessage=successMessage.getText();
       // Assert.assertEquals(actualMessage,expectedMessage,"Actual message url is not the same as expected");
        Assert.assertTrue(actualMessage.contains(expectedMessage),"Actual message does not contain expected message.\nActaul Message: "
                                                                            + actualMessage + "\nExpected Message: " + expectedMessage);
        //close browser
        driver.quit();
    }

    private static void sleep(long m) {
        try {
            Thread.sleep(m);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
