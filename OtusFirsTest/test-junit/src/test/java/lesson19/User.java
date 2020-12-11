package lesson19;

public class User {
    @Override
    public String toString() {
        return "Name = " + Name + ", Nickname = " + Nickname;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    private String Name;
    private String Nickname;
}
