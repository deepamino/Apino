package deepamino.controller.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RegexUtils {
    public static List<String> getRegexElements(String seqRegex, String content) {
        Pattern pattern = Pattern.compile(seqRegex);
        Matcher matcher = pattern.matcher(content);

        List<String> elements = new ArrayList<>();
        while (matcher.find())
            elements.add(matcher.group(1).replace("\n", ""));

        return elements;
    }

    public static String getFirstRegex(String regex, String content) {
        try {
            return getRegex(regex, content).get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public static List<String> getRegex(String regex, String content) {
        try {
            List<String> appearances = RegexUtils.getRegexElements(regex, content);
            return appearances.stream().
                    map(match -> match.replaceAll("\\s+", " ")).
                    map(match -> match.replaceAll("^\\s", "")).
                    collect(Collectors.toList());

        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
