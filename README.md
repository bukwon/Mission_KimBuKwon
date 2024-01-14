# Mission_KimBuKwon

# 명언 요구사항(final)

- 1단계 : 종료
- 2단계 : 등록
- 3단계 : 등록 시 생성된 명언 번호 노출
- 4단계 : 등록할 때 마다 생성되는 명언 번호 증가
- 5단계 : 목록
- 6단계 : 명언 삭제
- 7단계 : 존재하지 않은 명언 삭제에 대한 예외 처리
- 8단계 : 명언 수정
- 9단계 : 파일을 통한 영속성
- 10단계 : data.json 빌드
- 11단계 : SimpleDbTest.java (X)
- 12단계 : SimpleDB 사용 후 DB 저장 (X)
- 13단계 : @Configuration, @Bean, @Component, @Autowired 를 이용한 의존성 주입 기능 구현 (X)
- 14단계 : @Configuration, @Bean, @Component, @Autowired 를 이용한 의존성 주입 기능 적용 (X)

#회고
코드가 좀 길었습니다. 큰 설명은 대부분 주석 처리 진행했으며 깃 허브를 잘 다룰 줄 몰라 중간에 작업물을 저장하지 않고 최종본만 풀 리퀘스트 진행했습니다.
코드 궁금한 점 있으시면 리뷰 남겨주세요! 제 프로젝트 통해 Json 기능만 간단히 소개하자면

## 1) build.gradle 설정
- Json 이용 위해 build.gradle에서 dependencies 설정 필수

## 2) saveToListFile()
- 파일 입출력 형식이기에 try, catch문 중요. Json에서 정보들의 정렬을 위해
setPrettyPrinting().create()를 이용해 빌드

## 3) readListFromFile()
- Json에 저장된 파일을 필드에 읽어 들여오기 위한 메서드
- saveToListFile()와 같이 외부에서 읽어 오기에 예외 처리 이용

## 4) 등록, 수정, 삭제, 목록 메뉴얼 설정
- 정보 작업이 필요할 때 **saveListToFile()** 로 작업
- 목록 확인이 필요할 때 **readListFromFile()** 이용

# 아쉬운 점
객체를 더 나누어 가독성을 높였어야 했는데 자바가 익숙하지 않은 탓에 조금 절차 지향의 느낌으로 넣은 것 같다.
다음 코딩을 구현할 때 객체를 분배할 고민이 더 필요하고 기술을 더 익혀야 할 것 같다.

# 프로젝트 이후 수정 작품
https://github.com/bukwon/JavaProject_Practice/tree/main
