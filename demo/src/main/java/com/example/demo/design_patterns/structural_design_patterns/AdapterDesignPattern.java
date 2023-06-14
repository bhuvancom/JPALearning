package com.example.demo.design_patterns.structural_design_patterns;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//BookHolder #########################################################
class BookHolder extends LibraryDetails implements LibraryCard {
    public void giveLibraryDetails() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the Library account holder name :");
            String readername = br.readLine();
            System.out.print("n");
            System.out.print("Enter the account number of the library:");
            long accno = Long.parseLong(br.readLine());
            System.out.print("n");
            System.out.print("Enter the Library name :");
            String libraryname = br.readLine();
            setAccHolderName(readername);
            setAccNumber(accno);
            setLibraryName(libraryname);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getLibraryCard() {
        long accno = getAccNumber();
        String accholdername = getAccHolderName();
        String lname = getLibraryName();
        return ("The Account number " + accno + " of " + accholdername + " in " + lname + " Library is valid and authenticated for issuing the Library card. ");
    }
}

//LibraryCard #########################################################
interface LibraryCard {
    public void giveLibraryDetails();

    public String getLibraryCard();
}

//library details #########################################################
class LibraryDetails {
    private String LibraryName;
    private String BookHolderName;
    private long BookHolderID;

    public String getLibraryName() {
        return LibraryName;
    }

    public void setLibraryName(String LibraryName) {
        this.LibraryName = LibraryName;
    }

    public String getAccHolderName() {
        return BookHolderName;
    }

    public void setAccHolderName(String BookHolderName) {
        this.BookHolderName = BookHolderName;
    }

    public long getAccNumber() {
        return BookHolderID;
    }

    public void setAccNumber(long BookHolderID) {
        this.BookHolderID = BookHolderID;
    }
}

public class AdapterDesignPattern {
    public static void main(String args[]) {
        LibraryCard targetInterface = new BookHolder();
        targetInterface.giveLibraryDetails();
        System.out.print(targetInterface.getLibraryCard());
    }
}
