package dev.bank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankCustomer {
    private static int lastCustomerID = 10_000_000;
    private final String name;
    private final int customerID;
    private final List<BankAccount> accounts = new ArrayList<>();

    public BankCustomer(String name,double checkingAmount, double savingsAmount) {
        this.name = name;
        this.customerID = lastCustomerID++;
        accounts.add(new BankAccount(AccountType.CHECKING,checkingAmount));
        accounts.add(new BankAccount(AccountType.SAVINGS,savingsAmount));
    }

    public String getName() {
        return name;
    }

    public String getCustomerID() {
        return "%015d".formatted(customerID);
    }

    public List<BankAccount> getAccounts() {
        return List.copyOf(accounts);
    }

    public BankAccount getAccount(AccountType type) {
      for(BankAccount account : accounts) {
          if(account.getAccount() == type) {
              return account;
          }
      }
      return null;
    }

    @Override
    public String toString() {
        String [] accountStrings = new String[accounts.size()];
        Arrays.setAll(accountStrings, i -> accounts.get(i).toString());
        return "Customer: %s (id:%015d)%n\t%s%n".
                formatted(name,customerID,
                        String.join("\n\t",accountStrings));
    }
}
