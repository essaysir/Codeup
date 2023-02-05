package test;

import java.util.Scanner;

public class Test_1 {

	public static void main(String[] args) {
	

	//[2차원 배열 출력]
	//1 2 3 4
	//5 6 7 8
	//9 10 11 12
	//[2차원 가변 배열 출력]
	//1 2 3 4 5 6
	//7 8 9 10
	//11 12 13 14 15
		int [][] intArr_1 = new int[][]{
				{1,2,3,4} ,
				{5,6,7,8} ,
				{9,10,11,12}
				};
		int[][] intArr_2 = {{11,12,13} , {14,15,16,17,18}, {19,20} } ;
		
		for(int x=0 ; x< intArr_1.length ; x++) {
			for (int y=0 ; y<intArr_1[x].length ; y++) {
				System.out.print(intArr_1[x][y]);
			}
			System.out.println(); // 행마다 한줄씩 띄우기 위해서
		}
		
		System.out.println("\n==================================\n");
		
		for(int x=0 ; x< intArr_2.length ; x++) {
			for (int y=0 ; y<intArr_2[x].length ; y++) {
				System.out.print(intArr_2[x][y]);
			}
			System.out.println(); // 행마다 한줄씩 띄우기 위해서
		}
		
		System.out.println("\n==================================\n");
		Scanner sc = new Scanner(System.in) ;
		int[][] intArr_3 = new int[2][3] ;
		// 2행 3열 
		
//		System.out.print("정수를 입력하세요 : ");
//		intArr_3[0][1] = sc.nextInt() ;
//		sc.nextLine() ;
//		
//		System.out.print("정수를 입력하세요 : ");
//		intArr_3[0][2] = sc.nextInt() ;
//		sc.nextLine() ;
//		
//		System.out.print("정수를 입력하세요 : ");
//		intArr_3[0][3] = sc.nextInt() ;
//		sc.nextLine() ;
//		
//		System.out.print("정수를 입력하세요 : ");
//		intArr_3[1][1] = sc.nextInt() ;
//		sc.nextLine() ;
//		
//		System.out.print("정수를 입력하세요 : ");
//		intArr_3[1][2] = sc.nextInt() ;
//		sc.nextLine() ;
//		
//		System.out.print("정수를 입력하세요 : ");
//		intArr_3[1][3] = sc.nextInt() ;
//		sc.nextLine() ;
//		
		// int[][] a = new int[2][3] ;
		
		// [0,1]	[0,2]	[0,3]
		// [1,1]	[1,2]	[1,3]
		
		// int[][] a = new int[3][2] ;
		
		// [0,1]	[0,2]	
		// [1,1]	[1,2]	
		// [2,1]	[2,2]
		
		// 위처럼 하나씩 입력하는 방법을 생각했었으나, 위를 보니, 반복되는 부분이 엄청많았고
		// 이를 반복문으로 해결 할 수 있다고 생각이 들어 밑에와 같이 바꾸게 됨.
		int[] sumArr = new int[3];
		for (int x=0 ; x<intArr_3.length ; x++) {
			for(int y=0 ; y<intArr_3[x].length ; y++) {
				System.out.print("정수를 입력하세요 : ");
				intArr_3[x][y] = sc.nextInt() ;
				sc.nextLine() ;
				sumArr[x] += intArr_3[x][y] ;
			}
		}
		System.out.println(sumArr[0]);
		System.out.println(sumArr[1]);
		
		sc.close();
	}// END OF MAIN METHOD ================================================

}
