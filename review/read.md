# reviewPoint


## 과제 구상도
&nbsp;  
![image](https://user-images.githubusercontent.com/81105748/175780265-8da7761c-3e2e-4d3a-bd09-ff8e3f49087f.png)

&nbsp;  
### :computer: __사용 프레임워크 및 라이브러리__
---
* Spring Boot 2.7.0  
* Lombok AnnotationProcessor  
* Spring Validation
* Spring Boot Stater Test
* Spring Data JPA  
* Mysql-connector-java

&nbsp;
### :earth_asia: __실행 환경__
---
* WINDOWS 10
* OPEN JDK 11
* MySQL 8.0.27

&nbsp;
### :cd: __설치__
---
 &nbsp;   
* __Open JDK 11 설치__
  * 설치 URL : https://jdk.java.net/archive/ 에서 __11.0.2__ 버전 설치
  * 압축 해제 : __C:\Program Files\Java__ 와 같은 경로에 해제 하는걸 권합니다.

&nbsp;
* __STS(Eclipse) 설치__
  * 설치 URL: https://spring.io/tools Spring Tools 4 for Eclipse - OS에 맞게 설치 후 압축 해제
  
 &nbsp;   
* __MySql 설치__
  * 설치 URL : https://downloads.mysql.com/archives/community  다운로드 후 실행
  * 주의 사항 : __comunity server와 connector j 는 꼭 설치하셔야 합니다.__
  * username : __dbAdmin__
  * password : __1234__
  *  __*자세한 설정은 프로젝트 내의 application.yml를 변경 하시면 됩니다.__

&nbsp;  
* __Project Lombok 설치__
  * 설치 URL : https://projectlombok.org/download 다운로드 후 실행
  * 플러그인 설치 : 실행 후 IDE 찾기에서 __프로젝트 Import할 Eclipse.exe 선택__

&nbsp;  
* __Eclipse 구동__
  * GitHub Repository 이용 / 프로젝트 __Import__
  * Import 완료 후 프로젝트 우클릭 -> Gradle -> __Refresh Gradle Project__
  * 프로젝트 우클릭 -> Run As -> __Spring Boot App__
&nbsp;  
&nbsp;

### :pencil2: __구현__
---
* DB 테이블 : event(리뷰이벤트)&nbsp; | &nbsp;PointHistory(포인트기록)  &nbsp; | &nbsp; User(사용자)
* DB Mapper : JPA , JPQL 이용
* 데이터 송수신 : JSON Data
* 에러 : Json Data {code : 400 , mssage: "잘못된 요청입니다." } 처리
&nbsp;  
&nbsp;  
### :runner: 실행
---

1. __JDK 환경 설정하기__ : 
   *  windows -  https://codingdreamtree.tistory.com/32
   *  linux -    https://veneas.tistory.com/entry/Linux-CentOS7-%EC%9B%90%ED%95%98%EB%8A%94-%ED%8A%B9%EC%A0%95-%EB%B2%84%EC%A0%84-Javajdk-%EC%84%A4%EC%B9%98%ED%95%98%EA%B8%B0-wget
  
2. __MySql DB 덤핑__ : 
   1. Mysql 사용자 : dbAdmin , 패스워드 : 1234 생성되어있는지 확인
      * 안되어있을경우 CREATE USER 'dbAdmin'@'%' IDENTIFIED  BY '1234';
      * grant all privileges on *.* to 'dbAdmin' with grant option; 
      * FLUSH PRIVILEGES
   2. 프로젝트 내의 __sql폴더 Sql 파일__ 실행 혹은 Import (스키마 까지 생성됨)
3. __Jar파일 빌드__ :  &nbsp; 
  * 프로젝트 디렉토리에서 gradlew bootjar&nbsp;        
![image](https://user-images.githubusercontent.com/81105748/175780075-46465d95-7489-47e7-a2be-d47873c1bc4a.png)

  * 아래와 같은 화면이 나오면 빌드 성공! &nbsp;     
![image](https://user-images.githubusercontent.com/81105748/175779665-08f04a67-9d5a-4919-b077-784f6e52b717.png)

4. __Jar파일 실행__ :  &nbsp; CMD 혹은 콘솔창에서 해당 JAR파일 실행
  * java -jar 디렉토리/파일명.jar &nbsp;  
<img width="458" alt="image" src="https://user-images.githubusercontent.com/81105748/175766693-d03a35d3-800d-4a70-8bf8-cad75951a3a9.png">
 
  * 아래와 같은 화면이 나오면 성공! &nbsp;  
<img width="1116" alt="image" src="https://user-images.githubusercontent.com/81105748/175766703-bd08f910-d33d-46b7-ab93-1897c1095e6e.png">

&nbsp;  
&nbsp;  
&nbsp;    
&nbsp;   
&nbsp;   
### :dart: __검증__
---
* __Post Man 검증 (외부 유틸리티)__ : 
  * https://www.postman.com/ 응용 프로그램 혹은 웹 로그인 
  * EventController에 해당하는 URL ex) PostMethod http://localhost/events
  * Authorization이 필요한경우 Headers탭에 __Authorization : REALTOR 1__ 같은 데이터 입력
  * Body가 필요한 경우 ex) BoardRequestDTO Body탭에 __raw선택 -> text를 json으로 변경 후__ 데이터 입력     

 &nbsp;
### * __POSTMAN 검증 예시__ *
&nbsp; 
![image](https://user-images.githubusercontent.com/81105748/175780583-b4ea12b2-7871-43a9-a3e1-856264f464cc.png)
&nbsp;
&nbsp;
&nbsp;  
&nbsp;  
&nbsp;  

* __테스트 코드 검증__ : &nbsp;  
  src/test/java 패키지에서 com.callbus.zaritalk.board 패키지 내의 Controller , Service , Repository Class __우클릭 후 Run As -> Junit5 Test 가동__
 &nbsp;
### * Junit5 검증 예시__ *
&nbsp; 
![image](https://user-images.githubusercontent.com/81105748/175780494-bb542742-1738-4fee-a00e-b6cf20dc4d55.png)
&nbsp;
&nbsp;
&nbsp;  
&nbsp;  

&nbsp;  
&nbsp;  
### :memo: __API Documentation__
---
&nbsp;  

 <img src="https://img.shields.io/badge/GET-getPointsOnUser-green">&nbsp;
  * URL : __GET &nbsp;```http://localhost/users/{userId}/points```__
  * Function : 포인트 및 목록 조회 
  * __Return__ : &nbsp;  
{&nbsp;  
  &nbsp;&nbsp;"userId" : "kth", &nbsp;  
  &nbsp;&nbsp;"point" : 10,&nbsp;  
  &nbsp;&nbsp;"pointHistoryList" : [&nbsp;  
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  {&nbsp;  
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  "point" : 2,"&nbsp;  
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"regDtm" : "2022-06-25T18:24:43"&nbsp;  
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},&nbsp;  
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{&nbsp;    
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  "point" : 1,"&nbsp;  
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"regDtm" : " 2022-06-25T18:27:50"&nbsp;  
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;},&nbsp;  
  &nbsp;&nbsp;  ]&nbsp;  
}

&nbsp;  
&nbsp;  
<img src="https://img.shields.io/badge/POST-insert-blue">&nbsp;  
  * URL : __POST &nbsp;```http://localhost/events```__
  * Function : 리뷰 이벤트 등록 / 수정 / 삭제
  * Body : JSON DATA &nbsp;  
   {&nbsp;  
   &nbsp;&nbsp;"type" : "REVIEW", &nbsp;  
   &nbsp;&nbsp;"action" : "ADD", /* "MOD", "DELETE" */&nbsp;  
   &nbsp;&nbsp;"reviewId" :  "240a0658-dc5f-4878-9381-ebb7b2667772", &nbsp;  
   &nbsp;&nbsp;"content" : "좋아요!", &nbsp;  
   &nbsp;&nbsp;"attachedPhotoIds" : [&nbsp;  
    &nbsp;&nbsp;&nbsp;&nbsp; "e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", &nbsp;   
    &nbsp;&nbsp;&nbsp;&nbsp;
    "afb0cef2-851d-4a50-bb07-9cc15cbdc332" &nbsp;  
    &nbsp;&nbsp;], &nbsp;  
   &nbsp;&nbsp;"userId" :  "3ede0ef2-92b7-4817-a5f3-0c575361f745",
 &nbsp;  
   &nbsp;&nbsp;"placeId" :  "2e4baf1c-5acb-4efb-a1af-eddada31b00f"
 &nbsp;  
   }
  * __Return__ : &nbsp;  
    {&nbsp;  
    &nbsp;&nbsp;"status" : "추가" , &nbsp;  
    &nbsp;&nbsp;"point" : 2 &nbsp;  
    }
