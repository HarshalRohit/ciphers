/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cipher;

/**
 *
 * @author Harshal
 */
public interface CipherOperations {
    public String encrypt(String plain_text, String key);
    
    public String decrypt(String cipher_text, String key);
    
//    public Object get_key();
}
