package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// 5714902906738363288 >
// 120560019358938 >
// 

public class Day19_2 {
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


        String[] Two = fullText.split(" ");

        String[] Work_Flows_Raw = Two[0].split("\n");

        String[][] Work_Flows = new String[Work_Flows_Raw.length][2];

        for (int i = 0; i < Work_Flows_Raw.length; i++) {

            String[] Split = Work_Flows_Raw[i].split("[{}]+");

            Work_Flows[i][0] = Split[0];
            Work_Flows[i][1] = Split[1];

        }


        Long Sum = 0L;

        Long[][] Ranges = {{1L, 4000L}, {1L, 4000L}, {1L, 4000L}, {1L, 4000L}};

        Sum = How_Many_In_Workflow("in", Ranges, Work_Flows, 0);
        

        System.out.println(Sum);
        



    }

    public static Long How_Many_In_Workflow(String Work_Flow_In, Long[][] Num_Ranges, String[][] Work_Flows, long Running_Total) {

        if (Work_Flow_In.equals("A")) {
            Long Total = ( (Num_Ranges[0][1] - Num_Ranges[0][0] + 1) * (Num_Ranges[1][1] - Num_Ranges[1][0] + 1) * (Num_Ranges[2][1] - Num_Ranges[2][0] + 1) * (Num_Ranges[3][1] - Num_Ranges[3][0] + 1) );
            System.out.println(Num_Ranges[0][0] + " " + Num_Ranges[0][1] + " - " + Num_Ranges[1][0] + " " + Num_Ranges[1][1] + " - " + Num_Ranges[2][0] + " " + Num_Ranges[2][1] + " - " + Num_Ranges[3][0] + " " + Num_Ranges[3][1]);
            //System.out.println(Total);
            //Running_Total += Total;
            return Total;
        }
        if (Work_Flow_In.equals("R")) {

            return 0L;
        }


        String Work_Flow_Wanted = "";

        for (int i = 0; i < Work_Flows.length; i++) {

            if (Work_Flows[i][0].equals(Work_Flow_In)) {
                Work_Flow_Wanted = Work_Flows[i][1];
                break;
            }

        }

        String[] Instructions = Work_Flow_Wanted.split(",");

        for (int i = 0; i < Instructions.length; i++) {

            if(Instructions[i].charAt(0) == 'A') {
                Long Total = ( (Num_Ranges[0][1] - Num_Ranges[0][0] + 1) * (Num_Ranges[1][1] - Num_Ranges[1][0] + 1) * (Num_Ranges[2][1] - Num_Ranges[2][0] + 1) * (Num_Ranges[3][1] - Num_Ranges[3][0] + 1) );
                System.out.println(Num_Ranges[0][0] + " " + Num_Ranges[0][1] + " - " + Num_Ranges[1][0] + " " + Num_Ranges[1][1] + " - " + Num_Ranges[2][0] + " " + Num_Ranges[2][1] + " - " + Num_Ranges[3][0] + " " + Num_Ranges[3][1]);
                //System.out.println(Total);
                //Running_Total += Total;

                return Total;
            } else if (Instructions[i].charAt(0) == 'R') {
                return 0L;
            } else {
                

                String[] Values = Instructions[i].split("[<>:]+");


                if (Values.length == 1) {
                    if (Values[0].equals("A")) {
                        Long Total = ( (Num_Ranges[0][1] - Num_Ranges[0][0] + 1) * (Num_Ranges[1][1] - Num_Ranges[1][0] + 1) * (Num_Ranges[2][1] - Num_Ranges[2][0] + 1) * (Num_Ranges[3][1] - Num_Ranges[3][0] + 1) );
                        System.out.println(Num_Ranges[0][0] + " " + Num_Ranges[0][1] + " - " + Num_Ranges[1][0] + " " + Num_Ranges[1][1] + " - " + Num_Ranges[2][0] + " " + Num_Ranges[2][1] + " - " + Num_Ranges[3][0] + " " + Num_Ranges[3][1]);
                        //System.out.println(Total);
                        //Running_Total += Total;
                        return Total;
                    } else if (Values[0].equals("R")) {
                        return 0L;
                    } else {

                        Long Total = How_Many_In_Workflow(Values[0], Num_Ranges, Work_Flows, 0);

                        //Running_Total += Total;

                        return Total;
                    }
                }

                int Number = Integer.valueOf(Values[1]);
                String Outcome = Values[2];

                if(Instructions[i].charAt(0) == 'x') {
                    if(Instructions[i].charAt(1) == '<') {

                        if (Num_Ranges[0][0] < Number && Num_Ranges[0][1] >= Number) {
                            
                            Long[][] Range_Take_Lower = new Long[4][2];
                            for (int l = 0; l < 4; l++) {
                                Range_Take_Lower[l][0] = Num_Ranges[l][0];
                                Range_Take_Lower[l][1] = Num_Ranges[l][1];
                            }

                            Range_Take_Lower[0][1] = Number - 1L;
                            Running_Total += How_Many_In_Workflow(Outcome, Range_Take_Lower, Work_Flows, 0);

                            Long[][] Range_Take_Upper = new Long[4][2];
                            for (int l = 0; l < 4; l++) {
                                Range_Take_Upper[l][0] = Num_Ranges[l][0];
                                Range_Take_Upper[l][1] = Num_Ranges[l][1];
                            }

                            Range_Take_Upper[0][0] = Number + 0L;
                            Running_Total += How_Many_In_Workflow(Work_Flow_In, Range_Take_Upper, Work_Flows, 0);

                            return Running_Total;
                            
                            
                        } else if (Num_Ranges[0][1] < Number) {
                            Running_Total += How_Many_In_Workflow(Outcome, Num_Ranges, Work_Flows, 0);
                            return Running_Total;
                        }

                    } else if (Instructions[i].charAt(1) == '>') {

                        if (Num_Ranges[0][0] <= Number && Num_Ranges[0][1] > Number) {
                            
                            Long[][] Range_Take_Lower = new Long[4][2];
                            for (int l = 0; l < 4; l++) {
                                Range_Take_Lower[l][0] = Num_Ranges[l][0];
                                Range_Take_Lower[l][1] = Num_Ranges[l][1];
                            }

                            Range_Take_Lower[0][1] = Number + 0L;
                            Running_Total += How_Many_In_Workflow(Work_Flow_In, Range_Take_Lower, Work_Flows, 0);

                            Long[][] Range_Take_Upper = new Long[4][2];
                            for (int l = 0; l < 4; l++) {
                                Range_Take_Upper[l][0] = Num_Ranges[l][0];
                                Range_Take_Upper[l][1] = Num_Ranges[l][1];
                            }

                            Range_Take_Upper[0][0] = Number + 1L;
                            Running_Total += How_Many_In_Workflow(Outcome, Range_Take_Upper, Work_Flows, 0);
                            return Running_Total;
                            
                            
                        } else if (Num_Ranges[0][0] > Number) {
                            Running_Total += How_Many_In_Workflow(Outcome, Num_Ranges, Work_Flows, 0);
                            return Running_Total;
                        }

                    }
                } else if (Instructions[i].charAt(0) == 'm') {
                    if(Instructions[i].charAt(1) == '<') {

                        if (Num_Ranges[1][0] < Number && Num_Ranges[1][1] >= Number) {
                            
                            Long[][] Range_Take_Lower = new Long[4][2];
                            for (int l = 0; l < 4; l++) {
                                Range_Take_Lower[l][0] = Num_Ranges[l][0];
                                Range_Take_Lower[l][1] = Num_Ranges[l][1];
                            }

                            Range_Take_Lower[1][1] = Number - 1L;
                            Running_Total += How_Many_In_Workflow(Outcome, Range_Take_Lower, Work_Flows, 0);

                            Long[][] Range_Take_Upper = new Long[4][2];
                            for (int l = 0; l < 4; l++) {
                                Range_Take_Upper[l][0] = Num_Ranges[l][0];
                                Range_Take_Upper[l][1] = Num_Ranges[l][1];
                            }

                            Range_Take_Upper[1][0] = Number + 0L;
                            Running_Total += How_Many_In_Workflow(Work_Flow_In, Range_Take_Upper, Work_Flows, 0);
                            return Running_Total;
                            
                            
                        } else if (Num_Ranges[1][1] < Number) {
                            Running_Total += How_Many_In_Workflow(Outcome, Num_Ranges, Work_Flows, 0);
                            return Running_Total;
                        }

                    } else if (Instructions[i].charAt(1) == '>') {

                        if (Num_Ranges[1][0] <= Number && Num_Ranges[1][1] > Number) {
                            
                            Long[][] Range_Take_Lower = new Long[4][2];
                            for (int l = 0; l < 4; l++) {
                                Range_Take_Lower[l][0] = Num_Ranges[l][0];
                                Range_Take_Lower[l][1] = Num_Ranges[l][1];
                            }

                            Range_Take_Lower[1][1] = Number + 0L;
                            Running_Total += How_Many_In_Workflow(Work_Flow_In, Range_Take_Lower, Work_Flows, 0);

                            Long[][] Range_Take_Upper = new Long[4][2];
                            for (int l = 0; l < 4; l++) {
                                Range_Take_Upper[l][0] = Num_Ranges[l][0];
                                Range_Take_Upper[l][1] = Num_Ranges[l][1];
                            }

                            Range_Take_Upper[1][0] = Number + 1L;
                            Running_Total += How_Many_In_Workflow(Outcome, Range_Take_Upper, Work_Flows, 0);
                            return Running_Total;
                            
                            
                        } else if (Num_Ranges[1][0] > Number) {
                            Running_Total += How_Many_In_Workflow(Outcome, Num_Ranges, Work_Flows, 0);
                            return Running_Total;
                        }

                    }
                } else if (Instructions[i].charAt(0) == 'a') {
                    if(Instructions[i].charAt(1) == '<') {

                        if (Num_Ranges[2][0] < Number && Num_Ranges[2][1] >= Number) {
                            
                            Long[][] Range_Take_Lower = new Long[4][2];
                            for (int l = 0; l < 4; l++) {
                                Range_Take_Lower[l][0] = Num_Ranges[l][0];
                                Range_Take_Lower[l][1] = Num_Ranges[l][1];
                            }

                            Range_Take_Lower[2][1] = Number - 1L;
                            Running_Total += How_Many_In_Workflow(Outcome, Range_Take_Lower, Work_Flows, 0);

                            Long[][] Range_Take_Upper = new Long[4][2];
                            for (int l = 0; l < 4; l++) {
                                Range_Take_Upper[l][0] = Num_Ranges[l][0];
                                Range_Take_Upper[l][1] = Num_Ranges[l][1];
                            }

                            Range_Take_Upper[2][0] = Number + 0L;
                            Running_Total += How_Many_In_Workflow(Work_Flow_In, Range_Take_Upper, Work_Flows, 0);
                            return Running_Total;
                            
                            
                        } else if (Num_Ranges[2][1] < Number) {
                            Running_Total += How_Many_In_Workflow(Outcome, Num_Ranges, Work_Flows, 0);
                            return Running_Total;
                        }

                    } else if (Instructions[i].charAt(1) == '>') {

                        if (Num_Ranges[2][0] <= Number && Num_Ranges[2][1] > Number) {
                            
                            Long[][] Range_Take_Lower = new Long[4][2];
                            for (int l = 0; l < 4; l++) {
                                Range_Take_Lower[l][0] = Num_Ranges[l][0];
                                Range_Take_Lower[l][1] = Num_Ranges[l][1];
                            }

                            Range_Take_Lower[2][1] = Number + 0L;
                            Running_Total += How_Many_In_Workflow(Work_Flow_In, Range_Take_Lower, Work_Flows, 0);

                            Long[][] Range_Take_Upper = new Long[4][2];
                            for (int l = 0; l < 4; l++) {
                                Range_Take_Upper[l][0] = Num_Ranges[l][0];
                                Range_Take_Upper[l][1] = Num_Ranges[l][1];
                            }

                            Range_Take_Upper[2][0] = Number + 1L;
                            Running_Total += How_Many_In_Workflow(Outcome, Range_Take_Upper, Work_Flows, 0);
                            return Running_Total;
                            
                            
                        } else if (Num_Ranges[2][0] > Number) {
                            Running_Total += How_Many_In_Workflow(Outcome, Num_Ranges, Work_Flows, 0);
                            return Running_Total;
                        }

                    }
                } else if (Instructions[i].charAt(0) == 's') {
                    if(Instructions[i].charAt(1) == '<') {

                        if (Num_Ranges[3][0] < Number && Num_Ranges[1][1] >= Number) {
                            
                            Long[][] Range_Take_Lower = new Long[4][2];
                            for (int l = 0; l < 4; l++) {
                                Range_Take_Lower[l][0] = Num_Ranges[l][0];
                                Range_Take_Lower[l][1] = Num_Ranges[l][1];
                            }

                            Range_Take_Lower[3][1] = Number - 1L;
                            Running_Total += How_Many_In_Workflow(Outcome, Range_Take_Lower, Work_Flows, 0);

                            Long[][] Range_Take_Upper = new Long[4][2];
                            for (int l = 0; l < 4; l++) {
                                Range_Take_Upper[l][0] = Num_Ranges[l][0];
                                Range_Take_Upper[l][1] = Num_Ranges[l][1];
                            }

                            Range_Take_Upper[3][0] = Number + 0L;
                            Running_Total += How_Many_In_Workflow(Work_Flow_In, Range_Take_Upper, Work_Flows, 0);
                            return Running_Total;
                            
                            
                        } else if (Num_Ranges[3][1] < Number) {
                            Running_Total += How_Many_In_Workflow(Outcome, Num_Ranges, Work_Flows, 0);
                            return Running_Total;
                        }

                    } else if (Instructions[i].charAt(1) == '>') {
                        if (Num_Ranges[3][0] <= Number && Num_Ranges[3][1] > Number) {
                            
                            Long[][] Range_Take_Lower = new Long[4][2];
                            for (int l = 0; l < 4; l++) {
                                Range_Take_Lower[l][0] = Num_Ranges[l][0];
                                Range_Take_Lower[l][1] = Num_Ranges[l][1];
                            }

                            Range_Take_Lower[3][1] = Number + 0L;
                            Running_Total += How_Many_In_Workflow(Work_Flow_In, Range_Take_Lower, Work_Flows, 0);

                            Long[][] Range_Take_Upper = new Long[4][2];
                            for (int l = 0; l < 4; l++) {
                                Range_Take_Upper[l][0] = Num_Ranges[l][0];
                                Range_Take_Upper[l][1] = Num_Ranges[l][1];
                            }

                            Range_Take_Upper[3][0] = Number + 1L;
                            Running_Total += How_Many_In_Workflow(Outcome, Range_Take_Upper, Work_Flows, 0);
                            return Running_Total;
                            
                            
                        } else if (Num_Ranges[3][0] > Number) {
                            Running_Total += How_Many_In_Workflow(Outcome, Num_Ranges, Work_Flows, 0);
                            return Running_Total;
                        }

                    }
                }

            }


            
            

        }


        return Running_Total;

    }



}