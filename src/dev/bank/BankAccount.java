package dev.bank;

import dev.dto.Transaction;

import java.util.LinkedHashMap;
import java.util.Map;

public class BankAccount {

    private final AccountType account;
    private double balance;

    private Map<Long, Transaction> transactions = new LinkedHashMap<>();

    BankAccount(AccountType account, double balance) {
        this.account = account;
        this.balance = balance;
    }

    public AccountType getAccount() {
        return account;
    }

    public double getBalance() {
        return balance;
    }

    public Map<Long, String> getTransactions() {
        Map<Long,String> txMap = new LinkedHashMap<>();
        for(var tx: transactions.entrySet()) {
            txMap.put(tx.getKey(),tx.getValue().toString());
        }
        return txMap;
    }

    private boolean withdrawal(double amount) {
        if(balance + amount < 0) {
            return false;
        }
        balance += amount;
        return true;
    }

    private void deposit(double amount) {
        balance+= amount;
    }


    public void commitTransaction(int routingNumber, long transactionId,
                                  String customerId, double amount) {
        if(amount < 0) {
            if(!withdrawal(amount)) {
                System.out.println("Cannot withdrawl $" + amount + ", not enough in " + account);
            } else {
                transactions.put(transactionId,new Transaction(routingNumber,
                        Integer.parseInt(customerId),transactionId,amount));
            }
        } else {
            deposit(amount);
            transactions.put(transactionId,new Transaction(routingNumber,
                    Integer.parseInt(customerId),transactionId,amount));
        }
    }

    @Override
    public String toString() {
        return "%s $%.2f".formatted(account,balance);
    }
}
