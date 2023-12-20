package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day20_1 {
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

        String[] Nodes = fullText.split("\n");


        String[][] Node_Details = new String[Nodes.length][5];

        for (int i = 0; i < Node_Details.length; i++) {
            Node_Details[i][0] = Nodes[i].charAt(0) + "";

            String[] Details = Nodes[i].split(" -> ");
            
            if (Details[0].length() < 5) {
                Node_Details[i][1] = Nodes[i].charAt(1) + "";
            } else {
                Node_Details[i][1] = Details[0];
            }

            Node_Details[i][2] = Details[1];
            
            if (Node_Details[i][0].equals("%")) {
                Node_Details[i][3] = "off";
            } else {
                Node_Details[i][3] = "";
            }
            Node_Details[i][4] = "low";

        }

        for (int i = 0; i < Node_Details.length; i++) {

            String[] Outputs = Node_Details[i][2].split("[ ,]+");

            for (int p = 0; p < Outputs.length; p++) {

                String Output = Outputs[p];
                int Output_Index = 0;

                for (int y = 0; y < Node_Details.length; y++) {

                    if (Output.equals(Node_Details[y][1])) {
                        Output_Index = y;
                        break;
                    }

                }

                if (Node_Details[Output_Index][0].equals("&")) {
                    Node_Details[Output_Index][3] += Node_Details[i][1] + " ";
                }

            }

        }

        
        String[] Pulse_Map = new String[Node_Details.length];
        for (int i = 0; i < Node_Details.length; i++) {
            if (Node_Details[i][0].equals("b")) {
                Pulse_Map[i] = "low";
                break;
            }
        }

       /*  for (int i = 0; i < Pulse_Map.length; i++) {
            System.out.println(Pulse_Map[i]);
        }

        System.out.println("-------");

        String[] Next_Pulse = Send_Pulse(Node_Details, Pulse_Map);

        for (int i = 0; i < Next_Pulse.length; i++) {
            System.out.println(Next_Pulse[i]);
        } */

        boolean Done = false;

        long Low = 0L;
        long High = 0L;

        while (Done == false) {

            String[] Next_Pulse = Send_Pulse(Node_Details, Pulse_Map);

            long New_Low = 0L;
            long New_High = 0L;

            for (int i = 0; i < Next_Pulse.length; i++) {
                if (Next_Pulse[i] != null && Next_Pulse[i].equals("low")) {
                    New_Low++;
                } else if (Next_Pulse[i] != null && Next_Pulse[i].equals("high")) {
                    New_High++;
                }
            }

            if (New_High + New_Low == 0) {
                Done = true;
            } else {
                Low += New_Low;
                High+= New_High;
                Pulse_Map = Next_Pulse;
            }

        }

        System.out.println(Low + " " + High);

    }

    public static String[] Send_Pulse(String[][] Node_Details, String[] Pulse_Map) {

        String[] New_Pulse_Map = new String[Pulse_Map.length];


        for (int i = 0; i < Node_Details.length; i++) {

            if (Pulse_Map[i] != null) {

                if (Pulse_Map[i].equals("low")) {

                    if (Node_Details[i][0].equals("%")) {

                        if (Node_Details[i][3].equals("off")) {
                            Node_Details[i][3] = "on";
                            Node_Details[i][4] = "high";

                            String[] Outputs = Node_Details[i][2].split("[, ]+");

                            for (int O = 0; O < Outputs.length; O++) {
                                for (int y = 0; y < Node_Details.length; y++) {
                                    if (Outputs[O].equals(Node_Details[y][1])) {
                                        New_Pulse_Map[y] = "high";
                                    }
                                }
                            }
                        } else if (Node_Details[i][3].equals("on")) {
                            Node_Details[i][3] = "off";
                            Node_Details[i][4] = "low";

                            String[] Outputs = Node_Details[i][2].split("[, ]+");

                            for (int O = 0; O < Outputs.length; O++) {
                                for (int y = 0; y < Node_Details.length; y++) {
                                    if (Outputs[O].equals(Node_Details[y][1])) {
                                        New_Pulse_Map[y] = "low";
                                    }
                                }
                            }
                        }

                    } else if (Node_Details[i][0].equals("&")) {

                        String[] InPuts = Node_Details[i][3].split("[, ]+");
                        boolean All_High = true;

                        for (int O = 0; O < InPuts.length; O++) {
                            for (int y = 0; y < Node_Details.length; y++) {
                                if (InPuts[O].equals(Node_Details[y][0])) {
                                    if (Node_Details[y][4].equals("low")) {
                                        All_High = false;
                                        break;
                                    }
                                }
                            }
                        }

                        if (All_High == true) {
                            String[] Outputs = Node_Details[i][2].split("[, ]+");

                            for (int O = 0; O < Outputs.length; O++) {
                                for (int y = 0; y < Node_Details.length; y++) {
                                    if (Outputs[O].equals(Node_Details[y][1])) {
                                        New_Pulse_Map[O] = "low";
                                    }
                                }
                            }
                        } else if (All_High == false) {
                            String[] Outputs = Node_Details[i][2].split("[, ]+");

                            for (int O = 0; O < Outputs.length; O++) {
                                for (int y = 0; y < Node_Details.length; y++) {
                                    if (Outputs[O].equals(Node_Details[y][1])) {
                                        New_Pulse_Map[y] = "high";
                                    }
                                }
                            }
                        }

                    } else if (Node_Details[i][0].equals("b")) {
                        String[] Outputs = Node_Details[i][2].split("[, ]+");

                        for (int O = 0; O < Outputs.length; O++) {
                            for (int y = 0; y < Node_Details.length; y++) {
                                if (Outputs[O].equals(Node_Details[y][1])) {
                                    New_Pulse_Map[y] = "low";
                                }
                            }
                        }

                        //System.out.println(New_Pulse_Map[1]);
                    }


                } else if (Pulse_Map[i].equals("high")) {
                    if (Node_Details[i][0].equals("&")) {

                        String[] InPuts = Node_Details[i][3].split("[, ]+");
                        boolean All_High = true;

                        for (int O = 0; O < InPuts.length; O++) {
                            for (int y = 0; y < Node_Details.length; y++) {
                                if (InPuts[O].equals(Node_Details[y][1])) {
                                    if (Node_Details[y][4].equals("low")) {
                                        All_High = false;
                                        break;
                                    }
                                }
                            }
                        }

                        if (All_High == true) {
                            String[] Outputs = Node_Details[i][2].split("[, ]+");

                            for (int O = 0; O < Outputs.length; O++) {
                                for (int y = 0; y < Node_Details.length; y++) {
                                    if (Outputs[O].equals(Node_Details[y][1])) {
                                        New_Pulse_Map[y] = "low";
                                    }
                                }
                            }
                        } else if (All_High == false) {
                            String[] Outputs = Node_Details[i][2].split("[, ]+");

                            for (int O = 0; O < Outputs.length; O++) {
                                for (int y = 0; y < Node_Details.length; y++) {
                                    if (Outputs[O].equals(Node_Details[y][1])) {
                                        New_Pulse_Map[y] = "high";
                                    }
                                }
                            }
                        }

                    }
                }


            }

        }


        return New_Pulse_Map;

    }
}
