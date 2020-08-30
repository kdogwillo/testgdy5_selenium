//import org.junit.Before;
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//public class FirstTest {
//
//    WebDriver driver; //inicjalizacja pustej przegladarki
//
//    public void highlightElement(WebDriver driver, WebElement element){
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].setAttribute('style', 'background: green; border: 3px solid blue;');", element);
//
//    }
//
//    @Before // sekcja before, a wlasciwie metoda setup wykona sie przed kazdym testem
//    public void setup (){
//        System.setProperty("webdriver.chrome.driver","C:\\chromedriver_win32\\chromedriver.exe"); //1. wskazanie chrome.driver
//        driver = new ChromeDriver(); // przypisanie do pustej przegladarki, przegladarki chrome
//        driver.manage().window().maximize(); //maksymalizacja okna
//
//    }
//
//    @Test
//    public void firstTest(){
//        driver.get("https://dev.to"); //mozna do before
//        WebElement podcastBtn = driver.findElement(By.xpath("//a[@href='/pod']"));
//        highlightElement(driver,podcastBtn);
//        podcastBtn.click();
//        //przejscie na inna strone z podcatsami
//
//        WebDriverWait wait = new WebDriverWait(driver,10);
//        wait.until(ExpectedConditions.urlToBe("https://dev.to/pod"));
//        //sprawdzaj co sekundę, czy url ma wartość https://dev/to/pod, jeśli w ciągu 10 sekund nie ma takiej wartości - wywal błąd
//
//        // ten wait nie zawsze zadziala
//        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //mozna to do before
//        //przed każdym kolejnym findElement/s poczekaj 10 sekund, zanim wywalisz error, co sekundę sprawdzaj czy element jest już dostępny
//
//
//        List<WebElement> podcasts = driver.findElements(By.tagName("h3"));
////        for (WebElement podcast: podcasts) {
////            highlightElement(driver,podcast);
////        }
//        WebElement thirdPodcast = podcasts.get (2);
//                thirdPodcast.click();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//                WebElement recordButton = driver.findElement(By.id("record"));
//                        recordButton.click();
//                wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("status-message"))));
//        String recordBtnClassAttribute = recordButton.getAttribute("class");
//        boolean isPlaying = recordBtnClassAttribute.contains("playing");
//        assertTrue("podcast wasn't played",isPlaying);
//    }
//    @Test
//    public void goToWeekAndSelectFirstBlog(){
//        driver.get("https://dev.to");
//        WebElement weekBtn = driver.findElement(By.xpath("//a[@href='/top/week']"));
//        weekBtn.click();
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        wait.until(ExpectedConditions.urlToBe("https://dev.to/top/week"));
////        List<WebElement> blogs = driver.findElements(By.tagName("h2"));
////        WebElement firstBlog = blogs.get(2);
//        WebElement firstBlog = driver.findElement(By.className("crayons-story__title"));
//        String firstBlogText = firstBlog.getText();
//        firstBlog.click();
//        String blogTitle = driver.findElement(By.cssSelector(".crayons-article__header__meta > h1:first-child")).getText();
//        assertEquals(blogTitle,firstBlogText);
//    }}

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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class FirstTest {
    WebDriver driver; //inicjalizacja pustej przeglądarki
    public void highlightElement(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: green; border: 3px solid blue;');", element);
    }
    @Before //sekcja before, a właściwie metoda setup, wykona się przed każdym testem
    public void setup(){
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver_win32\\chromedriver.exe"); //1.wskazanie chrome.driver
        driver = new ChromeDriver(); //przypisanie do pustej przeglądarki, przeglądarki chrome do wykonywania testów
        driver.manage().window().maximize();
        driver.get("https://dev.to");
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        //przed każdym kolejnym findElement/s poczekaj 10 sekund, zanim wywalisz error, co sekundę sprawdzaj czy element jest już dostępny
    }
    @Test
    public void goToPodcastsAndSelectThirdOneAndPlayIt(){
        WebElement podcastBtn = driver.findElement(By.xpath("//a[@href='/pod']"));
        highlightElement(driver,podcastBtn);
        podcastBtn.click();
        String podcastUrl = driver.getCurrentUrl();
        //przejscie na inna strone z podcastami
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.urlToBe("https://dev.to/pod"));
        //sprawdzaj co sekundę, czy url ma wartość https://dev/to/pod, jeśli w ciągu 10 sekund nie ma takiej wartości - wywal błąd
        List<WebElement> podcasts = driver.findElements(By.tagName("h3"));
//        for (WebElement podcast: podcasts) {
//            highlightElement(driver,podcast);
//        }
        WebElement thirdPodcast = podcasts.get(2);
        thirdPodcast.click();
        WebElement recordBtn = driver.findElement(By.className("record-wrapper"));
        recordBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("status-message"))));
        String recordBtnClassAttribute = recordBtn.getAttribute("class");
        boolean isPlaying = recordBtnClassAttribute.contains("playing");
        assertTrue("podcast wasn't played",isPlaying);
    }
    @Test
    public void goToWeekAndSelectFirstBlog(){
        WebElement weekBtn = driver.findElement(By.xpath("//a[@href='/top/week']"));
        weekBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("https://dev.to/top/week"));
//        List<WebElement> blogs = driver.findElements(By.tagName("h2"));
//        WebElement firstBlog = blogs.get(2);
        WebElement firstBlog = driver.findElement(By.className("crayons-story__title"));
        String firstBlogText = firstBlog.getText();
        firstBlog.click();
        String blogTitle = driver.findElement(By.cssSelector(".crayons-article__header__meta > h1:first-child")).getText();
        assertEquals(blogTitle,firstBlogText);
    }
}



