package DataBase;

import java.util.ArrayList;
import java.util.Iterator;
import myClass.DB_Element;
/**
 * LibDB는 책DB, 이용자DB 등 공통적인 DB역할을 하는 제네릭 클래스이다.
 *
 * @author (2021320090 이봉헌, 2021320018 김준혁, 2024320003 니시 야스히로)
 * @version (2025.10.1)
 */
public class LibDB<T extends DB_Element> {
    private ArrayList<T> db;

    public LibDB() {
        db = new ArrayList<T>();
    }

    public void addElement(T e) {
        db.add(e);
    }

    public T findElement(String id) {
        Iterator<T> it = db.iterator();

        while (it.hasNext()) {
            T e = it.next();

            if (e.getID().equals(id)) return e;
        }
        return null;
    }

    public void printAllElement() {
        for (T e : db) {
            System.out.println(e);
        }
    }
}
