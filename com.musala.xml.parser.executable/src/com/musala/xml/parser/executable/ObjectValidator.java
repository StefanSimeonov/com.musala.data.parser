package com.musala.xml.parser.executable;

public class ObjectValidator {
    
    private ObjectValidator() {

    }

    public static void CheckIfObjectIsNull(Object obj, String message){
        if(obj==null){
            throw new NullPointerException(message);
        }
    }
}
