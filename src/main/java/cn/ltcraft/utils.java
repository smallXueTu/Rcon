package cn.ltcraft;

import cn.ltcraft.rcon.Rcon;
import net.mamoe.mirai.console.plugins.Config;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class utils {
    public static String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return list.isEmpty()?"":sb.toString().substring(0, sb.toString().length() - 1);
    }
    public static String clean(final String input) {
        if (input == null) {
            return null;
        }
        return Pattern.compile("(?i)" + String.valueOf("\u00A7") + "[0-9A-FK-OR]").matcher(input).replaceAll("");
    }
    public static List<String> splitArticle(final String text) {
        List<String> list = new LinkedList<>();
        if (text.length()<=600){
            list.add(text);
            return list;
        }else{
            String[] texts = text.split("\n");
            String Article = "";
            for (String t : texts){
                Article+=t;
                if (Article.length()>=600){
                    list.add(Article);
                    Article = "";
                }
            }
        }
        return list;
    }
    public static Boolean configNormal(Config config){
        return !config.getString("serverAdder").equals("") && config.getInt("serverPort")>0 && !config.getString("serverAdder").equals("passworld");
    }
}
