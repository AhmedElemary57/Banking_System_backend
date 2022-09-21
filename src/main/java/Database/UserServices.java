package Database;

import com.mongodb.client.MongoDatabase;
import models.User;
import org.bson.Document;

import java.util.ArrayList;

public class UserServices {

    public static User[] getAllUsers() {
        MongoDatabase database = DatabaseConnection.connectToDB("BankingSystem");
        ArrayList<User> users = new ArrayList<>();
        for (Document document : database.getCollection("Users").find()) {
            User user = new User();
            user.set_id(document.get("_id").toString());
            user.setName(document.get("name").toString());
            user.setEmail(document.get("email").toString());
            user.setBalance(Integer.parseInt(document.get("balance").toString()));
            users.add(user);
        }
        User[] usersArray = new User[users.size()];
        usersArray = users.toArray(usersArray);
        return usersArray;
    }


}
