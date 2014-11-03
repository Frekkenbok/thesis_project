
package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Евгения
 */
 public class CopyFiles 
 
 {
            public static void main(String[] args) throws IOException {
         String dir1 = "D://A1 Test//In";
         String dir2 = "C:\\Users\\Евгения\\Desktop\\Диплом\\trial";               
         
                       
                 CopyFiles.scannerDir(dir1, dir2);
}
            public static void copyFile(File in, File out) throws IOException {
               
                byte buffer[] = new byte[100000000];
                        try {
                                FileInputStream fileIn = new FileInputStream(in);
                                int bytes = fileIn.read(buffer,0,100000000);
                                fileIn.close();
                                
                                FileOutputStream fileOut = new FileOutputStream(out);
                                fileOut.write(buffer,0,bytes);
                                fileOut.close();
                        }
                        catch (Exception e) {
                                        
                        }
            }

    private static void scannerDir(String dir1, String dir2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 }