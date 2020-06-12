package intro;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchMovieParentTest {
    public static WebDriver driver;
    public static WebDriverWait wait;
    private static WebElement searchBox;
    private static WebElement searchButton;
    public static void navegarUrl(String url) {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.navigate().to(url);
    }
    public static void validarHomePage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("home_img_holder")));
        searchBox = driver.findElement(By.cssSelector("[name='q']"));
        searchButton = driver.findElement(By.id("suggestion-search-button"));
        WebElement signinLink = driver.findElement(By.cssSelector("[href='/registration/signin?ref=nv_generic_lgin']"));
        if (searchBox.isDisplayed() && searchButton.isDisplayed() && signinLink.isDisplayed())
            System.out.println("Home page cargó bien");
        else {
            System.out.println("Home page no cargó");
            System.exit(-1);
        }
    }
    public static void buscarPelicula(String pelicula) {
        searchBox.sendKeys(pelicula);
        if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'imdb-header__search-menu')]"))).isDisplayed())
            System.out.println("Sugerencias deplegadas correctamente");
        else {
            System.out.println("Sugerencias no se deplegaron correctamente");
            System.exit(-1);
        }
        searchButton.click();
    }
    public static void validarBusqueda(String pelicula) {
        WebElement headerResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='findHeader']")));
        WebElement primerResultado = driver.findElement(By.cssSelector("#main .findSection .findList"));
        if (headerResult.getText().contains(pelicula) && primerResultado.isDisplayed())
            System.out.println("Busqueda exitosa");
        else
            System.out.println("Fallo en busqueda");
    }
    public static void cerrarBrowser() {
        //driver.quit();
    }
}
