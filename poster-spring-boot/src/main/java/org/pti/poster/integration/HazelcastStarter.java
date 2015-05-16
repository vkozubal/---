package org.pti.poster.integration;

import java.util.Map;
import java.util.Collection;
import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastStarter {

    public static void main(String[] args) {

        Config cfg = new Config();
        HazelcastInstance hz = HazelcastStarter.newHazelcastInstance(cfg);
        Map<String, Customer> mapCustomers = hz.getMap("customers");
        mapCustomers.put("1", new Customer("Joe", "Smith"));
        mapCustomers.put("2", new Customer("Ali", "Selam"));
        mapCustomers.put("3", new Customer("Avi", "Noyan"));

        Collection<Customer> colCustomers = mapCustomers.values();
        for (Customer customer : colCustomers) {
            // process customer
        }
    }
}
