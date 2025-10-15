package myClass;
/**
 * User는 학생객체를 생성하는 클래스이다
 *
 * @author (2021320090 이봉헌, 2021320018 김준혁, 2024320003 니시 야스히로)
 * @version (2025.10.1)
 */
public class User extends DB_Element {
    private String name;
    private Integer stID;
    
    public User(Integer stID, String name){
        this.name = name;
        this.stID = stID;
    }
    @Override
    public String getID() { 
        return stID.toString(); 
    }

    @Override
    public String toString() {
        return "[" + stID + "] " + name;
    }
}
