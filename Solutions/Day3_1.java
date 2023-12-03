package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day3_1 {

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


        int Starting_Index = 0;
        int Ending_Index = Input_Array.length - 1;
        int Number = 0;
        int Sum = 0;

        String[] First_Row = Input_Array[Starting_Index].split("");
        String[] Second_Row = Input_Array[1].split("");
        
        //First Row

        for (int p = 0; p < First_Row.length; p++) {

            int Final_Num_Index = p;

            if ( IsNum(First_Row[p]) == true ) {
            
                
                boolean y = false;

                try{
                    y = IsNum(First_Row[p + 1]);
                } catch (IndexOutOfBoundsException e) {
                    y = false;
                }


                if (y == true) {

                    boolean x = false;
                    Final_Num_Index++;

                    try {
                        x = IsNum(First_Row[p + 2]);
                        
                    } catch (IndexOutOfBoundsException e) {
                        x = false;
                    }

                    if (x == true) {
                        Final_Num_Index++;
                        Number = (Integer.valueOf(First_Row[p]) * 100 ) + (Integer.valueOf(First_Row[p + 1]) * 10) + (Integer.valueOf(First_Row[p + 2]));

                        Boolean Should_Add = false;

                        if (Second_Row[p].equals(".") == false | Second_Row[p +  1].equals(".") == false | Second_Row[p + 2].equals(".") == false) {
                            Should_Add = true;
                        }

                        try{
                            if (First_Row[p - 1].equals(".") == false | Second_Row[p - 1].equals(".") == false) {
                                Should_Add = true;
                            }
                        } catch (IndexOutOfBoundsException e) {

                        }

                        try{
                            if (First_Row[p + 3].equals(".") == false | Second_Row[p + 3].equals(".") == false) {
                                Should_Add = true;
                            }
                        } catch (IndexOutOfBoundsException e) {

                        }

                        if (Should_Add == true) {
                            

                            Sum += Number;
                        }

                    } else if (x == false) {
                        Number = (Integer.valueOf(First_Row[p]) * 10 ) + (Integer.valueOf(First_Row[p + 1]));

                        Boolean Should_Add = false;

                        if (Second_Row[p].equals(".") == false | Second_Row[p +  1].equals(".") == false) {
                            Should_Add = true;
                        }

                        try{
                            if (First_Row[p - 1].equals(".") == false | Second_Row[p - 1].equals(".") == false) {
                                Should_Add = true;
                            }
                        } catch (IndexOutOfBoundsException e) {

                        }

                        try{
                            if (First_Row[p + 2].equals(".") == false | Second_Row[p + 2].equals(".") == false) {
                                Should_Add = true;
                            }
                        } catch (IndexOutOfBoundsException e) {

                        }

                        if (Should_Add == true) {
                            
                            
                            Sum += Number;
                        }


                    }



                    } else if (y == false) {
                        
                        Number = Integer.valueOf(First_Row[p]);
                        
                        Boolean Should_Add = false;

                        if (Second_Row[p].equals(".") == false) {
                            Should_Add = true;
                        }

                        try{
                            if (First_Row[p - 1].equals(".") == false | Second_Row[p - 1].equals(".") == false) {
                                Should_Add = true;
                            }
                        } catch (IndexOutOfBoundsException e) {

                        }

                        try{
                            if (First_Row[p + 1].equals(".") == false | Second_Row[p + 1].equals(".") == false) {
                                Should_Add = true;
                            }
                        } catch (IndexOutOfBoundsException e) {

                        }

                        if (Should_Add == true) {
                            Sum += Number;


                        }

                    }

                }
                

                p = Final_Num_Index;

            }







    


        for (int i = 1; i < Input_Array.length - 1; i++) {

            String[] Target_Row = Input_Array[i].split("");

            String[] Row_Above = Input_Array[i - 1].split("");
            String[] Row_Below = Input_Array[i + 1].split("");

            for (int p = 0; p < Target_Row.length; p++) {
                
                int Final_Num_Index = p;
                
                if ( IsNum(Target_Row[p]) == true ) {
                    
                    boolean y = false;

                    try{
                        y = IsNum(Target_Row[p + 1]);
                    } catch (IndexOutOfBoundsException e) {
                        y = false;
                    }

                    if (y == true) {
                        
                        Final_Num_Index++;
                        boolean x = false;
                        try {
                            x = IsNum(Target_Row[p + 2]);
                            
                        } catch (IndexOutOfBoundsException e) {
                            x = false;
                        }

                        if (x == true) {

                            Final_Num_Index++;

                            Number = (Integer.valueOf(Target_Row[p]) * 100 ) + (Integer.valueOf(Target_Row[p + 1]) * 10) + (Integer.valueOf(Target_Row[p + 2]));

                            Boolean Should_Add = false;

                            if ((!Row_Below[p].equals(".")) | (!Row_Below[p +  1].equals(".")) | (!Row_Below[p + 2].equals(".")) |
                                (!Row_Above[p].equals(".")) | (!Row_Above[p +  1].equals(".")) | (!Row_Above[p + 2].equals("."))) {
                                Should_Add = true;
                            }

                            try{
                                if ((!Target_Row[p - 1].equals(".")) | (!Row_Below[p - 1].equals(".")) | (!Row_Above[p - 1].equals("."))) {
                                    Should_Add = true;
                                }
                            } catch (IndexOutOfBoundsException e) {

                            }

                            try{
                                if ((!Target_Row[p + 3].equals(".")) | (!Row_Below[p + 3].equals(".")) | (!Row_Above[p + 3].equals("."))) {
                                    Should_Add = true;
                                }
                            } catch (IndexOutOfBoundsException e) {

                            }

                            if (Should_Add) {
                                Sum += Number;

                            }

                        } else if (x == false) {
                            Number = (Integer.valueOf(Target_Row[p]) * 10 ) + (Integer.valueOf(Target_Row[p + 1]));

                            Boolean Should_Add = false;

                            if ((Row_Below[p].equals(".") == false) | (Row_Below[p +  1].equals(".") == false) |
                                (Row_Above[p].equals(".") == false) | (Row_Above[p +  1].equals(".") == false)) {
                                Should_Add = true;
                            }

                            try{
                                if ((Target_Row[p - 1].equals(".") == false) | (Row_Below[p - 1].equals(".") == false) | (Row_Above[p - 1].equals(".") == false )) {
                                    Should_Add = true;
                                }
                            } catch (IndexOutOfBoundsException e) {

                            }

                            try{
                                if ((Target_Row[p + 2].equals(".") == false) | (Row_Below[p + 2].equals(".") == false) | (Row_Above[p + 2].equals(".") == false)) {
                                    Should_Add = true;
                                }
                            } catch (IndexOutOfBoundsException e) {

                            }

                            if (Should_Add) {
                                Sum += Number;


                            }


                        }



                        } else if (y == false) {
                            
                            Number = Integer.valueOf(Target_Row[p]);
                            
                            Boolean Should_Add = false;

                            if ((Row_Below[p].equals(".") == false) | (Row_Above[p].equals(".") == false)) {
                                Should_Add = true;
                            }

                            try{
                                if ((Target_Row[p - 1].equals(".") == false) | (Row_Below[p - 1].equals(".") == false) | (Row_Above[p - 1].equals(".") == false)) {
                                    Should_Add = true;
                                }
                            } catch (IndexOutOfBoundsException e) {

                            }

                            try{
                                if ((Target_Row[p + 1].equals(".") == false) | (Row_Below[p + 1].equals(".") == false) | (Row_Above[p + 1].equals(".") == false)) {
                                    Should_Add = true;
                                }
                            } catch (IndexOutOfBoundsException e) {

                            }

                            if (Should_Add == true) {
                                Sum += Number;

                            }

                        }

                }
                

                p = Final_Num_Index;

            }

        }





        String[] Last_Row = Input_Array[Ending_Index].split("");
        String[] Before_Last = Input_Array[Ending_Index - 1].split("");

        for (int p = 0; p < Last_Row.length; p++) {

            int Final_Num_Index = p;

            if ( IsNum(Last_Row[p]) == true ) {
            

                boolean y = false;

                try{
                    y = IsNum(Last_Row[p + 1]);
                } catch (IndexOutOfBoundsException e) {
                    y = false;
                }
                
                if (y == true) {

                    Final_Num_Index++;

                    boolean x = false;
                    try {
                        x = IsNum(Last_Row[p + 2]);
                        
                    } catch (IndexOutOfBoundsException e) {
                        x = false;
                    }

                    if (x == true) {

                        Final_Num_Index++;

                        Number = (Integer.valueOf(Last_Row[p]) * 100 ) + (Integer.valueOf(Last_Row[p + 1]) * 10) + (Integer.valueOf(Last_Row[p + 2]));

                        Boolean Should_Add = false;

                        if (Before_Last[p].equals(".") == false | Before_Last[p +  1].equals(".") == false | Before_Last[p + 2].equals(".") == false) {
                            Should_Add = true;
                        }

                        try{
                            if (Last_Row[p - 1].equals(".") == false | Before_Last[p - 1].equals(".") == false) {
                                Should_Add = true;
                            }
                        } catch (IndexOutOfBoundsException e) {

                        }

                        try{
                            if (Last_Row[p + 3].equals(".") == false | Before_Last[p + 3].equals(".") == false) {
                                Should_Add = true;
                            }
                        } catch (IndexOutOfBoundsException e) {

                        }

                        if (Should_Add == true) {
                            Sum += Number;


                        }

                    } else if (x == false) {
                        Number = (Integer.valueOf(Last_Row[p]) * 10 ) + (Integer.valueOf(Last_Row[p + 1]));

                        Boolean Should_Add = false;

                        if (Before_Last[p].equals(".") == false | Before_Last[p +  1].equals(".") == false) {
                            Should_Add = true;
                        }

                        try{
                            if (Last_Row[p - 1].equals(".") == false | Before_Last[p - 1].equals(".") == false) {
                                Should_Add = true;
                            }
                        } catch (IndexOutOfBoundsException e) {

                        }

                        try{
                            if (Last_Row[p + 2].equals(".") == false | Before_Last[p + 2].equals(".") == false) {
                                Should_Add = true;
                            }
                        } catch (IndexOutOfBoundsException e) {

                        }

                        if (Should_Add == true) {
                            Sum += Number;


                        }


                    }



                    } else if (y == false) {
                        
                        Number = Integer.valueOf(Last_Row[p]);
                        
                        Boolean Should_Add = false;

                        if (Before_Last[p].equals(".") == false) {
                            Should_Add = true;
                        }

                        try{
                            if (Last_Row[p - 1].equals(".") == false | Before_Last[p - 1].equals(".") == false) {
                                Should_Add = true;
                            }
                        } catch (IndexOutOfBoundsException e) {

                        }

                        try{
                            if (Last_Row[p + 1].equals(".") == false | Before_Last[p + 1].equals(".") == false) {
                                Should_Add = true;
                            }
                        } catch (IndexOutOfBoundsException e) {

                        }

                        if (Should_Add == true) {
                            Sum += Number;


                        }

                    }

                }
                

                p = Final_Num_Index;

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
}
