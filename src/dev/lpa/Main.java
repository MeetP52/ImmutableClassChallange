package dev.lpa;

import dev.bank.AccountType;
import dev.bank.BankAccount;
import dev.bank.BankCustomer;
import dev.dto.Bank;

public class Main {
    public static void main(String[] args) {
//        BankAccount account = new BankAccount(BankAccount.AccountType.CHECKING,500);
//        System.out.println(account);
//
//        BankCustomer joe = new BankCustomer("Joe",500,1000);
//        System.out.println(joe);

        Bank bankOfAmerica = new Bank(3214567);
        bankOfAmerica.addCustomer("Joe",500,5000);


        BankCustomer joe = bankOfAmerica.getCustomer("000000010000000");
        System.out.println(joe);
        if(bankOfAmerica.doTransaction(joe.getCustomerID(), AccountType.CHECKING,
                35)) {
            System.out.println(joe);
        }
        if(bankOfAmerica.doTransaction(joe.getCustomerID(), AccountType.CHECKING,
                -535)) {
            System.out.println(joe);
        }
        BankAccount checkings = joe.getAccount(AccountType.CHECKING);
        var transactions = checkings.getTransactions();
        transactions.forEach((k,v) -> System.out.println(k + ": " + v));

        System.out.println("--------------------------------------------");

//        for(var tx : transactions.values()) {
//            tx.setCustomerId(2);
//            tx.setTransactionAmount(1000);
//        }
        joe.getAccount(AccountType.CHECKING).getTransactions().clear();
        transactions.forEach((k,v) -> System.out.println(k + ": " + v));
        System.out.println("--------------------------------------------");
        joe.getAccount(AccountType.CHECKING).getTransactions().forEach(
                (k,v) -> System.out.println(k + ": " + v)
        );

    }
}
