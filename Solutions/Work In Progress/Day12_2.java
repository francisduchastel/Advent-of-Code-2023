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

            int[] Numbers = new int[Numbers_As_String.length];

            for (int p = 0; p < Numbers.length; p++) {
                Numbers[p] = Integer.valueOf(Numbers_As_String[p % Numbers_As_String.length]);
            }


            String Symbols = Symbol_Numbers[0];

/* 
            //Solve to Part 1 Using Big Loop
            int[] Question_Index = Find_Questions(Symbols);

            Sum += Big_Loop(Symbols, Numbers, 0, Question_Index);

 */
            String Symbols_S_M = Symbols += "?";

            String[] Possible_Starts = new String[0];
            String[] Possible_Middles = new String[0];
            String[] Possible_Ends = new String[0];

            int Number_Questions_S_M = Number_Question(Symbols_S_M);

            int[] Question_Index_S_M = Find_Questions(Symbols_S_M);

            for (int z = 0; z < Math.pow(2, Number_Questions_S_M); z++) {

                String Binary = Integer.toBinaryString(z);

                while (Binary.length() < Number_Questions_S_M) {
                    Binary = "0" + Binary;
                }

                String Inputed_Questions = Replace_Questions(Symbols_S_M, Question_Index_S_M, Binary);

                if (Same_So_Far(Inputed_Questions, Numbers, Inputed_Questions.length() - 1) == true) {

                    String[] temp_Start = new String[Possible_Starts.length + 1];

                    for (int PS = 0; PS < Possible_Starts.length; PS++) {

                        temp_Start[PS] = Possible_Starts[PS];

                    }

                    temp_Start[temp_Start.length - 1] = Inputed_Questions;

                    Possible_Starts = temp_Start;



                    String[] temp_Middle = new String[Possible_Middles.length + 1];

                    for (int PM = 0; PM < Possible_Middles.length; PM++) {

                        temp_Middle[PM] = Possible_Middles[PM];

                    }

                    temp_Middle[temp_Middle.length - 1] = Inputed_Questions;

                    Possible_Middles = temp_Middle;

                } else if (Component_Of(Numbers, Inputed_Questions) == true) {

                    String[] temp_Middle = new String[Possible_Middles.length + 1];

                    for (int PM = 0; PM < Possible_Middles.length; PM++) {

                        temp_Middle[PM] = Possible_Middles[PM];

                    }

                    temp_Middle[temp_Middle.length - 1] = Inputed_Questions;

                    Possible_Middles = temp_Middle;

                }

                String Inputed_Questions_End = Remove_Last(Inputed_Questions);

                if (Valid_End(Inputed_Questions_End, Numbers) == true && Inputed_Questions.charAt(Inputed_Questions.length() - 1) == '#') {

                    String[] temp_End = new String[Possible_Ends.length + 1];

                    for (int PS = 0; PS < Possible_Ends.length; PS++) {

                        temp_End[PS] = Possible_Ends[PS];

                    }

                    temp_End[temp_End.length - 1] = Inputed_Questions_End;

                    Possible_Ends = temp_End;
                }



            }

            String[] Possible_Middle_Pairs = new String[0];

            for (int f1 = 0; f1 < Possible_Middles.length; f1++) {

                for (int f2 = 0; f2 < Possible_Middles.length; f2++) {

                    String Pair = Possible_Middles[f1] + Possible_Middles[f2];

                    if (Component_Of(Numbers, Pair) == true) {


                        String[] Temp = new String[Possible_Middle_Pairs.length + 1];

                        for (int y = 0; y < Possible_Middle_Pairs.length; y++) {

                            Temp[y] = Possible_Middle_Pairs[y];

                        }

                        Temp[Temp.length - 1] = Pair;


                        Possible_Middle_Pairs = Temp;


                    }

                }

            }

            String[] Possible_Middle_Trips = new String[0];

            for (int f1 = 0; f1 < Possible_Middle_Pairs.length; f1++) {

                for (int f2 = 0; f2 < Possible_Middles.length; f2++) {

                    String Triple = Possible_Middle_Pairs[f1] + Possible_Middles[f2];

                    if (Component_Of(Numbers, Triple) == true) {


                        String[] Temp = new String[Possible_Middle_Trips.length + 1];

                        for (int y = 0; y < Possible_Middle_Trips.length; y++) {

                            Temp[y] = Possible_Middle_Trips[y];

                        }

                        Temp[Temp.length - 1] = Triple;


                        Possible_Middle_Trips = Temp;


                    }

                }

            }


            System.out.println("Starts: " + Possible_Starts.length + " Middles: " + Possible_Middle_Trips.length + " Ends: " + Possible_Ends.length);


            Long Number_Added = 0L;
            
            String List = "";

            for (int t1 = 0; t1 < Possible_Starts.length; t1++) {

                List = Possible_Starts[t1];

                for (int t2 = 0; t2 < Possible_Middle_Trips.length; t2++) {

                    String List_2 = List + Possible_Middle_Trips[t2];

                    if (Same_So_Far(List_2, Numbers, List_2.length() - 1) == true) {

                        for (int t3 = 0; t3 < Possible_Ends.length; t3++) {

                            String List_3 = List_2 + Possible_Ends[t3];

                            int[] List_3_Numbers = Symbols_To_Numbers(List_3);

                            if (Arrays.equals(List_3_Numbers, Numbers) == true) {

                                Number_Added++;

                            }

                        }



                    }

                }


            }


            System.out.println(Number_Added + " --- " + i);

            Sum += Number_Added;


        } 


        System.out.println(Sum);


        


    }


    //Solves Part 1 quicker
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

    public static boolean Component_Of(int[] Numbers, String Symbols) {

        int[] Possible_Component = Symbols_To_Numbers(Symbols);

        boolean Return = false;

        for (int i = 0; i < Numbers.length / 5; i++) {

            boolean does_work = true;

            for (int p = 0; p < Possible_Component.length; p++) {

                if (Symbols.charAt(0) == '#' && Symbols.charAt(Symbols.length() - 1) == '#') {

                    if (p == 0 || p == Possible_Component.length - 1) {

                        if (Possible_Component[p] > Numbers[i + p % (Numbers.length / 5)]) {
                            does_work = false;
                            break;
                        }

                    } else {

                        if (Possible_Component[p] != Numbers[i + p % (Numbers.length / 5)]) {

                            does_work = false;
                            break;
                        }

                    }

                } else if (Symbols.charAt(0) == '.' && Symbols.charAt(Symbols.length() - 1) == '#') {

                    if (p == Possible_Component.length - 1) {

                        if (Possible_Component[p] > Numbers[i + p % (Numbers.length / 5)]) {
                            does_work = false;
                            break;
                        }

                    } else {

                        if (Possible_Component[p] != Numbers[i + p % (Numbers.length / 5)]) {

                            does_work = false;
                            break;
                        }

                    }

                } else if (Symbols.charAt(0) == '#' && Symbols.charAt(Symbols.length() - 1) == '.') {

                    if (p == 0) {

                        if (Possible_Component[p] > Numbers[i + p % (Numbers.length / 5)]) {
                            does_work = false;
                            break;
                        }

                    } else {

                        if (Possible_Component[p] != Numbers[i + p % (Numbers.length / 5)]) {

                            does_work = false;
                            break;
                        }

                    }

                } else if (Symbols.charAt(0) == '.' && Symbols.charAt(Symbols.length() - 1) == '.') {

                    if (Possible_Component[p] != Numbers[i + p % (Numbers.length / 5)]) {

                        does_work = false;
                        break;
                    }

                }


            }

            if (does_work == true) {

                Return = true;

            }


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

    public static boolean Valid_End(String Symbols, int[] Numbers) {

        boolean Return = true;

        int[] Symbols_Numbers = Symbols_To_Numbers(Symbols);

        int p = Numbers.length - 1;

        int Index = 0;

        if (Symbols.charAt(0) == '#') {
            Index = 1;
        }

        for (int i = Symbols_Numbers.length - 1; i >= Index; i--) {

            if (p < 0) {
                p = Numbers.length - 1;
            }

            if (Symbols_Numbers[i] != Numbers[p]) {

                Return = false;
                return Return;

            }

            p--;


        }

        if (Symbols.charAt(0) == '#') {
            if (Symbols_Numbers[0] > Numbers[p]) {
                Return = false; 
                return Return;
            }
        }

        

        return Return;

    }

    public static String Remove_Last(String String) {

        String[] Array = String.split("");

        String New = "";

        for (int i = 0; i < Array.length - 1; i++) {

            New += Array[i];

        }

        return New;

    }

}
