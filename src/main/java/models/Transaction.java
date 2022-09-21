package models;

import java.util.Date;

public class Transaction {
    String _id;
    String date;
    String from;
    String to;
    int amount;

    public Transaction(String _id, String date, String from, String to, int amount) {
        this._id = _id;
        this.date = date;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public Transaction() {

    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String  getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
