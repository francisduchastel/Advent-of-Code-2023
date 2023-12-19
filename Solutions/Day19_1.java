package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day19_1 {
    public static void main(String[] args) {
        String fullText = "";
        String filepath = "Inputs\\Day 19.txt";
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

        String[] Parts_temp = Two[1].split("\n");
        String[] Parts = new String[Parts_temp.length - 1];

        for (int i = 0; i < Parts.length; i++) {
            Parts[i] = Parts_temp[i + 1];
        }


        Long Sum = 0L;

        for (int i = 0; i < Parts.length; i++) {
            
            String[] Part_Attributes = Parts[i].split("[x=,mas{}]+");

            int X = Integer.valueOf(Part_Attributes[1]);
            int M = Integer.valueOf(Part_Attributes[2]);
            int A = Integer.valueOf(Part_Attributes[3]);
            int S = Integer.valueOf(Part_Attributes[4]);

            int[] Part_Nums = new int[4];
            Part_Nums[0] = X;
            Part_Nums[1] = M;
            Part_Nums[2] = A;
            Part_Nums[3] = S;


            String Outcome = Put_In_Workflow("in", Part_Nums, Work_Flows);


            if (Outcome.equals("A")) {
                Sum += Part_Nums[0] + Part_Nums[1] + Part_Nums[2] + Part_Nums[3];
            }


        }

        System.out.println(Sum);
        



    }

    public static String Put_In_Workflow(String Work_Flow_In, int[] Part_Nums, String[][] Work_Flows) {

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
                return "A";
            } else if (Instructions[i].charAt(0) == 'R') {
                return "R";
            } else {
                

                String[] Values = Instructions[i].split("[<>:]+");

                if (Values.length == 1) {
                    return Put_In_Workflow(Values[0], Part_Nums, Work_Flows);
                }

                int Number = Integer.valueOf(Values[1]);
                String Outcome = Values[2];

                if(Instructions[i].charAt(0) == 'x') {
                    if(Instructions[i].charAt(1) == '<') {

                        if (Part_Nums[0] < Number) {
                            if (Outcome.equals("R") || Outcome.equals("A")) {
                                return Outcome;
                            } else {
                                return Put_In_Workflow(Outcome, Part_Nums, Work_Flows);
                            }
                        }

                    } else if (Instructions[i].charAt(1) == '>') {

                        if (Part_Nums[0] > Number) {
                            
                            if (Outcome.equals("R") || Outcome.equals("A")) {
                                return Outcome;
                            } else {
                                return Put_In_Workflow(Outcome, Part_Nums, Work_Flows);
                            }
                            
                        }

                    }
                } else if (Instructions[i].charAt(0) == 'm') {
                    if(Instructions[i].charAt(1) == '<') {

                        if (Part_Nums[1] < Number) {
                            if (Outcome.equals("R") || Outcome.equals("A")) {
                                return Outcome;
                            } else {
                                return Put_In_Workflow(Outcome, Part_Nums, Work_Flows);
                            }
                        }

                    } else if (Instructions[i].charAt(1) == '>') {

                        if (Part_Nums[1] > Number) {
                            if (Outcome.equals("R") || Outcome.equals("A")) {
                                return Outcome;
                            } else {
                                return Put_In_Workflow(Outcome, Part_Nums, Work_Flows);
                            }
                        }

                    }
                } else if (Instructions[i].charAt(0) == 'a') {
                    if(Instructions[i].charAt(1) == '<') {

                        if (Part_Nums[2] < Number) {
                            if (Outcome.equals("R") || Outcome.equals("A")) {
                                return Outcome;
                            } else {
                                return Put_In_Workflow(Outcome, Part_Nums, Work_Flows);
                            }
                        }

                    } else if (Instructions[i].charAt(1) == '>') {

                        if (Part_Nums[2] > Number) {
                            if (Outcome.equals("R") || Outcome.equals("A")) {
                                return Outcome;
                            } else {
                                return Put_In_Workflow(Outcome, Part_Nums, Work_Flows);
                            }
                        }

                    }
                } else if (Instructions[i].charAt(0) == 's') {
                    if(Instructions[i].charAt(1) == '<') {

                        if (Part_Nums[3] < Number) {
                            if (Outcome.equals("R") || Outcome.equals("A")) {
                                return Outcome;
                            } else {
                                return Put_In_Workflow(Outcome, Part_Nums, Work_Flows);
                            }
                        }

                    } else if (Instructions[i].charAt(1) == '>') {
                        if (Part_Nums[3] > Number) {
                            if (Outcome.equals("R") || Outcome.equals("A")) {
                                return Outcome;
                            } else {
                                return Put_In_Workflow(Outcome, Part_Nums, Work_Flows);
                            }
                        }

                    }
                }

            }


            
            

        }


        return "R";

    }



}