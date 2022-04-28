package jobKorea;

// 키워드 카운트

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

public class crawlingJOBKOREA3 {
	
	public static void main(String[] args) throws IOException {

		FileReader fr1 = new FileReader("C:\\Users\\501-10\\Desktop\\jobKorea\\jobKorea.csv");
		BufferedReader br1 = new BufferedReader(fr1);

		FileReader fr2 = new FileReader("C:\\Users\\501-10\\Desktop\\jobKorea\\jobKoreaITkeyword.csv");
		BufferedReader br2 = new BufferedReader(fr2);
		
		File file = new File("C:\\Users\\501-10\\Desktop\\jobKorea\\jobKoreaITkeywordCnt.csv"); // 이 파일을 열기, 없으면 새로 만듦
		BufferedWriter bw = new BufferedWriter (new FileWriter(file)); // 새로 만든 파일의 내용을 한 줄 씩 추가

		String br_text1;
		String br_text2;

		ArrayList <String> list1 = new ArrayList <>();
		ArrayList <String> list2 = new ArrayList <>();
		
		// 전체 데이터 파일을 배열에 저장
		while ((br_text1 = br1.readLine()) != null) { // 문장 하나를 읽고
			String[] textSplit = br_text1.split(",");// 쉼표에 따라 분해하고
			for (int j = 0; j < textSplit.length; j++) {// 분해된 문장의수 만큼 해당 명령을 실행
				//System.out.println(textSplit[j]);
				list1.add(textSplit[j]);
				if (textSplit[j].equals("")) {// 빈칸이면 건너뛰기 다음 문장 받기
					continue;
				}
			}
		}

		// 키워트 리스트를 배열에 저장
		while ((br_text2 = br2.readLine()) != null) { // 문장 하나를 읽고
			String[] textSplit = br_text2.split(",");// 쉼표에 따라 분해하고
			for (int j = 0; j < textSplit.length; j++) {// 분해된 문장의수 만큼 해당 명령을 실행
				//System.out.println(textSplit[j]);
				list2.add(textSplit[j]);
				if (textSplit[j].equals("")) {// 빈칸이면 건너뛰기 다음 문장 받기
					continue;
				}
			}
		}
		
		int[] cnt = new int[list2.size()];
		
		for (int i = 0; i < list1.size(); i++) {
			for (int j = 0; j < list2.size(); j++) {
				if(list1.get(i).contains(list2.get(j))) {
					cnt[j]++; 
				}
			}
		}
		
		for (int i = 0; i < cnt.length; i++) {
			//System.out.println(list2.get(i)+ "  " + cnt[i]);
			bw.write(list2.get(i));
			bw.write(",");
			bw.write(String.valueOf(cnt[i]));
			bw.newLine();
		}

		br1.close();
		br2.close();
		bw.close();
		
	}
}
