package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2_2 {

    public static void main(String[] args) {
        
        String fullText = "";
        String filepath = "Inputs\\Day 2.txt";
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

        int sum = 0;


        for (int i = 0; i < Text_Array.length; i++) {

            String Game = Text_Array[i];

            int Min_Red = 0;
            int Min_Blue = 0;
            int Min_Green = 0;

            String[] Game_Split = Game.split("[;:]+");


            for (int p = 0; p < (Game_Split.length - 1); p++) {

                String[] Round_Split = Game_Split[p + 1].split("[ ,]+");

                for (int q = 0; q < Round_Split.length / 2; q++) {

                    int Number = Integer.valueOf(Round_Split[q*2 + 1]);

                    switch (Round_Split[(q * 2) + 2]) {
                        case "red":

                            if (Number > Min_Red) {
                                Min_Red = Number;
                            }

                            break;
                    
                        case "blue":

                            if (Number > Min_Blue) {
                                Min_Blue = Number;
                            }

                            break;

                        case "green":

                            if (Number > Min_Green) {
                                Min_Green = Number;
                            }

                            break;
                    }

                }

            }


            int Power = Min_Red * Min_Blue * Min_Green;

            sum += Power;


        }


        System.out.println(sum);

    }
}