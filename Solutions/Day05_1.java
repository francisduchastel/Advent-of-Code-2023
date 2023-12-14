package advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day5_1 {

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

        long[] Seeds = new long[First_line.length - 1];


        for (int i = 1; i < First_line.length; i++) {
            Seeds[i - 1] = Long.parseLong(First_line[i]);
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

        Long[][] Seed_Props = new Long[20][8];

        for (int i = 0; i < Seeds.length; i++) {
            Seed_Props[i][0] = Seeds[i];
        }



        for (int i = 0; i < Seeds.length; i++) {

            Boolean map_used = false;

            long Soil_Used = 0;

            for (int p = 0; p < Seed_Soil.length; p += 3) {
                if (Seeds[i] <=  Seed_Soil[p + 1] + Seed_Soil[p + 2] && Seeds[i] >= Seed_Soil[p + 1] ) {
                    long Distance = Seeds[i] - Seed_Soil[p + 1];

                    Soil_Used = Seed_Soil[p] + Distance;

                    map_used = true;
                }
            }

            if (map_used == false) {
                Soil_Used = Seeds[i];
            }

            Seed_Props[i][1] = Soil_Used;
        }

        for (int i = 0; i < Seeds.length; i++) {

            Boolean map_used = false;

            long Fert_Used = 0;

            for (int p = 0; p < Soil_fertilizer.length; p += 3) {
                if (Seed_Props[i][1] <=  Soil_fertilizer[p + 1] + Soil_fertilizer[p + 2] && Seed_Props[i][1] >= Soil_fertilizer[p + 1] ) {
                    long Distance = Seed_Props[i][1] - Soil_fertilizer[p + 1];

                    Fert_Used = Soil_fertilizer[p] + Distance;

                    map_used = true;
                }
            }

            if (map_used == false) {
                Fert_Used = Seed_Props[i][1];
            }

            Seed_Props[i][2] = Fert_Used;
        }

        for (int i = 0; i < Seeds.length; i++) {

            Boolean map_used = false;

            long Water_Used = 0;

            for (int p = 0; p < Fertilizer_Water.length; p += 3) {
                if (Seed_Props[i][2] <=  Fertilizer_Water[p + 1] + Fertilizer_Water[p + 2] && Seed_Props[i][2] >= Fertilizer_Water[p + 1] ) {
                    long Distance = Seed_Props[i][2] - Fertilizer_Water[p + 1];

                    Water_Used = Fertilizer_Water[p] + Distance;

                    map_used = true;
                }
            }

            if (map_used == false) {
                Water_Used = Seed_Props[i][2];
            }

            Seed_Props[i][3] = Water_Used;
        }

        for (int i = 0; i < Seeds.length; i++) {

            Boolean map_used = false;

            long Light_Used = 0;

            for (int p = 0; p < Water_Light.length; p += 3) {
                if (Seed_Props[i][3] <=  Water_Light[p + 1] + Water_Light[p + 2] && Seed_Props[i][3] >= Water_Light[p + 1] ) {
                    long Distance = Seed_Props[i][3] - Water_Light[p + 1];

                    Light_Used = Water_Light[p] + Distance;

                    map_used = true;
                }
            }

            if (map_used == false) {
                Light_Used = Seed_Props[i][3];
            }

            Seed_Props[i][4] = Light_Used;
        }

        for (int i = 0; i < Seeds.length; i++) {

            Boolean map_used = false;

            long Temp_Used = 0;

            for (int p = 0; p < Light_Temp.length; p += 3) {
                if (Seed_Props[i][4] <=  Light_Temp[p + 1] + Light_Temp[p + 2] && Seed_Props[i][4] >= Light_Temp[p + 1] ) {
                    long Distance = Seed_Props[i][4] - Light_Temp[p + 1];

                    Temp_Used = Light_Temp[p] + Distance;

                    map_used = true;
                }
            }

            if (map_used == false) {
                Temp_Used = Seed_Props[i][4];
            }

            Seed_Props[i][5] = Temp_Used;
        }

        for (int i = 0; i < Seeds.length; i++) {

            Boolean map_used = false;

            long Hum_Used = 0;

            for (int p = 0; p < Temp_Hum.length; p += 3) {
                if (Seed_Props[i][5] <=  Temp_Hum[p + 1] + Temp_Hum[p + 2] && Seed_Props[i][5] >= Temp_Hum[p + 1] ) {
                    long Distance = Seed_Props[i][5] - Temp_Hum[p + 1];

                    Hum_Used = Temp_Hum[p] + Distance;

                    map_used = true;
                }
            }

            if (map_used == false) {
                Hum_Used = Seed_Props[i][5];
            }

            Seed_Props[i][6] = Hum_Used;
        }

        for (int i = 0; i < Seeds.length; i++) {

            Boolean map_used = false;

            long Location_Used = 0;

            for (int p = 0; p < Hum_Location.length; p += 3) {
                if (Seed_Props[i][6] <=  Hum_Location[p + 1] + Hum_Location[p + 2] && Seed_Props[i][6] >= Hum_Location[p + 1] ) {
                    long Distance = Seed_Props[i][6] - Hum_Location[p + 1];

                    Location_Used = Hum_Location[p] + Distance;

                    map_used = true;
                }
            }

            if (map_used == false) {
                Location_Used = Seed_Props[i][6];
            }

            Seed_Props[i][7] = Location_Used;
        }

        long Min_Location = 999999999;
        for (int i = 0; i < Seed_Props.length; i++) {
            if (Seed_Props[i][7] < Min_Location) {
                Min_Location = Seed_Props[i][7];
            }
        }

        System.out.println(Min_Location);

        //add for other 5 properties

    }
}
