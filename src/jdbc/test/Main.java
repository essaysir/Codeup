package jdbc.test;

import java.util.Scanner;

public class Main {
// 우선적으로 해야하는 것. 오라클을 불러오기위해서는 referenced librarise 에서
// ojdbc8.jar를 만들어야한다. 만드는 방법은 다음과 같다.
// 1. 프로젝트 우클릭 => 2. BuildPath  3. add external jars
// 4. 오라클이 설치된 폴더에서 들어가서, ojdbc8.jar를 찾으면 된다. 
// C:\OracleXE18C\product\18.0.0\dbhomeXE\jdbc\lib 
	 
	public static void main(String[] args) {
		
		InterTotalController ctrl = new TotalController() ;
		Scanner sc = new Scanner(System.in) ;
		
		ctrl.menu_Start(sc) ;
		
		sc.close();
		System.out.println("~~~ 프로그램 종료 ~~~");
		
		
		
		
		
	}// end of main() ---------------------------------

}
