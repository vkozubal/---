package org.pti.poster.integration;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Map;
import java.util.Set;

public class HazelcastStarter {

    public static void main(String[] args) {

//        Config config = new Config();
//        config.getNetworkConfig().setPort(5701);
//        config.getNetworkConfig().setPortAutoIncrement(false);
//        config.setInstanceName("hztest");
        Set<HazelcastInstance> allHazelcastInstances = Hazelcast.getAllHazelcastInstances();
        System.out.println();
//        Map<String, Customer> mapCustomers = hz.getMap("customers");
//        mapCustomers.put("1", new Customer("Joe", "Smith"));
//        mapCustomers.put("2", new Customer("Ali", "Selam"));
//        mapCustomers.put("3", new Customer("Avi", "Noyan"));

//        for (Map.Entry<Object, Object> customer : hz.getMap("customers").entrySet()) {
//            System.out.println(customer);
//        }
    }
}
