package codeup;

import java.util.Scanner;

public class codeup_1 {

	public static void main(String[] args) {
		//1001.
		//printf()를 이용해 다음 단어를 출력하시오.
		//Hello
		System.out.println("Hello");
		
		//1002.
//		이번에는 공백()을 포함한 문장을 출력한다.
//		다음 문장을 출력해보자.
//
//		Hello World
//		(대소문자에 주의한다.)
		System.out.println("Hello World");
		
		
//		1003.
//		이번에는 줄을 바꿔 출력하는 출력문을 연습해보자.
//		다음과 같이 줄을 바꿔 출력해야 한다.
//
//		Hello
//		World
//		(두 줄에 걸쳐 줄을 바꿔 출력)
		System.out.println("Hello \n World");
		
//		1004.
//		이번에는 작은 따옴표(single quotation mark)가 들어있는
//		특수한 형태의 출력문에 대한 연습을 해보자.
//
//		다음 문장을 출력하시오.
//
//		'Hello'
		System.out.println("'Hello'");
		
//		1005.
//		이번에는 큰따옴표(double quotation mark)가 포함된 출력문을 연습해보자.
//
//		다음 문장을 출력하시오.
//
//		"Hello World"
//		(단, 큰따옴표도 함께 출력한다.)
		System.out.println("\"Hello World\"");
		
//		1006.
//		이번에는 특수문자 출력에 도전하자!!
//
//		다음 문장을 출력하시오.
//
//		"!@#$%^&*()"
//		(단, 큰따옴표도 함께 출력한다.)
		System.out.printf("\"!@#$^&()%d%%\"",20);
		
		// printf 를 사용하여 20%를 나타내고 싶을 경우에 다음과 같이, %%를 사용하면 된다.
		
		System.out.println();
//		1007.
//		윈도우 운영체제의 파일 경로를 출력하는 연습을 해보자.		 
//		파일 경로에는 특수문자들이 포함된다.
//		다음 경로를 출력하시오.
//
//		"C:\Download\hello.cpp"
//		(단, 큰따옴표도 함께 출력한다.)
		System.out.println("\"C:\\Download\\hello.cpp\"");
		
		// /(역슬래쉬)를 프린트하고 싶은 경우에는 \\ 두번 입력하면 된다.
		
//		1008.
//		이번에는 특수문자를 출력하는 연습을 해보자.
//
//		키보드로 입력할 수 없는 다음 모양을 출력해보자.
//		(** 참고 : 운영체제의 문자 시스템에 따라 아래와 같은 모양이 출력되지 않을 수 있다.)
//
//		┌┬┐
//		├┼┤
//		└┴┘
		System.out.println("┌┬┐");
		System.out.println("├┼┤");
		System.out.println("└┴┘");
		
//		1010.
//		정수형(int)으로 변수를 선언하고, 변수에 정수값을 저장한 후
//		변수에 저장되어 있는 값을 그대로 출력해보자.

		int i = 100 ;
		System.out.println(i);
		
//		1011.		
//		문자형(char)으로 변수를 하나 선언하고, 변수에 문자를 저장한 후
//		변수에 저장되어 있는 문자를 그대로 출력해보자.
		
		char ch = 'A' ; // 'A'는 65 , 'a'는 97
		System.out.println(ch);
		System.out.println((int)ch); // 문자를 정수로 나타내는 방법.
		
//		1012.
//		실수형(float)로 변수를 선언하고 그 변수에 실수값을 저장한 후
//		저장되어 있는 실수값을 출력해보자.
		
		float ft = 3.141592f; 
		System.out.println(ft);
		// 자바에서 실수는 기본값으로 double이다.
		// 그러하여, float으로 나타내주기위해서는, 앞의 (flaot)를 붙이거나, 뒤에 f 또는 F를 붙여야 한다.
		
		//강제 형변환(casting)
		//→ 데이터타입의 크기가 큰것을 작은것으로 강제적으로 형변환 시키는 것을 말한다.
		
//		1013.
//		정수(int) 2개를 입력받아 그대로 출력해보자.
		int x=4 , y=5 ; // 여러개를 정의할 경우 다음과 같이 할 수 있다.
		System.out.println(x+" "+y);
		// 자바에서 인트타입과 string 타입이 합쳐지면 String 타입으로 판단하여 처리되기 때문에
		// 가운데에 String 타입을 넣어준것이다.
		// 단순히 (x+y)를 할 경우에는 둘이 더한 값이 나타난다.
		
		
//		1014.
//		2개의 문자(ASCII CODE)를 입력받아서 순서를 바꿔 출력해보자.
		
//		1015.
//		실수(float) 1개를 입력받아 저장한 후,
//		저장되어 있는 값을 소수점 셋 째 자리에서 반올림하여
//		소수점 이하 둘 째 자리까지 출력하시오.
		float ft1 = 3.141592f ;
		System.out.println((Math.round(ft1*100))/100.0);
		
//		1017.
//		int형 정수 1개를 입력받아 공백을 사이에 두고 3번 출력해보자.
		int x1 = 10 ;
		System.out.println(x1+" "+x1+""+x1);
		
		
//		1018.
//		어떤 형식에 맞추어 시간이 입력될 때, 그대로 출력하는 연습을 해보자.

//		1019.
//		년, 월, 일을 입력받아 지정된 형식으로 출력하는 연습을 해보자.

		
//		1020.
//		주민번호는 다음과 같이 구성된다.
//
//		XXXXXX-XXXXXXX
//
//		앞의 6자리는 생년월일(yymmdd)이고 뒤 7자리는 성별, 지역, 오류검출코드이다.
//		주민번호를 입력받아 형태를 바꿔 출력해보자.
		System.out.print("주민번호를 입력해주세여. EX)XXXXXX-XXXXXXX ");
		Scanner sc = new Scanner(System.in) ;
		
		
		
		
	}// END OF MAIN METHOD ==============================================

}
 