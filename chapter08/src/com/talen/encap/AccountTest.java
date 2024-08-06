package com.talen.encap;

public class AccountTest {
    public static void main(String[] args) {
        Account account = new Account();
        account.setName("talen");
        account.setPasswd("8999");
        account.setBalance(10.0);
        System.out.println(account.info());

        Account jack = new Account("jack", "989098", 78.0);
        System.out.println(jack.info());
    }
}
