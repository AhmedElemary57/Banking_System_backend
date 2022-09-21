package models;

public class User {
    String _id;
    String name;
    String email;
    int balance;

    public User(String _id, String name, String email, int balance) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.balance = balance;
    }

    public User() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
