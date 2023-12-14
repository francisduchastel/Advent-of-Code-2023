package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day6_2 {
    
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

        

        int Number_Wins = 0;

        Long Time = 44806572L;
        Long Distance = 208158110501102L;


        for (Long h = 1L; h < Time; h++) {

            if (h * (Time - h) > Distance) {
                Number_Wins++;
            }

        }

        System.out.println(Number_Wins);

        //---------------------------------------------------------------------------------------


        Double sqrt =  Math.sqrt(Math.pow(Time, 2) - 4 * -1 * -Distance);

        Long x1 = ( -Time + sqrt.longValue() ) / -2;
        Long x2 = ( -Time - sqrt.longValue() ) / -2;

        System.out.println(x2 - x1);

    }

}
