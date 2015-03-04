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
public class OutMessage {
    
    private String id;
    private String email;
    private File file;
    
    public OutMessage() {
        
    }
    
    public OutMessage(String id, String email, File file) {
        this.id = id;
        this.email = email;
        this.file = file;
    }
    
   public String getId(){
        return "id_"+id+"_id";
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }
            
    public void setEmail(String email) {
        this.email = email;
    }
    
    public File getFile() {
        return file;
    }
    
    public void setFile(File file) {
        this.file = file;
    }
    
    @Override
    public String toString() {
        return (id + " " + email + " "+ file.getName());
    }
}
