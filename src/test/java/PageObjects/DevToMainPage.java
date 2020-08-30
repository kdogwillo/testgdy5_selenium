package PageObjects;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DevToMainPage {
    public String url = "https;//dev.to";
    WebDriver driver;
    @Before //sekcja before, a właściwie metoda setup, wykona się przed każdym testem
    public void setup(){
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver_win32\\chromedriver.exe"); //1.wskazanie chrome.driver
        driver = new ChromeDriver(); //przypisanie do pustej przeglądarki, przeglądarki chrome do wykonywania testów
        driver.manage().window().maximize();
        driver.get("https://dev.to");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //przed każdym kolejnym findElement/s poczekaj 10 sekund, zanim wywalisz error, co sekundę sprawdzaj czy element jest już dostępny
    }
}
