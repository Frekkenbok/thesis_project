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
// Класс для получения айдишника Препаратов - Cures
public class IDCure { 
    public static int cID;
    
    public static void setIDCure(int CureID) {
        cID = CureID;
    }
    public static int getIDCure() {
        return cID;
    }
}
