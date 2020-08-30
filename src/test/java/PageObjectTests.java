import PageObjects.DevToMainPage;
import PageObjects.DevToPodcastsPage;
import PageObjects.DevToSinglePodcastPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;
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
        //Przekazujemy przeglądarkę z PageObjectTests do DevToMainPage, do konstruktora
        //Konstruktor tworzy nam nowy obiekt devToMainPage
        DevToPodcastsPage devToPodcastsPage = devToMainPage.selectPodcasts();
        DevToSinglePodcastPage devToSinglePodcastPage = devToPodcastsPage.selectPodcastFromList(2);
        devToSinglePodcastPage.playPodcast();
        assertTrue("Podcast's wasn't played",devToSinglePodcastPage.isPodcastPlaying);
    }
}