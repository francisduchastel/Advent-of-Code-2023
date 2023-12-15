package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day15_1 {
    public static void main(String[] args) {
        String fullText = "";
        String filepath = "Inputs\\Day 15.txt";
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


        String[] Values = fullText.split("[,\n]+");

        Long Sum = 0L;

        for (int i = 0; i < Values.length; i++) {

            Long Internal_Sum = 0L;


            for (int p = 0; p < Values[i].length(); p++) {

                Internal_Sum += (int) Values[i].charAt(p);

                Internal_Sum *= 17;

                Internal_Sum = Internal_Sum % 256;

            }

            Sum += Internal_Sum;


        }


        System.out.println(Sum);
    }
}
