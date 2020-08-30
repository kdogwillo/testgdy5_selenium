package PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class DevToMainPage {
    String url = "https://dev.to";
    WebDriver driverInDevToMainPage;
    @FindBy(xpath = "//a[@href='/pod']")
    WebElement podcastsBtn;
    public DevToMainPage(WebDriver driverFromPageObjectTests) {
        this.driverInDevToMainPage = driverFromPageObjectTests;
        //przypisanie zależności przeglądarki z klasy PageObjectTests do przelgądarki używanej w kalsie DevToMainPage
        driverInDevToMainPage.get(url); //otwiermy stronę w przeglądarce
        PageFactory.initElements(driverInDevToMainPage,this);
    }
    public DevToPodcastsPage selectPodcasts(){
        podcastsBtn.click();
        return new DevToPodcastsPage(driverInDevToMainPage);
    }
}