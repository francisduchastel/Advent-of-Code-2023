package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day3_2 {

    public static void main(String[] args) {
        
        String fullText = "";
        String filepath = "Inputs\\Day 3.txt";
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


        String[] Input_Array = fullText.split("[\n]+");

        int Sum = 0;



        for (int i = 1; i < Input_Array.length - 1; i++) {

            String[] Target_Row = Input_Array[i].split("");

            String[] Row_Above = Input_Array[i - 1].split("");
            String[] Row_Below = Input_Array[i + 1].split("");






             for (int p = 0; p < Target_Row.length; p++) {
                
                
                if ( Target_Row[p].equals("*") ) {

                    int Gear_Power = 1;

                    int[] Above = FindNums(Row_Above, p);
                    int[] Same_Level = FindNums(Target_Row, p);
                    int[] Below = FindNums(Row_Below, p);

                    int[] All_Nums = new int[Above.length + Same_Level.length + Below.length];

                    int a = 0;
                    int s = 0;
                    int b = 0;


                    for (int z = 0; z < All_Nums.length; z++) {
                        
                        try {
                            All_Nums[z] = Above[a];
                            a++;
                        } catch (IndexOutOfBoundsException e) {
                            try {
                                All_Nums[z] = Same_Level[s];
                                s++;
                            } catch (IndexOutOfBoundsException ex) {
                                try {
                                    All_Nums[z] = Below[b];
                                    b++;
                                } catch( IndexOutOfBoundsException exc) {

                                }
                            }
                        }


                    }



                    if (All_Nums.length == 2) {
                        Gear_Power *= All_Nums[0] * All_Nums[1];

                        Sum += Gear_Power;
                    }


                



                




                }
                


            }


        }
    
        System.out.println(Sum);
    
    }




    public static Boolean IsNum(String x) {
        
        boolean Is_Num = false;

        if (x.equals("0") | x.equals("1") | x.equals("2") | x.equals("3") | x.equals("4") |
                x.equals("5") | x.equals("6") | x.equals("7") | x.equals("8") | x.equals("9") == true ) {
            Is_Num = true;
        }

        return Is_Num;

    }

    public static int[] FindNums(String[] Row, int Gear_Index) {
        
        String Possible_Num_Range = "";

        int i = -3;
        int j = 4;

        if (Row[Gear_Index - 1].equals(".")) {
            i = 0;
        } else if (Row[Gear_Index - 2].equals(".")) {
            i = -1;
        } else if (Row[Gear_Index - 3].equals(".")) {
            i = -2;
        }

        if (Row[Gear_Index + 1].equals(".")) {
            j = 1;
        } else if (Row[Gear_Index + 2].equals(".")) {
            j = 2;
        } else if (Row[Gear_Index + 3].equals(".")) {
            j = 3;
        }


        while (i < j) {
            try {
                Possible_Num_Range += Row[Gear_Index + i];
            } catch (IndexOutOfBoundsException e) {

            }

            i++;
        }

        String[] Numbers = Possible_Num_Range.split("[^0-9]+");


        int[] Nums = new int[Numbers.length];
        int p = 0;


        while (p < Numbers.length) {
            try {
                Nums[p] = Integer.valueOf(Numbers[p]);
            } catch (NumberFormatException e) {}
            
            p++;
        }

        int[] Return = new int[0];

        for (int x = 0; x < Nums.length; x++) {
            if (Nums[x] != 0) {

                int[] Temp = new int[Return.length + 1];

                for (int a = 0; a < Return.length; a++ ) {
                    Temp[a] = Return[a];
                }

                Return = Temp;

                Return[Return.length - 1] = Nums[x];

            }
        }

        return Return;

    }

}
