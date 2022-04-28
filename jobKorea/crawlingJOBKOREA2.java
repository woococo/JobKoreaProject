package jobKorea;

// 키워드 추출

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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

public class crawlingJOBKOREA2 {
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\chromeDriver\\chromedriver.exe";
	
	static HashSet<String> keywordHashSet = new HashSet<String>();
	static List requestList = new ArrayList();
	
	static StringBuffer sb = new StringBuffer();
	static String[] abilList = {"Java","C++","Python","Linux","빅데이터","데이터분석"};
	
	static void click(WebDriver a, String b) {
		a.findElement(By.xpath(b)).click();
	}

	static String getText(WebDriver a, String b) {
		String c = a.findElement(By.xpath(b)).getText();
		return c;
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
	
	static void sleep(int a) {
		try {
			Thread.sleep(a * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated
		}
	}
	
	static void onlyName(String a) {
		String[] b = a.split("\n");
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length(); j++) {
				if (b[i].toCharArray()[j] == '(') {
					keywordHashSet.add(b[i].substring(0, j).trim());
				}
			}
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
		
		FileReader fr = new FileReader("C:\\Users\\501-10\\Desktop\\jobKorea\\jobKorea.csv");
		BufferedReader br = new BufferedReader(fr);

		File file = new File("C:\\Users\\501-10\\Desktop\\jobKorea\\jobKoreaITkeyword.csv"); // 이 파일을 열기, 없으면 새로 만듦
		BufferedWriter bw = new BufferedWriter (new FileWriter(file)); // 새로 만든 파일의 내용을 한 줄 씩 추가
		
		bw.write("keyword, count"); bw.newLine();
		
		String webAddress = "https://www.jobkorea.co.kr/recruit/joblist?menucode=duty&dutyCtgr=10016";
		driver.get(webAddress);
		sleep(3);
		
		for (int i = 1; i < 18; i++) {
			click(driver, "/html/body/div[6]/div[1]/div/div[2]/div[1]/div[2]/div/div[1]/dl[1]/dd[2]/div[2]/dl[2]/dd/div[1]/ul/li[" + i + "]/label/span");
			String getKeyword = getText(driver, "/html/body/div[6]/div[1]/div/div[2]/div[1]/div[2]/div/div[1]/dl[1]/dd[2]/div[2]/dl[3]/dd[2]/div[1]");
			onlyName(getKeyword);
		}
		
		requestList = new ArrayList(keywordHashSet);
		
		int[] keywordCount = new int[requestList.size()];
		for (int i = 0; i < requestList.size(); i++) {
			System.out.println(requestList.get(i));
			
		}

		String br_text;
		while ((br_text = br.readLine()) != null) { //문장 하나를 읽고
			String[] textSplit = br_text.split(",");//쉼표에 따라 분해하고
			for (int j = 0; j < textSplit.length; j++) {//분해된 문장의수 만큼 해당 명령을 실행
				if (textSplit[j].equals("")) {//빈칸이면 건너뛰기 다음 문장 받기
					continue;
				}
				for (int i = 0; i < requestList.size(); i++) {//
					if (textSplit[j].contains(requestList.get(i).toString()));{
						keywordCount[i]++;
					//	System.out.println(textSplit[j]+ " "+ requestList.get(i) + " " + keywordCount[i]); sleep(1);
						break;
					}
				}
			}
		}
		
		
		for (int i = 0; i < requestList.size(); i++) {
			StringBuffer sb = new StringBuffer();
			sb.append(requestList.get(i) + ",");
			sb.append(keywordCount[i]);
//			System.out.println(requestList.get(i) + " " + keywordCount[i]);
			
			bw.write(sb.toString()); bw.newLine(); 
		}
		bw.close();
	}

}
