import java.util.HashMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import DataBase.LibDB;
import myClass.User;
import myClass.Book;
import myClass.DB_Element;

/**
 * LibraryManagementSystem 클래스의 설명을 작성하세요.
 *
 * @author (2024320003 니시 야스히로)
 * @version (2025.10.22)
 */
public class LibraryManagementSystem{
    LibDB<Book> bookDB; // 모든 책들에 대한 정보가 저장되는 책 데이터 베이스
    HashMap<User, Book> loanDB; // 이용자와 해당 이용자가 대출한 책에 대한 정보가 저장되는 대출 데이터 베이스
    LibDB<User> userDB; // 모든 이용자들에 대한 정보가 저장되는 이용자 데이터 베이스

    /**
     * LibraryManagementSystem 클래스의 객체 생성자
     * 책, 이용자, 대출 데이터 베이스에 각 자료형별 알맞은 객체를 생성하여 초기화 하는 메소드이다.
     * 
     */
    public LibraryManagementSystem(){
        this.bookDB = new LibDB<Book>();
        //this.loanDB = new HashMap<User, Book>();
        this.loanDB = new HashMap<User, Book>();
        this.userDB = new LibDB<User>();
    }

    /**
     * 이용자와 책의 id를 String타입으로 전달 받아 대출을 수행하는 메소드
     * 전달받은 파라미터를 토대로 각 데이터 베이스에서 객체를 검색하고 없을 경우 null을 반환하고 '존재하지 않습니다' 메시지를 출력한다.
     *
     * @param  userID, bookID  이용자(stID)와 책(bookID)
     */
    public void borrowBook(String userID, String bookID){
        // 이용자의 id를 토대로 "이용자 데이터 베이스"에서 이용자 객체를 검색, 성공시 id에 해당되는 이용자 객체를, 실패시 null을 반환
        User user = this.userDB.findElement(userID);
        // 마찬가지로 책의 id를 토대로 "책 데이터 베이스"에서 책 객체를 검색, 성공시 id에 해당되는 책 객체를, 실패시 null을 반환
        Book book = this.bookDB.findElement(bookID);

        if (user == null) { // 검색한 이용자 객체가 없는 경우 오류 문구 출력후 메소드 종료
            System.out.println("이용자 ID가 존재하지 않습니다: " + userID);
            return;
        }

        if (book == null) { // 검색한 책 객체가 없는 경우 오류 문구 출력후 메소드 종료
            System.out.println("책 ID가 존재하지 않습니다: " + bookID);
            return;
        }

        // 이용자 객체와 책 객체가 둘다 존재하는 경우 대출 데이터 베이스에 정보를 저장
        this.loanDB.put(user, book);
    }

    /**
     * 책 데이터 베이스 또는 이용자 데이터 베이스를 전달 받아 모든 요소를 출력하는 메소드
     *
     * @param  db  제네릭 데이터베이스
     */
    public <T extends DB_Element> void printDB(LibDB<T> db){
        db.printAllElements();
    }

    /**
     * 이용자가 대출한 책 목록을 출력하는 메소드이다.
     * 
     * 
     */
    public void printLoanList(){
        for (User u : loanDB.keySet()) {
            System.out.print(u);
            System.out.print(" ===> ");
            System.out.print(loanDB.get(u));
            System.out.println();
        }
    }

    /**
     * 모든 책 목록에 대한 데이터 파일을 읽어와 책 데이터 베이스에 저장하는 기능
     *
     * @param  bookFile  책 목록이 들어있는 데이터 파일
     * @return   책 데이터 베이스를 반환한다.
     */
    public LibDB<Book> setBookDB(String bookFile) {
        try {
            File file = new File(bookFile); // File 객체 생성
            Scanner sc = new Scanner(file); // FileReader객체를 전달하여 Scanner객체를 생성

            while (sc.hasNextLine()) { // 다음 줄이 있을때만 while문 실행
                String line = sc.nextLine(); // 현재 줄을 읽기

                String[] elems = line.split("/"); // "/"를 기준으로 읽어온 한줄을 분리하기

                String id = elems[0].trim(); // 책의 아이디
                String title = elems[1].trim(); // 책의 제목 
                String author = elems[2].trim(); // 책의 저자
                String publisher = elems[3].trim(); // 책의 출판사
                int year = Integer.parseInt(elems[4]); // 책의 출판년도, 정수로의 형변환을 수행

                // Book 객체 생성 후 DB에 추가
                bookDB.addElement(new Book(id, title, author, publisher, year));
            }

            sc.close(); // Scanner 닫기

        } catch (FileNotFoundException e) {
            System.out.println("책 데이터 파일 읽기 실패");
        }

        return bookDB;
    }

    /**
     * 모든 이용자 목록에 대한 데이터 파일을 읽어와 이용자 데이터 베이스에 저장하는 기능
     *
     * @param  userFile  이용자 목록이 들어있는 데이터 파일
     * @return    이용자 데이터 베이스를 반환한다.
     */
    public LibDB<User> setUserDB(String userFile) {
        try {
            File file = new File(userFile);
            Scanner sc = new Scanner(file); // FileReader객체를 전달하여 Scanner객체를 생성

            while (sc.hasNextLine()) { // 다음 줄이 있을때만 while문 실행
                String line = sc.nextLine(); // 현재 줄을 읽기

                String[] elems = line.split("/"); // "/"를 기준으로 읽어온 한줄을 분리하기

                int id = Integer.parseInt(elems[0].trim()); // 이용자의 아이디, 정수로의 형변환을 수행
                String name = elems[1].trim(); // 이용자의 이름

                // User 객체 생성 후 DB에 추가
                userDB.addElement(new User(id, name));
            }

            sc.close(); // Scanner 닫기

        } catch (FileNotFoundException e) {
            System.out.println("이용자 데이터 파일 읽기 실패");
        }

        return userDB;
    }
}