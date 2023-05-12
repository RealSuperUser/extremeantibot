package ir.realstresser.extremeantibot.value.impl;

import ir.realstresser.extremeantibot.value.ValueHandler;
import ir.realstresser.extremeantibot.value.ValueInformation;

@ValueInformation(CategoryName = "storage", name = "storage", DefaultValBool = false, DefaultValStr = "json", DefaultValInt = 0)
public class storage extends ValueHandler {
    public storage(){
        super("");
    }
}
