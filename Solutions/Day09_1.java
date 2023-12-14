package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day9_1 {
    public static void main(String[] args) {
        String fullText = "";
        String filepath = "Inputs\\Day 9.txt";
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
            Sum += Find_Next_Num(Array[i]);
        }

        System.out.println(Sum);



    }


    public static int Find_Next_Num(String Line) {

        String[] Line_Array = Line.split(" ");

        int[] Line_Nums = new int[Line_Array.length];

        for (int i = 0; i < Line_Array.length; i++) {
            Line_Nums[i] = Integer.valueOf(Line_Array[i]);
        }

        int[] Line_Difference = new int[Line_Nums.length - 1];

        for (int p = 0; p < Line_Difference.length; p++) {
            Line_Difference[p] = Line_Nums[p + 1] - Line_Nums[p];
        }

        Boolean All_Zero = true;

        for (int z = 0; z < Line_Difference.length; z++) {
            if (Line_Difference[z] != 0) {
                All_Zero = false;
            }
        }

        int Next_Num = 0;

        if (All_Zero == true) {
            Next_Num = Line_Nums[Line_Nums.length - 1] + Line_Difference[Line_Difference.length - 1];
        } else if (All_Zero == false) {
            int Next_Num_Difference = Find_Next_Num(Line_Difference);
            Next_Num = Line_Nums[Line_Nums.length - 1] + Next_Num_Difference;
        }



        return Next_Num;
    }

    public static int Find_Next_Num(int[] Line_Nums) {

        int[] Line_Difference = new int[Line_Nums.length - 1];

        for (int p = 0; p < Line_Difference.length; p++) {
            Line_Difference[p] = Line_Nums[p + 1] - Line_Nums[p];
        }

        Boolean All_Zero = true;

        for (int z = 0; z < Line_Difference.length; z++) {
            if (Line_Difference[z] != 0) {
                All_Zero = false;
            }
        }

        int Next_Num = 0;

        if (All_Zero == true) {
            Next_Num = Line_Nums[Line_Nums.length - 1] + Line_Difference[Line_Difference.length - 1];
        } else if (All_Zero == false) {
            int Next_Num_Difference = Find_Next_Num(Line_Difference);
            Next_Num = Line_Nums[Line_Nums.length - 1] + Next_Num_Difference;
        }

        return Next_Num;
    }
}
