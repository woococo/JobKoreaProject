package jobKorea;

// Ű���� ī��Ʈ

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
		
		File file = new File("C:\\Users\\501-10\\Desktop\\jobKorea\\jobKoreaITkeywordCnt.csv"); // �� ������ ����, ������ ���� ����
		BufferedWriter bw = new BufferedWriter (new FileWriter(file)); // ���� ���� ������ ������ �� �� �� �߰�

		String br_text1;
		String br_text2;

		ArrayList <String> list1 = new ArrayList <>();
		ArrayList <String> list2 = new ArrayList <>();
		
		// ��ü ������ ������ �迭�� ����
		while ((br_text1 = br1.readLine()) != null) { // ���� �ϳ��� �а�
			String[] textSplit = br_text1.split(",");// ��ǥ�� ���� �����ϰ�
			for (int j = 0; j < textSplit.length; j++) {// ���ص� �����Ǽ� ��ŭ �ش� ����� ����
				//System.out.println(textSplit[j]);
				list1.add(textSplit[j]);
				if (textSplit[j].equals("")) {// ��ĭ�̸� �ǳʶٱ� ���� ���� �ޱ�
					continue;
				}
			}
		}

		// Ű��Ʈ ����Ʈ�� �迭�� ����
		while ((br_text2 = br2.readLine()) != null) { // ���� �ϳ��� �а�
			String[] textSplit = br_text2.split(",");// ��ǥ�� ���� �����ϰ�
			for (int j = 0; j < textSplit.length; j++) {// ���ص� �����Ǽ� ��ŭ �ش� ����� ����
				//System.out.println(textSplit[j]);
				list2.add(textSplit[j]);
				if (textSplit[j].equals("")) {// ��ĭ�̸� �ǳʶٱ� ���� ���� �ޱ�
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
