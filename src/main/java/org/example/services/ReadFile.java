package main.java.org.example.services;

import java.io.File;

public class ReadFile {
    public static void main(String[] args) {
        File file = new File("../localDb/users.json");
        String absolutePath = file.getAbsolutePath();
        System.out.println("File path : " + absolutePath);
        // System.out.println(absolutePath);
    }
}