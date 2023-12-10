package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.sound.sampled.Line;

public class Day10_2 {
    public static void main(String[] args) {
        String fullText = "";
        String filepath = "Inputs\\Day 10.txt";
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

        String[][] Pipes = new String[Lines.length][Lines[0].length()];

        int[][] Counting_Map = new int[Pipes.length][Pipes[0].length];

        for (int i = 0; i < Pipes.length; i++) {
            String[] temp = Lines[i].split("");

            for (int p = 0; p < Pipes[0].length; p++) {
                Pipes[i][p] = temp[p];

                if (temp[p].equals("S")) {
                    Counting_Map[i][p] = 0;
                } else {
                    Counting_Map[i][p] = -1;
                }

            }
        }


        boolean Keep_Going = true;
        int z = 1;

        while (Keep_Going == true) {

            int[][] Temp = Find_Next(Pipes, Counting_Map, z);

            if (Temp.length == 1) {
                z -= 1;
                Keep_Going = false;
            } else {
                Counting_Map = Temp;
                z++;
            }


        }

        int[][] Super_Size_Counting = new int[Pipes.length * 3][Pipes[0].length * 3];

        for (int i = 0; i < Pipes.length; i++) {
            for (int p = 0; p < Pipes[0].length; p++) {

                int a = 3 * i;
                int b = 3 * p;


                if (Pipes[i][p].equals("|") && Counting_Map[i][p] > -1) {
                    Super_Size_Counting[a][b] = 0;
                    Super_Size_Counting[a+1][b] = 0;
                    Super_Size_Counting[a+2][b] = 0;
                    Super_Size_Counting[a][b+1] = 1;
                    Super_Size_Counting[a+1][b+1] = 1;
                    Super_Size_Counting[a+2][b+1] = 1;
                    Super_Size_Counting[a][b+2] = 0;
                    Super_Size_Counting[a+1][b+2] = 0;
                    Super_Size_Counting[a+2][b+2] = 0;

                } else if (Pipes[i][p].equals("-") && Counting_Map[i][p] > -1) {
                    Super_Size_Counting[a][b] = 0;
                    Super_Size_Counting[a+1][b] = 1;
                    Super_Size_Counting[a+2][b] = 0;
                    Super_Size_Counting[a][b+1] = 0;
                    Super_Size_Counting[a+1][b+1] = 1;
                    Super_Size_Counting[a+2][b+1] = 0;
                    Super_Size_Counting[a][b+2] = 0;
                    Super_Size_Counting[a+1][b+2] = 1;
                    Super_Size_Counting[a+2][b+2] = 0;

                } else if (Pipes[i][p].equals("J") && Counting_Map[i][p] > -1) {
                    Super_Size_Counting[a][b] = 0;
                    Super_Size_Counting[a+1][b] = 1;
                    Super_Size_Counting[a+2][b] = 0;
                    Super_Size_Counting[a][b+1] = 1;
                    Super_Size_Counting[a+1][b+1] = 1;
                    Super_Size_Counting[a+2][b+1] = 0;
                    Super_Size_Counting[a][b+2] = 0;
                    Super_Size_Counting[a+1][b+2] = 0;
                    Super_Size_Counting[a+2][b+2] = 0;

                } else if (Pipes[i][p].equals("L") && Counting_Map[i][p] > -1) {
                    Super_Size_Counting[a][b] = 0;
                    Super_Size_Counting[a+1][b] = 0;
                    Super_Size_Counting[a+2][b] = 0;
                    Super_Size_Counting[a][b+1] = 1;
                    Super_Size_Counting[a+1][b+1] = 1;
                    Super_Size_Counting[a+2][b+1] = 0;
                    Super_Size_Counting[a][b+2] = 0;
                    Super_Size_Counting[a+1][b+2] = 1;
                    Super_Size_Counting[a+2][b+2] = 0;

                } else if (Pipes[i][p].equals("F") && Counting_Map[i][p] > -1) {
                    Super_Size_Counting[a][b] = 0;
                    Super_Size_Counting[a+1][b] = 0;
                    Super_Size_Counting[a+2][b] = 0;
                    Super_Size_Counting[a][b+1] = 0;
                    Super_Size_Counting[a+1][b+1] = 1;
                    Super_Size_Counting[a+2][b+1] = 1;
                    Super_Size_Counting[a][b+2] = 0;
                    Super_Size_Counting[a+1][b+2] = 1;
                    Super_Size_Counting[a+2][b+2] = 0;

                } else if (Pipes[i][p].equals("7") && Counting_Map[i][p] > -1) {
                    Super_Size_Counting[a][b] = 0;
                    Super_Size_Counting[a+1][b] = 1;
                    Super_Size_Counting[a+2][b] = 0;
                    Super_Size_Counting[a][b+1] = 0;
                    Super_Size_Counting[a+1][b+1] = 1;
                    Super_Size_Counting[a+2][b+1] = 1;
                    Super_Size_Counting[a][b+2] = 0;
                    Super_Size_Counting[a+1][b+2] = 0;
                    Super_Size_Counting[a+2][b+2] = 0;

                } else if (Pipes[i][p].equals("S") && Counting_Map[i][p] > -1) {
                    Super_Size_Counting[a][b] = 1;
                    Super_Size_Counting[a+1][b] = 1;
                    Super_Size_Counting[a+2][b] = 1;
                    Super_Size_Counting[a][b+1] = 1;
                    Super_Size_Counting[a+1][b+1] = 1;
                    Super_Size_Counting[a+2][b+1] = 1;
                    Super_Size_Counting[a][b+2] = 1;
                    Super_Size_Counting[a+1][b+2] = 1;
                    Super_Size_Counting[a+2][b+2] = 1;

                }

            }
        }



        Boolean Going = true;
        
        while (Going == true) {

            Super_Size_Counting = Count_Zeros(Super_Size_Counting);

            if (Super_Size_Counting[0][0] == 0) {
                Going = false;
            }

        }

        Super_Size_Counting[0][0] = -1;

        int Zeros_Out = 0;


        for (int i = 0; i < Counting_Map.length; i++) {
            for (int p = 0; p < Counting_Map[0].length; p++) {

                int a = 3 * i;
                int b = 3 * p;


                if (
                    Super_Size_Counting[a][b] == -1 &&
                    Super_Size_Counting[a+1][b] == -1 &&
                    Super_Size_Counting[a+2][b] == -1 &&
                    Super_Size_Counting[a][b+1] == -1 &&
                    Super_Size_Counting[a+1][b+1] == -1 &&
                    Super_Size_Counting[a+2][b+1] == -1 &&
                    Super_Size_Counting[a][b+2] == -1 &&
                    Super_Size_Counting[a+1][b+2] == -1 &&
                    Super_Size_Counting[a+2][b+2] == -1 ) {

                        Zeros_Out++;
                    }


            }
        }

        int Zeros_Total = 0;


        for (int i = 0; i < Counting_Map.length; i++) {

            for (int p = 0; p < Counting_Map[0].length; p++) {
                if (Counting_Map[i][p] == -1) {
                    Zeros_Total++;
                }
            }

        }


        int Zeros_In = Zeros_Total - Zeros_Out;

        System.out.println(Zeros_In);


    }



    public static int[][] Find_Next(String[][] Pipe_Map, int[][] Counting, int Next_Num) {

        int Last_Num = Next_Num - 1;

        boolean Make_A_Change = false;

        for (int i = 0; i < Pipe_Map.length; i++) {
            for (int p = 0; p < Pipe_Map[0].length; p++) {


                if (Counting[i][p] == Last_Num) {


                    if (Pipe_Map[i][p].equals("S")) {
                        if (Pipe_Map[i-1][p].equals("|") || Pipe_Map[i-1][p].equals("7") || Pipe_Map[i-1][p].equals("F")) {
                            Counting[i+1][p] = Next_Num;
                            Make_A_Change = true;
                        }

                        if (Pipe_Map[i+1][p].equals("|") || Pipe_Map[i+1][p].equals("J") || Pipe_Map[i+1][p].equals("L")) {
                            Counting[i+1][p] = Next_Num;
                            Make_A_Change = true;
                        }

                        if (Pipe_Map[i][p+1].equals("-") || Pipe_Map[i][p+1].equals("J") || Pipe_Map[i][p+1].equals("7")) {
                            Counting[i][p+1] = Next_Num;
                            Make_A_Change = true;
                        }

                        if (Pipe_Map[i][p-1].equals("-") || Pipe_Map[i][p-1].equals("F") || Pipe_Map[i][p-1].equals("L")) {
                            Counting[i][p-1] = Next_Num;
                            Make_A_Change = true;
                        }

                    } else if (Pipe_Map[i][p].equals("|")) {
                        if (Counting[i-1][p] < 0) {
                            Counting[i-1][p] = Next_Num;
                            Make_A_Change = true;
                        } if (Counting[i+1][p] < 0) {
                            Counting[i+1][p] = Next_Num;
                            Make_A_Change = true;
                        }
                    } else if (Pipe_Map[i][p].equals("-")) {
                        if (Counting[i][p-1] < 0) {
                            Counting[i][p-1] = Next_Num;
                            Make_A_Change = true;
                        } if (Counting[i][p+1] < 0) {
                            Counting[i][p+1] = Next_Num;
                            Make_A_Change = true;
                        }
                    } else if (Pipe_Map[i][p].equals("L")) {
                        if (Counting[i][p+1] < 0) {
                            Counting[i][p+1] = Next_Num;
                            Make_A_Change = true;
                        } if (Counting[i-1][p] < 0) {
                            Counting[i-1][p] = Next_Num;
                            Make_A_Change = true;
                        }
                    } else if (Pipe_Map[i][p].equals("J")) {
                        if (Counting[i][p-1] < 0) {
                            Counting[i][p-1] = Next_Num;
                            Make_A_Change = true;
                        } if (Counting[i-1][p] < 0) {
                            Counting[i-1][p] = Next_Num;
                            Make_A_Change = true;
                        }
                    } else if (Pipe_Map[i][p].equals("7")) {
                        if (Counting[i][p-1] < 0) {
                            Counting[i][p-1] = Next_Num;
                            Make_A_Change = true;
                        } if (Counting[i+1][p] < 0) {
                            Counting[i+1][p] = Next_Num;
                            Make_A_Change = true;
                        }
                    } else if (Pipe_Map[i][p].equals("F")) {
                        if (Counting[i][p+1] < 0) {
                            Counting[i][p+1] = Next_Num;
                            Make_A_Change = true;
                        } if (Counting[i+1][p] < 0) {
                            Counting[i+1][p] = Next_Num;
                            Make_A_Change = true;
                        }
                    } 

                }

            }
        }


        if (Make_A_Change == false) {
            Counting = new int[1][1];
        }


        return Counting;

    }


    public static int[][] Count_Zeros(int[][] Super_Size_Counting) {

        int Sum = 0;

        if (Super_Size_Counting[0][0] == 0)  {
            Super_Size_Counting[0][0] = -1;
            Sum = 1;
        }
        

        for (int i = 0; i < Super_Size_Counting.length; i++) {
            for (int p = 0; p < Super_Size_Counting[0].length; p++) {

                if (Super_Size_Counting[i][p] == 0) {

                    try {
                        if (Super_Size_Counting[i-1][p] == -1) {
                            Super_Size_Counting[i][p] = -1;
                            Sum++;
                        }
                    } catch (IndexOutOfBoundsException e) {}

                    try {
                        if (Super_Size_Counting[i+1][p] == -1) {
                            Super_Size_Counting[i][p] = -1;
                            Sum++;
                        }
                    } catch (IndexOutOfBoundsException e) {}

                    try {
                        if (Super_Size_Counting[i][p-1] == -1) {
                            Super_Size_Counting[i][p] = -1;
                            Sum++;
                        }
                    } catch (IndexOutOfBoundsException e) {}

                    try {
                        if (Super_Size_Counting[i][p+1] == -1) {
                            Super_Size_Counting[i][p] = -1;
                            Sum++;
                        }
                    } catch (IndexOutOfBoundsException e) {}




                }
            }
        }



        Super_Size_Counting[0][0] = Sum;


        return Super_Size_Counting;



    }




}