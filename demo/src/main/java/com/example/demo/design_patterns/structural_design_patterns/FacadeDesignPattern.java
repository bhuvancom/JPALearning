package com.example.demo.design_patterns.structural_design_patterns;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

interface Franchise {
    public void Option();

    public void Cost();
}

//KFC ################################################
class KFC implements Franchise {
    public void Option() {
        System.out.println("KFC");
    }

    public void Cost() {
        System.out.println("Rs 1,00,00,000");
    }
}

//McDonalds  ######################################
class McDonalds implements Franchise {
    public void Option() {
        System.out.println("McDonalds");
    }

    public void Cost() {
        System.out.println("Rs 90,00,000");
    }
}

//Dominos  ######################################
class Dominos implements Franchise {
    public void Option() {
        System.out.println("Dominos");
    }

    public void Cost() {
        System.out.println("Rs 80,00,000");
    }
}

//FranchiseServiceReg   ################################
class FranchiseServiceReg {
    private Franchise KFC;
    private Franchise McDonalds;
    private Franchise Dominos;

    public FranchiseServiceReg() {
        KFC = new KFC();
        McDonalds = new McDonalds();
        Dominos = new Dominos();
    }

    public void BuyKFCFranchise() {
        KFC.Option();
        KFC.Cost();
    }

    public void BuyMcDonaldsFranchise() {
        McDonalds.Option();
        McDonalds.Cost();
    }

    public void BuyDominosFranchise() {
        Dominos.Option();
        Dominos.Cost();
    }
}

public class FacadeDesignPattern {
    private static int choice;

    public static void main(String args[]) throws NumberFormatException, IOException {
        do {
            System.out.print("Welcome to Franchise Service Registration...!n");
            System.out.print(" 1. KFC n");
            System.out.print(" 2. McDonalds n");
            System.out.print(" 3. Dominos n");
            System.out.print(" 4. EXIT n");
            System.out.print("Please Enter your Franchise Option Number: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            choice = Integer.parseInt(br.readLine());
            FranchiseServiceReg sp = new FranchiseServiceReg();

            switch (choice) {
                case 1: {
                    sp.BuyKFCFranchise();
                }
                break;
                case 2: {
                    sp.BuyMcDonaldsFranchise();
                }
                break;
                case 3: {
                    sp.BuyDominosFranchise();
                }
                break;
                default: {
                    System.out.println("Please Check the input and try again");
                }
                return;
            }

        } while (choice != 4);
    }
}

