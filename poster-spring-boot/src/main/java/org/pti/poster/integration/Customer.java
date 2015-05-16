package org.pti.poster.integration;

public class Customer {
    public Customer(String name, String secondName) {
        this.name = name;
        this.secondName = secondName;
    }

    String name;
    String secondName;

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                '}';
    }
}