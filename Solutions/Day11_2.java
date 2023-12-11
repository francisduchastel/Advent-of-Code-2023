package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Day11_2 {
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



        int[][] Coords_Array = new int[0][2];

        for (int i = 0; i < Space_Array.length; i++) {

            for (int p = 0; p < Space_Array[0].length; p++) {

                if (Space_Array[i][p].equals("#")) {

                    int[] Coords = {i, p};

                    Coords_Array = Add_Coords(Coords_Array, Coords);


                }

            }
        }


        Long Sum = 0L;

        for (int i = 0; i < Coords_Array.length; i++) {

            for (int p = i + 1; p < Coords_Array.length; p++) {


                Sum += Distance_Between(Coords_Array[i], Coords_Array[p], Row_Index, Column_Index);

            }
        }


        System.out.println(Sum);        




    }


    public static Long Distance_Between(int[] Coords_1, int[] Coords_2, int[] Row_Index, int[] Column_Index) {

        Long Sum = 0L;

        Sum += Math.abs(Coords_1[0] - Coords_2[0]) + Math.abs(Coords_1[1] - Coords_2[1]);

        int Min_row = Math.min(Coords_1[0], Coords_2[0]);
        int Max_row = Math.max(Coords_1[0], Coords_2[0]);

        int Min_Column = Math.min(Coords_1[1], Coords_2[1]);
        int Max_Column = Math.max(Coords_1[1], Coords_2[1]);


        for (int i = 0; i < Row_Index.length; i++) {

            int Row = Row_Index[i];

            if (Row <= Max_row && Row >= Min_row) {
                Sum += 999999;
            }

        }

        for (int p = 0; p < Column_Index.length; p++) {

            int Column = Column_Index[p];

            if (Column <= Max_Column && Column >= Min_Column) {
                Sum += 999999;
            }
        }


        return Sum;
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
