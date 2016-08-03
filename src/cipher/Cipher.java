/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cipher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
    NOTE : following are the plain-text files used
        name : plain_text.txt - contains the plain_text
               cipher_text.txt - 
               key.txt - (Not used till now)
*/
/**
 *
 * @author coed162
 */
public class Cipher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
//        FileOperations fo = new FileOperations();
//        String plain_text = fo.readFromFile("G:\\plain_text.txt"); 
//        System.out.println(plain_text);
        int choice = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Caesar caesar = new Caesar();
        FileOperations fo = new FileOperations();
        String key,plain_text, cipher_text;
        do
        {
            displayOptions();
            choice = Integer.parseInt(br.readLine());
            switch(choice)
            {
                case 1:
                    System.out.print("Enter the key or char : ");
//                  Assumes that key is a single digit or a single character
                    key = br.readLine().trim();
                    //Change the path according to usage
                    plain_text = fo.readFromFile("C:\\Users\\Harshal\\Google Drive\\sem-7\\lab\\CNS\\plain_text.txt"); 
//                    String plain_text = fo.readFromFile("/home/coed162/rohit/plain_text.txt"); 
//                    String cipher_text = caesar.encrypt(plain_text, key);
                    cipher_text = caesar.encrypt(plain_text, key);
                    fo.writeToFile(cipher_text, "C:\\Users\\Harshal\\Google Drive\\sem-7\\lab\\CNS\\cipher_text.txt");
                    break;
                case 2:
                    System.out.print("Enter the key or char : ");
//                  Assumes that key is a single digit or a single character
                    key = br.readLine().trim();
                    //Change the path according to usage
                    cipher_text = fo.readFromFile("C:\\Users\\Harshal\\Google Drive\\sem-7\\lab\\CNS\\cipher_text.txt"); 
//                    String plain_text = fo.readFromFile("/home/coed162/rohit/plain_text.txt"); 
//                    String cipher_text = caesar.encrypt(plain_text, key);
                    plain_text = caesar.decrypt(cipher_text, key);
                    fo.writeToFile(plain_text, "C:\\Users\\Harshal\\Google Drive\\sem-7\\lab\\CNS\\plain_text.txt");
                    break;
                case 3:
                    
                    cipher_text = fo.readFromFile("C:\\Users\\Harshal\\Google Drive\\sem-7\\lab\\CNS\\cipher_text.txt"); 
                    plain_text = caesar.bruteForce(cipher_text, br);
                    if( plain_text != null)
                        fo.writeToFile(plain_text, "C:\\Users\\Harshal\\Google Drive\\sem-7\\lab\\CNS\\plain_text.txt");
                    else
                    {
                        System.out.println("No suitable key found. The plain text file will be empty.");
                        fo.writeToFile("", "C:\\Users\\Harshal\\Google Drive\\sem-7\\lab\\CNS\\plain_text.txt");
                    }
                    break;
                case 4:
                    cipher_text = fo.readFromFile("C:\\Users\\Harshal\\Google Drive\\sem-7\\lab\\CNS\\cipher_text.txt"); 
                    plain_text = caesar.frequencyAnalysis(cipher_text, br);
                    if( plain_text != null)
                        fo.writeToFile(plain_text, "C:\\Users\\Harshal\\Google Drive\\sem-7\\lab\\CNS\\plain_text.txt");
                    else
                    {
                        System.out.println("No suitable key found. The plain text file will be empty.");
                        fo.writeToFile("", "C:\\Users\\Harshal\\Google Drive\\sem-7\\lab\\CNS\\plain_text.txt");
                    }
                    break;
                default:
                    System.out.println("Choice invalid!");
            }
            
        }while(choice != -1);
        
        
        
    }
    
    public static void displayOptions()
    {
        System.out.println("Select from the options given below\nTo Quit press -1");
        System.out.println("1. Encrypt");
        System.out.println("2. Decrypt");
        System.out.println("3. Brute Force Decryption");
        System.out.println("4. Frequency Analysis");
    }
    
}
