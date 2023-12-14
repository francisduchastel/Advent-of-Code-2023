package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1_2 {
    
    
    public static void main(String[] args) {
        String fullText = "";
        String filepath = "Inputs\\Day 1.txt";
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


        String[] Text_Array = fullText.split("[\n]+");

        int[] Number_Array = new int[Text_Array.length];

        for (int i = 0; i < Text_Array.length; i++) {


            String String_Wanted = Text_Array[i];

            String_Wanted = SplitNumber(String_Wanted, "one", 1);
            String_Wanted = SplitNumber(String_Wanted, "two", 2);
            String_Wanted = SplitNumber(String_Wanted, "three", 3);
            String_Wanted = SplitNumber(String_Wanted, "four", 4);
            String_Wanted = SplitNumber(String_Wanted, "five", 5);
            String_Wanted = SplitNumber(String_Wanted, "six", 6);
            String_Wanted = SplitNumber(String_Wanted, "seven", 7);
            String_Wanted = SplitNumber(String_Wanted, "eight", 8);
            String_Wanted = SplitNumber(String_Wanted, "nine", 9);

            String[] Split_Array = String_Wanted.split("");

            System.out.println(String_Wanted);

            String First_Number = "";
            String Last_Number = "";


            for (int p = 0; p < Split_Array.length;) {

                try {
                    int Test = Integer.valueOf(Split_Array[p]);
                    First_Number = Split_Array[p];
                    p = Split_Array.length;
                } catch (NumberFormatException e) {
                    p++;
                }
                
            }

            for (int q = (Split_Array.length - 1); q > -1;) {

                try {
                    int Test = Integer.valueOf(Split_Array[q]);
                    Last_Number = Split_Array[q];
                    q = -1;
                } catch (NumberFormatException e) {
                    q--;
                }
            }


            String FullNumber = First_Number + "" + Last_Number;

            Number_Array[i] = Integer.valueOf(FullNumber);

        }

        int Sum = 0;
        for (int i = 0; i < Number_Array.length; i ++) {
            Sum += Number_Array[i];
        }

        System.out.println(Sum);
    }



    public static String SplitNumber(String Input, String Number_String, int Number) {

        String Input_Appended = Input + "X";

        String[] Input_Array = Input_Appended.split(Number_String);


        String Return = "";
        if (Input_Array.length != 1) { 

            for (int i = 0; i < Input_Array.length; i++) {
                Return += Input_Array[i];

                if (i < Input_Array.length - 1) {
                    Return += Number_String + Number + Number_String;
                }
            }

        } else if (Input_Array.length == 1) {
            Return = Input;
        }

        return Return;

    }
}

