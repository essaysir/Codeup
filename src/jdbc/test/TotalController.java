package jdbc.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;




public class TotalController implements InterTotalController {
	// field
	InterBoardDAO bdao = new BoardDAO() ;
	InterMemberDAO mdao = new MemberDAO() ;	
	// 시작메뉴 구현하기
	@Override
	public void menu_Start(Scanner sc) {
		String menu_no = "";
		do {
		System.out.println(">>>---- 시작메뉴 ----<<<\n");
		System.out.println("1.회원가입  2.로그인 3.프로그램종료");
		
		System.out.print("★메뉴번호선택: ");
		try {
		menu_no = sc.nextLine();
		Integer.parseInt(menu_no) ;
		}catch(NumberFormatException e) {
			System.out.println("숫자만 입력가능합니다.");
		}
		switch (menu_no) {
		case "1": // 회원가입
			SignUp(sc);
			break;
		case "2": // 로그인
			login(sc) ;
			break;
		case "3": // 프로그램종료
			break;
		default:
			System.out.println(" 선택하신 번호는 존재하지 않습니다.");
			break;
		}
		
		}while(!("3".equals(menu_no)));
		// while 문안의 조건문이 false 가 되면 반복문을 나온다.

	
	
	}//  end of 	public void menu_Start(Scanner sc) {

	// 회원가입하기 메소드 구현하기
	private void SignUp(Scanner sc) {
		System.out.println(">>> 회원가입 <<<");
		System.out.print("1. 아이디 : ");
		String userid = sc.nextLine() ;
		System.out.print("2. 암 호 : ");
		String passwd = sc.nextLine() ;
		System.out.print("3. 회원명 : ");
		String name = sc.nextLine() ;
		System.out.print("4. 연락처(휴대폰) : ");
		String mobile = sc.nextLine() ;
		
		// 선생님 풀이에는 MemberDTO 에 담고서 MemberDAO로 보냈다. 
		Map<String,String> map1 = new HashMap<>();
		map1.put("USERID", userid) ;
		map1.put("PASSWD", passwd) ;
		map1.put("NAME", name) ;
		map1.put("MOBILE", mobile);
		do {
		System.out.print(">> 회원가입을 정말로 하시겠습니까?[Y/N] ");
		String yn = sc.nextLine() ;
		if ("y".equalsIgnoreCase(yn)) {
			int n = mdao.SignUp(map1);
			if(n==1) {
				System.out.println("성공적으로 회원가입이 성공하셨습니다.");
				break;
			}
			else {
				System.out.println(" 회원가입이 실패하셨습니다. ");
				break ;
			}
		}
		else if ("n".equalsIgnoreCase(yn)) {
			System.out.println(" 회원가입을 취소하셨습니다.");
			break; 
		}
		else {
			System.out.println("Y 또는 N만 입력하세요");
		}
		}while(true) ;
	}// end of SignUp

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}// =================================================================================
