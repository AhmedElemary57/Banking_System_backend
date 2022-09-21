package Database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Collection;

public class DatabaseConnection {


    public static MongoDatabase connectToDB(String databaseName){
        ConnectionString connectionString = new ConnectionString("mongodb+srv://bankingSystem:bankingsystem@bankingsystem.kipmk2j.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        return mongoClient.getDatabase(databaseName);

    }

    public static void main(String[] args){

        MongoDatabase usersDatabase =connectToDB("BankingSystem");
        MongoCollection collection = usersDatabase.getCollection("Users");
        Document document = new Document();
        document.append("name","yahya");
        document.append("email","yahya@gmail.com");
        document.append("balance",12478);
        collection.insertOne(document);

    }

}
