import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

public class PracticeForm {
  WebDriver driver;
  @Before
  public void setUp(){
    System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
    ChromeOptions option= new ChromeOptions();
    option.addArguments("--headed");
    driver= new ChromeDriver(option);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

  }
  @Test
  public void studentRegistrationForm() throws InterruptedException, IOException, ParseException {
    driver.get("https://demoqa.com/automation-practice-form");

     //Input Fitst Name
    driver.findElement(By.id("firstName")).sendKeys("Tanvir");
    String firstName=driver.findElement(By.id("firstName")).getAttribute("value");

    //Input Last Name
    driver.findElement(By.id("lastName")).sendKeys("Mitul");
    String latName=driver.findElement(By.id("lastName")).getAttribute("value");

    //Input Email
    driver.findElement(By.id("userEmail")).sendKeys("tanvirmitul@gmail.com");
    String Email=driver.findElement(By.id("userEmail")).getAttribute("value");

    //Select Gender
    driver.findElement(By.xpath("//label[contains(text(),'Male')]")).click();

    //Input User Number
    driver.findElement(By.id("userNumber")).sendKeys("01770736309");
    String UserNum=driver.findElement(By.id("userNumber")).getAttribute("value");

    //Input Date Of Birth
    WebElement date= driver.findElement(By.id("dateOfBirthInput"));
    date.sendKeys(Keys.CONTROL+"a");
    date.sendKeys("20 Nov 2022");
    date.sendKeys(Keys.ENTER);

    //Input Subject
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollBy(0,400)");
    WebElement subject= driver.findElement(By.id("subjectsInput"));
    subject.sendKeys("Hin");
    subject.sendKeys(Keys.ARROW_DOWN);
    subject.sendKeys(Keys. ENTER);

    subject.sendKeys("His");
    subject.sendKeys(Keys.ARROW_DOWN);
    subject.sendKeys(Keys. ENTER);

    // //Select Hobbies
    Actions actions = new Actions(driver);
    List <WebElement> list= driver.findElements(By.cssSelector("[type=checkbox]"));
    actions.click(list.get(0)).perform();

    //Upload Picture
    driver.findElement(By.id("uploadPicture")).sendKeys("E:\\mitul.jpg");

    //Input Address
    driver.findElement(By.id("currentAddress")).sendKeys("Mirpur-1, Dhaka");
    String address=driver.findElement(By.id("currentAddress")).getAttribute("value");

    //Select State 
    WebElement state1 = driver.findElement(By.id("state"));
    actions.click(state1).sendKeys("Haryana").sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

    //Select City
    WebElement city = driver.findElement(By.id("city"));
    actions.click(city).sendKeys("Lucknow").sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

    //Submit Information
    driver.findElement(By.id("submit")).submit();

    //Input in JSON
    String fileName="./src/test/resources/studentInfo.json";
    JSONParser jsonParser=new JSONParser();
    Object obj=jsonParser.parse(new FileReader(fileName));
    JSONObject jsonObject=new JSONObject();

    jsonObject.put("firstname",firstName);
    jsonObject.put("lastname",latName);
    jsonObject.put("Email",Email);
    jsonObject.put("User_Num",UserNum);
    jsonObject.put("Address",address);

    JSONArray jsonArray=(JSONArray)obj;
    jsonArray.add(jsonObject);

    FileWriter fw= new FileWriter(fileName);
    fw.write(jsonArray.toJSONString());
    fw.flush();
    fw.close();

  }
  @After
  public void browserclose() {
    driver.quit();
  }

}

