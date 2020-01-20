package cntroller;

import model.BankAccount;
import model.Client;
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
    //инициализировать коннект
    //после всех операция вызвать .close();
    private void initConnect(){
        try {
            Class.forName(db.getForName());
            connection = DriverManager.getConnection(db.getUrl(),db.getLogin(),db.getPass());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //изменить баланс номера счета
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
                if(preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //если счета активны и на счету отправителя есть достаточно средств то совершаем транзакцию
    public boolean transaction(String numberBankAccountSender,String numberBankAccountRecipient,int amount){
        BankAccount bankAccountSender = getBankAccount(numberBankAccountSender);
        BankAccount bankAccountRecipient = getBankAccount(numberBankAccountRecipient);
        //надо переписать чтобы получать данные из БД
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
    //возвращает наполненный из БД BankAccount принимает номер счета
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
                if (preparedStatement!=null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bankAccount;
    }
    //возвращает контейрер с наполненными BankAccount
    //надо переписать два похожих метода getBankAccount и getListBankAccount
    public ArrayList<BankAccount> getListBankAccount(){
        BankAccount bankAccount;
        ArrayList<BankAccount> bankAccounts = new ArrayList<>();
        Statement statement = null;
        ResultSet result = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("select number_bank_account from bank_account;");
            result = statement.getResultSet();
            while (result.next()){
                bankAccount = getBankAccount(result.getString(1));
                bankAccounts.add(bankAccount);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
               if (statement != null)
                   statement.close();
               if (result != null)
                   result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bankAccounts;
    }
    //пишем историю в БД можно получить методом getHistory()
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
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //должен возвращать контейнер с историей транзакций
    public ArrayList<NodeHistory> getHistory(){
        ArrayList<NodeHistory> arrayList = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        NodeHistory node = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("select * from transaction_history;");
            resultSet = statement.getResultSet();
            while (resultSet.next()){
                node = new NodeHistory();
                node.setId(resultSet.getInt(1));
                node.setNumberSender(resultSet.getInt(2));
                node.setNumberRecipient(resultSet.getInt(3));
                node.setAmount(resultSet.getInt(4));
                node.setDate(resultSet.getString(5));
                arrayList.add(node);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (statement != null)
                    statement.close();
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }
    //проверка клиента (доделать)
    public Client checkClient(String name, String password){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Client client = null;
        String sql = "select * from client where name = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                if (!resultSet.getString(4).equals(password)){
                    return null;
                }
                client = new Client();
                client.setId(resultSet.getInt(1));
                client.setNameClient(resultSet.getString(2));
                client.setAge(resultSet.getInt(3));
                client.setGender(resultSet.getString(5));
                client.setPassword(resultSet.getString(4));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement!=null)
                    preparedStatement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return client;
    }
    //закрть подключение к БД
    public void close(){
        try {
            if (connection!=null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Kernel kernel = new Kernel();
        ArrayList<NodeHistory> bankAccounts = kernel.getHistory();
        System.out.println(bankAccounts);
    }
}
