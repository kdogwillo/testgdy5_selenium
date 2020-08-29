import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FirstTest {

    WebDriver driver; //inicjalizacja pustej przegladarki

    public void highlightElement(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: green; border: 3px solid blue;');", element);

    }

    @Before // sekcja before, a wlasciwie metoda setup wykona sie przed kazdym testem
    public void setup (){
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver_win32\\chromedriver.exe"); //1. wskazanie chrome.driver
        driver = new ChromeDriver(); // przypisanie do pustej przegladarki, przegladarki chrome
        driver.manage().window().maximize(); //maksymalizacja okna

    }

    @Test
    public void firstTest(){
        driver.get("https://dev.to");
        WebElement podcastBtn = driver.findElement(By.xpath("//a[@href='/pod']"));
        highlightElement(driver,podcastBtn);
        podcastBtn.click();
        //przejscie na inna strone z podcatsami

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.urlToBe("https://dev.to/pod"));
        //sprawdzaj co sekundę, czy url ma wartość https://dev/to/pod, jeśli w ciągu 10 sekund nie ma takiej wartości - wywal błąd

        // ten wait nie zawsze zadziala
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //mozna to do before
        //przed każdym kolejnym findElement/s poczekaj 10 sekund, zanim wywalisz error, co sekundę sprawdzaj czy element jest już dostępny


        List<WebElement> podcasts = driver.findElements(By.tagName("h3"));
//        for (WebElement podcast: podcasts) {
//            highlightElement(driver,podcast);
//        }
        WebElement thirdPodcast = podcasts.get (2);
                thirdPodcast.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                WebElement recordButton = driver.findElement(By.id("record"));
                        recordButton.click();
    }

    @Test
    public void secondTest(){
        driver.get("https://dev.to");
    }
}
