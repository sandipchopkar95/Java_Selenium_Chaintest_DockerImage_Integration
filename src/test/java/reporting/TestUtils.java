package reporting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestUtils extends RootTest {

    @BeforeMethod(dependsOnMethods = "setUp")
    public void setUpPage() {
        driver.get("https://www.saucedemo.com/v1/");
    }

    @Test
    public void verify_Login_Logo_Is_Available() {
        WebElement logo_Login = driver.findElement(By.xpath("//div[@class='login_logo']"));
        Assert.assertTrue(logo_Login.isDisplayed(), "Login logo not displayed");
    }

    @Test
    public void verify_Error_Message_Displayed_For_Empty_Credentials() {
        driver.findElement(By.id("user-name")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.id("login-button")).click();
        String errorMsg = driver.findElement(By.xpath("//h3")).getText();
        Assert.assertTrue(errorMsg.contains("Username is required"));
    }

    @Test
    public void verify_User_Can_Login_With_Valid_Credentials() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String pageTitle = driver.findElement(By.xpath("//div[@class='product_label']")).getText();
        Assert.assertEquals(pageTitle, "Products");
    }


}
