package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//971 >
//895 >

public class Day17_1 {
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


        int Count = Count_Distance(Map, Start, End);

        System.out.println(Count);

        


    }


    public static int Count_Distance(int[][] Map, int[] Starting_Point, int[] Ending_Point) {

        int Counter = 0;

        //{Count down to when can move, is ready to move, which direction came from, Counter for up to 3 same direction}
        int[][][] Counters = new int[Map.length][Map[0].length][4];

        //Counters[Starting_Point[0]][Starting_Point[1]][1] = 1;
        Counters[Starting_Point[0]][Starting_Point[1]][0] = 1;

        Boolean Done = false;


        while (Done == false) {
            for (int i = 0; i < Map.length; i++) {
                for (int p = 0; p < Map[0].length; p++) {


                    if (Counters[i][p][0] > 0) {

                        Counters[i][p][0]--;

                        if (Counters[i][p][0] == 0) {
                            Counters[i][p][1] = 1;
                            Counters[i][p][0] = -1;
                        }
                    }
                    
                }
            }


            for (int i = 0; i < Map.length; i++) {
                for (int p = 0; p < Map[0].length; p++) {

                    if (Counters[i][p][1] == 1) {

                        Counters[i][p][1] = 0;


                        if (i > 0) {

                            if (Counters[i - 1][p][0] == 0 && ( (Counters[i][p][2] == 1 && Counters[i][p][3] >= 3) == false ) ) {

                                Counters[i - 1][p][0] = Map[i - 1][p];
                                Counters[i - 1][p][2] = 1;
                                if (Counters[i][p][2] == 1) {
                                    Counters[i - 1][p][3] = Counters[i][p][3] + 1;
                                } else {
                                    Counters[i - 1][p][3] = 1;
                                }
                            }
                        }

                        if (i < Map.length - 1) {

                            if (Counters[i + 1][p][0] == 0 && ( (Counters[i][p][2] == 3 && Counters[i][p][3] >= 3) == false ) ) {

                                Counters[i + 1][p][0] = Map[i + 1][p];
                                Counters[i + 1][p][2] = 3;
                                if (Counters[i][p][2] == 3) {
                                    Counters[i + 1][p][3] = Counters[i][p][3] + 1;
                                } else {
                                    Counters[i + 1][p][3] = 1;
                                }
                            }

                        }

                        if (p > 0) {

                            if (Counters[i][p - 1][0] == 0 && ( (Counters[i][p][2] == 4 && Counters[i][p][3] >= 3) == false ) ) {

                                Counters[i][p - 1][0] = Map[i][p - 1];
                                Counters[i][p - 1][2] = 4;
                                if (Counters[i][p][2] == 4) {
                                    Counters[i][p - 1][3] = Counters[i][p][3] + 1;
                                } else {
                                    Counters[i][p - 1][3] = 1;
                                }
                            }

                        }

                        if (p < Map[0].length - 1) {

                            if (Counters[i][p + 1][0] == 0 && ((Counters[i][p][2] == 2 && Counters[i][p][3] >= 3) == false)) {

                                Counters[i][p + 1][0] = Map[i][p + 1];
                                Counters[i][p + 1][2] = 2;
                                if (Counters[i][p][2] == 2) {
                                    Counters[i][p + 1][3] = Counters[i][p][3] + 1;
                                } else {
                                    Counters[i][p + 1][3] = 1;
                                }
                            }

                        }





                    }




                }
            }


            Counter++;


            if (Counters[Ending_Point[0]][Ending_Point[1]][0] != 0) {
                
                Counter += Map[Ending_Point[0]][Ending_Point[1]];

                Done = true;
                break;
            }


        }
        

        for (int i = 0; i < Map.length; i++) {
            String Line = "";
            for (int p = 0; p < Map[0].length; p++) {

                if (Counters[i][p][0] != 0) {
                    if (Counters[i][p][2] == 1) {
                        Line += "^";
                    } else if (Counters[i][p][2] == 2) {
                        Line += ">";
                    } else if (Counters[i][p][2] == 3) {
                        Line += "V";
                    } else if (Counters[i][p][2] == 4) {
                        Line += "<";
                    } else {
                        Line += ".";
                    }
                } else {
                    Line += ".";
                }
            }
            System.out.println(Line);

        } 



        return Counter;

    }

}
