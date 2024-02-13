package westminsterShoppingCart;

public class User {

    private final String userName;
    private String passWord;

    public User(String userName, String passWord){
        this.userName = userName;
        this.passWord = passWord;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + userName + '\'' +
                ", password='" + passWord + '\'' +
                '}';
    }
}

