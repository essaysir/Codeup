show user ; -- USER이(가) "JDBC_USER"입니다.

select * 
from tbl_member 
order by userseq desc ;

update tbl_member set status = 1 ;
commit ;

desc tbl_member ;

-- 이름          널?       유형            
------------- -------- ------------- 
-- USERSEQ     NOT NULL NUMBER        
-- USERID      NOT NULL VARCHAR2(30)  
-- PASSWD      NOT NULL VARCHAR2(30)  
-- NAME        NOT NULL NVARCHAR2(20) 
-- MOBILE               VARCHAR2(20)  
-- POINT                NUMBER(10)    
-- REGISTERDAY          DATE          
-- STATUS               NUMBER(1)      

------ **** 게시판 테이블 생성하기 **** ------
create table tbl_board
(boardno number not null        -- 글번호
,fk_userid  varchar2(30) not null       -- 작성자아이디 -- 위의 tbl_member 와 자료 유형도 똑같이 한것
,subject   Nvarchar2(100) not null       -- 글제목
,contents Nvarchar2(200) not null
,writeday date default sysdate not null  -- 작성일자
,viewcount number default 0 not null     -- 조회수   
,boardpasswd varchar2(20) not null       -- 글암호
, constraint PK_tbl_board_boardno primary key (boardno)
, constraint FK_tbl_board_fk_userid foreign key (fk_userid)
references tbl_member(userid) ); -- Table TBL_BOARD이(가) 생성되었습니다.

create sequence seq_board
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache ; -- Sequence SEQ_BOARD이(가) 생성되었습니다.

desc tbl_board ;

create table tbl_comment
( commentno number                not null   -- 댓글번호
, fk_boardno number               not null   -- 원글의 글번호
, fk_userid  varchar2(30)         not null   -- 작성자 아이디
, contents   Nvarchar2(100)       not null   -- 댓글 내용
, writeday   date default sysdate not null   -- 작성일자
, constraint PK_tbl_comment_commentno primary key (commentno)
, constraint FK_tbl_comment_fk_boardno foreign key(fk_boardno) 
references tbl_board(boardno) on delete cascade 
, constraint FK_tbl_comment_fk_userid foreign key(fk_userid) 
references tbl_member(userid) ) ;
-- Table TBL_COMMENT이(가) 생성되었습니다.

create sequence seq_comment
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache ; -- Sequence SEQ_COMMENT(가) 생성되었습니다.

select *
from tbl_comment ;

update tbl_member set status = 0
where userid = 'sonjh' ;

select *
from tbl_member ;

----------------------------------------------------------------------------------------

-- Transaction(트랜잭션) 처리 실습을 위해서
-- tbl_member 테이블의 point 컬럼의 값은 최대 30을 넘지 못하도록 check 제약을 걸도록 하겠습니다.
alter table tbl_member
add constraint CK_tbl_member_point check ( point between 0 and 30 ) ;
-- Table TBL_MEMBER이(가) 변경되었습니다.

----------------------------------------------------------------------------------------

update tbl_member set point = 40
where userid = 'leess' ;
-- ORA-02290: 체크 제약조건(JDBC_USER.CK_TBL_MEMBER_POINT)이 위배되었습니다

select *
from tbl_board ;

select *
from tbl_comment ;

select fk_boardno ,count(*)
from tbl_comment 
group by fk_boardno;

insert into tbl_comment ( commentno , fk_boardno , fk_userid, contents )
values ( seq_comment.nextval , 1 , 'eomjh' , '글이 너무 좋아요') ; 

insert into tbl_comment ( commentno , fk_boardno , fk_userid, contents )
values ( seq_comment.nextval , 2 , 'eomjh' , '또 씁니다..글이 너무 좋아요') ; 

insert into tbl_comment ( commentno , fk_boardno , fk_userid, contents )
values ( seq_comment.nextval , 2 , 'eomjh' , 'ㅠㅠㅠ..글이 너무 좋아요') ; 
commit ;
-- 글번호      글제목     작성자명      작성일자   조회수  

-- 내 풀이 --
select B.boardno ,  B.subject || '[' || nvl(A.countA , 0) || ']' AS 제목 
, M.name , writeday , viewcount
from tbl_board B
LEFT JOIN 
(select fk_boardno, count(*) as countA from tbl_comment group by fk_boardno)A
ON B.boardno = A.fk_boardno
JOIN tbl_member M
ON B.fk_userid = M.userid;

-- 선생님 풀이
SELECT boardno 
    , subject 
    , name 
    , writeday 
    , viewcount 
    , nvl(commentcnt,0) as commentcnt 
FROM
(
select B.boardno , B.subject , M.name 
, to_char(B.writeday, 'yyyy-mm--dd hh24:mi:ss') as writeday , B.viewcount
from tbl_board B JOIN tbl_member M
ON B.fk_userid = M.userid
)A LEFT JOIN
(
select fk_boardno , count(*) AS commentcnt
from tbl_comment
group by fk_boardno 
) CMT
ON A.boardno = CMT.fk_boardno
ORDER BY A.boardno desc ;


SELECT A.boardno AS 글번호
    , A.subject || '[' || nvl(CMT.commentcnt,0) || ']' AS 글제목
    , A.name AS 작성자명
    , A.writeday as 작성일자
    , A.viewcount as 조회수

FROM
(
select B.boardno , B.subject , M.name 
, to_char(B.writeday, 'yyyy-mm--dd hh24:mi:ss') as writeday , B.viewcount
from tbl_board B JOIN tbl_member M
ON B.fk_userid = M.userid
)A LEFT JOIN
(
select fk_boardno , count(*) AS commentcnt
from tbl_comment
group by fk_boardno 
) CMT
ON A.boardno = CMT.fk_boardno
ORDER BY A.boardno desc ;
-- 우클릭후 포함 표시 
String sql = "SELECT boardno \n"+
"    , subject \n"+
"    , name \n"+
"    , writeday \n"+
"    , viewcount \n"+
"    , nvl(commentcnt,0) as commentcnt \n"+
"FROM\n"+
"(\n"+
"select B.boardno , B.subject , M.name \n"+
", to_char(B.writeday, 'yyyy-mm--dd hh24:mi:ss') as writeday , B.viewcount\n"+
"from tbl_board B JOIN tbl_member M\n"+
"ON B.fk_userid = M.userid\n"+
")A LEFT JOIN\n"+
"(\n"+
"select fk_boardno , count(*) AS commentcnt\n"+
"from tbl_comment\n"+
"group by fk_boardno \n"+
") CMT\n"+
"ON A.boardno = CMT.fk_boardno\n"+
"ORDER BY A.boardno desc";

------------------------------------------------------------

String sql = "select subject ,contents,fk_userid,boardpasswd\n"+
"from tbl_board\n"+
"where boardno = 1"; ;

String sql = "update tbl_board set viewcount = viewcount +1 \n"+
"where boardno = 1 ; ";

select *
from tbl_board ;
rollback ;

insert into tbl_comment ( commentno , fk_boardno , fk_userid, contents )
values ( seq_comment.nextval , 1 , 'eomjh' , '좋은하루보내세요~^^!') ; 
commit ; -- 커밋 완료.

-- 내풀이
select M.name , contents , to_char(writeday , 'yyyy-mm-dd hh24:mi:ss') as writeday 
from tbl_comment C
JOIN tbl_member M 
ON C.fk_userid = M.userid;

select userid , name
from tbl_member ;

-- 선생님 풀이
SELECT contents , name , writeday
FROM
(select fk_userid , contents, to_char(writeday , 'yyyy-mm-dd hh24:mi:ss') as writeday 
from tbl_comment where fk_boardno = 2 ) A
JOIN
(select userid , name 
from tbl_member ) B
ON A.fk_userid = B.userid 

String sql = "SELECT contents , name , writeday\n"+
"FROM\n"+
"(select fk_userid , contents, to_char(writeday , 'yyyy-mm-dd hh24:mi:ss') as writeday \n"+
"from tbl_comment where fk_boardno = 2 ) A\n"+
"JOIN\n"+
"(select userid , name \n"+
"from tbl_member ) B\n"+
"ON A.fk_userid = B.userid ";

----------------------------------------------------------------------------------------

-- Transaction(트랜잭션) 처리 실습을 위해서
-- tbl_member 테이블의 point 컬럼의 값은 최대 30을 넘지 못하도록 check 제약을 걸도록 하겠습니다.
-- 이제는 Transaction(트랜잭션)이 무엇인지 알았으니 위에서 준 check 제약을 삭제하겠습니다. 
alter table tbl_member
drop constraint CK_tbl_member_point ; -- Table TBL_MEMBER이(가) 변경되었습니다.

-- 사용자가 다른사용자가 쓴 원글에 댓글쓰기를 하면 포인트 점수를 5점씩 증가하도록 하겠습니다.


----------------------------------------------------------------------------------------

delete from tbl_comment ; -- 4개 행 이(가) 삭제되었습니다.
commit ;
select *
from tbl_comment ;

            ---- *** ==== 최근 1주일간 일자별 게시글 작성건수 ==== *** ----
    
    select *
    from tbl_board
    order by boardno asc ;
    
    insert into tbl_board(boardno, fk_userid, subject, contents, boardpasswd)
    values(seq_board.nextval, 'eomjh', '첫번째 엄정화가 글을 씁니다.', '안녕하세요~~ 엄정화 입니다.', '1234');

    insert into tbl_board(boardno, fk_userid, subject, contents, boardpasswd)
    values(seq_board.nextval, 'eomjh', '두번째 엄정화가 글을 씁니다.', '안녕하세요~~ 엄정화 입니다.', '1234');

    insert into tbl_board(boardno, fk_userid, subject, contents, boardpasswd)
    values(seq_board.nextval, 'eomjh', '세번째 엄정화가 글을 씁니다.', '안녕하세요~~ 엄정화 입니다.', '1234');

    insert into tbl_board(boardno, fk_userid, subject, contents, boardpasswd)
    values(seq_board.nextval, 'eomjh', '네번째 엄정화가 글을 씁니다.', '안녕하세요~~ 엄정화 입니다.', '1234');

    insert into tbl_board(boardno, fk_userid, subject, contents, boardpasswd)
    values(seq_board.nextval, 'eomjh', '다섯번째 엄정화가 글을 씁니다.', '안녕하세요~~ 엄정화 입니다.', '1234');

    insert into tbl_board(boardno, fk_userid, subject, contents, boardpasswd)
    values(seq_board.nextval, 'eomjh', '여섯번째 엄정화가 글을 씁니다.', '안녕하세요~~ 엄정화 입니다.', '1234');

    insert into tbl_board(boardno, fk_userid, subject, contents, boardpasswd)
    values(seq_board.nextval, 'eomjh', '일곱번째 엄정화가 글을 씁니다.', '안녕하세요~~ 엄정화 입니다.', '1234');

    insert into tbl_board(boardno, fk_userid, subject, contents, boardpasswd)
    values(seq_board.nextval, 'eomjh', '여덟번째 엄정화가 글을 씁니다.', '안녕하세요~~ 엄정화 입니다.', '1234');

    commit ;
    
    update tbl_board set writeday = writeday - 7 where boardno in (1,3) ;
    
    update tbl_board set writeday = writeday - 1 where boardno in ( 8,9,10 ) ;
    
    select *
    from tbl_board
    where writeday between sysdate-7 and sysdate ;
    -- 위의 문제점은 날짜 계산 방식에 맞지 않다는 것이다. 현재 시각으로 부터 7일전을 의미한다.
    
    select to_date(to_char(writeday,'yyyy-mm-dd')) as writeday , count(*)
    from tbl_board
    where to_date(to_char(sysdate,'yyyy-mm-dd')) - to_date(to_char(writeday , 'yyyy-mm-dd')) < 8 
    group by to_date(to_char(writeday,'yyyy-mm-dd')) ;
    
    select writeday 
    ,decode(to_date(to_char(sysdate,'yyyy-mm-dd')) - to_date(to_char(writeday , 'yyyy-mm-dd')),6,1,0) as PREVIOUS 
    ,decode(to_date(to_char(sysdate,'yyyy-mm-dd')) - to_date(to_char(writeday , 'yyyy-mm-dd')),5,1,0) as PREVIOUS 
    ,decode(to_date(to_char(sysdate,'yyyy-mm-dd')) - to_date(to_char(writeday , 'yyyy-mm-dd')),4,1,0) as PREVIOUS 
    ,decode(to_date(to_char(sysdate,'yyyy-mm-dd')) - to_date(to_char(writeday , 'yyyy-mm-dd')),3,1,0) as PREVIOUS
    ,decode(to_date(to_char(sysdate,'yyyy-mm-dd')) - to_date(to_char(writeday , 'yyyy-mm-dd')),2,1,0) as PREVIOUS 
    ,decode(to_date(to_char(sysdate,'yyyy-mm-dd')) - to_date(to_char(writeday , 'yyyy-mm-dd')),1,1,0) as PREVIOUS 
    ,decode(to_date(to_char(sysdate,'yyyy-mm-dd')) - to_date(to_char(writeday , 'yyyy-mm-dd')),0,1,0) as TODAY 
    from tbl_board 
    where to_date(to_char(sysdate,'yyyy-mm-dd')) - to_date(to_char(writeday , 'yyyy-mm-dd')) < 8
    order by writeday desc ;
    
    
    
    select *
    from tbl_board
    where writeday between sysdate-7 and sysdate ;
    -- 위의 문제점은 날짜 계산 방식에 맞지 않다는 것이다. 현재 시각으로 부터 7일전을 의미한다.
    
    select to_date(to_char(writeday,'yyyy-mm-dd')) as writeday , count(*)
    from tbl_board
    where to_date(to_char(sysdate,'yyyy-mm-dd')) - to_date(to_char(writeday , 'yyyy-mm-dd')) < 7 
    group by to_date(to_char(writeday,'yyyy-mm-dd')) ;
    
    select count(writeday) AS TOTAL
    ,sum(decode(to_date(to_char(sysdate,'yyyy-mm-dd')) - to_date(to_char(writeday , 'yyyy-mm-dd')),6,1,0)) as PREVIOUS 
    ,sum(decode(to_date(to_char(sysdate,'yyyy-mm-dd')) - to_date(to_char(writeday , 'yyyy-mm-dd')),5,1,0)) as PREVIOUS 
    ,sum(decode(to_date(to_char(sysdate,'yyyy-mm-dd')) - to_date(to_char(writeday , 'yyyy-mm-dd')),4,1,0)) as PREVIOUS 
    ,sum(decode(to_date(to_char(sysdate,'yyyy-mm-dd')) - to_date(to_char(writeday , 'yyyy-mm-dd')),3,1,0)) as PREVIOUS
    ,sum(decode(to_date(to_char(sysdate,'yyyy-mm-dd')) - to_date(to_char(writeday , 'yyyy-mm-dd')),2,1,0)) as PREVIOUS 
    ,sum(decode(to_date(to_char(sysdate,'yyyy-mm-dd')) - to_date(to_char(writeday , 'yyyy-mm-dd')),1,1,0)) as PREVIOUS 
    ,sum(decode(to_date(to_char(sysdate,'yyyy-mm-dd')) - to_date(to_char(writeday , 'yyyy-mm-dd')),0,1,0)) as TODAY 
    from tbl_board 
    where to_date(to_char(sysdate,'yyyy-mm-dd')) - to_date(to_char(writeday , 'yyyy-mm-dd')) <  7;
    
    select *
    from tbl_board 
    where to_char(sysdate , 'yyyy-mm') = to_char(writeday,'yyyy-mm');
    
    -----------------------------------------------------------------
    -- WRITEDAY     CNT     
    -----------------------------------------------------------------
    -- 2023-03-01    3 
    -- 2023-03-20    5 
    --   전체         8 
    
    select DECODE(grouping(to_char(writeday,'yyyy-mm-dd')),0,to_char(writeday,'yyyy-mm-dd'),'전체') as writeday 
    , count(*) as CNT
    from tbl_board 
    where to_char(sysdate , 'yyyy-mm') = to_char(writeday,'yyyy-mm')
    group by rollup( to_char(writeday,'yyyy-mm-dd'));
    
    
    