- ![image](https://user-images.githubusercontent.com/70404643/110276541-acb34e00-8016-11eb-9ea3-b079e84405f2.png)

  ### 🚗**스프린트**

  -   각 스프린트는 `1주일`을 기준으로 진행한다.
  -   각 스프린트 기준으로 일인당 40 Point의 스토리 포인트가 부여된다.
      -   하루에 8포인트 ( 8시간 ) \* 5 = 40 Point

  ### 🚓**이슈 등록**

  -   이슈 등록은 개인이 JIRA Convention에 맞추어 등록한다.
  -   이슈 등록 후 해당 이슈에 본인 파트의 팀원을 등록한다.

  ### 🚕**이슈관리**

  -   최초 이슈를 할당받으면 담당자는 `스토리 포인트를` 부여한다.
  -   또한 해당 이슈의 **우선순위**를 설정한다.
  -   작업 들어가기 전 **할 일 --> 진행 중**  
      **진행 완료하면 --> 완료**  
      상태를 최신화한다.
  -   **설명**란에 최대한 자세히 해당 이슈에 있어서 **`담당자`가 작성**한다.
  -   모든 이슈 관련 문의는 **댓글 기능을 통해 이뤄지며** SNS/전화는 지양한다.

  ![image](https://user-images.githubusercontent.com/70404643/104996677-a6b7dc80-5a6b-11eb-9c4b-53823cc69716.png)

  ### 🚌**작업유형**

  #### **에픽**

  -   큰 단위의 업무(여러 User Story, Task 등을 묶은 단위)
  -   매주 월요일 스프린트를 들어가기 전에 생성할 Epic에 있어서 이야기한다. **(필요한 Epic 조사)**
  -   논의한 Epic을 기본으로 해당 Epic에 담당자를 지정하여 생성한다.
  -   **생성 Convention**
      -   Epic을 생성할 때 파트, 기능을 적어준다.
      -   ex) \[Backend\] User , \[Frontend\] 페이지 개발, \[Blockchain\] 노드 개발
      -   \[Backend\] : Back-end
      -   \[Frontend\] : Front-end
      -   \[Blockchain\] : Blockchain

  #### **스토리**

  -   해당 Epic의 하위 단위 작업으로 직접적인 개발과 기능 구현을 기본으로 한다.
      -   **ex ) 최종 고객에게 가치를 제공하는 기능**
      -   작성 방법 : “I as WHO want to do WHAT, so that WHY”    

  -   Tip) User story의 크기는 sprint내에 완료 가능한 단위로 분할 필요
      -   예) 사용자 관리 개념
      -   **생성 Convention**
      -   **ex ) 로그인 기능**
      -   로그인 기능 개발(Epic이 \[BE\] User일 경우)
      -   UI/UX 구현(Epic이 \[FE\] 목업 개발)
      -   합의 알고리즘 구현(Epic이 \[BC\] 합의 알고리즘)

  #### **부작업**

  -   **Story, Task를 더 작은 단위로 나눈 업무**  
      -   즉, 모든 Sub-Task가 끝나야 해당 업무 종료
      -   Sub-task를 사용하는 것은 Story를 이루기 위한 단계별 작성으로 선택상으로 지정한다.
      -   예) 사용자 관리(UI) 개발, 사용자 관리(Service) 개발
      -   **생성 Convention**
          -   **ex) 자동 로그인 기능 구현**
          -   작업에 직관적으로 알 수 있도록 작성.
          -   작업을 진행하면서 해당 이슈를 분할하여 작성

  #### **작업**

  -   해당 스토리가 필요하기 위한 작업으로 일반적으로 기술적, 관리적 업무를 지칭한다.
      -   하지만 초기 스프린트에서 비개발적인 작업이 많고 해당 에픽의 내용에 있어서는 작업을 문서작업과 같은 작업을 작업 이슈로 관리한다.
  -   ex) User Story의 기술적, 관리적, 서류 작업
      -   설계, 서버 설치, 클라우드 도입 등
  -   **생성 Convention**
      -   **\[docker 학습\] document 작업 진행**
      -   **작업에 직관적으로 알 수 있도록 작성.**

  #### **추가 기능 ) 시간 설정**

  ![image](https://user-images.githubusercontent.com/70404643/105811931-ad58cd80-5ff0-11eb-84a0-45aa9cb3d019.png)

  -   **작업 > 상세보기 > 더 많은 조치 > 작업 로그 > 작업한 시간**

  ---

  이번 JIRA Convention을 정하면서 크게 세 가지에 집중하여 신경 썼다.

  -   JIRA 에픽을 관리는 해당 스프린트가 시작하기 전, 월요일 아침 스크럼 회의를 통해서 팀 내에서 협의 후 생성한다.
  -   생성한 에픽을 기준으로 개인별로 스토리, 작업 이슈 유형을 이용하여 관리한다.
  -   스토리 포인트는 40으로 1 단위당 1시간이다.