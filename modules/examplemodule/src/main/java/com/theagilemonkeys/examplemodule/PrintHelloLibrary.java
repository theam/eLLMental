package com.theagilemonkeys.examplemodule;

public class PrintHelloLibrary {
    public static String printHello() {

        var returnString = "Hello from inside the library";

        System.out.print(returnString);

        return returnString;
    }
}
