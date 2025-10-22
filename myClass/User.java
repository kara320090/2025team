package myClass;

/**
 * User는 학생 객체를 생성하는 클래스이다.
 *
 * @author (2021320018 김준혁)
 * @version (2025.10.22)
 */
public class User extends DB_Element {
    private String name;
    private Integer stID;

    /**
     * User 생성자 - 이용자의 ID와 이름을 초기화한다.
     *
     * @param stID  이용자의 (ID)
     * @param name  이용자의 이름
     */
    public User(int stID, String name) {
        this.name = name;
        this.stID = stID;
    }

    /**
     * getID 메소드 - 이용자의 ID를 문자열로 반환한다.
     *
     * @return  이용자의 ID 문자열
     */
    @Override
    public String getID() {
        return this.stID.toString();
    }

    /**
     * toString 메소드 - 이용자의 정보를 문자열 형태로 반환한다.
     *
     * @return  "[이용자 ID] 이름" 형식의 문자열
     */
    @Override
    public String toString() {
        return "[" + this.stID + "] " + this.name;
    }
}
