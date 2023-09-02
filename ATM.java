import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InaccessibleObjectException;
import java.nio.file.*;
import java.sql.SQLDataException;
import java.util.HashMap;

public class ATM{
    private HashMap<String,Double> map;
    public ATM(HashMap<String,Double> map){
        this.map=map;
    }
    public void openAccount(String userID, double amount) throws SQLDataException{
        if(map.containsKey(userID)) {
            throw new SQLDataException("Nice try Capone");
    }
    else{
        map.put(userID,amount);
    }
    }
    public void closeAccount(String userID) throws ArithmeticException{
            if(!map.containsKey(userID)||map.get(userID)>0){
                throw new ArithmeticException("You still have money in the account");
            }
            else{
                map.remove(userID);
            }
    }
    public double checkBalance(String userID) throws NullPointerException{
        if(map.get(userID)==0) throw new NullPointerException("You are Broke");
        return map.get(userID);
    }
    public double depositMoney(String userID,double amount) {
        if(!map.containsKey(userID)) throw new InaccessibleObjectException("Error 404");
        map.put(userID,map.get(userID)+amount);
        return amount;
    }

    public double withdrawMoney(String userID, double amount){
        if(!map.containsKey(userID)||map.get(userID)<amount){
            throw new InaccessibleObjectException("You can't withdraw money you don't have");
        }
        map.put(userID,map.get(userID)-amount);
        return map.get(userID);
    }

    public boolean transferMoney(String fromAccount, String toAccount, double amount) throws IllegalAccessException{
        withdrawMoney(fromAccount, amount);
        depositMoney(toAccount, amount);
        return true;
    }

    public void audit() throws IOException{
        final String fileName = "AccountAudit.txt";
        Files.deleteIfExists(Paths.get(fileName));
        PrintWriter pw = new PrintWriter(fileName);
        for(String str : map.keySet()){
            pw.write(str+":"+map.get(str)+"\n");
        }
        pw.close();
    }

}