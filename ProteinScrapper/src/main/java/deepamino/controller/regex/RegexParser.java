package deepamino.controller.regex;

public interface RegexParser<Type> {
    Type parse(String[] args);
}
