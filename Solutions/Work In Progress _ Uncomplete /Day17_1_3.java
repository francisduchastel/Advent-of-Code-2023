package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day17_1_3 {
    public static void main(String[] args) {
        
        String fullText = "";
        String filepath = "Inputs\\SelfTest.txt";
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

        int[][] Map = new int[Lines.length][Lines[0].length()];

        for (int i = 0; i < Lines.length; i++) {

            String[] Specific_Line = Lines[i].split("");

            for (int p = 0; p < Lines[0].length(); p++) {

                Map[i][p] = Integer.valueOf(Specific_Line[p]);

            }
        }

        int[][][][] Values = new int[Map.length][Map[0].length][4][3];

        System.out.println(Map.length);
        System.out.println(Map[0].length);

        Values[0][1][1][0] = Map[0][1];
        Values[1][0][2][0] = Map[1][0];

        boolean Done = false;
        while (Done == false) {
            
            Values = Next_Step(Values, Map);


            for (int i = 0; i < 4; i++) {
                for (int p = 0; p < 3; p++) {

            
                    if (Values[Values.length - 1][Values[0].length - 1][i][p] != 0) {
                        System.out.println(Values[Values.length - 1][Values[0].length - 1][i][p] + " " + i + " " + p);
                        
                    }

                }
            }

            System.out.println("-----");


        }





    }

    public static int[][][][] Next_Step(int[][][][] Values, int[][] Map) {

        int[][][][] New = new int[Values.length][Values[0].length][4][3];

        for (int i = 0; i < Values.length; i++) {
            for (int p = 0; p < Values[0].length; p++) {
                for (int i2 = 0; i2 < 4; i2++) {
                    for (int p2 = 0; p2 < 3; p2++) {
                        New[i][p][i2][p2]= Values[i][p][i2][p2];
                    }
                }
            }
        }


        for (int i = 0; i < Map.length; i++) {
            for (int p = 0; p < Map.length; p++) {


                //Up

                //0
                if (i != 0) {
                    if (Values[i][p][0][0] != 0 && (New[i - 1][p][0][1] > Values[i][p][0][0] + Map[i - 1][p] || New[i - 1][p][0][1] == 0 )) {
                        New[i - 1][p][0][1] = Values[i][p][0][0] + Map[i - 1][p];
                    } 
                }
                if (p != Values.length - 1) {
                    if (Values[i][p][0][0] != 0 && (New[i][p + 1][1][0] == 0 || New[i][p + 1][1][0] > Values[i][p][0][0] + Map[i][p + 1])) {
                        New[i][p + 1][1][0] = Values[i][p][0][0] + Map[i][p + 1];
                    } 
                }
                if (p != 0) {
                    if (Values[i][p][0][0] != 0 && (New[i][p - 1][3][0] == 0 || New[i][p - 1][3][0] > Values[i][p][0][0] + Map[i][p - 1])) {
                        New[i][p - 1][3][0] = Values[i][p][0][0] + Map[i][p - 1];
                    } 
                }
                //1
                if (i != 0) {
                    if (Values[i][p][0][1] != 0 && (New[i - 1][p][0][2] == 0 || New[i - 1][p][0][2] > Values[i][p][0][1] + Map[i - 1][p])) {
                        New[i - 1][p][0][2] = Values[i][p][0][1] + Map[i - 1][p];
                    }
                }

                if (p != Values.length - 1) {
                    if (Values[i][p][0][1] != 0 && (New[i][p + 1][1][0] == 0 || New[i][p + 1][1][0] > Values[i][p][0][1] + Map[i][p + 1])) {
                        New[i][p + 1][1][0] = Values[i][p][0][1] + Map[i][p + 1];
                    }
                }
                if (p != 0) {
                    if (Values[i][p][0][1] != 0 && (New[i][p - 1][3][0] == 0 || New[i][p - 1][3][0] > Values[i][p][0][1] + Map[i][p - 1])) {
                        New[i][p - 1][3][0] = Values[i][p][0][1] + Map[i][p - 1];
                    }
                }
                //2

                if (p != Values.length - 1) {
                    if (Values[i][p][0][2] != 0 && (New[i][p + 1][1][0] == 0 || New[i][p + 1][1][0] > Values[i][p][0][2] + Map[i][p + 1])) {
                        New[i][p + 1][1][0] = Values[i][p][0][2] + Map[i][p + 1];
                    }
                }
                if (p != 0) {
                    if (Values[i][p][0][2] != 0 && (New[i][p - 1][3][0] == 0 || New[i][p - 1][3][0] > Values[i][p][0][2] + Map[i][p - 1])) {
                        New[i][p - 1][3][0] = Values[i][p][0][2] + Map[i][p - 1];
                    }
                }
                //Right

                //0

                if (p != Values.length - 1) {
                    if (Values[i][p][1][0] != 0 && (New[i][p + 1][1][1] == 0 || New[i][p + 1][1][1] > Values[i][p][1][0] + Map[i][p + 1])) {
                        New[i][p + 1][1][1] = Values[i][p][1][0] + Map[i][p + 1];
                    }
                }
                if (i != Values.length - 1) {
                    if (Values[i][p][1][0] != 0 && (New[i + 1][p][2][0] == 0 || New[i + 1][p][2][0] > Values[i][p][1][0] + Map[i + 1][p])) {
                        New[i + 1][p][2][0] = Values[i][p][1][0] + Map[i + 1][p];
                    }
                }
                if (i != 0) {
                    if (Values[i][p][1][0] != 0 && (New[i - 1][p][0][0] == 0 || New[i - 1][p][0][0] > Values[i][p][1][0] + Map[i - 1][p])) {
                        New[i - 1][p][0][0] = Values[i][p][1][0] + Map[i - 1][p];
                    }
                }
                //1

                if (p != Values.length - 1) {
                    if (Values[i][p][1][1] != 0 && (New[i][p + 1][1][2] == 0 || New[i][p + 1][1][2] > Values[i][p][1][1] + Map[i][p + 1])) {
                        New[i][p + 1][1][2] = Values[i][p][1][1] + Map[i][p + 1];
                    }
                }
                if (i != Values.length - 1) {
                    if (Values[i][p][1][1] != 0 && (New[i + 1][p][2][0] == 0 || New[i + 1][p][2][0] > Values[i][p][1][1] + Map[i + 1][p])) {
                        New[i + 1][p][2][0] = Values[i][p][1][1] + Map[i + 1][p];
                    }
                }
                if (i != 0) {
                    if (Values[i][p][1][1] != 0 && (New[i - 1][p][0][0] == 0 || New[i - 1][p][0][0] > Values[i][p][1][1] + Map[i - 1][p])) {
                        New[i - 1][p][0][0] = Values[i][p][1][1] + Map[i - 1][p];
                    }
                }
                //2
                if (i != Values.length - 1) {
                    if (Values[i][p][1][2] != 0 && (New[i + 1][p][2][0] == 0 || New[i + 1][p][2][0] > Values[i][p][1][2] + Map[i + 1][p])) {
                        New[i + 1][p][2][0] = Values[i][p][1][2] + Map[i + 1][p];
                    }
                }
                if (i != 0) {
                    if (Values[i][p][1][2] != 0 && (New[i - 1][p][0][0] == 0 || New[i - 1][p][0][0] > Values[i][p][1][2] + Map[i - 1][p])) {
                        New[i - 1][p][0][0] = Values[i][p][1][2] + Map[i - 1][p];
                    }
                }

                //Down

                //0
                if (i != Values.length - 1) {
                    if (Values[i][p][2][0] != 0 && (New[i + 1][p][2][1] > Values[i][p][2][0] + Map[i + 1][p] || New[i - 1][p][2][1] == 0 )) {
                        New[i + 1][p][2][1] = Values[i][p][2][0] + Map[i + 1][p];
                    } 
                }
                if (p != Values.length - 1) {
                    if (Values[i][p][2][0] != 0 && (New[i][p + 1][1][0] == 0 || New[i][p + 1][1][0] > Values[i][p][2][0] + Map[i][p + 1])) {
                        New[i][p + 1][1][0] = Values[i][p][2][0] + Map[i][p + 1];
                    } 
                }
                if (p != 0) {
                    if (Values[i][p][2][0] != 0 && (New[i][p - 1][3][0] == 0 || New[i][p - 1][3][0] > Values[i][p][2][0] + Map[i][p - 1])) {
                        New[i][p - 1][3][0] = Values[i][p][2][0] + Map[i][p - 1];
                    } 
                }
                //1

                if (i != Values.length - 1) {
                    if (Values[i][p][2][1] != 0 && (New[i + 1][p][2][2] == 0 || New[i + 1][p][0][2] > Values[i][p][2][1] + Map[i + 1][p])) {
                        New[i + 1][p][2][2] = Values[i][p][2][1] + Map[i + 1][p];
                    }
                }
                if (p != Values.length - 1) {
                    if (Values[i][p][2][1] != 0 && (New[i][p + 1][1][0] == 0 || New[i][p + 1][1][0] > Values[i][p][2][1] + Map[i][p + 1])) {
                        New[i][p + 1][1][0] = Values[i][p][2][1] + Map[i][p + 1];
                    }
                }

                if (p != 0) {
                    if (Values[i][p][2][1] != 0 && (New[i][p - 1][3][0] == 0 || New[i][p - 1][3][0] > Values[i][p][2][1] + Map[i][p - 1])) {
                        New[i][p - 1][3][0] = Values[i][p][2][1] + Map[i][p - 1];
                    }
                }
                //2
                if (p != Values.length - 1) {
                    if (Values[i][p][2][2] != 0 && (New[i][p + 1][1][0] == 0 || New[i][p + 1][1][0] > Values[i][p][2][2] + Map[i][p + 1])) {
                        New[i][p + 1][1][0] = Values[i][p][2][2] + Map[i][p + 1];
                    }
                }
                if (p != 0) {
                    if (Values[i][p][2][2] != 0 && (New[i][p - 1][3][0] == 0 || New[i][p - 1][3][0] > Values[i][p][2][2] + Map[i][p - 1])) {
                        New[i][p - 1][3][0] = Values[i][p][2][2] + Map[i][p - 1];
                    }
                }


                //Left

                //0
                if (p != 0) {
                    if (Values[i][p][3][0] != 0 && (New[i][p - 1][3][1] == 0 || New[i][p - 1][3][1] > Values[i][p][3][0] + Map[i][p - 1])) {
                        New[i][p - 1][3][1] = Values[i][p][3][0] + Map[i][p - 1];
                    }
                }
                if (i != Values.length - 1) {
                    if (Values[i][p][3][0] != 0 && (New[i + 1][p][2][0] == 0 || New[i + 1][p][2][0] > Values[i][p][3][0] + Map[i + 1][p])) {
                        New[i + 1][p][2][0] = Values[i][p][3][0] + Map[i + 1][p];
                    }
                }
                if (i != 0) {
                    if (Values[i][p][3][0] != 0 && (New[i - 1][p][0][0] == 0 || New[i - 1][p][0][0] > Values[i][p][3][0] + Map[i - 1][p])) {
                        New[i - 1][p][0][0] = Values[i][p][3][0] + Map[i - 1][p];
                    }
                }
                //1
                if (p != 0) {
                    if (Values[i][p][3][1] != 0 && (New[i][p - 1][3][2] == 0 || New[i][p - 1][3][2] > Values[i][p][3][1] + Map[i][p - 1])) {
                        New[i][p - 1][3][2] = Values[i][p][3][1] + Map[i][p - 1];
                    }
                }
                if (i != Values.length - 1) {
                    if (Values[i][p][3][1] != 0 && (New[i + 1][p][2][0] == 0 || New[i + 1][p][2][0] > Values[i][p][3][1] + Map[i + 1][p])) {
                        New[i + 1][p][2][0] = Values[i][p][3][1] + Map[i + 1][p];
                    }
                }
                if (i != 0) {
                    if (Values[i][p][3][1] != 0 && (New[i - 1][p][0][0] == 0 || New[i - 1][p][0][0] > Values[i][p][3][1] + Map[i - 1][p])) {
                        New[i - 1][p][0][0] = Values[i][p][3][1] + Map[i - 1][p];
                    }
                }
                //2
                if (i != Values.length - 1) {
                    if (Values[i][p][3][2] != 0 && (New[i + 1][p][2][0] == 0 || New[i + 1][p][2][0] > Values[i][p][3][2] + Map[i + 1][p])) {
                        New[i + 1][p][2][0] = Values[i][p][3][2] + Map[i + 1][p];
                    }
                }
                if (i != 0) {
                    if (Values[i][p][3][2] != 0 && (New[i - 1][p][0][0] == 0 || New[i - 1][p][0][0] > Values[i][p][3][2] + Map[i - 1][p])) {
                        New[i - 1][p][0][0] = Values[i][p][3][2] + Map[i - 1][p];
                    }
                }



            }
        }

        return New;


    }
}
