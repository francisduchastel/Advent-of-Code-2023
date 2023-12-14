package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day8_1 {
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
        int Destination = 656565;
        Boolean Found_Destination = false;
        int Steps = 0;

        while (Found_Destination == false) {

            for (int p = 0; p < Instructions_As_Index.length; p++) {

                int New_Destination = LR_Using_Node_Values[Destination][Instructions_As_Index[p]];

                Destination = New_Destination;

                Steps++;

                if (Destination == 909090) {
                    Found_Destination = true;
                    break;
                }

            }

        }


        System.out.println(Steps);
        

    }





    public static int Node_Value(String Node_Part) {

        char[] Characters = Node_Part.toCharArray();

        int Return = 0;

        for (int i = 0; i < 3; i++) {
            int Character_As_Int = (int)Characters[i];

            Return += Character_As_Int * Math.pow(10, 4 - (2* i));
        }

        return Return;

    }

}
