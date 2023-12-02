package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2_1 {

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

        final int Red_Balls = 12;
        final int Green_Balls = 13;
        final int Blue_Balls = 14;


        for (int i = 0; i < Text_Array.length; i++) {

            Boolean Possible = true;

            String Game = Text_Array[i];

            String[] Game_Split = Game.split("[;:]+");

            for (int p = 0; p < (Game_Split.length - 1); p++) {

                String[] Round_Split = Game_Split[p + 1].split("[  ,]+");


                for (int q = 0; q < Round_Split.length / 2; q++) {

                    int Number = Integer.valueOf(Round_Split[q*2 + 1]);

                    switch (Round_Split[(q * 2) + 2]) {
                        case "red":

                            if (Number > Red_Balls) {
                                Possible = false;
                            }
                            break;
                    
                        case "blue":

                            if (Number > Blue_Balls) {
                                Possible = false;
                            }

                            break;

                        case "green":

                            if (Number > Green_Balls) {
                                Possible = false;
                            }

                            break;
                    }

                }

            }


            if (Possible == true) {
                sum += i + 1;
            }


        }


        System.out.println(sum);

    }
}