package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day8_2 {
    public static void main(String[] args) {
        String fullText = "";
        String filepath = "Inputs\\Day 8.txt";
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

        String[] fulltext_Split = fullText.split("\n");

        String[] Instructions = fulltext_Split[0].split("");
        int[] Instructions_As_Index = new int[Instructions.length];

        for (int i = 0; i < Instructions.length; i++) {
            if (Instructions[i].equals("L")) {
                Instructions_As_Index[i] = 0;
            } else if (Instructions[i].equals("R")) {
                Instructions_As_Index[i] = 1;
            }
        }

        String[] Nodes = new String[fulltext_Split.length - 2];

        for (int i = 0; i < Nodes.length; i++) {
            Nodes[i] = fulltext_Split[i + 2];
        }
        
        //Z = 90
        int[][] LR_Using_Node_Values = new int[909091][2];


        for (int i = 0; i < Nodes.length; i++) {
            String[] Node_Parts = Nodes[i].split("[ ()=,]+");

            int Origin_Value = Node_Value(Node_Parts[0]);
            int Left_Value = Node_Value(Node_Parts[1]);
            int Right_Value = Node_Value(Node_Parts[2]);

            LR_Using_Node_Values[Origin_Value][0] = Left_Value;
            LR_Using_Node_Values[Origin_Value][1] = Right_Value;
        }

        //A = 65
        int Destinations[] = {656565, 708365, 748665, 818865, 757865, 708865};
        int[] Steps_Array = new int[6];


        for (int j = 0; j < Destinations.length; j++) {

            Boolean Found_Destination = false;
            int Steps = 0;

            while (Found_Destination == false) {

                for (int p = 0; p < Instructions_As_Index.length; p++) {

                    
                        
                    int New_Destination = LR_Using_Node_Values[Destinations[j]][Instructions_As_Index[p]];

                    Destinations[j] = New_Destination;


                    Steps++;

                    if (Arrived_At_Z(Destinations[j])) {
                        Found_Destination = true;
                        Steps_Array[j] = Steps;
                        break;
                    }
                    
                }
            }

        }

        Long Steps_For_All = LCM(Steps_Array);
        
        System.out.println(Steps_For_All);

    }





    public static int Node_Value(String Node_Part) {

        char[] Characters = Node_Part.toCharArray();

        int Return = 0;

        for (int i = 0; i < 3; i++) {
            int Character_As_int = (int)Characters[i];

            Return += Character_As_int * Math.pow(10, 4 - (2* i));
        }

        return Return;

    }

    public static Boolean Arrived_At_Z(int Destination_As_int) {

        String[] Destination_Array = String.valueOf(Destination_As_int).split("");

        if (Destination_Array[4].equals("9") && Destination_Array[5].equals("0")) {
            return true;
        } else {
            return false;
        }


    }

    public static Long LCM(int[] Array) {

        Long[] Array_Long = new Long[Array.length];

        for (int i = 0; i < Array.length; i++) {
            Array_Long[i] = Long.valueOf(Array[i]);
        }


        while(Array_Long.length > 1){

            Long[] temp = new Long[(Array_Long.length / 2) + 1];

            if (Array_Long.length % 2 == 0) {
                temp = new Long[Array_Long.length / 2];
            }

            for (int i = 0; i < Array_Long.length - 1; i += 2) {
                Long First_Number = Array_Long[i];
                Long Second_Number = Array_Long[i + 1];

                boolean Done = false;

                Long LCM = 1L;


                while (Done == false) {
                    for (int z = 2; z <= First_Number + 1; z++) {
                        
                        if ((First_Number % z == 0) && (Second_Number % z == 0)) {
                            
                            First_Number /= z;
                            Second_Number /= z;
                            LCM *= z;

                            if (First_Number == 1 || Second_Number == 1) {
                                
                                LCM *= First_Number * Second_Number;
                                Done = true;
                            }

                            break;
                        }
                        if (z == First_Number || z == First_Number + 1) {
                            
                            LCM *= First_Number * Second_Number;
                            
                            Done = true;
                            break;
                        }
                    }
                }

                temp[i / 2] = LCM;

            }

            if (Array_Long.length % 2 != 0) {
                temp[temp.length - 1] = Array_Long[Array_Long.length - 1];
            }

            Array_Long = temp;

        }


        return Array_Long[0];
    }

}
