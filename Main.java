package encryptdecrypt;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    static String importFile = "./";
    static String exportFile = "./";

    public static String enc(String input, char[] alphabet0, int key) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            String inputCharacter = Character.toString(input.charAt(i));
            for (int j = 0; j < alphabet0.length; j++) {
                String alphabetCharacter = Character.toString(alphabet0[j]);
                String decryptedCharacter = Character.toString(alphabet0[(j + key) % alphabet0.length]);
                if (inputCharacter.equals(alphabetCharacter)) {
                    output += decryptedCharacter;
                }
            }
        }
        return output;
    }

    public static String dec(String input, char[] alphabet0, int key) {
        String output = "";
        int k = alphabet0.length - 1, l = 0;
        char[] alphabet = new char[alphabet0.length];
        while (k >= 0) {
            alphabet[l] = alphabet0[k];
            k--;
            l++;
        }
        for (int i = 0; i < input.length(); i++) {
            String inputCharacter = Character.toString(input.charAt(i));
            for (int j = 0; j < alphabet.length; j++) {
                String alphabetCharacter = Character.toString(alphabet[j]);
                String decryptedCharacter = Character.toString(alphabet[(j + key) % alphabet.length]);
                if (inputCharacter.equals(alphabetCharacter)) {
                    output += decryptedCharacter;
                }
            }
        }
        return output;
    }

    public static String enc2(String input, String alphabet, int key) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            String inputCharacter = Character.toString(input.charAt(i));
            if (!alphabet.contains(inputCharacter)) {
                output += inputCharacter;
            }
            for (int j = 0; j < alphabet.length(); j++) {
                String alphabetCharacter = Character.toString(alphabet.charAt(j));
                String decryptedCharacter = Character.toString(alphabet.charAt((j + key) % alphabet.length()));
                if (inputCharacter.equals(alphabetCharacter)) {
                    output += decryptedCharacter;
                }
            }
        }
        return output;
    }

    public static String dec2(String input, String alphabet, int key) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            String inputCharacter = Character.toString(input.charAt(i));
            if (!alphabet.contains(inputCharacter)) {
                output += inputCharacter;
            }
            for (int j = 0; j < alphabet.length(); j++) {
                String alphabetCharacter = Character.toString(alphabet.charAt(j));
                String decryptedCharacter = Character.toString(alphabet.charAt((j + key) % alphabet.length()));
                if (inputCharacter.equals(alphabetCharacter)) {
                    output += decryptedCharacter;
                }
            }
        }
        return output;
    }

    private static void lista(String data, File filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(data);
            writer.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public static void main(String[] args) throws Exception {
        char[] alphabet = {' ', '!', '\"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
                'Y', 'Z', '[', '\\', ']', '^', '_', '`',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '{', '|', '}', '~'};
        String alphabet2 = "abcdefghijklmnopqrstuvwxyz";
        String alphabet3 = "zyxwvutsrqponmlkjihgfedcba";

        if (args.length > 0 ) {
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-in":
                        importFile += args[i + 1];
                        break;
                    case "-out":
                        exportFile += args[i + 1];
                        break;
                }
            }
        }
        File file = new File(importFile);
        File file2 = new File(exportFile);

        int key = 0;
        String data = "";
        int count = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int count5 = 0;
        int count6 = 0;
        int count7 = 0;
        int count8 = 0;

        for (int i=0; i < args.length; i++) {
            if (args[i].equals("-key")) {
                if (args[i + 1].matches("[0-9]+")) {
                    try {
                        key = Integer.parseInt(args[i + 1]);
                    } catch (Exception e) {
                        System.out.println("Error");
                    }
                }
            }
        }
        for (int i=0; i < args.length; i++) {
            if (args[i].equals("-data")) {
                data = args[i + 1];
                break;
            } else if (args[i].equals("-in")) {
                try (Scanner skaner = new Scanner(file)) {
                    data = skaner.nextLine();
                } catch (Exception e) {
                    System.out.println("Error");
                }
            }
        }
        for (int i=0; i < args.length; i++) {
            if (args[i].equals("-mode")) {
                if (args[i + 1].equals("enc")) {
                    count3++;
                } else if (args[i + 1].equals("dec")) {
                    count4++;
                }
            }
        }
        for (int i=0; i < args.length; i++) {
            if (args[i].equals("-out")) {
                count6++;
            }
        }
        for (int i=0; i < args.length; i++) {
            if (args[i].equals("-alg")) {
                if (args[i + 1].equals("unicode")) {
                    count7++;
                } else if (args[i + 1].equals("shift")) {
                    count8++;
                }
            }
        }

        if ((count3==1 | count3==0) & count4==0) {
            if (count6==1) {
                if (count7==1) {
                    lista(enc(data, alphabet, key), file2);
                } else if (count8==1) {
                    lista(enc2(data, alphabet2, key), file2);
                }
            }
        } else if (count4==1) {
            if (count6==1) {
                if (count7==1) {
                    lista(dec(data, alphabet, key), file2);
                } else if (count8==1) {
                    lista(dec2(data, alphabet3, key), file2);
                }
            }
        }
    }
}
