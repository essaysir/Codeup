package codeup;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class codeup_2 {

	public static void main(String[] args) {
//		1024.
//		단어를 1개 입력받는다.
//		입력받은 단어(영어)의 각 문자를 한줄에 한문자씩 분리해 출력한다.
		
		Scanner sc = new Scanner(System.in) ;
		System.out.print("단어를 하나 입력하세요");
		String str_1 = sc.nextLine() ;
		char[]strArr = str_1.toCharArray() ;
		for (int i=0;i<strArr.length ; i++) {
			System.out.println(strArr[i]);
		}
		
		
//		1025.
//		다섯 자리의 정수 1개를 입력받아 각 자리별로 나누어 출력한다.
		System.out.println("다섯 자리의 정수 1개를 입력하세요");
		String str_2 = sc.nextLine() ;
		Pattern p = Pattern.compile("^[1-9][0-9]{4}$");
		Matcher m = p.matcher(str_2);
		boolean bool= m.matches() ;
		
		if( !bool ) {
			System.out.println("조건에 맞지 않는 정수를 입력하셨습니다.");
		}
		else {
			char[]strArr_1 = str_2.toCharArray() ;
			for(int i=0 ; i<strArr_1.length ; i++) {
				System.out.println(strArr_1[i]*(Math.pow(10, 4-i)));
			}
		}
		
//		1026.
//		입력되는 시:분:초에서 분만 출력해보자
		
		
		
//		1027.
//		년월일을 출력하는 방법은 나라마다, 형식마다 조금씩 다르다.
//		년월일(yyyy.mm.dd) 를 입력받아,
//		일월년(dd-mm-yyyy)로 출력해보자.
		
		
		
		
		
		
		sc.close();
	}

}
