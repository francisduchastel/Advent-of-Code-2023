package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day7_2 {
    public static void main(String[] args) {

        String fullText = "";
        String filepath = "Inputs\\Day 7.txt";
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

        String[] Hands = fullText.split("[\n]");


        String[][] five_of_Kind = new String[0][2];
        String[][] four_of_Kind = new String[0][2];
        String[][] Full_House = new String[0][2];
        String[][] three_of_Kind = new String[0][2];
        String[][] two_pair = new String[0][2];
        String[][] one_pair = new String[0][2];
        String[][] High_Card = new String[0][2];
        
        for (int i = 0; i < Hands.length; i++) {

            String Hand_Cards = Hands[i].split(" ")[0];
            String Power = Hands[i].split(" ")[1];

            String[][] Hand_Makeup = Hand_Type(Hand_Cards);


            if (Hand_Makeup.length == 1) {

                String[][] temp = new String[five_of_Kind.length + 1][2];

                for (int p = 0; p < five_of_Kind.length; p++) {
                    temp[p] = five_of_Kind[p];
                }

                five_of_Kind = temp;

                five_of_Kind[five_of_Kind.length - 1][0] = Hand_Cards;
                five_of_Kind[five_of_Kind.length - 1][1] = Power;

            } else if (Hand_Makeup.length == 5) {

                String[][] temp = new String[High_Card.length + 1][2];

                for (int p = 0; p < High_Card.length; p++) {
                    temp[p] = High_Card[p];
                }

                High_Card = temp;

                High_Card[High_Card.length - 1][0] = Hand_Cards;
                High_Card[High_Card.length - 1][1] = Power;

            } else if (Hand_Makeup.length == 4) {

                String[][] temp = new String[one_pair.length + 1][2];

                for (int p = 0; p < one_pair.length; p++) {
                    temp[p] = one_pair[p];
                }

                one_pair = temp;

                one_pair[one_pair.length - 1][0] = Hand_Cards;
                one_pair[one_pair.length - 1][1] = Power;

            } else if (Hand_Makeup.length == 3) {

                Boolean True_For_2_Pair = true;

                for (int z = 0; z < Hand_Makeup.length; z++) {
                    if (Hand_Makeup[z][1].equals("3")) {
                        True_For_2_Pair = false;
                    }
                }

                if (True_For_2_Pair == true) {

                    String[][] temp = new String[two_pair.length + 1][2];

                    for (int p = 0; p < two_pair.length; p++) {
                        temp[p] = two_pair[p];
                    }

                    two_pair = temp;

                    two_pair[two_pair.length - 1][0] = Hand_Cards;
                    two_pair[two_pair.length - 1][1] = Power;

                } else if (True_For_2_Pair == false) {

                    String[][] temp = new String[three_of_Kind.length + 1][2];

                    for (int p = 0; p < three_of_Kind.length; p++) {
                        temp[p] = three_of_Kind[p];
                    }

                    three_of_Kind = temp;

                    three_of_Kind[three_of_Kind.length - 1][0] = Hand_Cards;
                    three_of_Kind[three_of_Kind.length - 1][1] = Power;

                }

            } else if (Hand_Makeup.length == 2) {


                Boolean True_For_Full_House = true;

                for (int z = 0; z < Hand_Makeup.length; z++) {
                    if (Hand_Makeup[z][1].equals("4")) {
                        True_For_Full_House = false;
                    }
                }

                if (True_For_Full_House == true) {

                    String[][] temp = new String[Full_House.length + 1][2];

                    for (int p = 0; p < Full_House.length; p++) {
                        temp[p] = Full_House[p];
                    }

                    Full_House = temp;

                    Full_House[Full_House.length - 1][0] = Hand_Cards;
                    Full_House[Full_House.length - 1][1] = Power;

                } else if (True_For_Full_House == false) {

                    String[][] temp = new String[four_of_Kind.length + 1][2];

                    for (int p = 0; p < four_of_Kind.length; p++) {
                        temp[p] = four_of_Kind[p];
                    }

                    four_of_Kind = temp;

                    four_of_Kind[four_of_Kind.length - 1][0] = Hand_Cards;
                    four_of_Kind[four_of_Kind.length - 1][1] = Power;

                }

            }



        }



        int[] five_of_Kind_Stength = GetStrengthArray(five_of_Kind);
        int[] four_of_Kind_Stength = GetStrengthArray(four_of_Kind);
        int[] Full_House_Stength = GetStrengthArray(Full_House);
        int[] three_of_Kind_Stength = GetStrengthArray(three_of_Kind);
        int[] two_pair_Stength = GetStrengthArray(two_pair);
        int[] one_pair_Stength = GetStrengthArray(one_pair);
        int[] High_Card_Stength = GetStrengthArray(High_Card);


        int Sum = 0;
        int Product = 1;

        while (High_Card.length > 0) {
            
            int Min_index = 0;
            int Min_Power = 2000000000;

            for (int f = 0; f < High_Card.length; f++) {
                if (High_Card_Stength[f] < Min_Power) {
                    Min_index = f;
                    Min_Power = High_Card_Stength[f];
                }
            }


            int Power = Integer.parseInt(High_Card[Min_index][1]);

            Sum += (Product * Power);
            Product++;

            String[][] temp = new String[High_Card.length - 1][2];

            int g = 0;
            for (int t = 0; t < High_Card.length; t++) {
                
                
                if (t != Min_index) {
                    temp[g] = High_Card[t];
                    g++;
                }
            }

            High_Card = temp;

            High_Card_Stength = GetStrengthArray(High_Card);


        }

        while (one_pair.length > 0) {
            
            int Min_index = 0;
            int Min_Power = 2000000000;

            for (int f = 0; f < one_pair.length; f++) {
                if (one_pair_Stength[f] < Min_Power) {
                    Min_index = f;
                    Min_Power = one_pair_Stength[f];
                }
            }


            Sum += (Product * Integer.valueOf(one_pair[Min_index][1]));
            Product++;

            String[][] temp = new String[one_pair.length - 1][2];

            int g = 0;
            
            for (int t = 0; t < one_pair.length; t++) {
            
                
                if (t != Min_index) {
                    temp[g][0] = one_pair[t][0];
                    temp[g][1] = one_pair[t][1];
                    g++;
                }
            }

            one_pair = temp;
            one_pair_Stength = GetStrengthArray(one_pair);


        }

        while (two_pair.length > 0) {

            int Min_index = 0;
            int Min_Power = 2000000000;

            for (int f = 0; f < two_pair.length; f++) {
                if (two_pair_Stength[f] < Min_Power) {
                    Min_index = f;
                    Min_Power = two_pair_Stength[f];
                }
            }


            Sum += (Product * Integer.valueOf(two_pair[Min_index][1]));
            Product++;

            String[][] temp = new String[two_pair.length - 1][2];

            int g = 0;

            for (int t = 0; t < two_pair.length; t++) {
            
                
                if (t != Min_index) {
                    temp[g] = two_pair[t];
                    g++;
                }
            }

            two_pair = temp;
            two_pair_Stength = GetStrengthArray(two_pair);
        }


        while (three_of_Kind.length > 0) {

            int Min_index = 0;
            int Min_Power = 2000000000;

            for (int f = 0; f < three_of_Kind.length; f++) {
                if (three_of_Kind_Stength[f] < Min_Power) {
                    Min_index = f;
                    Min_Power = three_of_Kind_Stength[f];
                }
            }


            Sum += (Product * Integer.valueOf(three_of_Kind[Min_index][1]));
            Product++;

            String[][] temp = new String[three_of_Kind.length - 1][2];

            int g = 0;

            for (int t = 0; t < three_of_Kind.length; t++) {
                
                if (t != Min_index) {
                    temp[g] = three_of_Kind[t];
                    g++;
                }
            }

            three_of_Kind = temp;
            three_of_Kind_Stength = GetStrengthArray(three_of_Kind);
        }

        while (Full_House.length > 0) {

            int Min_index = 0;
            int Min_Power = 2000000000;

            for (int f = 0; f < Full_House.length; f++) {
                if (Full_House_Stength[f] < Min_Power) {
                    Min_index = f;
                    Min_Power = Full_House_Stength[f];
                }
            }


            Sum += (Product * Integer.valueOf(Full_House[Min_index][1]));
            Product++;

            String[][] temp = new String[Full_House.length - 1][2];

            int g = 0;

            for (int t = 0; t < Full_House.length; t++) {
                
                if (t != Min_index) {
                    temp[g] = Full_House[t];
                    g++;
                }
            }

            Full_House = temp;
            Full_House_Stength = GetStrengthArray(Full_House);
        }


        while (four_of_Kind.length > 0) {

            int Min_index = 0;
            int Min_Power = 2000000000;

            for (int f = 0; f < four_of_Kind.length; f++) {
                if (four_of_Kind_Stength[f] < Min_Power) {
                    Min_index = f;
                    Min_Power = four_of_Kind_Stength[f];
                }
            }


            Sum += (Product * Integer.valueOf(four_of_Kind[Min_index][1]));
            Product++;

            String[][] temp = new String[four_of_Kind.length - 1][2];

            int g = 0;

            for (int t = 0; t < four_of_Kind.length; t++) {
                
                if (t != Min_index) {
                    temp[g] = four_of_Kind[t];
                    g++;
                }
            }

            four_of_Kind = temp;
            four_of_Kind_Stength = GetStrengthArray(four_of_Kind);
        }

        while (five_of_Kind.length > 0) {

            int Min_index = 0;
            int Min_Power = 2000000000;

            for (int f = 0; f < five_of_Kind.length; f++) {
                if (five_of_Kind_Stength[f] < Min_Power) {
                    Min_index = f;
                    Min_Power = five_of_Kind_Stength[f];
                }
            }


            Sum += (Product * Integer.valueOf(five_of_Kind[Min_index][1]));
            Product++;

            String[][] temp = new String[five_of_Kind.length - 1][2];

            int g = 0;

            for (int t = 0; t < five_of_Kind.length; t++) {
                
                if (t != Min_index) {
                    temp[g] = five_of_Kind[t];
                    g++;
                }
            }

            five_of_Kind = temp;
            five_of_Kind_Stength = GetStrengthArray(five_of_Kind);
        }


        System.out.println(Sum);
    }

    public static String[][] Hand_Type(String Hand) {

        String[] Cards = Hand.split("");

        String[][] Types_of_cards = new String[0][2];

        for (int i = 0; i < Cards.length; i++) {
            
            String Card = Cards[i];

            Boolean Added = false;
            for (int p = 0; p < Types_of_cards.length; p++) {
                if (Card.equals(Types_of_cards[p][0])) {
                    int New_Value = Integer.valueOf(Types_of_cards[p][1]) + 1;

                    Types_of_cards[p][1] = New_Value + "";
                    Added = true;

                }
            }

            if (Added == false) {
                String[][] Temp = new String[Types_of_cards.length + 1][2];

                for (int q = 0; q < Types_of_cards.length; q++) {
                    Temp[q] = Types_of_cards[q];
                }

                Types_of_cards = Temp;
                
                Types_of_cards[Types_of_cards.length - 1][0] = Card;
                Types_of_cards[Types_of_cards.length - 1][1] = 1 + "";
            }

        }


        for (int i = 0; i < Types_of_cards.length; i++) {
            if (Types_of_cards[i][0].equals("J") && Types_of_cards.length > 1) {
                String[][] temp = new String[Types_of_cards.length - 1][2];

                int p = 0;

                for (int q = 0; q < Types_of_cards.length; q++) {
                    if (q != i) {
                        temp[p][0] = Types_of_cards[q][0];
                        temp[p][1] = Integer.valueOf(Types_of_cards[q][1]) + Integer.valueOf(Types_of_cards[i][1]) + "";
                        p++;
                    }
                }

                Types_of_cards = temp;
            }
        }

        return Types_of_cards;


    }


    public static int[] GetStrengthArray(String[][] Hand_Category) {

        int[] Strenth = new int[Hand_Category.length];


        for (int i = 0; i < Hand_Category.length; i++) {

            int Strength_Of_Hand = 0;

            String Hand = Hand_Category[i][0];

            String[] Cards = Hand.split("");

            for (int z = 0; z < Cards.length; z++) {
                if (Cards[z].equals("A")) {
                    Strength_Of_Hand += 14 * Math.pow(10, 8 - (2 * z));
                } else if (Cards[z].equals("K")) {
                    Strength_Of_Hand += 13 * Math.pow(10, 8 - (2 * z));
                } else if (Cards[z].equals("Q")) {
                    Strength_Of_Hand += 12 * Math.pow(10, 8 - (2 * z));
                } else if (Cards[z].equals("J")) {
                    Strength_Of_Hand += 1 * Math.pow(10, 8 - (2 * z));
                } else if (Cards[z].equals("T")) {
                    Strength_Of_Hand += 10 * Math.pow(10, 8 - (2 * z));
                } else {
                    Strength_Of_Hand += Integer.valueOf(Cards[z]) * Math.pow(10, 8 - (2 * z));
                }
                
            }


            Strenth[i] = Strength_Of_Hand;

        }



        return Strenth;
    }

}
