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
    public void closeAccount(String userID) throws IllegalAccessException{
            if(map.get(userID)>0){
                throw new IllegalAccessException("You still have money in the account");
            }
            else{
                map.remove(userID);
            }
    }
    public double checkBalance(String userID) throws NullPointerException{
        if(map.get(userID)==0) throw new NullPointerException("What money?");
        return map.get(userID);
    }
    public double depositMoney(String userID,double amount) throws NullPointerException{
        if(!map.containsKey(userID)) throw new NullPointerException("What money?");
        map.put(userID,map.get(userID)+amount);
        return amount;
    }

}