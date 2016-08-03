/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cipher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author coed162
 */
public class FileOperations {
    
    private InputStream is = null;
    private InputStreamReader isr = null;
    private BufferedReader br = null;
    private StringBuffer temp_str;
    private BufferedWriter bw;
    
    //reads from file and cleans it
    //ie removes non-alphabet symbols and converts to lowercase
    public String readFromFile(String name) throws IOException
    {
        temp_str = new StringBuffer();
        try 
        {
            is = new FileInputStream(name);
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            
            char ch;
            int temp;
            while( (temp =  br.read()) != -1)
            {
                ch = (char) temp;
                
                if( Character.isLetter(ch) )
                {
                    if( Character.isUpperCase(ch))
                        ch = Character.toLowerCase(ch);
                    temp_str.append(ch);
                }
                    
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(is != null)
                is.close();
            
            if(isr != null)
                isr.close();
            
            if(br != null)
                br.close();
        }
        
        return temp_str.toString();
    }
    
    public void writeToFile(String contents, String name) throws IOException
    {
        try{
            bw = new BufferedWriter(new FileWriter(name));
            bw.write(contents);
        } catch (IOException ex) {
            Logger.getLogger(FileOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            if( bw != null)
                bw.close();
        }
    }
    
}
