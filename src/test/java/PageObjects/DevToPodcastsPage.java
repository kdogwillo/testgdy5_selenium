package PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
public class DevToPodcastsPage {
    WebDriver driverInDevToPodcastsPage;
    @FindBy(tagName = "h3")
    List<WebElement> podcastList;
    public DevToPodcastsPage(WebDriver driverFromDevToMainPage) {
        this.driverInDevToPodcastsPage = driverFromDevToMainPage;
        PageFactory.initElements(driverInDevToPodcastsPage,this);
    }
    public DevToSinglePodcastPage selectPodcastFromList(Integer pocastNumber){
        WebDriverWait wait = new WebDriverWait(driverInDevToPodcastsPage,10);
        wait.until(ExpectedConditions.urlToBe("https://dev.to/pod"));
        WebElement selectedPodcast = podcastList.get(pocastNumber);
        selectedPodcast.click();
        return new DevToSinglePodcastPage(driverInDevToPodcastsPage, wait);
    }
}