package advent;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

//6951 <

public class Day16_1 {
    public static void main(String[] args) {

        String fullText = "";
        String filepath = "Inputs\\Day 16.txt";
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

        String[][] Array = new String[Lines.length][Lines[0].length()];

        for (int i = 0; i < Lines.length; i++) {

            String[] Line_Split = Lines[i].split("");

            for (int p = 0; p < Line_Split.length; p++) {

                Array[i][p] = Line_Split[p];

            }

        }

        int[][] Number_Map = new int[Array.length][Array[0].length];
        int[][][] Source = new int[Array.length][Array[0].length][4];

        Number_Map[0][0] = 1;

        Number_Map = Beam_Down(Array, 0, 0, Number_Map, Source);

        Long Sum = 0L;

        for (int i = 0; i < Number_Map.length; i++) {

            String Line = "";

            for (int p = 0; p < Number_Map[0].length; p++) {

                if (Number_Map[i][p] != 0) {
                    Sum++;

                    Line += "#";

                } else {
                    Line += ".";
                }

            }

            System.out.println(Line);

        }

        System.out.println(Sum);

    }

    public static int[][] Beam_Right(String[][] Array, int i, int p, int[][] Number_Map, int[][][] Source) {

        Number_Map[i][p] = 1;


        String Wall = "";

        boolean hit_wall = false;

        while (hit_wall == false) {

            if (p >= Array[0].length - 1) {
                hit_wall = true;
            } else if (Array[i][p + 1].equals(".") || Array[i][p + 1].equals("-")) {
                Number_Map[i][p + 1] = 1;
                p++;
            } else {

                hit_wall = true;
                Wall = Array[i][p + 1];
                Number_Map[i][p + 1] = 1;

            }

        }


        if (Wall.equals("\\")) {

            if (Source[i][p + 1][2] == 0) {

                Source[i][p + 1][2] = 1;
                Number_Map = Beam_Down(Array, i, p + 1, Number_Map, Source);

            }

        } else if (Wall.equals("/")) {

            if (Source[i][p + 1][0] == 0) {

                Source[i][p + 1][0] = 1;
                Number_Map = Beam_Up(Array, i, p + 1, Number_Map, Source);
            }

        } else if (Wall.equals("|")) {

            if (Source[i][p + 1][0] == 0) {

                Source[i][p + 1][0] = 1;
                Number_Map = Beam_Up(Array, i, p + 1, Number_Map, Source);
            }

            if (Source[i][p + 1][2] == 0) {

                Source[i][p + 1][2] = 1;
                Number_Map = Beam_Down(Array, i, p + 1, Number_Map, Source);

            }

        }

        return Number_Map;


    }

    public static int[][] Beam_Down(String[][] Array, int i, int p, int[][] Number_Map, int[][][] Source) {


        String Wall = "";

        Number_Map[i][p] = 1;

        boolean hit_wall = false;

        while (hit_wall == false) {

            if (i >= Array[0].length - 1) {
                hit_wall = true;
            } else if (Array[i + 1][p].equals(".") || Array[i + 1][p].equals("|")) {
                Number_Map[i + 1][p] = 1;
                i++;
            } else {

                hit_wall = true;
                Wall = Array[i + 1][p];
                Number_Map[i + 1][p] = 1;

            }

        }


        if (Wall.equals("\\")) {

            if (Source[i + 1][p][1] == 0) {

                Source[i + 1][p][1] = 1;
                Number_Map = Beam_Right(Array, i + 1, p, Number_Map, Source);

            }

        } else if (Wall.equals("/")) {

            if (Source[i + 1][p][3] == 0) {

                Source[i + 1][p][3] = 1;
                Number_Map = Beam_Left(Array, i + 1, p, Number_Map, Source);
            }

        } else if (Wall.equals("-")) {

            if (Source[i + 1][p][3] == 0) {

                Source[i + 1][p][3] = 1;
                Number_Map = Beam_Left(Array, i + 1, p, Number_Map, Source);
            }

            if (Source[i + 1][p][1] == 0) {

                Source[i + 1][p][1] = 1;
                Number_Map = Beam_Right(Array, i + 1, p, Number_Map, Source);

            }

        }

        return Number_Map;

    }

    public static int[][] Beam_Left(String[][] Array, int i, int p, int[][] Number_Map, int[][][] Source) {


        String Wall = "";

        Number_Map[i][p] = 1;

        boolean hit_wall = false;

        while (hit_wall == false) {

            if (p <= 0) {
                hit_wall = true;
            } else if (Array[i][p - 1].equals(".") || Array[i][p - 1].equals("-")) {
                Number_Map[i][p - 1] = 1;
                p--;
            } else {

                hit_wall = true;
                Wall = Array[i][p - 1];
                Number_Map[i][p - 1] = 1;

            }

        }


        if (Wall.equals("\\")) {

            if (Source[i][p - 1][0] == 0) {

                Source[i][p - 1][0] = 1;
                Number_Map = Beam_Up(Array, i, p - 1, Number_Map, Source);

            }

        } else if (Wall.equals("/")) {

            if (Source[i][p - 1][2] == 0) {

                Source[i][p - 1][2] = 1;
                Number_Map = Beam_Down(Array, i, p - 1, Number_Map, Source);
            }

        } else if (Wall.equals("|")) {

            if (Source[i][p - 1][0] == 0) {

                Source[i][p - 1][0] = 1;
                Number_Map = Beam_Up(Array, i, p - 1, Number_Map, Source);
            }

            if (Source[i][p - 1][2] == 0) {

                Source[i][p - 1][2] = 1;
                Number_Map = Beam_Down(Array, i, p - 1, Number_Map, Source);

            }

        }

        return Number_Map;

    }



    public static int[][] Beam_Up(String[][] Array, int i, int p, int[][] Number_Map, int[][][] Source) {


        String Wall = "";

        Number_Map[i][p] = 1;

        boolean hit_wall = false;

        while (hit_wall == false) {

            //System.out.println(i);

            if (i <= 0) {
                hit_wall = true;
            } else if (Array[i - 1][p].equals(".") || Array[i - 1][p].equals("|")) {
                Number_Map[i - 1][p] = 1;
                i--;
            } else {

                hit_wall = true;
                Wall = Array[i - 1][p];
                Number_Map[i - 1][p] = 1;

            }

        }


        if (Wall.equals("\\")) {

            if (Source[i - 1][p][3] == 0) {

                Source[i - 1][p][3] = 1;
                Number_Map = Beam_Left(Array, i - 1, p, Number_Map, Source);

            }

        } else if (Wall.equals("/")) {

            if (Source[i - 1][p][1] == 0) {

                Source[i - 1][p][1] = 1;
                Number_Map = Beam_Right(Array, i - 1, p, Number_Map, Source);
            }

        } else if (Wall.equals("-")) {

            if (Source[i - 1][p][3] == 0) {

                Source[i - 1][p][3] = 1;
                Number_Map = Beam_Left(Array, i - 1, p, Number_Map, Source);
            }

            if (Source[i - 1][p][1] == 0) {

                Source[i - 1][p][1] = 1;
                Number_Map = Beam_Right(Array, i - 1, p, Number_Map, Source);

            }

        }

        return Number_Map;

    }

}