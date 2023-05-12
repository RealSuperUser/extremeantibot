package ir.realstresser.extremeantibot.value.impl;

import ir.realstresser.extremeantibot.value.ValueHandler;
import ir.realstresser.extremeantibot.value.ValueInformation;

@ValueInformation(CategoryName = "debug", name = "debug", DefaultValBool = false, DefaultValStr = "", DefaultValInt = 0)
public class debug extends ValueHandler{
    public debug(){
        super(true);
    }
}