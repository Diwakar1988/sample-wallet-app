package com.github.diwakar1988.noon.utils;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by 'Diwakar Mishra' on 16,November,2018
 */

public class CountryFlagUtils {
    private static final String FILE_NAME = "country_flag_emoji.json";



    public static class CountryFlag{
        private String name;
        private String unicode;
        private String emoji;
        private String code;

        private CountryFlag(String name, String unicode, String code,String emoji) {
            this.name = name;
            this.unicode = unicode;
            this.code = code;
            this.emoji = emoji;
        }

        public String getCode() {
            return code;
        }

        public String getUnicode() {
            return unicode;
        }

        public String getName() {
            return name;
        }

        public String getEmoji() {
            return emoji;
        }
    }
    private static HashMap<String,CountryFlag> flagHashMap;
    private static void parse(Context context) throws IOException {
        Log.d(CountryFlagUtils.class.getSimpleName(),"****** parsing country flags.");
        InputStream is = context.getAssets().open(FILE_NAME);
        Scanner scanner = new Scanner(is).useDelimiter("\\A");
        String data=scanner.hasNext()?scanner.next():"";

        flagHashMap = new HashMap<>();
        Gson gson = new Gson();

        CountryFlag[]flags = gson.fromJson(data,CountryFlag[].class);

        for (CountryFlag flag :
                flags) {
            flagHashMap.put(flag.getCode(),flag);
        }
        Log.d(CountryFlagUtils.class.getSimpleName(),"****** country flags loaded. COUNT="+flags.length);
    }
    public static void initialize(final Context context) throws IOException{
        parse(context);
    }
    public static CountryFlag getFlag(String isoCode){
        return flagHashMap.get(isoCode);
    }

}
