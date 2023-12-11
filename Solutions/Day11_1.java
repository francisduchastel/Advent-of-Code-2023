package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Day11_1 {
    public static void main(String[] args) {
        String fullText = "";
        String filepath = "Inputs\\Day 11.txt";
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

        String[][] Space_Array = new String[Lines.length][Lines[0].length()];

        for (int i = 0; i < Lines.length; i++) {
            String[] Line_Split = Lines[i].split("");

            for (int p = 0; p < Line_Split.length; p++) {

                Space_Array[i][p] = Line_Split[p];
            }
                
        }

        int[] Row_Index = new int[0];
        int[] Column_Index = new int[0];

        for (int i = 0; i < Space_Array.length; i++) {

            boolean Row_Empty = true;

            for (int p = 0; p < Space_Array[0].length; p++) {

                if (Space_Array[i][p].equals("#")) {

                    Row_Empty = false;
                }
            }

            if (Row_Empty == true) {
                Row_Index = Add_Index(Row_Index, i);
            }


        }

        for (int p = 0; p < Space_Array[0].length; p++) {
            
            boolean Column_Empty = true;
            
            for (int i = 0; i < Space_Array.length; i++) {


                if (Space_Array[i][p].equals("#")) {
                    Column_Empty = false;
                }

            }

            if (Column_Empty == true) {
                Column_Index = Add_Index(Column_Index, p);
            }
        }




        for (int i = 0; i < Row_Index.length; i++) {
            Space_Array = Add_Row(Space_Array, Row_Index[i] + i);
        }

        for (int p = 0; p < Column_Index.length; p++) {
            Space_Array = Add_Column(Space_Array, Column_Index[p] + p);
        }


        int[][] Coords_Array = new int[0][2];

        for (int i = 0; i < Space_Array.length; i++) {

            for (int p = 0; p < Space_Array[0].length; p++) {

                if (Space_Array[i][p].equals("#")) {

                    int[] Coords = {i, p};

                    Coords_Array = Add_Coords(Coords_Array, Coords);


                }

            }
        }


        int Sum = 0;

        for (int i = 0; i < Coords_Array.length; i++) {

            for (int p = i + 1; p < Coords_Array.length; p++) {


                Sum += Distance_Between(Coords_Array[i], Coords_Array[p]);

            }
        }


        System.out.println(Sum);        




    }


    public static int Distance_Between(int[] Coords_1, int[] Coords_2) {

        int Sum = 0;

        Sum += Math.abs(Coords_1[0] - Coords_2[0]) + Math.abs(Coords_1[1] - Coords_2[1]);


        return Sum;
    }

    public static String[][] Add_Row(String[][] Array, int Index) {

        String[][] New_Array = new String[Array.length + 1][Array[0].length];

        int q = 0;
        for (int i = 0; i < New_Array.length; i++) {

            if (i == Index) {

                for (int p = 0; p < Array[0].length; p++) {
                    New_Array[i][p] = ".";
                }

            } else {
                
                for (int p = 0; p < Array[0].length; p++) {
                    New_Array[i][p] = Array[q][p];
                }

                q++;

            }

        }

        return New_Array;


    }

    public static String[][] Add_Column(String[][] Array, int Index) {

        String[][] New_Array = new String[Array.length][Array[0].length + 1];


        for (int i = 0; i < New_Array.length; i++) {

            int q = 0;

            for (int p = 0; p < New_Array[0].length; p++) {

                if (p == Index) {
                    New_Array[i][p] = ".";
                } else {
                    New_Array[i][p] = Array[i][q];
                    q++;
                }


            }

        }

        return New_Array;


    }


    public static int[] Add_Index(int[] Array, int New_Index) {

        int[] Temp = new int[Array.length + 1];

        for (int i = 0; i < Array.length; i++) {
            Temp[i] = Array[i];
        }

        Temp[Temp.length - 1] = New_Index;

        return Temp;


    }

    public static int[][] Add_Coords(int[][] Array, int[] New_Coords) {

        int[][] Temp = new int[Array.length + 1][2];

        for (int i = 0; i < Array.length; i++) {
            Temp[i][0] = Array[i][0];
            Temp[i][1] = Array[i][1];
        }

        Temp[Temp.length - 1][0] = New_Coords[0];
        Temp[Temp.length - 1][1] = New_Coords[1];

        return Temp;


    }

}
