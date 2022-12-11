import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;

public class JUnitTutorial {
  WebDriver driver;
  @Before
  public void setUp(){
    System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
    ChromeOptions options= new ChromeOptions();
    options.addArguments("--headed");
    driver= new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
  }@Test
  public void getTitle(){
    driver.get("https://demoqa.com/");
    String title= driver.getTitle();
    Assert.assertEquals(title, "ToolsQA");
    driver.quit();
  }@Test
  public void writeOnTextBox(){
    driver.get("https://demoqa.com/text-box");
    // driver.findElement(By.id("userName")).sendKeys("Tanvir Mitul");
    // driver.findElement(By.id("userEmail")).sendKeys("tanvirmitul@gmail.com");
    // driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("tanvirmitul@gmail.com");
    driver.findElement(By.cssSelector("[type=email]")).sendKeys("mitul@gmail.com");
        driver.quit();

  }@Test
  public void handleAlert() throws InterruptedException{
    driver.get("https://demoqa.com/alerts");
    // driver.findElement(By.id("alertButton")).click();
    // Thread.sleep(3000);
    // driver.switchTo().alert().accept();
    // driver.findElement(By.id("timerAlertButton")).click();
    // driver.switchTo().alert().accept();
    // driver.findElement(By.id("confirmButton")).click();
    // driver.switchTo().alert().accept();
    // String text1=driver.findElement(By.id("confirmResult")).getText();
    // Assert.assertEquals(text1, "You selected Ok");
    //  driver.findElement(By.id("confirmButton")).click();
    // driver.switchTo().alert().dismiss();
    // String text1=driver.findElement(By.id("confirmResult")).getText();
    // Assert.assertEquals(text1, "You selected Cancel");
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollBy(0,500)");
    driver.findElement(By.id("promtButton")).click();
    Thread.sleep(2000);
    Alert alert=driver.switchTo().alert();
    alert.sendKeys("Mitul");
    alert.accept();
    String text=driver.findElement(By.id("promptResult")).getText();
    Assert.assertEquals(text, "You entered Mitul");
    driver.quit();
           
  }@Test
  public void selectDate() throws InterruptedException{
    driver.get("https://demoqa.com/date-picker");
    WebElement date= driver.findElement(By.id("datePickerMonthYearInput"));
    date.sendKeys(Keys.CONTROL+"a");
    date.sendKeys(Keys.BACK_SPACE);
    date.sendKeys("11/20/2022");
    date.sendKeys(Keys.ENTER);

    Thread.sleep(4000);

    WebElement dateTime= driver.findElement(By.id("dateAndTimePickerInput"));
    dateTime.sendKeys(Keys.CONTROL+"a");
    dateTime.sendKeys(Keys.BACK_SPACE);
    dateTime.sendKeys("November 20, 2022 9:26 PM");
    dateTime.sendKeys(Keys.ENTER);
        driver.quit();

  }@Test
  public void keyBoardEvent(){
    driver.get("https://www.google.com/");
    Actions actions= new Actions(driver);
    WebElement text=driver.findElement(By.name("q"));
    actions.moveToElement(text);
    text.sendKeys("Selenium Webdriver");
    text.sendKeys(Keys.CONTROL+"a");
    actions.contextClick().perform();
        driver.quit();

  }@Test
  public void dropDown(){
    driver.get("https://demoqa.com/select-menu");
    WebElement menu=driver.findElement(By.id("oldSelectMenu"));
    Select select=new Select(menu);
    select.selectByValue("2");
    Select cars= new Select(driver.findElement(By.id("cars")));

    if (cars.isMultiple()) {
      cars.selectByValue("volvo");
      cars.selectByValue("audi");
    }
        driver.quit();

  }@Test
  public void mouseHover(){
    driver.get("https://www.bubt.edu.bd/");
    List<WebElement>list =  driver.findElements(By.xpath("//a[contains(text(),\"About\")]"));
    Actions actions= new Actions(driver);
    actions.moveToElement(list.get(2)).perform();
        driver.quit();


  }@Test
  public void actionCLick() {
    driver.get("https://demoqa.com/buttons");
    List<WebElement> list = driver.findElements(By.cssSelector("[type=button]"));
    Actions actions = new Actions(driver);
    actions.doubleClick(list.get(1)).perform();
    actions.contextClick(list.get(2)).perform();
    actions.click(list.get(3)).perform();
    driver.quit();

  }
  @Test
    public void takeScreenShot() throws IOException {
        driver.get("https://www.google.com/");
        File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String time = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss-aa").format(new java.util.Date());
        String fileWithPath = "./src/test/resources/screenshots/" + time + ".png";
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(screenshotFile, DestFile);
        driver.quit();
    }
  @Test
  public void uploadDownloadFile() throws InterruptedException {
    driver.get("https://demoqa.com/upload-download");
    driver.findElement(By.id("uploadFile")).sendKeys("E:\\mitul.jpg");
    Thread.sleep(3000);
    //download file
        driver.findElement(By.id("downloadButton")).click();

        driver.quit();

  }
  
  @Test
  public void handeAutoSuggetion() throws InterruptedException{
    driver.get("https://demoqa.com/select-menu");
    JavascriptExecutor js = (JavascriptExecutor) driver;
    List<WebElement> list=driver.findElements(By.className("css-1hwfws3"));
    
    Actions actions=new Actions(driver);
    actions.moveToElement(list.get(0));
    actions.click().sendKeys("Group 1, option 1").
    sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

    Thread.sleep(2000);

    actions.moveToElement(list.get(1));
    actions.click().sendKeys("Dr.").
    sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

    Thread.sleep(2000);

    js.executeScript("window.scrollBy(0,400)");
    actions.moveToElement(list.get(2));
    Thread.sleep(2000);
    actions.click().sendKeys("Green").sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    actions.sendKeys(Keys.TAB).click().perform();
        driver.quit();

    
  }@Test
  public void handleTab(){
    driver.get("https://demoqa.com/browser-windows");
    driver.findElement(By.id("tabButton")).click();
    ArrayList<String> windw= new ArrayList<>(driver.getWindowHandles());
    driver.switchTo().window(windw.get(1));
    String text= driver.findElement(By.id("sampleHeading")).getText();
    Assert.assertEquals(text, "This is a sample page");

    driver.close();
    driver.switchTo().window(windw.get(0));
        driver.quit();

  }@Test
  public void handleWindow(){
    driver.get("https://demoqa.com/browser-windows");
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollBy(0,500)");
    driver.findElement(By.id("windowButton")).click();
    String mainWindow= driver.getWindowHandle();
    Set<String> allWindows= driver.getWindowHandles();
    Iterator<String> iter= allWindows.iterator();

    while(iter.hasNext()){
      String childWindow= iter.next();

      if(mainWindow.equalsIgnoreCase(childWindow)){
        continue;
      }
      else{
        driver.switchTo().window(childWindow);
        String text=driver.findElement(By.id("sampleHeading")).getText();
        Assert.assertEquals(text, "This is a sample page");
        driver.close();
        break;
      }
    }
    driver.switchTo().window(mainWindow);
        driver.quit();

  }@Test
  public void dataScrap(){
    driver.get("https://demoqa.com/webtables");
    WebElement table= driver.findElement(By.className("rt-tbody"));
    List<WebElement> allRows= table.findElements(By.className("rt-tr"));
    List<WebElement> allCols= table.findElements(By.className("rt-td"));

    for (WebElement row : allRows) {
      int i = 0;
      for (WebElement col : allCols) {
        i++;
        System.out.println("num[" + i + "]" + col.getText());
      }
    }
        driver.quit();


  }@Test
  public void handleFrame(){
    driver.get("https://demoqa.com/frames");
    driver.switchTo().frame("frame1");
    String text=  driver.findElement(By.id("sampleHeading")).getText();
    Assert.assertEquals(text, "This is a sample page");
    driver.switchTo().defaultContent();
        driver.quit();

  }

}
