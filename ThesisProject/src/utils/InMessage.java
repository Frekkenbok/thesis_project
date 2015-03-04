/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;

/**
 *
 * @author Евгения
 */
public class InMessage {
    private String inid;
    private String cureID;
    private File infile;
    
    public InMessage () {
    
    }
    
    public InMessage(String inid, String cureID, File infile) {
        this.inid = inid;
        this.cureID = cureID;
        this.infile = infile;
    }
    public String getInid(){
        return "id_"+inid+"_id";
    }
    
    public void setInid(String inid) {
        this.inid = inid;
    }
    
    public String getCureId() {
        return cureID;
    }
            
    public void setCureId(String cureID) {
        this.cureID = cureID;
    }
    
    public File getInfile() {
        return infile;
    }
    
    public void setInfile(File infile) {
        this.infile = infile;
    }
//   @Override
//    public String toString() {
//        return (inid + " " + cureID + " "+ infile.getName());
//    }
}
