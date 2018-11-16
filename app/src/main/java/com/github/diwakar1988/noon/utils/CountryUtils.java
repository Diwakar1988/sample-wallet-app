package com.github.diwakar1988.noon.utils;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by 'Diwakar Mishra' on 16,November,2018
 */
public class CountryUtils {
    private static Map<String, String> mapCountryAndPhone = new HashMap<String, String>();
    private static HashMap<String, Flag> flagsMap = new HashMap<>();

    private CountryUtils() {
        //do nothing
    }

    private static void initCountryAndPhoneCodeMap() {
        mapCountryAndPhone.put("AF", "+93");
        mapCountryAndPhone.put("AL", "+355");
        mapCountryAndPhone.put("DZ", "+213");
        mapCountryAndPhone.put("AD", "+376");
        mapCountryAndPhone.put("AO", "+244");
        mapCountryAndPhone.put("AG", "+1268");
        mapCountryAndPhone.put("AR", "+54");
        mapCountryAndPhone.put("AM", "+374");
        mapCountryAndPhone.put("AU", "+61");
        mapCountryAndPhone.put("AT", "+43");
        mapCountryAndPhone.put("AZ", "+994");
        mapCountryAndPhone.put("BS", "+1242");
        mapCountryAndPhone.put("BH", "+973");
        mapCountryAndPhone.put("BD", "+880");
        mapCountryAndPhone.put("BB", "+1246");
        mapCountryAndPhone.put("BY", "+375");
        mapCountryAndPhone.put("BE", "+32");
        mapCountryAndPhone.put("BZ", "+501");
        mapCountryAndPhone.put("BJ", "+229");
        mapCountryAndPhone.put("BT", "+975");
        mapCountryAndPhone.put("BO", "+591");
        mapCountryAndPhone.put("BA", "+387");
        mapCountryAndPhone.put("BW", "+267");
        mapCountryAndPhone.put("BR", "+55");
        mapCountryAndPhone.put("BN", "+673");
        mapCountryAndPhone.put("BG", "+359");
        mapCountryAndPhone.put("BF", "+226");
        mapCountryAndPhone.put("BI", "+257");
        mapCountryAndPhone.put("KH", "+855");
        mapCountryAndPhone.put("CM", "+237");
        mapCountryAndPhone.put("CA", "+1");
        mapCountryAndPhone.put("CV", "+238");
        mapCountryAndPhone.put("CF", "+236");
        mapCountryAndPhone.put("TD", "+235");
        mapCountryAndPhone.put("CL", "+56");
        mapCountryAndPhone.put("CN", "+86");
        mapCountryAndPhone.put("CO", "+57");
        mapCountryAndPhone.put("KM", "+269");
        mapCountryAndPhone.put("CD", "+243");
        mapCountryAndPhone.put("CG", "+242");
        mapCountryAndPhone.put("CR", "+506");
        mapCountryAndPhone.put("CI", "+225");
        mapCountryAndPhone.put("HR", "+385");
        mapCountryAndPhone.put("CU", "+53");
        mapCountryAndPhone.put("CY", "+357");
        mapCountryAndPhone.put("CZ", "+420");
        mapCountryAndPhone.put("DK", "+45");
        mapCountryAndPhone.put("DJ", "+253");
        mapCountryAndPhone.put("DM", "+1767");
        mapCountryAndPhone.put("DO", "+18");
        mapCountryAndPhone.put("EC", "+593");
        mapCountryAndPhone.put("EG", "+20");
        mapCountryAndPhone.put("SV", "+503");
        mapCountryAndPhone.put("GQ", "+240");
        mapCountryAndPhone.put("ER", "+291");
        mapCountryAndPhone.put("EE", "+372");
        mapCountryAndPhone.put("ET", "+251");
        mapCountryAndPhone.put("FJ", "+679");
        mapCountryAndPhone.put("FI", "+358");
        mapCountryAndPhone.put("FR", "+33");
        mapCountryAndPhone.put("GA", "+241");
        mapCountryAndPhone.put("GM", "+220");
        mapCountryAndPhone.put("GE", "+995");
        mapCountryAndPhone.put("DE", "+49");
        mapCountryAndPhone.put("GH", "+233");
        mapCountryAndPhone.put("GR", "+30");
        mapCountryAndPhone.put("GD", "+1473");
        mapCountryAndPhone.put("GT", "+502");
        mapCountryAndPhone.put("GN", "+224");
        mapCountryAndPhone.put("GW", "+245");
        mapCountryAndPhone.put("GY", "+592");
        mapCountryAndPhone.put("HT", "+509");
        mapCountryAndPhone.put("HN", "+504");
        mapCountryAndPhone.put("HU", "+36");
        mapCountryAndPhone.put("IS", "+354");
        mapCountryAndPhone.put("IN", "+91");
        mapCountryAndPhone.put("ID", "+62");
        mapCountryAndPhone.put("IR", "+98");
        mapCountryAndPhone.put("IQ", "+964");
        mapCountryAndPhone.put("IE", "+353");
        mapCountryAndPhone.put("IL", "+972");
        mapCountryAndPhone.put("IT", "+39");
        mapCountryAndPhone.put("JM", "+1876");
        mapCountryAndPhone.put("JP", "+81");
        mapCountryAndPhone.put("JO", "+962");
        mapCountryAndPhone.put("KZ", "+7");
        mapCountryAndPhone.put("KE", "+254");
        mapCountryAndPhone.put("KI", "+686");
        mapCountryAndPhone.put("KP", "+850");
        mapCountryAndPhone.put("KR", "+82");
        mapCountryAndPhone.put("KW", "+965");
        mapCountryAndPhone.put("KG", "+996");
        mapCountryAndPhone.put("LA", "+856");
        mapCountryAndPhone.put("LV", "+371");
        mapCountryAndPhone.put("LB", "+961");
        mapCountryAndPhone.put("LS", "+266");
        mapCountryAndPhone.put("LR", "+231");
        mapCountryAndPhone.put("LY", "+218");
        mapCountryAndPhone.put("LI", "+423");
        mapCountryAndPhone.put("LT", "+370");
        mapCountryAndPhone.put("LU", "+352");
        mapCountryAndPhone.put("MK", "+389");
        mapCountryAndPhone.put("MG", "+261");
        mapCountryAndPhone.put("MW", "+265");
        mapCountryAndPhone.put("MY", "+60");
        mapCountryAndPhone.put("MV", "+960");
        mapCountryAndPhone.put("ML", "+223");
        mapCountryAndPhone.put("MT", "+356");
        mapCountryAndPhone.put("MH", "+692");
        mapCountryAndPhone.put("MR", "+222");
        mapCountryAndPhone.put("MU", "+230");
        mapCountryAndPhone.put("MX", "+52");
        mapCountryAndPhone.put("FM", "+691");
        mapCountryAndPhone.put("MD", "+373");
        mapCountryAndPhone.put("MC", "+377");
        mapCountryAndPhone.put("MN", "+976");
        mapCountryAndPhone.put("ME", "+382");
        mapCountryAndPhone.put("MA", "+212");
        mapCountryAndPhone.put("MZ", "+258");
        mapCountryAndPhone.put("MM", "+95");
        mapCountryAndPhone.put("NA", "+264");
        mapCountryAndPhone.put("NR", "+674");
        mapCountryAndPhone.put("NP", "+977");
        mapCountryAndPhone.put("NL", "+31");
        mapCountryAndPhone.put("NZ", "+64");
        mapCountryAndPhone.put("NI", "+505");
        mapCountryAndPhone.put("NE", "+227");
        mapCountryAndPhone.put("NG", "+234");
        mapCountryAndPhone.put("NO", "+47");
        mapCountryAndPhone.put("OM", "+968");
        mapCountryAndPhone.put("PK", "+92");
        mapCountryAndPhone.put("PW", "+680");
        mapCountryAndPhone.put("PA", "+507");
        mapCountryAndPhone.put("PG", "+675");
        mapCountryAndPhone.put("PY", "+595");
        mapCountryAndPhone.put("PE", "+51");
        mapCountryAndPhone.put("PH", "+63");
        mapCountryAndPhone.put("PL", "+48");
        mapCountryAndPhone.put("PT", "+351");
        mapCountryAndPhone.put("QA", "+974");
        mapCountryAndPhone.put("RO", "+40");
        mapCountryAndPhone.put("RU", "+7");
        mapCountryAndPhone.put("RW", "+250");
        mapCountryAndPhone.put("KN", "+1869");
        mapCountryAndPhone.put("LC", "+1758");
        mapCountryAndPhone.put("VC", "+1784");
        mapCountryAndPhone.put("WS", "+685");
        mapCountryAndPhone.put("SM", "+378");
        mapCountryAndPhone.put("ST", "+239");
        mapCountryAndPhone.put("SA", "+966");
        mapCountryAndPhone.put("SN", "+221");
        mapCountryAndPhone.put("RS", "+381");
        mapCountryAndPhone.put("SC", "+248");
        mapCountryAndPhone.put("SL", "+232");
        mapCountryAndPhone.put("SG", "+65");
        mapCountryAndPhone.put("SK", "+421");
        mapCountryAndPhone.put("SI", "+386");
        mapCountryAndPhone.put("SB", "+677");
        mapCountryAndPhone.put("SO", "+252");
        mapCountryAndPhone.put("ZA", "+27");
        mapCountryAndPhone.put("ES", "+34");
        mapCountryAndPhone.put("LK", "+94");
        mapCountryAndPhone.put("SD", "+249");
        mapCountryAndPhone.put("SR", "+597");
        mapCountryAndPhone.put("SZ", "+268");
        mapCountryAndPhone.put("SE", "+46");
        mapCountryAndPhone.put("CH", "+41");
        mapCountryAndPhone.put("SY", "+963");
        mapCountryAndPhone.put("TJ", "+992");
        mapCountryAndPhone.put("TZ", "+255");
        mapCountryAndPhone.put("TH", "+66");
        mapCountryAndPhone.put("TL", "+670");
        mapCountryAndPhone.put("TG", "+228");
        mapCountryAndPhone.put("TO", "+676");
        mapCountryAndPhone.put("TT", "+1868");
        mapCountryAndPhone.put("TN", "+216");
        mapCountryAndPhone.put("TR", "+90");
        mapCountryAndPhone.put("TM", "+993");
        mapCountryAndPhone.put("TV", "+688");
        mapCountryAndPhone.put("UG", "+256");
        mapCountryAndPhone.put("UA", "+380");
        mapCountryAndPhone.put("AE", "+971");
        mapCountryAndPhone.put("GB", "+44");
        mapCountryAndPhone.put("US", "+1");
        mapCountryAndPhone.put("UY", "+598");
        mapCountryAndPhone.put("UZ", "+998");
        mapCountryAndPhone.put("VU", "+678");
        mapCountryAndPhone.put("VA", "+379");
        mapCountryAndPhone.put("VE", "+58");
        mapCountryAndPhone.put("VN", "+84");
        mapCountryAndPhone.put("YE", "+967");
        mapCountryAndPhone.put("ZM", "+260");
        mapCountryAndPhone.put("ZW", "+263");
        mapCountryAndPhone.put("GE", "+995");
        mapCountryAndPhone.put("TW", "+886");
        mapCountryAndPhone.put("AZ", "+37497");
        mapCountryAndPhone.put("CY", "+90392");
        mapCountryAndPhone.put("MD", "+373533");
        mapCountryAndPhone.put("SO", "+252");
        mapCountryAndPhone.put("GE", "+995");
        mapCountryAndPhone.put("CX", "+61");
        mapCountryAndPhone.put("CC", "+61");
        mapCountryAndPhone.put("NF", "+672");
        mapCountryAndPhone.put("NC", "+687");
        mapCountryAndPhone.put("PF", "+689");
        mapCountryAndPhone.put("YT", "+262");
        mapCountryAndPhone.put("GP", "+590");
        mapCountryAndPhone.put("GP", "+590");
        mapCountryAndPhone.put("PM", "+508");
        mapCountryAndPhone.put("WF", "+681");
        mapCountryAndPhone.put("CK", "+682");
        mapCountryAndPhone.put("NU", "+683");
        mapCountryAndPhone.put("TK", "+690");
        mapCountryAndPhone.put("GG", "+44");
        mapCountryAndPhone.put("IM", "+44");
        mapCountryAndPhone.put("JE", "+44");
        mapCountryAndPhone.put("AI", "+1264");
        mapCountryAndPhone.put("BM", "+1441");
        mapCountryAndPhone.put("IO", "+246");
        mapCountryAndPhone.put("", "+357");
        mapCountryAndPhone.put("VG", "+1284");
        mapCountryAndPhone.put("KY", "+1345");
        mapCountryAndPhone.put("FK", "+500");
        mapCountryAndPhone.put("GI", "+350");
        mapCountryAndPhone.put("MS", "+1664");
        mapCountryAndPhone.put("SH", "+290");
        mapCountryAndPhone.put("TC", "+1649");
        mapCountryAndPhone.put("MP", "+1670");
        mapCountryAndPhone.put("AS", "+1684");
        mapCountryAndPhone.put("GU", "+1671");
        mapCountryAndPhone.put("VI", "+1340");
        mapCountryAndPhone.put("HK", "+852");
        mapCountryAndPhone.put("MO", "+853");
        mapCountryAndPhone.put("FO", "+298");
        mapCountryAndPhone.put("GL", "+299");
        mapCountryAndPhone.put("GF", "+594");
        mapCountryAndPhone.put("GP", "+590");
        mapCountryAndPhone.put("MQ", "+596");
        mapCountryAndPhone.put("RE", "+262");
        mapCountryAndPhone.put("AX", "+35818");
        mapCountryAndPhone.put("AW", "+297");
        mapCountryAndPhone.put("AN", "+599");
        mapCountryAndPhone.put("SJ", "+47");
        mapCountryAndPhone.put("AC", "+247");
        mapCountryAndPhone.put("TA", "+290");
        mapCountryAndPhone.put("CS", "+381");
        mapCountryAndPhone.put("PS", "+970");
        mapCountryAndPhone.put("EH", "+212");
    }

    private static void parseFlags(Context context) throws IOException {
        InputStream is = context.getAssets().open("country_flag_emoji.json");
        Scanner scanner = new Scanner(is).useDelimiter("\\A");
        String data=scanner.hasNext()?scanner.next():"";

        flagsMap = new HashMap<>();
        Gson gson = new Gson();

        Flag[]flags = gson.fromJson(data, Flag[].class);

        for (Flag flag :
                flags) {
            flagsMap.put(flag.getCode(),flag);
        }
    }

    public static void load(Context context) throws IOException{
        parseFlags(context);
        initCountryAndPhoneCodeMap();
    }
    public static Map<String, String> getAll() {
        return mapCountryAndPhone;
    }
    public static String getPhoneCode(String isoCode) {
        return mapCountryAndPhone.get(isoCode.toUpperCase());
    }
    public static Flag getFlag(String isoCode){
        return flagsMap.get(isoCode);
    }
    public static String getCountryName(String isoCode){
        Locale locale = new Locale("",isoCode);
        String name = locale.getDisplayCountry();
        if (TextUtils.isEmpty(name)){
            //try from flag map
            Flag flag = getFlag(isoCode);
            if (flag!=null){
                name = flag.getName();
            }
        }
        return name;
    }

    public static class Flag {
        private String name;
        private String unicode;
        private String emoji;
        private String code;

        private Flag(String name, String unicode, String code, String emoji) {
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
    }
}