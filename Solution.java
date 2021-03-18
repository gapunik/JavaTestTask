import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        while (true) {
            System.out.println("Введите строку, которую требуется распаковать. Для завершения программы введите exit.");
            Scanner console = new Scanner(System.in);
            String inputString = console.nextLine();
            if (inputString.equalsIgnoreCase("exit")) return;
            System.out.println("Исходная строка: " + inputString);

            String unpackedString = unpackString(inputString);
            System.out.println("Распакованная строка: " + unpackedString);
            System.out.println();
        }
    }

    public static String unpackString(String string) {
        if (string.isEmpty()) return string;

        StringBuilder resultStrBuilder = new StringBuilder();

        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {

            char ch = chars[i];

            if (Character.isDigit(ch)) {

                StringBuilder timesStrBuilder = new StringBuilder();
                timesStrBuilder.append(ch);

                for (int j = i + 1; j < chars.length && Character.isDigit(chars[j]); j++, i++) {
                    timesStrBuilder.append(chars[j]);
                }

                int bracketCounter = 0;

                StringBuilder currentStrBuilder = new StringBuilder();
                for (int j = i + 1; j < chars.length; j++) {

                    i++;

                    ch = chars[j];

                    if (isLeftBracket(ch)) {
                        if (bracketCounter > 0) currentStrBuilder.append(ch);
                        bracketCounter++;
                    } else if (isRightBracket(ch)) {
                        bracketCounter--;
                        if (bracketCounter > 0) currentStrBuilder.append(ch);
                        else break;
                    } else if (bracketCounter > 0) currentStrBuilder.append(ch);

                }

                int timesToRepeat = Integer.parseInt(timesStrBuilder, 0, timesStrBuilder.length(), 10);
                for (int j = 0; j < timesToRepeat; j++) {
                    resultStrBuilder.append( unpackString(currentStrBuilder.toString()) );
                }

            } else {

                StringBuilder currentStrBuilder = new StringBuilder();
                currentStrBuilder.append(ch);
                for (int j = i + 1; j < chars.length && !Character.isDigit(chars[j]); j++, i++) {
                    currentStrBuilder.append(chars[j]);
                }

                resultStrBuilder.append(currentStrBuilder);

            }

        }

        return resultStrBuilder.toString();
    }

    private static boolean isLeftBracket(char c) {
        return c == '[';
    }

    private static boolean isRightBracket(char c) {
        return c == ']';
    }

}
