package advent;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

// 44338290487 <
// 37989541620
public class Day18_2 {
    public static void main(String[] args) {
        String fullText = "";
        String filepath = "Inputs\\Day 18.txt";
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

        Long[][] Points = new Long[Lines.length][2];

        Long x_coord = 0L;
        Long y_coord = 0L;

        Long Perimeter = 0L;

        for (int i = 0; i < Lines.length; i++) {

            String[] Instructions = Lines[i].split("[ ()#]+");

            /* String direction = Instructions[0];
            String Length = Instructions[1]; */

            String hex_deminal = Instructions[2];

            
            String direction_h = "";
            String Length_h = "";

            for (int l = 0; l < hex_deminal.length(); l++) {
                if (l != hex_deminal.length() - 1) {
                    Length_h += hex_deminal.charAt(l);
                } else {
                    direction_h += hex_deminal.charAt(l);
                }
            }

            String Length = "" + Integer.parseInt(Length_h, 16);
            String direction = "";
            if (direction_h.equals("0")) {
                direction += "R";
            } else if (direction_h.equals("1")) {
                direction += "D";
            } else if (direction_h.equals("2")) {
                direction += "L";
            } else if (direction_h.equals("3")) {
                direction += "U";
            }
 


            if (direction.equals("D")) {

                y_coord -= Integer.valueOf(Length);

                Perimeter += Integer.valueOf(Length);

            } else if (direction.equals("U")) {

                y_coord += Integer.valueOf(Length);
                Perimeter += Integer.valueOf(Length);

            } else if (direction.equals("R")) {

                x_coord += Integer.valueOf(Length);
                Perimeter += Integer.valueOf(Length);

            } else if (direction.equals("L")) {

                x_coord -= Integer.valueOf(Length);
                Perimeter += Integer.valueOf(Length);

            }

            Long[] Pair = new Long[2];
            
            Pair[0] = x_coord;
            Pair[1] = y_coord;

            Points[i] = Pair;

        }



        long Sum = 0L;

        for (int i = 0; i < Points.length; i++) {

            if (i != Points.length - 1) {
                Sum += ( ((Points[i][1] + Points[i + 1][1]) / 2) * (Points[i][0] - Points[i + 1][0]) );

            } else if (i == Points.length - 1) {
                Sum += ( ((Points[i][1] + Points[0][1]) / 2) * (Points[i][0] - Points[0][0]) );

            }

           /*  if (i !=  Points.length - 1) {
                Sum += ( (Points[i][0] * Points[i + 1][1]) - (Points[i + 1][0] * Points[i][1]) );

            } else if (i == Points.length - 1) {

                Sum += ( (Points[i][0] * Points[0][1]) - (Points[0][0] * Points[i][1]) );

            } */

        }

        Sum = Math.abs(Sum);

        Sum += (Perimeter / 2) + 1;

        System.out.println(Sum);
        

    }
}
