package myClass;
/**
 * Book는 책 객체를 생성하는 클래스다
 *
 * @author (2021320090 이봉헌, 2021320018 김준혁, 2024320003 니시 야스히로)
 * @version (2025.10.1)
 */
public class Book extends DB_Element {
    private String author;
    private String bookID;
    private String publisher;
    private String title;
    private int year;
    
    public Book (String bookID, String title, String author, String publisher, int year){
        this.author = author;
        this.bookID = bookID;
        this.publisher = publisher;
        this.title = title;
        this.year = year;
    }
    @Override
    public String getID(){
        return this.bookID;
    }
    
    @Override
    public String toString() {
        return "(" + bookID + ") " + title + ", " + author + ", " + publisher + ", " + year;
    }
}
