package Database;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import models.Transaction;
import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.mongodb.client.model.Sorts.ascending;

import java.util.ArrayList;

public class TransactionServices {

    public static Transaction[] getAllTransactions(){
        MongoDatabase database=DatabaseConnection.connectToDB("BankingSystem");
        ArrayList<Transaction> transactions=new ArrayList<>();
        for (Document document : database.getCollection("Transactions").find().sort(new Document("date", -1))) {
            System.out.println(document);
            Transaction transaction=new Transaction();
            transaction.set_id(document.get("_id").toString());
            transaction.setAmount(Integer.parseInt(document.get("amount").toString()));
            transaction.setDate(document.get("date").toString());
            transaction.setTo(document.get("to").toString());
            transaction.setFrom(document.get("from").toString());
            transactions.add(transaction);
        }
        Transaction[] transactionsArray=new Transaction[transactions.size()];
        transactionsArray=transactions.toArray(transactionsArray);
        return transactionsArray;
    }

    public static ResponseEntity<Boolean> makeTransactionIfPossible(Transaction transaction){
        MongoDatabase database=DatabaseConnection.connectToDB("BankingSystem");
        MongoCollection collection=database.getCollection("Users");
        Document document = (Document) collection.find(new Document("email",transaction.getFrom())).first();
        assert document != null;
        int senderBalance= (int) document.get("balance");
        if(senderBalance>=transaction.getAmount()){
            collection.updateOne(new Document("email",transaction.getFrom()),new Document("$set",new Document("balance",senderBalance-transaction.getAmount())));
            document = (Document) collection.find(new Document("email",transaction.getTo())).first();
            assert document != null;
            int receiverBalance= (int) document.get("balance");
            collection.updateOne(new Document("email",transaction.getTo()),new Document("$set",new Document("balance",receiverBalance+transaction.getAmount())));
            collection=database.getCollection("Transactions");
            document=new Document();
            document.append("date",transaction.getDate());
            document.append("from",transaction.getFrom());
            document.append("to",transaction.getTo());
            document.append("amount",transaction.getAmount());
            collection.insertOne(document);
            return new ResponseEntity<> (true, HttpStatus.OK);
        }
        else{
            System.out.println("Insufficient Balance");
            return new ResponseEntity<> (false, HttpStatus.ACCEPTED);

        }


    }

    public static Transaction[] getUserTransactions(String email) {
        MongoDatabase database = DatabaseConnection.connectToDB("BankingSystem");
        ArrayList<Transaction> transactions=new ArrayList<>();
        for (Document document : database.getCollection("Transactions").find(new Document("from",email)).sort(new Document("date", -1))) {
            System.out.println(document);
            Transaction transaction=new Transaction();
            transaction.set_id(document.get("_id").toString());
            transaction.setAmount(Integer.parseInt(document.get("amount").toString()));
            transaction.setDate(document.get("date").toString());
            transaction.setTo(document.get("to").toString());
            transaction.setFrom(document.get("from").toString());
            transactions.add(transaction);
        }
        for (Document document : database.getCollection("Transactions").find(new Document("to",email)).sort(new Document("date", -1))) {
            System.out.println(document);
            Transaction transaction=new Transaction();
            transaction.set_id(document.get("_id").toString());
            transaction.setAmount(Integer.parseInt(document.get("amount").toString()));
            transaction.setDate(document.get("date").toString());
            transaction.setTo(document.get("to").toString());
            transaction.setFrom(document.get("from").toString());
            transactions.add(transaction);
        }
        Transaction[] transactionsArray=new Transaction[transactions.size()];
        transactionsArray=transactions.toArray(transactionsArray);
        return transactionsArray;
    }


}
