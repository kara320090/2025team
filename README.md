# 도서관 대출 관리 | Java 객체지향 기초

텍스트 파일에서 이용자와 도서 정보를 읽고, 대출 내역을 콘솔에 출력하는 Java 학습 프로젝트입니다.

**Java · Generics · Collections · File I/O · BlueJ**

## 학습 목표와 구현

| 주제 | 코드에서의 적용 |
|---|---|
| 추상화 | 공통 식별 인터페이스를 갖는 `DB_Element`와 `Book`, `User` |
| 제네릭 | `LibDB<T extends DB_Element>`로 자료 저장·검색 공통화 |
| 컬렉션 | 이용자와 도서의 연결을 `HashMap<User, Book>`으로 관리 |
| 파일 입출력 | 도서·이용자 파일을 읽어 객체로 변환 |
| 실행 흐름 | 자료 로드 → 목록 출력 → 예제 대출 → 대출 현황 출력 |

## 파일 구성

- [App.java](App.java): 실행 진입점과 예제 시나리오
- [LibraryManagementSystem.java](LibraryManagementSystem.java): 파일 로드와 대출 처리
- [DataBase/LibDB.java](DataBase/LibDB.java): 제네릭 저장·검색
- [myClass](myClass/): 도서·이용자·공통 추상 클래스
- `BookData2025.txt`, `UserData2025.txt`: 실행에 사용하는 입력 자료

## 실행 방법

BlueJ에서 `package.bluej`가 있는 폴더를 열고 컴파일한 뒤 `App.main`을 실행합니다. 입력 파일의 상대 경로를 사용하므로 저장소 루트가 실행 기준 폴더여야 합니다.

JDK의 명령행 도구로는 다음과 같이 실행할 수 있습니다.

```powershell
javac -encoding UTF-8 -d out App.java LibraryManagementSystem.java DataBase/LibDB.java myClass/Book.java myClass/User.java myClass/DB_Element.java
java -cp out App
```

## 구현 범위

기본 버전은 이용자별 한 권을 매핑하는 구조입니다. 같은 이용자에게 다시 대출을 기록하면 기존 매핑이 대체될 수 있습니다. 다권 대출과 입력 검증을 확장한 버전은 [teammodule-1-v2](https://github.com/kara320090/teammodule-1-v2)에 있습니다.

각 실행에서 입력 파일을 읽어 메모리에 도서·이용자·대출 상태를 구성하는 학습 프로그램입니다.
