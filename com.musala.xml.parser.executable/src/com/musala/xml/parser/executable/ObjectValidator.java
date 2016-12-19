package com.musala.xml.parser.executable;

public class ObjectValidator {
    
    private ObjectValidator() {

    }

    public static void CheckIfObjectIsNull (Object obj, String message)throws NullPointerException{
        if(obj==null){
            throw new NullPointerException(message);
        }
    }
}
