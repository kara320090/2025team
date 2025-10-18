package myClass;

/**
 * Book는 책 객체를 생성하는 클래스다.
 * 
 * @author (2021320090 이봉헌, 2021320018 김준혁, 2024320003 니시 야스히로)
 * @version (2025.10.19)
 */
public class Book extends DB_Element {
    private String author;
    private String bookID;
    private String publisher;
    private String title;
    private int year;

    /**
     * Book 생성자 - 책의 속성(책ID, 제목, 저자, 출판사, 출판연도)을 초기화한다.
     *
     * @param bookID     책의 ID
     * @param title      책 제목
     * @param author     책 저자
     * @param publisher  출판사
     * @param year       출판연도
     */
    public Book(String bookID, String title, String author, String publisher, int year) {
        this.author = author;
        this.bookID = bookID;
        this.publisher = publisher;
        this.title = title;
        this.year = year;
    }

    /**
     * getID 메소드 - 책의 ID를 반환한다.
     *
     * @return  책의 ID
     */
    @Override
    public String getID() {
        return this.bookID;
    }

    /**
     * toString 메소드 - 책의 정보를 문자열 형태로 반환한다.
     *
     * @return  "(책ID) 제목, 저자, 출판사, 출판연도" 형식의 문자열
     */
    @Override
    public String toString() {
        return "(" + this.bookID + ") " + this.title + ", " + this.author + ", " + this.publisher + ", " + this.year;
    }
}

