package jdbc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;

// DAO( Data Access Object ) ==> 데이터베이스에 연결하여 SQL구문(DDL , DML , DQL )을 실행시켜주는 객체

public class MemberDAO implements InterMemberDAO {
	//field 
	Connection conn ; // conn 과 pstmt 는 인스턴스 변수로 정의해줘야한다.
	// 인스턴스 변수는 초기화가 필요없다.
	PreparedStatement pstmt ;
	ResultSet rs ;
	// === 자원반납을 해주는 메소드
	private void close() {
		try {
			if(rs != null) {
				rs.close();
				rs = null ;
			}
			if(pstmt != null) {
				pstmt.close();
				pstmt = null ;
			}
			// Singleton 으로 만들고 여기서 conn 을 닫는다면, 
			// 이후에 , 연결을 다시 만들지 않기 때문에, 작동하지 않는다. 
		}catch(SQLException e) {	
			e.printStackTrace();
		}
		
	} // end of private void close() { -------------------------------------------

	@Override
	public int SignUp(Map<String, String> map1) {
		int n = 0 ;
		try {
		conn = MyDBConnection.getConn();
		
		String sql = " insert into tbl_member ( userseq, userid, passwd, name, mobile ) "
     		   + " values( userseq.nextval , ? , ? , ? , ? )" ;

			pstmt = conn.prepareStatement(sql);
			// 위의 문장은 SQLException을 던짐
			pstmt.setString(1, map1.get("USERID"));
			pstmt.setString(2, map1.get("PASSWD"));
			pstmt.setString(3, map1.get("NAME"));
			pstmt.setString(4, map1.get("MOBILE"));
			
			// INSERT 문 이므로 , executeUpdate() ;
			n = pstmt.executeUpdate();
			
			
		}catch (SQLIntegrityConstraintViolationException e) {
			// 제약조건에 위배됐을 경우 생기는 exception 
			if ( e.getErrorCode() == 1) { // primary key 와 unique key 에서 제약조건을 충족못시켰을 때
				System.out.println(">> 아이디가 중복되었습니다. 새로운 아이디를 입력하세요!!<<");
			}
			else {
				System.out.println("에러메시지 : "+ e.getMessage());
			} 
		}catch (SQLException e) {
			System.out.println("SQL문에 오류가 존재합니다.");
			e.printStackTrace(); 
		}finally {
			close();
		}
		
		return n;
	}

	
	


	

 

	
	
	
	
	
	
}
