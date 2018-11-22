package org.chenmin.open.chrome;

import bsh.EvalError;
import bsh.Interpreter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
@SpringBootApplication
public class ChromeApplication {

    public static WebDriver getLocalPhantomjs(){
        WebDriverManager.phantomjs().useMirror().setup();

        DesiredCapabilities dc =  DesiredCapabilities.phantomjs();
        dc.setCapability("phantomjs.page.settings.userAgent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        //"phantomjs.page.settings.loadImages" 不要图片
        dc.setCapability("phantomjs.page.settings.loadImages",false);
        PhantomJSDriver driver = new PhantomJSDriver(dc);

        //212.96.56.114:32231
//        String host="212.96.56.114";
//        String port="32231";
//        driver.executePhantomJS("phantom.setProxy('"+host+"', "+port+")");

//        String  phantomScript =
//        "var url =  'http://www.cnblogs.com/';" +
//                "var page = this; page.onResourceReceived = function(response) {" +
//                "if (response.stage !== \"end\" || response.url != url) " +
//                "return;" +
//                " console.log('##### ' + response.id + ' ##### ' + response.url + ' ##### ' + response.stage + ' ##### ' + response.status + ' ##### ' + response.statusText + ' #####');" +
//                " };";
//        String as = "page.onResourceRequested = function(requestData, request) {\n" +
//                "    if ((/http:\\/\\/.+?\\.css/gi).test(requestData['url']) || requestData.headers['Content-Type'] == 'text/css') {\n" +
//                "        console.log('The url of the request is matching. Aborting: ' + requestData['url']);\n" +
//                "        request.abort();\n" +
//                "    }\n" +
//                "};";
//        String baidu="var page = require('webpage').create();\n" +
////                "page.onResourceRequested = function(requestData, request) {\n" +
////                "    if ((/http:\\/\\/.+?\\.css/gi).test(requestData['url']) || requestData.headers['Content-Type'] == 'text/css') {\n" +
////                "        console.log('The url of the request is matching. Aborting: ' + requestData['url']);\n" +
////                "        request.abort();\n" +
////                "    }\n" +
////                "};"+
//                "page.open('http://www.baidu.com', function() {\n" +
//                "    setTimeout(function() {\n" +
//                "        page.render('baidu.png');\n" +
//                "        phantom.exit();\n" +
//                "    }, 200);\n" +
//                "});";
////        driver.executePhantomJS(phantomScript);
////        driver.executePhantomJS(as);
////        driver.executePhantomJS(baidu);
////        String ass = "console.log('using PhantomJS version ' +\n" +
////                "  phantom.version.major + '.' +\n" +
////                "  phantom.version.minor + '.' +\n" +
////                "  phantom.version.patch);";
////        driver.executePhantomJS(ass);
//
//        Object result  = driver.executePhantomJS(
//                "  console.log('Hello world!')"  );
        return driver;
    }

    public static WebDriver getPhantomjs()throws Exception {

        DesiredCapabilities dc =  DesiredCapabilities.phantomjs();
        dc.setCapability("phantomjs.page.settings.userAgent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        //"phantomjs.page.settings.loadImages" 不要图片
        dc.setCapability("phantomjs.page.settings.loadImages",false);
//        String url = "http://192.168.169.58:8910";
        String url = "http://jp.chenmin.org:8910";
        //106.12.211.88 baidu
//        String url = "http://106.12.211.88:8910";
        WebDriver  driver = new RemoteWebDriver(
                new URL(url),
                dc);
//        PhantomJSDriver driver = new PhantomJSDriver(
//                (new HttpCommandExecutor( new URL(url))),
//                dc);
//
//        String  phantomScript =
//                "var url =  'http://www.cnblogs.com/';" +
//                        "var page = this; page.onResourceReceived = function(response) {" +
//                        "if (response.stage !== \"end\" || response.url != url) " +
//                        "return;" +
//                        " console.log('##### ' + response.id + ' ##### ' + response.url + ' ##### ' + response.stage + ' ##### ' + response.status + ' ##### ' + response.statusText + ' #####');" +
//                        " };";
//        driver.executePhantomJS(phantomScript);

        return driver;
    }

    public static WebDriver getChrome()throws Exception {

        DesiredCapabilities capabilities= DesiredCapabilities.chrome();

        HashMap<String, Object> images = new HashMap<String, Object>();
        images.put("images", 2);
        HashMap<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values", images);

        ChromeOptions options= new ChromeOptions();
        options.setHeadless(true);
        options.addArguments("--test-type --no-sandbox");
//        options.addArguments("--test-type --no-sandbox --headless --disable-gpu");
        options.addArguments("--enable-strict-powerful-feature-restrictions");

        options.setExperimentalOption("prefs", prefs);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

//        ChromeOptions options = new ChromeOptions();
//        Map<String, Object> prefs = new HashMap<String, Object>();
//        //禁止加载图片
//        prefs.put("profile.default_content_setting_values.images", 2);
//        options.setExperimentalOption("prefs", prefs);
//        options.setHeadless(true);
//        //设置将要代理的IP和端口号
//        String proxyIpPort="178.205.251.170:45587";
//        //设置一个空的协议对象
//        Proxy proxy=new Proxy();
//        //设置对象支持http、ftp、ssl协议
////        proxy.setHttpProxy(proxyIpPort).setFtpProxy(proxyIpPort).setSslProxy(proxyIpPort);
//        proxy.setHttpProxy(proxyIpPort);
//
//
//        //设置能力对象，将proxy对象设置为值
////        options.setCapability(CapabilityType.PROXY, proxy);
//
//        WebDriver driver = new RemoteWebDriver( new URL("http://127.0.0.1:9515"), options);
//        WebDriver driver = new RemoteWebDriver( new URL("http://127.0.0.1:9515"), capabilities);
//        WebDriver driver = new RemoteWebDriver( new URL("http://192.168.169.57:9515"), capabilities);
//        WebDriver driver = new RemoteWebDriver( new URL("http://106.12.211.88:9515"), capabilities);
        WebDriver driver = new RemoteWebDriver( new URL("http://47.74.6.128:9515"), capabilities);
        return driver;
    }

    public static void main(String[] args) throws Exception {


        SpringApplication.run(ChromeApplication.class, args);
//        WebDriver  driver = getChrome();
        WebDriver  driver = getPhantomjs();
//        WebDriver  driver = getLocalPhantomjs();



        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        try {
            driver.manage().window().maximize();
//            driver.get("http://2018.ip138.com/ic.asp");
//            driver.get("http://www.baidu.com");
////            JavascriptExecutor jse = (JavascriptExecutor)driver;
//////            jse.executeScript("console.log('chenmin');");
//////            String asa = "var jquery = document.createElement('script');  \n" +
//////                    "jquery.src = 'https://code.jquery.com/jquery-3.3.1.min.js';\n" +
//////                    "document.getElementsByTagName('head')[0].appendChild(jquery);\n";
//////            jse.executeScript(asa);
////            Thread.sleep(3000);
////            String asa2 = "$(document).ready(function(){\n" +
////                    "  $(\"body\").append(\"123\");\n" +
////                    "});";
////            jse.executeScript(asa2);
//        WebDriverWait wait = new WebDriverWait(driver, 30);
//        driver.get("http://2018.ip138.com/ic.asp");
//        driver.get("http://www.baidu.com");
//            driver.get("http://www.qq.com");
//            //打印网页标题
//            System.out.println(driver.getTitle());
//            System.out.println(driver.getPageSource());
//            driver.get("https://www.baidu.com/s?wd=ip");
//香草开始
             driver.get("https://www.vanilla-air.com/hk/");
            //最大化浏览器窗口
            driver.manage().window().maximize();
            driver.findElement(By.id("oneWay")).click();
            driver.findElement(By.cssSelector("input.triggerDropdownMenus.startPlacement.validationInvalid")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[@id='reservation_point_select']/div[2]/div/ul/li[9]")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[@id='reservation_point_select']/div[3]/div/ul/li")).click();
            driver.findElement(By.id("edit-traveldate1-datepicker-popup-0")).click();
            driver.findElement(By.id("edit-traveldate1-datepicker-popup-0")).clear();
            driver.findElement(By.id("edit-traveldate1-datepicker-popup-0")).sendKeys("2018/12/04");
            driver.findElement(By.id("oneWay")).click();
            driver.findElement(By.id("edit-submit-ticket")).click();
//
//            Thread.sleep(5000);
            driver.findElement(By.cssSelector("div.vnl-search-submit > a")).click();
            Thread.sleep(3000);
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("var ajaxhook = document.createElement('script');  \n" +
                    "ajaxhook.src = 'https://unpkg.com/ajax-hook/dist/ajaxhook.min.js';\n" +
                    "document.getElementsByTagName('head')[0].appendChild(ajaxhook);");
            jse.executeScript("var div = $(\"<div></div>\");\n" +
                    "div.attr(\"id\",\"my_data\");\n" +
                    "$(\"body\").append(div);div.text(\"456\");\n");
            Thread.sleep(3000);
            jse.executeScript("\n" +
                    "hookAjax({\n" +
                    "  //拦截回调\n" +
                    "  onreadystatechange:function(xhr){\n" +
                    "    console.log(\"onreadystatechange called: %O\",xhr)\n" +
                    "\tconsole.log(\"onreadystatechange called1223: %O\",xhr.status)\n" +
                    "\t if(xhr.status == 200){\n" +
                    "\t\tconsole.log(\"onreadystatechange called1223142857: %O\",xhr.responseText)\n" +
                    "\t }\n" +
                    "  },\n" +
                    "  onload:function(xhr){\n" +
                    "    console.log(\"onload called: %O\",xhr)\n" +
                    "\tconsole.log(\"onload called:1223: %O\",xhr.status)\n" +
                    "\t if(xhr.status == 200){\n" +
                    "\t \n" +
                    "\t $(\"#my_data\").html(xhr.responseText)\n" +
                    "\t\tconsole.log(\"onload called:1223142857: %O\",xhr.responseText)\n" +
                    "\t }\n" +
                    "  },\n" +
                    "  //拦截方法\n" +
                    "  open:function(arg,xhr){\n" +
                    "    console.log(\"open called: method:%s,url:%s,async:%s\",arg[0],arg[1],arg[2])\n" +
                    "  }\n" +
                    "})");
            Thread.sleep(3000);
            driver.findElement(By.cssSelector("li.right > a.ng-binding")).click();
            Thread.sleep(30000);
            String title = (String) jse.executeScript("return $(\"#my_data\").html()");
            System.out.println("current page title get by js: " + title);
            screen((TakesScreenshot) driver);
//            driver.findElement(By.linkText("搜尋")).click();
//            Thread.sleep(20000);
//            System.out.println(driver.getTitle());
//            System.out.println(driver.getPageSource());
//            driver.findElement(By.cssSelector("li.right > a.ng-binding")).click();
//            Thread.sleep(1000);
//            JavascriptExecutor jse = (JavascriptExecutor)driver;
//            jse.executeScript("document.title='213'");
//            jse.executeScript("alert(1)");
//            driver.findElement(By.id("edit-promocode")).click();
//            driver.findElement(By.id("edit-promocode")).clear();
//            driver.findElement(By.id("edit-promocode")).sendKeys("213214124");
//            System.out.println("getText:");
//            WebElement d1 = driver.findElement(By.className("vnl-search-submit"));
//            d1.findElement(By.tagName("a")).click();
//            System.out.println(driver.findElement(By.className("vnl-search-submit").findElement(By.tagName("a"))).getText());
//            System.out.println("getTagName:");
//            System.out.println(driver.findElement(By.className("vnl-search-submit")).getTagName());

//            jse.executeScript("window.document.getElementById('oneWay').click()");
//            //searchCtl.search()
//            jse.executeScript("searchCtl.search()");

//            Thread.sleep(20000);

//            System.out.println(driver.findElement(By.cssSelector("div.vnl-search-submit > a")).getText());
        } catch (Exception e) {
            screen((TakesScreenshot) driver);
            e.printStackTrace();
        }

//        Interpreter interpreter = new Interpreter(); // 构造一个解释器
//
//        interpreter.set("driver",driver); //设置一个时间对象
//        JavascriptExecutor jse = (JavascriptExecutor)driver;
//        interpreter.set("jse",jse);
//
//        String line = "";
//        while(!line.equals("exit")){
//                System.out.print("chrome:>");
//                Scanner scan = new Scanner(System.in);
//                line = scan.nextLine();
//                if(line.equals("s")){
//                    screen((TakesScreenshot) driver);
//                    System.out.print("TakesScreenshot is  ok!");
//                }else{
//                    try {
//                        interpreter.eval(line);
//                    } catch (EvalError evalError) {
//                        evalError.printStackTrace();
//                    }
//                }
//        }
//        System.out.print("chrome:>");
//        Scanner scan = new Scanner(System.in);
//        String read = scan.nextLine();
//        System.out.println("输入数据："+read);

        //退出浏览器
        driver.quit();
        System.exit(0);
    }

    private static void screen(TakesScreenshot driver) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");  //转换时间格式
        String time = dateFormat.format(Calendar.getInstance().getTime());  //获取当前时间
        File srcFile = driver.getScreenshotAs(OutputType.FILE);  //执行屏幕截取
        FileUtils.copyFile(srcFile, new File("屏幕截图", time + ".png")); //利用FileUtils工具类的copyFile()方法保存getScreenshotAs()返回的文件;"屏幕截图"即时保存截图的文件夹
    }
}
