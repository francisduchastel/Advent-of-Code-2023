package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day15_2 {
    public static void main(String[] args) {
        String fullText = "";
        String filepath = "Inputs\\Day 15.txt";
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


        String[] Values = fullText.split("[,\n]+");

        String[][][] Boxes = new String[256][0][2];

        for (int i = 0; i < Values.length; i++) {

            
            int[] Data = What_Box_Add_Remove_Focal(Values[i]);

            int Box = Data[0];

            String Label = Label(Values[i]);

            if (Data[1] == 1) {

                int Focal = Data[2];

                boolean Repeated = false;

                int Index = 0;

                for (int b = 0; b < Boxes[Box].length; b++) {

                    if (Boxes[Box][b][0].equals(Label)) {

                        Repeated = true;
                        Index = b;

                        break;

                    }

                }

                if (Repeated == false) {

                    String[][] temp = new String[Boxes[Box].length + 1][2];

                    for (int b = 0; b < Boxes[Box].length; b++) {

                        temp[b] = Boxes[Box][b];

                    }

                    temp[temp.length - 1][0] = Label;
                    temp[temp.length - 1][1] = "" + Focal;

                    Boxes[Box] = temp;

                } else if (Repeated == true) {

                    Boxes[Box][Index][1] = "" + Focal;

                }

            } else if (Data[1] == 0) {


                boolean Repeated = false;

                int Index = 0;

                for (int b = 0; b < Boxes[Box].length; b++) {

                    if (Boxes[Box][b][0].equals(Label)) {

                        Repeated = true;
                        Index = b;

                        break;

                    }

                }


                if (Repeated == true) {

                    Boxes[Box] = Remove_Index(Boxes[Box], Index);


                }




            }
            



        }



        long Sum = 0L;


        for (int i = 0; i < Boxes.length; i++) {

            Long Box_Sum = 0L;

            for (int p = 0; p < Boxes[i].length; p++) {

                Box_Sum += ( (i + 1) * (p + 1) * Integer.valueOf(Boxes[i][p][1]) );

            }


            Sum += Box_Sum;

        }

        System.out.println(Sum);

 



        String[][] a = { {"a", "b"}, {"b", "c"}  };

        //System.out.println(Remove_Index(a, 1)[0][0] + " " + Remove_Index(a, 1)[0][1]);

    }

    public static String[][] Remove_Index(String[][] Current_Box, int Index_Remove) {

        int c = 0;

        String[][] New_Box = new String[Current_Box.length - 1][2];

        for (int i = 0; i < Current_Box.length; i++) {

            if (i != Index_Remove) {

                New_Box[c] = Current_Box[i];
                c++;

            }

        }

        return New_Box;


    }

    public static int[] What_Box_Add_Remove_Focal(String Full_Value) {

        int[] Return = new int[3];

        String Label = "";

        for (int i = 0; i < Full_Value.length(); i++) {

            if (Full_Value.charAt(i) == '=') {

                Return[1] = 1;

                Return[2] = (int) Full_Value.charAt( i + 1) % 48;

                break;

            } else if (Full_Value.charAt(i) == '-') {

                Return[1] = 0;

                break;

            } else {

                Label += Full_Value.charAt(i);

            }

        }

        int Internal_Sum = 0;

        for (int p = 0; p < Label.length(); p++) {

            Internal_Sum += (int) Label.charAt(p);

            Internal_Sum *= 17;

            Internal_Sum = Internal_Sum % 256;

        }

        Return[0] = Internal_Sum;

        return Return;


    }

    public static String Label(String Full_Value) {

        String Label = Full_Value.split("[-=]+")[0];

        return Label;


    }
}
