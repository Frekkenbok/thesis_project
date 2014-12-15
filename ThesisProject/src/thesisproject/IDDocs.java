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
public class IDDocs {
    public static int DocID;
    public static String Doc;
    
    public static String getDocument() {
        return Doc;
    }

    public static void setDocument(String DocDB) {
        Doc = DocDB;
    }
    
    public static void setIDDoc(int DocIDdb) {
        DocID = DocIDdb;
    }
    public static int getIDDoc() {
        return DocID;
    }
    
}
