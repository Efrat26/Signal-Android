package org.thoughtcrime.securesms.biu;

public class Crypto {
    public void Gen(){}

    public String Encrypt(String key, String message){
        return message;
    }

    public String Decrypt(String key, String message){
        return message;
    }

    public void Sign(String key, String message){}

    public boolean Ver(String key, String message, String signature){
        return false;
    }
}
