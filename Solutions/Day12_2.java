package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

import java.util.Arrays;

public class Day12_2 {

    private static Map<String, Long> memo = new HashMap<>();

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

            for (int p = 0; p < Numbers.length; p++) {
                Numbers[p] = Integer.valueOf(Numbers_As_String[p % Numbers_As_String.length]);
            }


            String Symbols1 = Symbol_Numbers[0];

            String Symbols = "";

            for (int c = 0; c < 4; c++) {

                Symbols += Symbols1 + "?";

            }

            Symbols += Symbols1;

            //Solve to Part 1 Using Big Loop
            int[] Question_Index = Find_Questions(Symbols);

            Sum += Big_Loop(Symbols, Numbers, 0, Question_Index);

            System.out.println("Done with Index " + i);


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


                String to_String = To_String(With_Period, Numbers, Question_Index[Next_Question_Index]);

                if (memo.containsKey(to_String)) {

                    Return += memo.get(to_String);

                } else {

                    
                    long Loop = Big_Loop(With_Period, Numbers, Next_Question_Index + 1, Question_Index);

                    Return += Loop;

                    memo.put(to_String, Loop);


                }


                
            }
            

            String With_Hash = Replace_Index(Symbols, Question_Index[Next_Question_Index], "#");

            if (Same_So_Far(With_Hash, Numbers, Question_Index[Next_Question_Index]) == true) {

                String to_String = To_String(With_Hash, Numbers, Question_Index[Next_Question_Index]);

                if (memo.containsKey(to_String)) {

                    Return += memo.get(to_String);

                } else {

                    
                    long Loop = Big_Loop(With_Hash, Numbers, Next_Question_Index + 1, Question_Index);

                    Return += Loop;

                    memo.put(to_String, Loop);

                }


                
            }

        }




        return Return;

    }

    public static String To_String(String Symbols, int[] Numbers, int Index_Up_To) {

        String Return = "";

        String Left = What_Left(Symbols, Index_Up_To);

        char Hash_or_Period = Symbols.charAt(Index_Up_To);

        Return += Left + "," + Hash_or_Period;

        for (int i = 0; i < Numbers.length; i++) {
            Return += "," + Numbers[i];
        }

        String So_Far = What_so_Far(Symbols, Index_Up_To);

        int[] Num_so_Far = Symbols_To_Numbers(So_Far);

        for (int i = 0; i < Num_so_Far.length; i++) {

            Return += "-" + Num_so_Far[i];

        }


        
        return Return;

    }

    public static String What_Left(String Symbols, int Index_Up_To) {

        String[] Array = Symbols.split("");

        String Return = "";

        for (int i = Index_Up_To + 1; i < Array.length; i++) {

            Return += Array[i];

        }

        return Return;



    }

    public static String What_so_Far(String Symbols, int Index_Up_To) {

        String[] Array = Symbols.split("");

        String Return = "";

        for (int i = 0; i < Index_Up_To + 1; i++) {

            Return += Array[i];

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

}
