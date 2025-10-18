package myClass;
/**
 * User는 학생객체를 생성하는 클래스이다
 *
 * @author (2021320090 이봉헌, 2021320018 김준혁, 2024320003 니시 야스히로)
 * @version (2025.10.19)
 */
public class User extends DB_Element {
    private String name;
    private Integer stID;
    
    // 입력값 : 이용자의 아이디(stID), 이용자의 이름(name)
    // 반환값 : 없음
    public User(int stID, String name){
        this.name = name;
        this.stID = stID;
    }
    
    // 입력값 : 없음
    // 반환값 : 이용자 ID에 대한 문자열
    @Override
    public String getID() { 
        return this.stID.toString(); 
    }

    // 입력값 : 없음
    // 반환값 : 이용자에 대한 정보
    @Override
    public String toString() {
        return "[" + this.stID + "] " + this.name;
    }
}
