package com.musala.xml.parser.executable.helpers;

public class WrongStringInputException extends Exception {

    private static final long serialVersionUID = -2268478663270285204L;

    public WrongStringInputException(String text){
        super(text);
    }
    public String getMessage(){
        return super.getMessage();
    }

}

