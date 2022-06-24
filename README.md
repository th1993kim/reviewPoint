# reviewPoint


## 과제 구상도
&nbsp;  
![image](https://user-images.githubusercontent.com/81105748/174888048-895f5580-678f-45c7-9866-0e5ef8debb58.png)

&nbsp;  
### :computer: 사용 프레임워크 및 라이브러리
---
* Spring Boot 2.7.0  
* Lombok AnnotationProcessor  
* Spring Validation
* Spring Boot Stater Test
* Spring Data JPA  
* Mysql-connector-java

&nbsp;
### :earth_asia: 실행 환경
---
* WINDOWS 10
* OPEN JDK 11
* MySQL 8.0.27

&nbsp;
### :cd: 설치
---
* STS(Eclipse) 설치
  * 설치 URL: https://spring.io/tools Spring Tools 4 for Eclipse - OS에 맞게 설치 후 압축 해제
  
 &nbsp;   
* MySql 설치 
  * 설치 URL : https://downloads.mysql.com/archives/community  다운로드 후 실행
  * 주의 사항 : __comunity server와 connector j 는 꼭 설치하셔야 합니다.__
  * username : __dbAdmin__
  * password : __1234s__
  *  __*자세한 설정은 프로젝트 내의 application.yml를 변경 하시면 됩니다.__

&nbsp;  
* Project Lombok 설치
  * 설치 URL : https://projectlombok.org/download 다운로드 후 실행
  * 플러그인 설치 : 실행 후 IDE 찾기에서 __프로젝트 Import할 Eclipse.exe 선택__

&nbsp;  
* Eclipse 구동
  * GitHub Repository 이용 / 프로젝트 __Import__
  * Import 완료 후 프로젝트 우클릭 -> Gradle -> __Refresh Gradle Project__
  * 프로젝트 우클릭 -> Run As -> __Spring Boot App__

