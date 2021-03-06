package com.greenfox.speting64.lionkingbank.model;

public class BankAccount {

    String name;
    int balance;
    String animalType;

    public BankAccount(String name, int balance, String animalType) {
        this.name = name;
        this.balance = balance;
        this.animalType = animalType;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
