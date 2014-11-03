/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author Евгения
 */
public class CopyFiles 
{
  public static void copy(File source, File dest) throws IOException {
        Files.copy(source.toPath(), dest.toPath());
    }
  
    public static void main(String[] args) throws IOException {
//        copy(new File("C:\\Users\\Евгения\\Desktop\\4d_o_Prikl_inf.xlsx"), new File("C:\\Users\\Евгения\\Desktop\\Диплом\\trial\\4d_o_Prikl_inf.xlsx"));
    
    File file = new File("C:\\Users\\Евгения\\Desktop\\4d_o_Prikl_inf.xlsx");
    
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getName());
    
    }
}
// {
//            public static void main(String[] args) throws IOException {
//         String dir1 = "Тут должен быть path";
//         String dir2 = "C:\\Users\\Евгения\\Desktop\\Диплом\\trial";               
//         
//                       
//                 CopyFiles.scannerDir(dir1, dir2);
//}
//            public static void copyFile(File in, File out) throws IOException {
//               
//                byte buffer[] = new byte[100000000];
//                        try {
//                                FileInputStream fileIn = new FileInputStream(in);
//                                int bytes = fileIn.read(buffer,0,100000000);
//                                fileIn.close();
//                                
//                                FileOutputStream fileOut = new FileOutputStream(out);
//                                fileOut.write(buffer,0,bytes);
//                                fileOut.close();
//                        }
//                        catch (Exception e) {
//                                        
//                        }
//            }
//
//    private static void scannerDir(String dir1, String dir2) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
// }