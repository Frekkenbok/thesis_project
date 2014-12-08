/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesisproject;
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

    }
}
