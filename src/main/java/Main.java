import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Scanner;


public class Main {
     static ChromeDriver driver;
     static Scanner input = new Scanner(System.in);
    public static void main (String[]args) throws InterruptedException {

        System.out.println("enter your user name");
        String userName=input.next();
        System.out.println("enter your password");
        String passWord=input.next();
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.get("https://www.aac.ac.il/");
        driver.manage().window().maximize();
        WebElement personalInfoElement = driver.findElement(By.cssSelector("a[href='https://portal.aac.ac.il']"));
        personalInfoElement.click();
        WebElement username = driver.findElement(By.id("Ecom_User_ID"));
        username.sendKeys(userName);
        WebElement password = driver.findElement(By.id("Ecom_Password" ));
        password.sendKeys(passWord);
        WebElement enter= driver.findElement(By.id("wp-submit"));
        enter.click();
        WebElement erorpayment = driver.findElement(By.cssSelector("span[aria-hidden='true']"));
        erorpayment.click();

        WebElement moodle = driver.findElement(By.cssSelector("a[href='https://moodle.aac.ac.il/login/index.php']"));
        moodle.click();
        Thread.sleep(2500);
        printCours();
        logout();
    }

    private static void printCours() {
        List<WebElement> listOfCourses = driver.findElements(By.cssSelector("a[class = 'aalink coursename']"));
        System.out.println("List of your courses:");
        for (int i = 0; i < listOfCourses.size(); i++)
            System.out.println((i + 1) + ") " + listOfCourses.get(i).getText() + "\n");
        System.out.println("Which course would you like to see? give me a number");
        int course = input.nextInt();

        WebElement selectedCourse = listOfCourses.get(course - 1);
        selectedCourse.click();
    }
    private static void logout() {
        WebElement openMenu = driver.findElement(By.cssSelector("#action-menu-toggle-1"));
        openMenu.click();
        WebElement logoutFromMoodle = driver.findElement(By.cssSelector("a[data-title = 'logout,moodle']"));
        logoutFromMoodle.click();
        WebElement logoutFromSystem = driver.findElement(By.cssSelector("a[href='https://portal.aac.ac.il/AGLogout']"));
        logoutFromSystem.click();
    }
}
