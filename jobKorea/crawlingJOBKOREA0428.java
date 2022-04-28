package jobKorea;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class crawlingJOBKOREA0428 {
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromeDriver\\chromedriver.exe";

	static HashSet<String> keywordHashSet = new HashSet<String>();
	static List requestList = new ArrayList();

	static void sleep(int a) {
		try {
			Thread.sleep(a * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated
		}
	}

	static void click(WebDriver a, String b) {
		a.findElement(By.xpath(b)).click();
	}

	static String getText(WebDriver a, String b) {
		String c = a.findElement(By.xpath(b)).getText();
		return c;
	}

	static String getAttribute(WebDriver a, String b, String c) {
		String text = a.findElement(By.xpath(b)).getAttribute(c);
		return text;
	}

	static void getKeyHashSet(String a) {
		String[] split = a.split("\n");

		for (int j = 1; j < split.length; j++) {
			int pose = 0;
			for (int i = 0; i < split[j].length(); i++) {
				if (split[j].toCharArray()[i] == '(') {
					pose = i;
					break;
				}
			}
			keywordHashSet.add(split[j].substring(0, pose));
		}
	}

	public static void main(String[] args) throws IOException {
		try {
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ChromeOptions options = new ChromeOptions();
		WebDriver driver = new ChromeDriver(options);

		File file = new File("C:\\Users\\501-10\\Desktop\\jobKorea\\jobKorea.csv"); // 이 파일을 열기, 없으면 새로 만듦
		BufferedWriter bw = new BufferedWriter (new FileWriter(file)); // 새로 만든 파일의 내용을 한 줄 씩 추가
		
		int cnt = 1;
		ArrayList <String> list = new ArrayList <>();
		
		
		while (true) {
			String webAddress = "https://www.jobkorea.co.kr/recruit/joblist?menucode=duty&dutyCtgr=10016#anchorGICnt_";
			driver.get(webAddress + cnt);
			sleep(3);
			
			if (cnt == 1) {
								
				click(driver, "/html/body/div[6]/div[1]/div/div[2]/div[1]/div[2]/div/div[1]/dl[2]/dt/p"); //근무지역 탭
				click(driver, "/html/body/div[6]/div[1]/div/div[2]/div[1]/div[2]/div/div[1]/dl[2]/dd[2]/div[2]/dl[1]/dd/div[1]/ul/li[1]/label/span/span");//서울
				click(driver, "/html/body/div[6]/div[1]/div/div[2]/div[1]/div[2]/div/div[1]/dl[2]/dd[2]/div[2]/dl[1]/dd/div[1]/ul/li[2]/label/span/span");//경기
				click(driver, "/html/body/div[6]/div[1]/div/div[2]/div[1]/div[2]/div/div[1]/dl[2]/dd[2]/div[2]/dl[1]/dd/div[1]/ul/li[3]/label/span/span");//인천
				click(driver, "/html/body/div[6]/div[1]/div/div[2]/div[1]/div[2]/div/div[1]/dl[3]/dt/p");//경력
				click(driver, "/html/body/div[6]/div[1]/div/div[2]/div[1]/div[2]/div/div[1]/dl[3]/dd/div[1]/ul[1]/li[1]/label/span/span");//신입
				click(driver, "/html/body/div[6]/div[1]/div/div[2]/div[1]/div[2]/div/div[1]/dl[4]/dd/div[1]/ul/li[1]/label/span/span");//대졸
				click(driver, "/html/body/div[6]/div[1]/div/div[2]/div[1]/div[2]/div/div[3]/div/dl[1]/dd[2]/button/span");//조건검색
				sleep(5);
			}
//			System.out.println(getText(driver, "/html/body/div[6]/div[1]/div/div[2]/div[4]/div/div[5]"));
			cnt++;
			sleep(5);
			
			String getText = getText(driver, "/html/body/div[6]/div[1]/div/div[2]/div[4]/div/div[5]");
			
			String[] list2 = getText.split("\n");
			
			for(int i = 0; i < list2.length; i++) {
				
				if (list2[i].contains(",") && !list2[i].contains("만원")) {
					list.add(getText);
					
					bw.write(list2[i]);
					bw.newLine();
				}
			}
			
			if (getText.contains("채용정보 검색결과가 존재하지 않습니다.")) {
				break;
			}
		}
		
		bw.close();
	}
}
