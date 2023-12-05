package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day5_2 {

    public static void main(String[] args) {
        
        String fullText = "";
        String filepath = "Inputs\\Day 5.txt";
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


        String[] First_line = fullText.split("\n")[0].split(" ");

        long[] First_line_nums = new long[First_line.length - 1];

        for (int i = 0; i < First_line_nums.length; i++) {
            First_line_nums[i] = Long.parseLong(First_line[i + 1]);
        }


        String[] fullText_Split = fullText.split("[:]+");


        String[] Seed_Soil_temp = fullText_Split[2].split("[ \n]+");
        long[] Seed_Soil = new long[Seed_Soil_temp.length - 1];

        String[] Soil_fertilizer_temp = fullText_Split[4].split("[ \n]+");
        long[] Soil_fertilizer = new long[Soil_fertilizer_temp.length - 1];

        String[] Fertilizer_Water_temp = fullText_Split[6].split("[ \n]+");
        long[] Fertilizer_Water = new long[Fertilizer_Water_temp.length - 1];

        String[] Water_Light_temp = fullText_Split[8].split("[ \n]+");
        long[] Water_Light = new long[Water_Light_temp.length - 1];

        String[] Light_Temp_temp = fullText_Split[10].split("[ \n]+");
        long[] Light_Temp = new long[Light_Temp_temp.length - 1];

        String[] Temp_Hum_temp = fullText_Split[12].split("[ \n]+");
        long[] Temp_Hum = new long[Temp_Hum_temp.length - 1];

        String[] Hum_Location_temp = fullText_Split[14].split("[ \n]+");
        long[] Hum_Location = new long[Hum_Location_temp.length - 1];

        for (int i = 0; i < Seed_Soil.length; i++) {
            Seed_Soil[i] = Long.parseLong(Seed_Soil_temp[i + 1]);
        }

        for (int i = 0; i < Soil_fertilizer.length; i++) {
            Soil_fertilizer[i] = Long.parseLong(Soil_fertilizer_temp[i + 1]);
        }

        for (int i = 0; i < Fertilizer_Water.length; i++) {
            Fertilizer_Water[i] = Long.parseLong(Fertilizer_Water_temp[i + 1]);
        }

        for (int i = 0; i < Water_Light.length; i++) {
            Water_Light[i] = Long.parseLong(Water_Light_temp[i + 1]);
        }

        for (int i = 0; i < Light_Temp.length; i++) {
            Light_Temp[i] = Long.parseLong(Light_Temp_temp[i + 1]);
        }

        for (int i = 0; i < Temp_Hum.length; i++) {
            Temp_Hum[i] = Long.parseLong(Temp_Hum_temp[i + 1]);
        }

        for (int i = 0; i < Hum_Location.length; i++) {
            Hum_Location[i] = Long.parseLong(Hum_Location_temp[i + 1]);
        }

        //given Location L

        //Used testing on various digits and comparing to seed value to get a lower approximation for location value
        // L = 41080704


        for (long L = 41080704; L > -1; L += 1) {

            long Hum_Used = 0;

            Boolean map_used = false;

            for (int p = 0; p < Hum_Location.length; p += 3) {
                if (L <=  Hum_Location[p] + Hum_Location[p + 2] && L >= Hum_Location[p] ) {
                    long Distance = L - Hum_Location[p];

                    Hum_Used = Hum_Location[p + 1] + Distance;

                    map_used = true;
                }
            }

            if (map_used == false) {
                Hum_Used = L;
            }

            //System.out.println("Hum: " + Hum_Used);

            long Temp_Used = 0;

            map_used = false;

            for (int p = 0; p < Temp_Hum.length; p += 3) {
                if (Hum_Used <=  Temp_Hum[p] + Temp_Hum[p + 2] && Hum_Used >= Temp_Hum[p] ) {
                    long Distance = Hum_Used - Temp_Hum[p];

                    Temp_Used = Temp_Hum[p + 1] + Distance;

                    map_used = true;
                }
            }

            if (map_used == false) {
                Temp_Used = Hum_Used;
            }

            //System.out.println("Temp: " + Temp_Used);

            long Light_Used = 0;

            map_used = false;

            for (int p = 0; p < Light_Temp.length; p += 3) {
                if (Temp_Used <=  Light_Temp[p] + Light_Temp[p + 2] && Temp_Used >= Light_Temp[p] ) {
                    long Distance = Temp_Used - Light_Temp[p];

                    Light_Used = Light_Temp[p + 1] + Distance;

                    map_used = true;
                }
            }

            if (map_used == false) {
                Light_Used = Temp_Used;
            }

            //System.out.println("Light: " + Light_Used);


            long Water_Used = 0;

            map_used = false;

            for (int p = 0; p < Water_Light.length; p += 3) {
                if (Light_Used <=  Water_Light[p] + Water_Light[p + 2] && Light_Used >= Water_Light[p] ) {
                    long Distance = Light_Used - Water_Light[p];

                    Water_Used = Water_Light[p + 1] + Distance;

                    map_used = true;
                }
            }

            if (map_used == false) {
                Water_Used = Light_Used;
            }


            //System.out.println("Water: " + Water_Used);

            long Fert_Used = 0;

            map_used = false;

            for (int p = 0; p < Fertilizer_Water.length; p += 3) {
                if (Water_Used <=  Fertilizer_Water[p] + Fertilizer_Water[p + 2] && Water_Used >= Fertilizer_Water[p] ) {
                    long Distance = Water_Used - Fertilizer_Water[p];

                    Fert_Used = Fertilizer_Water[p + 1] + Distance;

                    map_used = true;
                }
            }

            if (map_used == false) {
                Fert_Used = Water_Used;
            }

            //System.out.println("Fert: " + Fert_Used);


            long Soil_Used = 0;

            map_used = false;

            for (int p = 0; p < Soil_fertilizer.length; p += 3) {
                if (Fert_Used <=  Soil_fertilizer[p] + Soil_fertilizer[p + 2] && Fert_Used >= Soil_fertilizer[p] ) {
                    long Distance = Fert_Used - Soil_fertilizer[p];

                    Soil_Used = Soil_fertilizer[p + 1] + Distance;

                    map_used = true;
                }
            }

            if (map_used == false) {
                Soil_Used = Fert_Used;
            }

            //System.out.println("Soil: " + Soil_Used);

            long Seed_Used = 0;

            map_used = false;

            for (int p = 0; p < Seed_Soil.length; p += 3) {
                if (Soil_Used <=  Seed_Soil[p] + Seed_Soil[p + 2] && Soil_Used >= Seed_Soil[p] ) {
                    long Distance = Soil_Used - Seed_Soil[p];

                    Seed_Used = Seed_Soil[p + 1] + Distance;

                    map_used = true;
                }
            }

            if (map_used == false) {
                Seed_Used = Soil_Used;
            }

            if (ValidSeed(Seed_Used, First_line_nums) == true) {
                System.out.println("Done    " + L);
                return;
            } else {
                System.out.println(L + "    " + Seed_Used);
            }

    }
}








    public static Boolean ValidSeed(long Seed, long[] First_line_nums) {

        Boolean Return = false;

        for (int i = 0; i < First_line_nums.length; i += 2) {
            if (Seed <= First_line_nums[i] + First_line_nums[i + 1] && Seed >= First_line_nums[i]) {
                Return = true;
            }
        }

        return Return;

    }
}