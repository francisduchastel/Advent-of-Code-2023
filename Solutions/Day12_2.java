package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.Arrays;


public class Day12_2 {
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

        Long Sum = 0L;

        for (int i = 0; i < Array.length; i++) {

            String[] Symbol_Numbers = Array[i].split(" ");

            String[] Numbers_As_String = Symbol_Numbers[1].split(",");

            int[] Numbers = new int[Numbers_As_String.length * 5];

            String Line = "";

            for (int p = 0; p < Numbers.length; p++) {
                Numbers[p] = Integer.valueOf(Numbers_As_String[p % Numbers_As_String.length]);

                Line += Numbers[p] + " ";
            }


            String Symbols_1 = Symbol_Numbers[0];

            String Symbols = "";

            for (int t = 0; t < 4; t++) {
                Symbols += Symbols_1 + "?";
            }

            Symbols += Symbols_1;

            String[] Symbols_Split = Symbols.split("[.]+");

            if (Symbols_Split[0].equals("")) {
                String[] temp = new String[Symbols_Split.length - 1];

                for (int f = 0; f < temp.length; f++) {
                    temp[f] = Symbols_Split[f + 1];
                }

                Symbols_Split = temp;

            }

           

            int Number_Questions = Number_Question(Symbols);

            int[] Question_Index = Find_Questions(Symbols);

            
            Long Loop = Big_Loop(Symbols, Numbers, 0, Question_Index);

            System.out.println(Loop + " -- " + i);

            Sum += Loop;

            }

            




        System.out.println(Sum);


    }

    public static long Big_Loop(String Symbols, int[] Numbers, int Next_Question_Index, int[] Question_Index) {

        long Return = 0L;

        if (Next_Question_Index == Question_Index.length) {


            int[] Symbols_Numbers = Symbols_To_Numbers(Symbols);

            if (Arrays.equals(Symbols_Numbers, Numbers)) {
                Return++;
            }

        } else {

            String With_Period = Replace_Index(Symbols, Question_Index[Next_Question_Index], ".");

            if (Same_So_Far(With_Period, Numbers, Question_Index[Next_Question_Index]) == true) {


                Return += Big_Loop(With_Period, Numbers, Next_Question_Index + 1, Question_Index);
            }
            

            String With_Hash = Replace_Index(Symbols, Question_Index[Next_Question_Index], "#");

            if (Same_So_Far(With_Hash, Numbers, Question_Index[Next_Question_Index]) == true) {


                Return += Big_Loop(With_Hash, Numbers, Next_Question_Index + 1, Question_Index);
            }

        }




        return Return;

    }

    public static String Replace_Index(String Symbols, int index, String Replacement) {


        String[] Symbols_Array = Symbols.split("");

        Symbols_Array[index] = Replacement;

        String Return = "";

        for (int i = 0; i < Symbols_Array.length; i++) {

            Return += Symbols_Array[i];

        }

        return Return;

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

    public static int[] Symbols_To_Numbers(String Symbols) {


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


        int[] Return = new int[Symbols_Array.length];

        for (int i = 0; i < Return.length; i++) {
            Return[i] = Symbols_Array[i].length();
        }

        return Return;
    }

    public static boolean Component_Of(int[] Numbers, int[] Possible_Component) {

        boolean Return = false;

        for (int i = 0; i < Numbers.length; i++) {

            boolean does_work = true;

            for (int p = 0; p < Possible_Component.length; p++) {

                if (Possible_Component[p] != Numbers[ (p + i) % Numbers.length]) {
                    does_work = false;
                    break;
                }

            }

            if (does_work == true) {

                Return = true;

            }


        }

    return Return;

    }

    public static int[] Append_List(int[] Parent, int[] Adding) {

        int[] New_list = new int[Parent.length + Adding.length];

        for (int i = 0; i < Parent.length; i++) {

            New_list[i] = Parent[i];

        }

        for (int p = Parent.length; p < Parent.length + Adding.length; p++) {

            New_list[p] = Adding[p - Parent.length];

        }

        return New_list;

    }

    public static boolean Same_So_Far(String Symbols, int[] Numbers, int Index_up_to) {

        boolean Return = true;

        String[] Symbols_Array = Symbols.split("");

        
        String Symbols_so_Far = "";

        for (int i = 0; i <= Index_up_to; i++) {

            Symbols_so_Far += Symbols_Array[i];
        }

        int[] Numbers_so_Far = Symbols_To_Numbers(Symbols_so_Far);


        if (Numbers_so_Far.length > Numbers.length) {
            Return = false;

            return Return;
        }


        int End_Index = 0;

        if (Symbols_Array[Index_up_to].equals(".")) {
            End_Index = Numbers_so_Far.length;
        } else if (Symbols_Array[Index_up_to].equals("#")) {
            End_Index = Numbers_so_Far.length - 1;

            if (Numbers_so_Far[Numbers_so_Far.length - 1] > Numbers[Numbers_so_Far.length - 1]) {
            
                Return = false;

                return Return;

            }
        }

        for (int i = 0; i < End_Index; i++) {

            if (Numbers_so_Far[i] != Numbers[i]) {
                Return = false;
                break;
            }

        }

        



        return Return;

    } 
    
    
    public static boolean Numbers_So_Far(int[] Numbers, int[] Numbers_so_Far) {

        boolean Return = true;

        if (Numbers_so_Far.length > Numbers.length) {
            Return = false;
            return Return;
        }

        for (int i = 0; i < Numbers_so_Far.length; i++) {
            if (Numbers_so_Far[i] != Numbers[i]) {
                Return = false;
                return Return;
            }
        }

        return Return;

    }

}
