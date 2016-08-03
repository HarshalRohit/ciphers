/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cipher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author coed162
 */
public class Caesar implements CipherOperations{
    
    StringBuffer str;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    //will only accept the first character of the key to encrypt
    @Override
    public String encrypt(String plain_text, String key)
    {
        int key_int = convert_key(key);
        int encrypt_char;
        str = new StringBuffer();
        for(int i=0; i<plain_text.length(); i++)
        {
            encrypt_char = plain_text.charAt(i) - 97;
            encrypt_char = (encrypt_char +  key_int)%26;
            encrypt_char = encrypt_char + 97;

            str.append( Character.toString((char)encrypt_char) );
        }
        return str.toString();
    }
    
    @Override
    public String decrypt(String cipher_text, String key)
    {
        int key_int = convert_key(key);
        int encrypt_char;
        str = new StringBuffer();
        for(int i=0; i<cipher_text.length(); i++)
        {
            encrypt_char = cipher_text.charAt(i) - 97;
            //()?();()
            encrypt_char = encrypt_char -  key_int;
            encrypt_char = ( encrypt_char>0 ? encrypt_char : encrypt_char + 26)%26;
            encrypt_char = encrypt_char + 97;

            str.append( Character.toString((char)encrypt_char) );
        }
        return str.toString();
 
    }
    

    public String bruteForce(String cipher_text, BufferedReader br) throws IOException
    {
        String key;
        int choice;
        StringBuffer plain_text;
        System.out.println("Enter 1 if the decrypted text is readable else 2 to continue");
        for(int i=97; i<=122; i++)
        {

            key = Character.toString((char)i);
            plain_text = new StringBuffer(decrypt(cipher_text, key));
            System.out.println(plain_text.toString());
            choice = Integer.parseInt(br.readLine().trim());
            if(choice == 1)
                return plain_text.toString();
        }
        
        return null;
    }
    
    public String frequencyAnalysis(String cipher_text, BufferedReader br) throws IOException
    {
        FileOperations fo = new FileOperations();
        char[] reference_text = fo.readFromFile("C:\\Users\\Harshal\\Google Drive\\sem-7\\lab\\CNS\\reference_text.txt").toCharArray();
        int[] frequency_of_characters_ref_text = new int[26];
        
        int[] frequency_of_characters_cipher_text = new int[26];
        char[] cipher_text_char = cipher_text.toCharArray();
        
        int index,max,choice;
        StringBuffer key,plain_text;
        
        //stores the frequency of each character in cipher text
        for(int i=0; i<cipher_text_char.length; i++)
        {
            index = cipher_text_char[i] - 97;
            frequency_of_characters_cipher_text[index]++;
        }
//        System.out.println("Cipher Text frequency of charactes : " + Arrays.toString(frequency_of_characters_cipher_text));
        
        //finds the character with max frequency in cipher text
        int max_freq_char_cipher_txt =  (find_index_max_frequency(frequency_of_characters_cipher_text));
        
        //Stores the frequency of each character in an reference text
        for(int i=0; i<reference_text.length; i++)
        {
            index = reference_text[i] - 97;
            frequency_of_characters_ref_text[index]++;
            
        }
//        System.out.println("Reference Text frequency of characters : " + Arrays.toString(frequency_of_characters_ref_text));
        
        
        //Iterates over the above reference text char array and
        //finds the character with (next) max frequency and decrypts the cipher_text
        //with it.
        //repeats till readable text is obtained
        System.out.println("Press 1 if the text is readable, otherwise press 2");
        for(int i=0; i<26; i++)
        {
            index = find_index_max_frequency(frequency_of_characters_ref_text);
            //from integer(0 based) to char (Ascii value)
            //key is equal to max_frequency_char in cipher_text - next max_frequency_char in reference_text
            key = new StringBuffer().append((char)(max_freq_char_cipher_txt - index + 97));
            plain_text = new StringBuffer(decrypt(cipher_text, key.toString() ));
            System.out.println("key : " + key.toString() + ", plain text : " + plain_text.toString());
            choice = Integer.parseInt(br.readLine().trim());
            if(choice == 1)
            {
                return plain_text.toString();
            }
            else
            {
                //This character has been tried
                frequency_of_characters_ref_text[index] = -1;
            }
        }
        return null;
    }
    
    private int find_index_max_frequency(int[] text)
    {
        int index = -1;
        int max = 0;
        for(int i=0; i<text.length; i++)
        {
            if( text[i] > max )
                index = i;
        }
        return index;
    }
    public int convert_key(String key)
    {
        char tmp = key.charAt(0);
        if( Character.isLowerCase(tmp) )
            return (tmp - 97);
        else 
            return tmp - 48;
    }

    
}
