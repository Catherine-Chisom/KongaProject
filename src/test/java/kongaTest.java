import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *-Visit the URL Konga
 * -Sign in to Konga Successfully
 * -From the Categories,click on the“Computers and Accessories”
 * -Click on the Laptop SubCategory
 * -Click on the Apple MacBooks
 * -Add an item to the cart
 * -Select Address
 * -Continue to make payment
 * -Select a Card Payment Method
 * -Input invalid card details
 * -Print Out the error message:Invalid card number
 * -Close the iFrame that displays the input cardModal
 * -Quit the browser.
 *
 */


public class kongaTest {
    private WebDriver driver;
    @BeforeTest
    public void start() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.konga.com/");
        driver.manage().window().maximize();
        Thread.sleep(5000);


    }
    @Test
    public void login() throws InterruptedException {


        //Click on login/signup and copy the element
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/div[4]/a")).click();
        Thread.sleep(2000);
        //input valid email
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("@gmail.com");
        //input valid password
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("kongatest$.");
        Thread.sleep(5000);
        //click the login button
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();
        Thread.sleep(5000);
    }

    @Test (priority = 1)
    public void categorySelection() throws InterruptedException {
    //click on Computers and Accessories
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[2]/div/a[2]")).click();
        Thread.sleep(5000);
        // Go to browse categories and click on laptop
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li[4]/a/label/span")).click();
        Thread.sleep(2000);
        // Select Apple Macbook
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li/a/ul/li[1]/a/label/span")).click();
        Thread.sleep(5000);

    }

    @Test (priority = 2)
    public void AddToCart() throws InterruptedException {
        // Add item to cart
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/section/section/section/ul/li[1]/div/div/div[2]/form/div[3]/button")).click();
        Thread.sleep(5000);
        // Go to My cart
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/a[2]/span[1]")).click();
        Thread.sleep(5000);
                // Click on Checkout
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[3]/section/section/aside/div[3]/div/div[2]/button")).click();
        Thread.sleep(5000);
       //Click on Add Delivery Address
       driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[2]/div[1]/div[2]/div[1]/div/button")).click();
        Thread.sleep(5000);
        // Select address
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[2]/div/div/div[2]/div/form/button")).click();
        Thread.sleep(5000);
        //Use Address
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[3]/div/div/div/a")).click();
        Thread.sleep(5000);
        // Click on Pay Now
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[1]/div[1]/span/input")).click();
        Thread.sleep(5000);
        // Click on Continue to Payment
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[3]/div[2]/div/button")).click();
        Thread.sleep(15000);

    }
    @Test (priority = 3)
    public void selectCardMethod() throws InterruptedException {
        // select a card payment method
        // change from default to iframe
        WebElement paymethod = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame("kpg-frame-component");
        Thread.sleep(7000);
        System.out.println("Payment Method");
        // select card payment method
        WebElement cardpayment = driver.findElement(By.className("Card"));
        cardpayment.click();
        System.out.println("Select card method");
        Thread.sleep(15000);
    }
    @Test (priority = 4)
    public void inputCardDetails() throws InterruptedException {
        //18. input individual card details
        //18a. input card number in its field
        WebElement carddigit = driver.findElement(By.id("card-number"));
        carddigit.sendKeys("123456789000");
        Thread.sleep(3000);
        //18b. input date in its field
        WebElement datedigit = driver.findElement(By.id("expiry"));
        datedigit.sendKeys("1124");
        Thread.sleep(3000);
        //18c. input CVV in its field
        WebElement cvvdigit = driver.findElement(By.id("cvv"));
        cvvdigit.sendKeys("456");
        Thread.sleep(20000);
        System.out.println("input card details");
        driver.findElement(By.xpath("//*[@id=\"card-pin-new\"]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"keypads\"]/button[1]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"keypads\"]/button[2]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"keypads\"]/button[3]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"keypads\"]/button[4]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"validateCardForm\"]")).click();
        Thread.sleep(5000);
        System.out.println("input card pin");
        Thread.sleep(10000);
    }
    @Test (priority = 5)
    public void errorMessage() throws InterruptedException {
        //19 print out the error message
        WebElement error = driver.findElement(By.id("card-number_unhappy"));
        System.out.println(error.getText());
        Thread.sleep(10000);
    }
    @Test (priority = 6)
    public void closeFrame() throws InterruptedException {
        //20. close the Iframe that displays input card details
        WebElement exitframe = driver.findElement(By.className("data-card__close"));
        exitframe.click();
        System.out.println("Exit payment method iframe");
        Thread.sleep(10000);
    }
    @Test (priority = 7)
    public void exitIFrame() throws InterruptedException {
        //21. Exit iFrame web
        driver.switchTo().defaultContent();
        Thread.sleep(5000);
        System.out.println("Restore default page");
    }


    @AfterTest
    public void end() {
        driver.quit();
    }
}
