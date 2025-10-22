package DataBase;
import java.util.ArrayList;
import java.util.Iterator;
import myClass.DB_Element;
/**
 * LibDB는 책DB, 이용자DB 등 공통적인 DB역할을 하는 제네릭 클래스이다.
 *
 * @author (2021320018 김준혁)
 * @version (2025.10.22)
 */
public class LibDB<T extends DB_Element> {
    private ArrayList<T> db;
    /**
     * LibDB 생성자 - ArrayList를 초기화한다.
     */
    public LibDB() {
        db = new ArrayList<T>();
    }
    /**
     * addElement 메소드 - DB에 요소를 추가한다.
     *
     * @param e  추가할 DB 요소
     */
    public void addElement(T element) {
        db.add(element);
    }
    /**
     * findElement 메소드 - 주어진 ID와 일치하는 요소를 검색한다.
     *
     * @param id  검색할 요소의 ID
     * @return    ID가 일치하는 DB 요소, 없으면 null 반환
     */
    public T findElement(String id) {
        Iterator<T> it = db.iterator();

        while (it.hasNext()) {
            T element = it.next();

            if (element.getID().equals(id)) return element;
        }
        return null;
    }
    /**
     * printAllElements 메소드 - DB 내의 모든 요소를 출력한다.
     */
    public void printAllElements() {
        for (T element : db) {
            System.out.println(element);
        }
    }
}