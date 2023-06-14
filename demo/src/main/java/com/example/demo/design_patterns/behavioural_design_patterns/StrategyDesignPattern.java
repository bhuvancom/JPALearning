package com.example.demo.design_patterns.behavioural_design_patterns;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

interface Calculator {
    float calculation(float a, float b);
}

//Add $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
class Add implements Calculator {
    public float calculation(float a, float b) {
        return a + b;
    }
}

//Sub $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
class Sub implements Calculator {
    public float calculation(float a, float b) {
        return a - b;
    }
}

//Mul $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
class mul implements Calculator {
    public float calculation(float a, float b) {
        return a * b;
    }
}

//Div $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
class div implements Calculator {
    public float calculation(float a, float b) {
        return a / b;
    }
}

//Mod $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
class mod implements Calculator {
    public float calculation(float a, float b) {
        return a % b;
    }
}

//Query $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
class Query {
    private Calculator calci;

    Query(Calculator calci) {
        this.calci = calci;
    }

    float executeStrategy(float num1, float num2) {
        return calci.calculation(num1, num2);
    }
}

public class StrategyDesignPattern {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the first value: ");
        float value1 = Float.parseFloat(br.readLine());
        System.out.print("Enter the second value: ");
        float value2 = Float.parseFloat(br.readLine());
        Query query = new Query(new Add());
        System.out.println("Addition = " + query.executeStrategy(value1, value2));
        query = new Query(new Sub());
        System.out.println("Subtraction = " + query.executeStrategy(value1, value2));
        query = new Query(new mul());
        System.out.println("Multiplication = " + query.executeStrategy(value1, value2));
        query = new Query(new div());
        System.out.println("Multiplication = " + query.executeStrategy(value1, value2));
        query = new Query(new mod());
        System.out.println("Multiplication = " + query.executeStrategy(value1, value2));
    }
}
