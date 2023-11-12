package edu.hw3.Task1;

@SuppressWarnings("HideUtilityClassConstructor")
public class AtbashCipher {
    public static String atbash(String input) {
        StringBuilder encryptedString = new StringBuilder();

        for (char ch : input.toCharArray()) {
            if (ch >= 'a' && ch <= 'z') {
                encryptedString.append((char) ('z' - ch + 'a'));
            } else if (ch >= 'A' && ch <= 'Z') {
                encryptedString.append((char) ('Z' - ch + 'A'));
            } else {
                encryptedString.append(ch);
            }
        }

        return encryptedString.toString();
    }
}
