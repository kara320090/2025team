import java.util.HashMap;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import DataBase.LibDB;
import myClass.User;
import myClass.Book;
import myClass.DB_Element;

/**
 * LibraryManagementSystem 클래스의 설명을 작성하세요.
 *
 * @author (작성자 이름)
 * @version (버전 번호 또는 작성한 날짜)
 */
public class LibraryManagementSystem{
    LibDB<Book> bookDB = new LibDB<Book>();
    HashMap<User, Book> loanDB = new HashMap<User, Book>();
    LibDB<User> userDB = new LibDB<User>();

    public LibraryManagementSystem(){

    }

    public void borrowBook(String userID, String bookID){
        User user = userDB.findElement(userID);
        Book book = bookDB.findElement(bookID);
        
        this.loanDB.put(user, book);
    }

    public <T extends DB_Element> void printDB(LibDB<T> db){
        db.printAllElement();
    }

    public void printLoanList(){
        for (User u : loanDB.keySet()) {
            System.out.print(u);
            System.out.print(" ===> ");
            System.out.print(loanDB.get(u));
            System.out.println();
        }
    }

    public LibDB<Book> setBookDB(String bookFile) {
        File file = new File(bookFile); // ① File 객체 생성

        try {
            // ② FileReader를 이용해서 Scanner 생성
            FileReader fr = new FileReader(file);
            Scanner sc = new Scanner(fr);

            // 한 줄씩 읽기
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue; // 빈 줄 방지

                // "/" 기준으로 나누기
                String[] parts = line.split("/");

                if (parts.length == 5) { // B01, 제목, 저자, 출판사, 연도
                    String id = parts[0].trim();
                    String title = parts[1].trim();
                    String author = parts[2].trim();
                    String publisher = parts[3].trim();
                    int year = Integer.parseInt(parts[4].trim());

                    // Book 객체 생성 후 DB에 추가
                    bookDB.addElement(new Book(id, title, author, publisher, year));
                }
            }

            sc.close(); // Scanner 닫기
            fr.close(); // FileReader 닫기

        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }

        return bookDB;
    }
    
    // public LibDB<Book> setBookDB(String bookFile){
        // this.bookDB.addElement(new Book("B01", "Java Programming", "홍길동", "ABC", 2000));
        // this.bookDB.addElement(new Book("B02", "Software Analysis and Design", "profsHwang", "SMU", 20230));
        // this.bookDB.addElement(new Book("B03", "명품 자바프로그래밍", "황기태", "생능출판", 2025));
        // this.bookDB.addElement(new Book("B04", "소프트웨어 테스트", "profsHwang", "SMU", 2024));
        
        // return this.bookDB;
    // }

    public LibDB<User> setUserDB(String userFile) {
        File file = new File(userFile);

        // try-with-resources: FileReader와 Scanner를 자동으로 닫음
        try (FileReader fr = new FileReader(file);
             Scanner sc = new Scanner(fr)) {

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("/");

                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();

                userDB.addElement(new User(id, name));
            }

        } catch (IOException e) {
            // 예외 처리: 파일 없음 또는 읽기 오류
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }

        return userDB;
    }
    
    // public LibDB<User> setUserDB(String userFile){
        // this.userDB.addElement(new User(2025320001, "Kim"));
        // this.userDB.addElement(new User(2024320002, "Lee"));
        // this.userDB.addElement(new User(2023320003, "Park"));

        // return this.userDB;
    // }
}