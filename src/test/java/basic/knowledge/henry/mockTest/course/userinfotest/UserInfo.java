package basic.knowledge.henry.mockTest.course.userinfotest;


public class UserInfo {
    private String name;
    private String password;

    public UserInfo(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public UserInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

