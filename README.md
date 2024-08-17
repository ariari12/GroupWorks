# 👥 GroupWorks
![gw](https://github.com/user-attachments/assets/04761ec1-eba2-4544-a323-98ee8283e824)

# 프로젝트 소개

## 팀원 구성
<table>
  <tr>
    <th>유현종</th>
    <th>최일성</th>
    <th>이정민</th>
    <th>김아현</th>
  </tr>
  <tr>
    <td>
        <img src="https://avatars.githubusercontent.com/u/59546443?v=4">
        <img src="https://github-readme-stats.vercel.app/api?username=appapixie">
        <img src="https://github-readme-stats.vercel.app/api/top-langs/?username=appapixie">
    </td>
    <td>
        <img src="https://avatars.githubusercontent.com/u/66054061?v=4">
        <img src="https://github-readme-stats.vercel.app/api?username=sczzsccs">
        <img src="https://github-readme-stats.vercel.app/api/top-langs/?username=ILSEONG">
    </td>
    <td>
        <img src="https://avatars.githubusercontent.com/u/104063759?v=4">
        <img src="https://github-readme-stats.vercel.app/api?username=sczzsccs">
        <img src="https://github-readme-stats.vercel.app/api/top-langs/?username=sczzsccs">
    </td>
    <td>
        <img src="https://avatars.githubusercontent.com/u/79638001?v=4">
        <img src="https://github-readme-stats.vercel.app/api?username=ariari12">
        <img src="https://github-readme-stats.vercel.app/api/top-langs/?username=ariari12">
    </td>
  </tr>
</table>

## 주제


주제 소개

<br/>

## 구성도 
- ERD
    <img style="width: 1000px;" src="https://github.com/user-attachments/assets/86af34bf-11fe-4ab7-824b-aabdfd3d8eab">
- System Architecture
    <img src="https://github.com/user-attachments/assets/6bd76005-654f-4c12-9d87-dcbfad559fb5">


<br/>

## 기능

- ### <담당: 유현종>
- ### <담당: 유현종>
- ### <담당: 최일성>
    
    <br/>
    <img src="https://github.com/user-attachments/assets/663561ed-b036-481a-867b-25c814a3e2d8">
- ### <담당: 최일성>
- ### Work Flow - 전자 결재 <담당: 이정민>
    - 결재 요청 : 결재 요청란(기안일, 결재구분, 결재자, 협조자, 참조자, 결재제목, 결재내용, 비용, 첨부파일 등) 작성 후 결재를 요청
    - 내 발송 결재 : 내가 신청한 결재(완료/진행/반려) 목록
    - 결재 현황 :
        - 부서별 결재 발송/승인/반려 건 수
        - 올해 전체 결재 발송 구분
        - 월 별 결재 발송/승인/반려 건 수
        - 결재 구분 별 내 누적 결재 발송 건 수
    - 결재 승인 / 반려 : 사용자가 결재 해야 하는 문서 목록.
        - 결재 승인/반려 : 승인 대기 목록에서 결재 승인 및 코멘트 작성
            - 결재 종류 :  1 :선결, 2: 전결, 3:후결, 4:대결, 5:반려
    - 결재 검토 : 협조자로 등록된 결재의 코멘트 및 피드백 작성 가능
    
    <img src="https://github.com/user-attachments/assets/21fbd2a0-2da3-4c88-be09-f8d52cfece22">
    <img src="https://github.com/user-attachments/assets/caa0032b-203d-4700-9231-291297adbdf2">
- ### Material Flow BOM - 통합 자재/재고/유통 관리 <담당: 이정민>
    - 발주/수주 :
      - 발주/수주(사업자 번호, 거래처 상호, 거래처 담당자(이름, 이메일, 연락처), 납품처, 발주일, 담당자 사원, 납기일, 자재코드(발주코드), 자재명, 수량, 단가)
      - 거래처 등록(사업자 번호, 상호 명, 대표자명, 업태, 종목, 주소, 연락처) 및  선택
      - 거래처 담당자 등록(담당자 성명, 연락처, 이메일) 및 선택
    - 자재/재고/유통(BOM) 관리
        - 자재/재고/유통을 구분하여 입하-입고-출하-츨고(QR Code로 자재 상태 변경 적용)
        - 자재코드, 상태(입하-입고-출하-츨고), 장소, 담당자(연락처, 이메일), 자재명, 수량, 단가
        - 품목 발주/수주 완료 시:
            - 발주: 품목 내 모든 자재가 출고 되었을 때
            - 수주: 품목 내 모든 자재가 입고 되었을 때
            - 완료 시 담당자(발주/수주)에게 완료 메시지 SMS 발송 (CoolSMS API 사용,  API KEY profiie에서 암호화 적용)
    - MES(생산 현황) :
        - 생산 실적 품목코드, 제품명, 공정, 장소, 수량, 단가, 불량 갯수 등 제공 - (생산량, 불량 현황 파악)
        - 연동 OpenAPI 제공(작업계획, 생산실적)제
    - 영업손익 산출: 발주와 수주, 불량 등으로 통계 계산 - 이익(발주 ≥ 수주) / 손해(발주 < 수주)
      
    <img src="https://github.com/user-attachments/assets/f7c0d865-b638-4212-a332-cd5af3385877">
    <img src="https://github.com/user-attachments/assets/f69235cf-7e54-4722-8b3c-ce256f48fc19">
    
- ### Vacation - 휴가 관리 <담당: 김아현>

  - 휴가 신청
      - 상세 내용, 휴가 일정 입력
      - 병가, 기타는 파일첨부 기능 추가
  - 휴가 보유 / 사용 현황 조회
      - 휴가 신청이 상급자 승인을 받을 경우 잔여 연차 감소 또는 사용 일수 증가
  - 휴가 신청 내역 조회
      - 휴가 신청시 조회가능
      - 휴가 수정 클릭시 상제 조회와 수정 가능
      - 승인 상태가 검토 중 일 경우 휴가 수정, 삭제 불가
  - 구성원 휴가 신청 내역 조회
      - 일정 직급 이상 조회 가능 (ex. 과장, 차장, 부장)
      - 부서에 해당 되는 휴가 신청 내역 전체 조회 가능
      - 신청 내역 클릭시 상세 조회 가능
      - 상세 조회 시 첨부파일 다운, 
      승인 상태가 검토 중일 경우 승인 반려 가능

- ### Notification - 알림 기능 <담당: 김아현>
    
  - 알림 조회
      - 알림 아이콘을 클릭시 
      사원의 전체 알림 조회
      - 개별 알림의 제목을 클릭 시
      해당 알림 페이지로 이동
  - 알림 삭제
      - 전체 삭제, 개별 삭제 가능
  - 알림 요청
      - 휴가 승인, 반려
      전자 결재 요청
      메일 발송 시 SSE 실시간 알림 전송
  - 알림 응답
      - 알림 토스트 창, 아이콘 배지 활성화

 - ### Calendar - 일정 관리 <담당: 김아현>
  
    - 캘린더 조회
        - 개인 일정, 휴가 일정, 구글 api 휴무일 연동 조회 가능
        - 캘린더의 이벤트를 클릭시
            - 개인일정, 휴가는 상세 조회 가능
            - 구글 api 휴무일은 상세 조회 불가능
    - 캘린더 개인 일정 추가
        - 캘린더의 날짜를 클릭시 개인 일정 추가 가능
    - 캘린더 개인 일정 수정, 삭제
        - 캘린더의 이벤트를 클릭시
            - 개인일정 수정 삭제 가능
            - 휴가, 휴무일 수정 삭제 불가능


# 📚 Stack
### FrontEnd
<div>
  <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
  <img src="https://img.shields.io/badge/css3-1572B6?style=for-the-badge&logo=css3&logoColor=white">
  <img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">
  <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=white">
  <img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white">
  <img src="https://img.shields.io/badge/webrtc-333333?style=for-the-badge&logo=webrtc&logoColor=white">
</div>

### BackEnd
<div>
  <img src="https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white">
  <img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white">
  <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">
  <img src="https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white">
  <img src="https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white">
  <img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white">
  <img src="https://img.shields.io/badge/Amazon_AWS-FF9900?style=for-the-badge&logo=amazonaws&logoColor=white">
</div>

### DataBase
<div>
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/mongodb-47A248?style=for-the-badge&logo=mongodb&logoColor=white">
  <img src="https://img.shields.io/badge/redis-FF4438?style=for-the-badge&logo=redis&logoColor=white">
</div>

### ETC
<div>
  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
  <img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white">
  <img src="https://img.shields.io/badge/Discord-7289DA?style=for-the-badge&logo=discord&logoColor=white">
</div>
