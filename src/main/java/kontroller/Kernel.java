package kontroller;

import model.BankAccount;
import model.NodeHistory;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Kernel {
    private PreparedStatement preparedStatement;
    private Connection connection;
    private DB db;
    public Kernel(){
        db = new DB();
        initConnect();
    }

    private void initConnect(){
        try {
            Class.forName(db.getForName());
            connection = DriverManager.getConnection(db.getUrl(),db.getLogin(),db.getPass());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private BankAccount getBankAccount(String numberBankAccount){
        BankAccount bankAccount = new BankAccount();
        String sql = "SELECT * from bank_account where number_bank_account = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,numberBankAccount);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()){
                bankAccount.setNameOwner(result.getString(2));
                bankAccount.setActive(result.getBoolean(3));
                bankAccount.setAccountNumber(result.getInt(1));
                bankAccount.setAccountBalance(result.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bankAccount;
    }
    private void updateBankAccount(BankAccount bankAccount){
        String sql = "update Bank.bank_account set bank_account.balance = ? where number_bank_account = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(bankAccount.getAccountBalance()));
            preparedStatement.setString(2,String.valueOf(bankAccount.getAccountNumber()));
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean transaction(String numberBankAccountSender,String numberBankAccountRecipient,int amount){
        BankAccount bankAccountSender = getBankAccount(numberBankAccountSender);
        BankAccount bankAccountRecipient = getBankAccount(numberBankAccountRecipient);
        if (bankAccountSender.getAccountBalance() < amount)
            return false;
        if (bankAccountSender.isActive()&&bankAccountRecipient.isActive()){
            bankAccountRecipient.addAccountBalance(amount);
            bankAccountSender.deductAccountBalance(amount);
            updateBankAccount(bankAccountSender);
            updateBankAccount(bankAccountRecipient);
            writeHistory(numberBankAccountSender,numberBankAccountRecipient,amount);
            return true;
        }
        return false;
    }
    public ArrayList<BankAccount> getListBankAccount(){
        BankAccount bankAccount;
        ArrayList<BankAccount> bankAccounts = new ArrayList<>();
        Statement statement = null;
        ResultSet result = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("select * from bank_account;");
            result = statement.getResultSet();
            while (result.next()){
                bankAccount = new BankAccount();
                bankAccount.setAccountNumber(result.getInt(1));
                bankAccount.setAccountBalance(result.getInt(4));
                bankAccount.setActive(result.getBoolean(3));
                bankAccount.setNameOwner(result.getString(2));
                bankAccounts.add(bankAccount);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                assert statement != null;
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                assert result!=null;
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bankAccounts;
    }
    private void writeHistory(String numberBankAccountSender, String numberBankAccountRecipient,int amount){
        PreparedStatement preparedStatement = null;
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sql = "insert into transaction_history set sender = ? , recipient = ?, amount = ?, dat = ?";
        try{

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(numberBankAccountSender));
            preparedStatement.setInt(2,Integer.parseInt(numberBankAccountRecipient));
            preparedStatement.setInt(3,amount);
            preparedStatement.setString(4,simpleDateFormat.format(date));
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public ArrayList<NodeHistory> getHistory(int numberOfLastItems){
        ArrayList<NodeHistory> arrayList = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        NodeHistory node = new NodeHistory();
        int maxId = 0;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("select max(id) from transaction_history;");
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                maxId = resultSet.getInt(1);
            }
            while (numberOfLastItems!=0){
                if (maxId==0)
                    break;
                node = new NodeHistory();
                statement.executeUpdate("select * from transaction_history where id = " + maxId);
                resultSet = statement.getResultSet();
                while (resultSet.next()){
                    node.setId(resultSet.getInt(1));
                    node.setNumberSender(resultSet.getInt(2));
                    node.setNumberRecipient(resultSet.getInt(3));
                    node.setAmount(resultSet.getInt(4));
                    node.setDate(resultSet.getString(5));
                }
                arrayList.add(node);
                maxId--;
                numberOfLastItems--;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return arrayList;
    }
    public void close(){
        try {
            assert connection!=null;
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Kernel kernel = new Kernel();
        ArrayList<NodeHistory> arrayList = kernel.getHistory(5);
        for (NodeHistory n:arrayList) {
            System.out.println(n);
        }
    }
}
