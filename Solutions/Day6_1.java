package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day6_1 {
    
    public static void main(String[] args) {
        
        String fullText = "";
        String filepath = "Inputs\\Day 6.txt";
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



        String[] fullText_Split = fullText.split("[ \n]+");

        int[] Times = new int[4];
        int[] Distances = new int[4];
        for (int i = 1; i < 5; i++) {
            Times[i - 1] = Integer.valueOf(fullText_Split[i]);

            Distances[i - 1] = Integer.valueOf(fullText_Split[i + 5]);
        }

        int[] Number_Wins_Array = new int[4];

        for (int i = 0; i < 4; i++) {

            int Number_Wins = 0;

            int Time = Times[i];
            int Distance = Distances[i];


            for (int h = 1; h < Time; h++) {

                if (h * (Time - h) > Distance) {
                    Number_Wins++;
                }

            }

            Number_Wins_Array[i] = Number_Wins;

        }

        int Product = 1;

        for (int i = 0; i < 4; i++) {
            Product *= Number_Wins_Array[i];
        }

        System.out.println(Product);

    }

}
