package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day13_1 {
    
    public static void main(String[] args) {
        String fullText = "";
        String filepath = "Inputs\\Day 13.txt";
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

        String[][] Mirrors = new String[1][0];

        int p = 0;

        for (int i = 0; i < Lines.length; i++) {

            //System.out.println("test:  " + i);

            if (Lines[i].equals("")) {

                //System.out.println("test");

                p++;

                String[][] temp = new String[Mirrors.length + 1][0];

                for (int z = 0; z < Mirrors.length; z++) {

                    temp[z] = Mirrors[z];

                }

                Mirrors = temp;


            } else {

                String[] temp = new String[Mirrors[p].length + 1];

                for (int z = 0; z < Mirrors[p].length; z++) {

                    temp[z] = Mirrors[p][z];

                }

                temp[temp.length - 1] = Lines[i];

                Mirrors[p] = temp;


            }


        }


        int Sum = 0;

        for (int i = 0; i < Mirrors.length; i++) {

            if (Horizontal_Symmetry(Mirrors[i]) != 0) {

                Sum += (100 * Horizontal_Symmetry(Mirrors[i]));

            } else if (Verticle_Symetry(Mirrors[i]) != 0) {

                Sum += Verticle_Symetry(Mirrors[i]);

            }


        }

        System.out.println(Sum);

        

    }



    public static int Verticle_Symetry(String[] Mirror) {

        String[] Tilted_Mirror = Tilt_Mirror(Mirror);

        int Return = Horizontal_Symmetry(Tilted_Mirror);

        return Return;


    }

    public static int Horizontal_Symmetry(String[] Mirror) {

        int Number_Row_Above = 0;

        for (int i = 0; i < Mirror.length - 1; i++) {

            boolean Symmetry = true;

            for (int p = i; p >= 0; p--) {

                if (Rows_Equal(Mirror[p], Mirror[(2 * i) + 1 - p]) == false) {
                    Symmetry = false;
                    break;
                }

                if ((2 * i) + 2 - p >= Mirror.length) {

                    break;

                }

            }

            if (Symmetry == true) {
                Number_Row_Above = i + 1;
                
                return Number_Row_Above;
            }


        }

        return Number_Row_Above;


    }


    public static boolean Rows_Equal(String Row1, String Row2) {

        boolean Return = false;

        if (Row1.equals(Row2)) {

            Return = true;

        }

        return Return;


    }

    public static String Get_Column(String[] Mirror, int index) {
    
        char[] Column_Array = new char[Mirror.length];
        
        for (int i = 0; i < Mirror.length; i++) {
            Column_Array[i] = Mirror[i].charAt(index);
        }

        String Column = "";


        for (int i = 0; i < Column_Array.length; i++) {
            Column += Column_Array[i];
        }
    
        return Column;
    }

    public static String[] Tilt_Mirror(String[] Mirror) {

        String[] Tilted_Mirror = new String[Mirror[0].length()];

        for (int i = 0; i < Tilted_Mirror.length; i++) {

            Tilted_Mirror[i] = Get_Column(Mirror, i);
        }

        return Tilted_Mirror;

    }

    




}
