package advent;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

// 34735 >

public class Day18_1 {
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

        String[][] Map = new String[400][475];

        for (int i = 0; i < Map.length; i++) {
            for (int p = 0; p < Map[0].length; p++) {
                Map[i][p] = ".";
            }
        }

        int x_coord = 250;
        int y_coord = 250;

        for (int i = 0; i < Lines.length; i++) {

            String[] Instructions = Lines[i].split(" ");

            String direction = Instructions[0];
            String Length = Instructions[1];

            if (direction.equals("D")) {

                for (int p = 1; p <= Integer.valueOf(Length); p++) {
                    Map[y_coord + p][x_coord] = "#";
                }

                y_coord += Integer.valueOf(Length);

            } else if (direction.equals("U")) {

                for (int p = 1; p <= Integer.valueOf(Length); p++) {
                    Map[y_coord - p][x_coord] = "#";
                }

                y_coord -= Integer.valueOf(Length);

            } else if (direction.equals("R")) {

                for (int p = 1; p <= Integer.valueOf(Length); p++) {
                    Map[y_coord][x_coord + p ] = "#";
                }

                x_coord += Integer.valueOf(Length);

            } else if (direction.equals("L")) {

                for (int p = 1; p <= Integer.valueOf(Length); p++) {
                    Map[y_coord][x_coord - p ] = "#";
                }

                x_coord -= Integer.valueOf(Length);

            }

        }


        for (int i = 0; i < Map.length; i++) {
            String Line = "";

            for (int p_c = 0; p_c < Map[i].length; p_c++) {
                Line += Map[i][p_c];
            }

            if (Line.split("[.]+").length == 3) {
                for (int p = 0; p < Map[i].length - 1; p++) {

                
            
                    if (Map[i][p].equals("#") && Map[i][p + 1].equals(".")) {


                    for (int p3 = p + 1; p3 < Map[i].length; p3++) {

                        if (Map[i][p3].equals(".")) {
                            Map[i][p3] = "X";
                        } else if (Map[i][p3].equals("#")) {
                            p = p3 + 1;
                            break;
                        }

                    }

                    }
                }

                break;


            }

        }

        boolean Done = false;

        while(Done == false) {

            int Number_New_X = 0;

            for (int i = 0; i < Map.length; i++) {
                for (int p = 0; p < Map[0].length; p++) {

                    if (Map[i][p].equals("X")) {

                        if (Map[i - 1][p].equals(".")) {
                            Map[i - 1][p] = "X";
                            Number_New_X++;
                        }
                        if (Map[i + 1][p].equals(".")) {
                            Map[i + 1][p] = "X";
                            Number_New_X++;
                        }
                        if (Map[i][p + 1].equals(".")) {
                            Map[i][p + 1] = "X";
                            Number_New_X++;
                        }
                        if (Map[i][p - 1].equals(".")) {
                            Map[i][p - 1] = "X";
                            Number_New_X++;
                        }

                    }

                }
            }

            if (Number_New_X == 0) {
                Done = true;
            }

        }

        int Sum = 0;

        for (int i = 0; i < Map.length; i++) {
            String Line = "";
            for (int p = 0; p < Map[0].length; p++) {

                Line += Map[i][p];
                
                if (Map[i][p].equals("#") || Map[i][p].equals("X")) {
                    Sum++;
                }

            }

            System.out.println(Line);
            
        }

        System.out.println(Sum);

    }
}
