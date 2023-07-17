package dev.dto;

import dev.bank.AccountType;
import dev.bank.BankAccount;
import dev.bank.BankCustomer;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private int routingNumber;
    private long lastTransactionId = 1;

    public Bank(int routingNumber) {
        this.routingNumber = routingNumber;
        customers = new HashMap<>();
    }

    // String = customerID;
    private final Map<String, BankCustomer> customers;

    public BankCustomer getCustomer(String id) {
        return customers.get(id);
    }

    public void addCustomer(String name,double checkingInitialDeposit,
                            double savingsInitialDeposite) {
        BankCustomer customer = new BankCustomer(name,checkingInitialDeposit,savingsInitialDeposite);
         customers.put(customer.getCustomerID(),customer);
    }

    public boolean doTransaction(String id, AccountType type, double amount) {
        BankCustomer customer = customers.get(id);
        if(customer != null) {
            BankAccount account = customer.getAccount(type);
            account.commitTransaction(routingNumber, lastTransactionId,
                    id, amount);
            lastTransactionId++;
        } else {
            System.out.println("Customer not found");
            return false;
        }
        return true;
    }

}
