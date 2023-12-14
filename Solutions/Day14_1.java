package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day14_1 {
    
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

        for (int i = 0; i < Array.length; i++) {

            String Line = "";

            for (int p = 0; p < Array[0].length; p++) {

                Line += Array[i][p];

            }

            System.out.println(Line);


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


}


