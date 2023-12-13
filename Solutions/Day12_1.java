package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Day12_1 {
    public static void main(String[] args) {
        String fullText = "";
        String filepath = "Inputs\\Day 12.txt";
        try {
         // try to open the file and extract its contents
            Scanner scn = new Scanner(new File(filepath));
            while (scn.hasNextLine()) {
            String line = scn.nextLine();
            fullText += line + "\n"; // nextLine() removes line breaks, so add them back in
            }
            scn.close(); // be nice and close the Scanner
        }
        catch (FileNotFoundException e) {
          // in case we fail to open the file, output a friendly message.
          System.out.println("Oh no... can't find the file!");
        }

        String[] Array = fullText.split("\n");

        int Sum = 0;

        for (int i = 0; i < Array.length; i++) {

            int Number_from_Symbols = 0;

            String[] Symbol_Numbers = Array[i].split(" ");

            String[] Numbers_As_String = Symbol_Numbers[1].split(",");

            int[] Numbers = new int[Numbers_As_String.length];

            for (int p = 0; p < Numbers.length; p++) {
                Numbers[p] = Integer.valueOf(Numbers_As_String[p]);
            }

            String Symbols = Symbol_Numbers[0];

            int Number_Questions = Number_Question(Symbols);

            int[] Question_Index = Find_Questions(Symbols);

            for (int z = 0; z < Math.pow(2, Number_Questions); z++) {

                String Binary = Integer.toBinaryString(z);

                while (Binary.length() < Number_Questions) {
                    Binary = "0" + Binary;
                }

                String Inputed_Questions = Replace_Questions(Symbols, Question_Index, Binary);
                

                if (Valid_String(Inputed_Questions, Numbers) == true) {
                

                    Number_from_Symbols++;


                }

            }

            Sum += Number_from_Symbols;

        }


        System.out.println(Sum);



    }


    public static boolean Valid_String(String Symbols, int[] Numbers) {

        String[] Symbols_Array_temp = Symbols.split("[.]+");

        String[] Symbols_Array = new String[0];


        if (Symbols.charAt(0) == '#') {
            Symbols_Array = Symbols_Array_temp;

        } else if (Symbols.charAt(0) == '.') {

            try {
                Symbols_Array = new String[Symbols_Array_temp.length - 1];
            } catch (NegativeArraySizeException e) {
                Symbols_Array = Symbols_Array_temp;
            }


            for (int i = 0; i < Symbols_Array.length; i++) {
            Symbols_Array[i] = Symbols_Array_temp[i + 1];
            }

        }

        boolean works = true;

        if (Symbols_Array.length != Numbers.length) {
            works = false;
        } else {

            for (int i = 0; i < Numbers.length; i++) {

                if (Symbols_Array[i].length() != Numbers[i]) {
                    works = false;
                }

            }

        }

        

        return works;

    }

    public static int Number_Question(String Symbols) {

        String[] Symbol_Array = Symbols.split("");

        int Return = 0;

        for (int i = 0; i < Symbol_Array.length; i++) {
            if (Symbol_Array[i].equals("?")) {
                Return++;
            }
        }

        return Return;

    }

    public static int[] Find_Questions(String Symbols) {

        String[] Symbol_Array = Symbols.split("");

        int[] Index_Array = new int[Number_Question(Symbols)];

        int p = 0;

        for (int i = 0; i < Symbol_Array.length; i++) {

            if (Symbol_Array[i].equals("?")) {
                Index_Array[p] = i;
                p++;
            }

        }

        return Index_Array;

    }
    
    public static String Replace_Questions(String Symbols, int[] Question_Index, String Binary) {

        String[] Binary_Array = Binary.split("");

        String[] Symbols_Array = Symbols.split("");

        for (int i = 0; i < Binary_Array.length; i++) {

            if (Binary_Array[i].equals("1")) {
                
                Symbols_Array[Question_Index[i]] = "#";
            
            } else {
                Symbols_Array[Question_Index[i]] = ".";
            }

        }

        String Return = "";

        for (int i = 0; i < Symbols_Array.length; i++) {
            Return += Symbols_Array[i];
        }

        return Return;

    }

}
