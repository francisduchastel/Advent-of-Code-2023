package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Day4_2 {
    public static void main(String[] args) {
        String fullText = "";
        String filepath = "Inputs\\Day 4.txt";
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

        String[] Input_Array = fullText.split("\n");

        int Sum = 0;

        int[] Number_Each_Card = new int[209];

        for (int i = 0; i < 209; i++) {
            Number_Each_Card[i] = 1;
        }


        for (int i = 0; i < Input_Array.length; i++) {

            String[] Card_Array = Input_Array[i].split("[:|]+");

            String[] Winning_Numbers_temp = Card_Array[1].split(" ");

            String[] Winning_Numbers = new String[Winning_Numbers_temp.length - 1];

            for (int p = 0; p < Winning_Numbers.length; p++) {
                Winning_Numbers[p] = Winning_Numbers_temp[p + 1];
            }

            String[] Your_Numbers_temp = Card_Array[2].split("[ ]+");

            String[] Your_Numbers = new String[Your_Numbers_temp.length - 1];

            for (int p = 0; p < Your_Numbers.length; p++) {
                Your_Numbers[p] = Your_Numbers_temp[p + 1];
            }


            int Number_Match = 0;
            for (int z = 0; z < Your_Numbers.length; z++) {

                String Test_Number = Your_Numbers[z];

                for (int y = 0; y < Winning_Numbers.length; y++) {
                    if (Test_Number.equals(Winning_Numbers[y])) {
                        Number_Match++;
                    }

                }


            }
        
            for (int q = 1; q < Number_Match + 1; q++) {
                Number_Each_Card[i + q] += Number_Each_Card[i];
            }


        }


        for (int i = 0; i < Number_Each_Card.length; i++) {
            Sum += Number_Each_Card[i];
        }

        System.out.println(Sum);

    }
}
