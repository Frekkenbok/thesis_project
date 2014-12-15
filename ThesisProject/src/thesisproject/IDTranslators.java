/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesisproject;

/**
 *
 * @author Евгения
 */
public class IDTranslators {
    public static int TrID;
    public static String Translator;
    
    public static String getTraslator() {
        return Translator;
    }

    public static void setTraslator(String TranslatorDB) {
        Translator = TranslatorDB;
    }
    
    public static void setIDTranslators(int TranslatorID) {
        TrID = TranslatorID;
    }
    public static int getIDTranslators() {
        return TrID;
    }
}
