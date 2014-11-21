
package test;

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
    
    //File file = new File("C:\\Users\\Евгения\\Desktop\\4d_o_Prikl_inf.xlsx");
    
        //System.out.println(file.getAbsolutePath());
        //System.out.println(file.getAbsoluteFile());
        //System.out.println(file.getName());
    
    }
}