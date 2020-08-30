import PageObjects.DevToMainPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
public class PageObjectTests {
    WebDriver driverInPageObjectTests; //obiekt typu WebDriver
    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        driverInPageObjectTests = new ChromeDriver();
        driverInPageObjectTests.manage().window().maximize();
        driverInPageObjectTests.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void test(){
        DevToMainPage devToMainPage = new DevToMainPage(driverInPageObjectTests); // tworzymy nowy obiekt klasy DevToMainPage
    }
}