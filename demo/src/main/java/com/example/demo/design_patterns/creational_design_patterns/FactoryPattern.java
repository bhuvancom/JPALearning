package com.example.demo.design_patterns.creational_design_patterns;

import java.io.*;

abstract class CellularPlan {
    protected double rate;

    abstract void getRate();

    public void processBill(int minutes) {
        System.out.println(minutes * rate);
    }
}

class AbcNetwork extends CellularPlan {
    public void getRate() {
        rate = 1.50;
    }
}

class PqrNetwork extends CellularPlan {
    public void getRate() {
        rate = 1.75;
    }
}

class SelectNetworkFactory {
    public CellularPlan getPlan(String planType) {
        if (planType == null) {
            return null;
        }
        if (planType.equalsIgnoreCase("abcNetwork")) {
            return new AbcNetwork();
        } else if (planType.equalsIgnoreCase("pqrNetwork")) {
            return new PqrNetwork();
        }
        return null;
    }
}


public class FactoryPattern {
    public static void main(String args[]) throws IOException {
        SelectNetworkFactory planFactory = new SelectNetworkFactory();
        System.out.print("Enter the name of network for which the bill will be generated: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String networkName = br.readLine();
        System.out.print("Enter the number of minutes for bill will be calculated: ");
        int minutes = Integer.parseInt(br.readLine());
        CellularPlan p = planFactory.getPlan(networkName);
        System.out.print("Bill amount for " + networkName + " of " + minutes + " units is: ");
        p.getRate();
        p.processBill(minutes);
    }
}