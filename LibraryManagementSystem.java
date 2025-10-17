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
 * @author (2021320090 이봉헌, 2021320018 김준혁, 2024320003 니시 야스히로)
 * @version (2025.10.1)
 */
public class LibraryManagementSystem{
    LibDB<Book> bookDB;
    HashMap<User, Book> loanDB;
    LibDB<User> userDB;

    public LibraryManagementSystem(){
        this.bookDB = new LibDB<Book>();
        this.loanDB = new HashMap<User, Book>();
        this.userDB = new LibDB<User>();
    }

    public void borrowBook(String userID, String bookID){
        User user = this.userDB.findElement(userID);
        Book book = this.bookDB.findElement(bookID);

        if (user == null) {
            System.out.println("사용자 ID가 존재하지 않습니다: " + userID);
            return;
        }

        if (book == null) {
            System.out.println("책 ID가 존재하지 않습니다: " + bookID);
            return;
        }

        this.loanDB.put(user, book);
    }

    public <T extends DB_Element> void printDB(LibDB<T> db){
        db.printAllElements();
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
        File file = new File(bookFile); // File 객체 생성

        try {
            // FileReader를 이용해서 Scanner 생성
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
            System.out.println("책 데이터 파일 읽기 실패");
        }

        return bookDB;
    }

    public LibDB<User> setUserDB(String userFile) {
        File file = new File(userFile);

        // try-with-resources: FileReader와 Scanner를 자동으로 닫음
        try {
            FileReader fr = new FileReader(file);
            Scanner sc = new Scanner(fr);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.isEmpty()) continue;

                String[] strings = line.split("/");

                int id = Integer.parseInt(strings[0]);
                String name = strings[1];

                userDB.addElement(new User(id, name));
            }
            
            fr.close();
        } catch (IOException e) {
            
            System.out.println("사용자 데이터 파일 읽기 실패");
        }

        return userDB;
    }
}