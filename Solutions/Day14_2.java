package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Day14_2 {

    private static Map<String, Integer> memo2 = new HashMap<>();
    
    public static void main(String[] args) {
        
        String fullText = "";
        String filepath = "Inputs\\Day 14.txt";
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


        String[] Lines = fullText.split("\n");

        String[][] Array = new String[Lines.length][Lines[0].length()];

        for (int i = 0; i < Lines.length; i++) {

            String[] Line_Array = Lines[i].split("");

            Array[i] = Line_Array;


        }


        for (int i = 1; i < 1000000000 + 1; i++) {

            Spin_Cycle(Array);


            String Key = To_String(Array);

            if (memo2.containsKey(Key)) {

                int Index = memo2.get(Key);

                for (int g = 1; g < (1000000000 + 1 - i) % (i - Index); g++) {
                    Spin_Cycle(Array);
                }

                break;


            } else { 

                memo2.put(Key, i);

            
            } 
        }

        long Sum = 0L;

        for (int i = 0; i < Array.length; i++) {

            for (int p = 0; p < Array[0].length; p++) {

                if (Array[i][p].equals("O")) {
                    Sum += Array.length - i;
                }



            }
        }


        System.out.println(Sum);


    }

    public static String[][] Spin_Cycle(String[][] Array_In) {

        String[][] Array_N = Roll_North(Array_In);
        String[][] Array_W = Roll_West(Array_N);
        String[][] Array_S = Roll_South(Array_W);
        String[][] Array_E = Roll_East(Array_S);
        

        return Array_E;


    }


    public static String[][] Roll_North(String[][] Array_In) {


        String[][] Array = Array_In;

        for (int p = 0; p < Array[0].length; p++) {

            boolean Movement_in_Column = false;


            for (int i = 0; i < Array.length; i++) {


                if (Array[i][p].equals("O") && i != 0) {

                    if ( Array[i - 1][p].equals(".")) {

                        Array[i][p] = ".";
                        Array[i-1][p] = "O";

                        Movement_in_Column = true;

                    }
                }

            }


            if (Movement_in_Column == true) {
                p--;
            }


        }

        return Array;

    }

    public static String To_String(String[][] Array) {

        String Return = "";

        for (int i = 0; i < Array.length; i++) {

            for (int p = 0; p < Array[0].length; p++) {

                Return += Array[i][p];

            }
        }

        return Return;


    }

    public static String[][] Roll_South(String[][] Array_In) {

        String[][] Array = Array_In;

        for (int p = 0; p < Array[0].length; p++) {

            boolean Movement_in_Column = false;


            for (int i = 0; i < Array.length; i++) {


                if (Array[i][p].equals("O") && i != Array.length - 1) {

                    if ( Array[i + 1][p].equals(".")) {

                        Array[i][p] = ".";
                        Array[i+1][p] = "O";

                        Movement_in_Column = true;

                    }
                }

            }


            if (Movement_in_Column == true) {
                p--;
            }


        }

        return Array;

    }

    public static String[][] Roll_East(String[][] Array_In) {

        String[][] Array = Array_In;

        int Row_Max = Array[0].length - 1;


        for (int i = 0; i < Array.length; i++) {

            boolean Movement_in_Row = false;


            for (int p = 0; p < Array[0].length; p++) {


                if (Array[i][p].equals("O") && p != Row_Max) {

                    if ( Array[i][p+1].equals(".")) {

                        Array[i][p] = ".";
                        Array[i][p+1] = "O";

                        Movement_in_Row = true;

                    }
                }

            }


            if (Movement_in_Row == true) {
                i--;
            }


        }

        return Array;

    }

    public static String[][] Roll_West(String[][] Array_In) {

        String[][] Array = Array_In;

        for (int i = 0; i < Array.length; i++) {

            boolean Movement_in_Row = false;


            for (int p = 0; p < Array[0].length; p++) {


                if (Array[i][p].equals("O") && p != 0) {

                    if ( Array[i][p-1].equals(".")) {

                        Array[i][p] = ".";
                        Array[i][p-1] = "O";

                        Movement_in_Row = true;

                    }
                }

            }


            if (Movement_in_Row == true) {
                i--;
            }


        }

        return Array;

    }



}


