import java.applet.Applet;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nafea8846
 */
public class Translator {
    //creates dictionary
    ArrayList<String> dictionary;
    
    //constructor fills dictionary with words when new translator is built
    public Translator() {
        try {
            URL dictionaryFile = new URL("https://github.com/dwyl/english-words/raw/master/words_alpha.txt");
            Scanner textInput = new Scanner(dictionaryFile.openStream());
            dictionary = new ArrayList<>();
            while (textInput.hasNext()) {
                String phrase = textInput.nextLine();
                dictionary.add(phrase);
            }
        } catch (IOException ex) {
            Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //method converts English To Javanais
    public String engToJava(String word) {
        word = word.toLowerCase();
        String translate = "";
        for (int i = 0; i < word.length() - 1; i++) {
            char currentLetter = word.charAt(i);
            char nextLetter = word.charAt(i + 1);
            //add letters to translate depending letters of word entered
            if ((nextLetter == 'a' || nextLetter == 'u' || nextLetter == 'o' || nextLetter == 'i' || nextLetter == 'e') && currentLetter != 'a' && currentLetter != 'u' && currentLetter != 'i' && currentLetter != 'e' && currentLetter != 'o') {
                translate = translate + currentLetter + "av";
            } else {
                translate = translate + currentLetter;
            }
        }
        translate = translate + word.charAt(word.length() - 1);
        return translate;
    }
    //
    //
    //
    //method converts English to UbbiDubbi
    public String engToUbbiDubbi(String word) {
        word = word.toLowerCase();
        String translate = "";
        for (int i = 0; i < word.length(); i++) {
            char currentLetter = word.charAt(i);
            //add letters to translate depending on letters of word entered
            if (currentLetter != 'a' && currentLetter != 'o' && currentLetter != 'u' && currentLetter != 'i' && currentLetter != 'e') {
                translate = translate + currentLetter;
            } else {
                //check if the previous letter also a vowel
                if (word.charAt(i - 1) != 'a' && word.charAt(i - 1) != 'o' && word.charAt(i - 1) != 'u' && word.charAt(i - 1) != 'i' && word.charAt(i - 1) != 'e') {
                    translate = translate + "ub" + currentLetter;
                } else {
                    translate = translate + currentLetter;
                }
            }
        }
        return translate;
    }
    //
    //
    //
    //method converts UbbiDubbi to English
    public String ubbiDubbiToEng(String word) {
        word = word.toLowerCase();
        String translate = word;
        //find if and where "ub" is in word
        int location = word.indexOf("ub");
        while (location > -2) {
            
            //check if current state of translate is in the dicitonary
            if (dictionary.contains(translate)) {
                break;
            } else {
                //remove "ub" if the letter after "ub" is a vowel
                if (translate.charAt(location + 2) == 'a' || translate.charAt(location + 2) == 'u' || translate.charAt(location + 2) == 'o' || translate.charAt(location + 2) == 'i' || translate.charAt(location + 2) == 'e') {
                    translate = translate.replaceFirst("ub", "");
                    location = translate.indexOf("ub");
                } else {
                    break;
                }
            }
        }
        return translate;
    }
    //
    //
    //
    //method converts Javanais to English
    public String javaToEng(String word) {
        word = word.toLowerCase();
        String translate = word;
        char letterBef;
        char letterAft;
        //find if and where "av" is in translate
        int location = translate.indexOf("av");
        while (location > 0) {
            letterBef = translate.charAt(location - 1);
            letterAft = translate.charAt(location + 2);
            location = location - 1;
            //check if current state of translate is in the dictionary
            if (!dictionary.contains(translate)) {
                //if params are met remove "av" form translate
                if ((letterAft == 'a' || letterAft == 'u' || letterAft == 'o' || letterAft == 'i' || letterAft == 'e') && (letterBef != 'a' || letterBef != 'u' || letterBef != 'o' || letterBef != 'i' || letterBef != 'e')) {
                    translate = translate.replaceFirst("av", "");
                    location = translate.indexOf("av");
                }
            }
        }
        return translate;
    }
}
