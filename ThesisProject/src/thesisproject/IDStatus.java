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
// Класс для получения ID статусов и связки с jComboBox
public class IDStatus {
    public static int IDStatus;
    
    public static void setIDStatus(int StatusID) {
        IDStatus = StatusID;
    }
    public static int getIDStatus() {
        return IDStatus;
    }
}
