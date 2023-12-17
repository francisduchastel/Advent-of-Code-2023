package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;



//971 >
//895 >

public class Day17_1Test {

    private static Map<String, Integer> memo = new HashMap<>();

    public static void main(String[] args) {
        
        String fullText = "";
        String filepath = "Inputs\\Test.txt";
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


        int[] Start = {0, 0};

        int[] End = new int[2];
        End[0] = Map.length - 1;
        End[1] = Map[0].length - 1;


        int[][][][] New = new int[Map.length][Map[0].length][0][3];

        int[][] Starting_Point = { {0, 0, 0} };

        New[0][0] = Starting_Point;


        /* boolean Done = false;

        int k = 0;

        while (Done == false) {


            int[][][][] Next = Find_Nex(Map, New);

            if (Next[End[0]][End[1]].length > 0) {

                int[] End_Values = new int[Next[End[0]][End[1]].length];

                for (int e = 0; e < Next[End[0]][End[1]].length; e++) {

                    End_Values[e] = Next[End[0]][End[1]][e][0];

                }

                Arrays.sort(End_Values);

                System.out.println(End_Values[0]);

            } else {
                System.out.println("Test " + k);
                k++;

            }

            New = Next;


        } */


 
        /* int[][][][] Next1 = Find_Nex(Map, New);

        int[][][][] Next2 = Find_Nex(Map, Next1);

        int[][][][] Next3 = Find_Nex(Map, Next2);

        int[][][][] Next = Find_Nex(Map, Next3); */

        int[][][][] Next;

        for (int i = 0; i < 200; i++) {

            Next = Find_Nex(Map, New);

            if (Next[12][12].length != 0) {
                System.out.println(i);

                /* Arrays.sort(Next[12][12]);

                System.out.println(Next[12][12][0]); */

            }
            

            New = Next;

        }

        Next = New;


        for (int i = 0; i < Map.length; i++) {
            for (int p = 0; p < Map[0].length; p++) {

                for (int c = 0; c < Next[i][p].length; c++) {

                    System.out.println(i + " " + p + " " + Next[i][p][c][0] + " - " + Next[i][p][c][1] + " - " + Next[i][p][c][2]);

                }


            }
        }



        //{ Cost, Last_Direction, Streak  }


    }


    public static int[][][][] Find_Nex(int[][] Map, int[][][][] Costs) {

        int[][][][] New_Costs = new int[Costs.length][Costs[0].length][0][3];


        for (int i = 0; i < Map.length; i++) {
            for (int p = 0; p < Map[0].length; p++) {

                for (int c = 0; c < Costs[i][p].length; c++) {

                    //up

                    if (i > 0 && (Costs[i][p][c][1] == 1 && Costs[i][p][c][2] >= 3) == false && Costs[i][p][c][1] != 3) {

                        int[] Next_Step = new int[3];
                        Next_Step[0] = Costs[i][p][c][0] + Map[i - 1][p];
                        Next_Step[1] = 1;
                        
                        Next_Step[2] = 1;
                        if (Costs[i][p][c][1] == 1) {
                            Next_Step[2] = Costs[i][p][c][2] + 1;
                        }

                        if (memo.containsKey((i - 1) + "," + p + "," + Next_Step[1] + "," + Next_Step[2])) {

                        } else {

                            int[][] temp = new int[New_Costs[i - 1][p].length + 1][3];

                            for (int s = 0; s < New_Costs[i - 1][p].length; s++) {
                                temp[s] = New_Costs[i - 1][p][s];
                            }

                            temp[temp.length - 1] = Next_Step;

                            New_Costs[i - 1][p] = temp;

                            memo.put((i - 1) + "," + p + "," + Next_Step[1] + "," + Next_Step[2], null);
                        }

                        


                    }



                    //right

                    if (p < Map[0].length - 1 && (Costs[i][p][c][1] == 2 && Costs[i][p][c][2] >= 3) == false && Costs[i][p][c][1] != 4) {


                        int[] Next_Step = new int[3];

                        Next_Step[0] = Costs[i][p][c][0] + Map[i][p + 1];
                        Next_Step[1] = 2;

                        Next_Step[2] = 1;
                        if (Costs[i][p][c][1] == 2) {
                            Next_Step[2] = Costs[i][p][c][2] + 1;
                        }


                        if (memo.containsKey((i) + "," + (p + 1) + "," + Next_Step[1] + "," + Next_Step[2])) {

                        } else {

                            int[][] temp = new int[New_Costs[i][p + 1].length + 1][3];

                            for (int s = 0; s < New_Costs[i][p + 1].length; s++) {
                                temp[s] = New_Costs[i][p + 1][s];
                            }

                            temp[temp.length - 1] = Next_Step;

                            New_Costs[i][p + 1] = temp;

                            memo.put((i) + "," + (p + 1) + "," + Next_Step[1] + "," + Next_Step[2], null);

                        }

                    }

                    //down

                    if (i < Map.length - 1 && (Costs[i][p][c][1] == 3 && Costs[i][p][c][2] >= 3) == false && Costs[i][p][c][1] != 1) {


                        int[] Next_Step = new int[3];

                        Next_Step[0] = Costs[i][p][c][0] + Map[i + 1][p];
                        Next_Step[1] = 3;
                        
                        Next_Step[2] = 1;
                        if (Costs[i][p][c][1] == 3) {
                            Next_Step[2] = Costs[i][p][c][2] + 1;
                        }

                        if (memo.containsKey((i + 1) + "," + (p) + "," + Next_Step[1] + "," + Next_Step[2])) {

                        } else {

                            int[][] temp = new int[New_Costs[i + 1][p].length + 1][3];

                            for (int s = 0; s < New_Costs[i + 1][p].length; s++) {
                                temp[s] = New_Costs[i + 1][p][s];
                            }


                            temp[temp.length - 1] = Next_Step;

                            New_Costs[i + 1][p] = temp;

                            memo.put((i + 1) + "," + (p) + "," + Next_Step[1] + "," + Next_Step[2], null);

                        }
                    }

                    //left

                    if (p > 0 && (Costs[i][p][c][1] == 4 && Costs[i][p][c][2] >= 3) == false && Costs[i][p][c][1] != 2) {



                        int[] Next_Step = new int[3];

                        Next_Step[0] = Costs[i][p][c][0] + Map[i][p - 1];
                        Next_Step[1] = 2;
                        
                        Next_Step[2] = 1;
                        if (Costs[i][p][c][1] == 4) {
                            Next_Step[2] = Costs[i][p][c][2] + 1;
                        }

                        if (memo.containsKey((i) + "," + (p - 1) + "," + Next_Step[1] + "," + Next_Step[2])) {

                        } else {

                            int[][] temp = new int[New_Costs[i][p - 1].length + 1][3];

                            for (int s = 0; s < New_Costs[i][p - 1].length; s++) {
                                temp[s] = New_Costs[i][p - 1][s];
                            }

                            temp[temp.length - 1] = Next_Step;

                            New_Costs[i][p - 1] = temp;

                            memo.put((i) + "," + (p - 1) + "," + Next_Step[1] + "," + Next_Step[2], null);

                        }

                    }

                }


            }
        }


        return New_Costs;

    }
}
