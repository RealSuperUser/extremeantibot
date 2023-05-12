package ir.realstresser.extremeantibot;

import ir.realstresser.extremeantibot.protections.protection;
import ir.realstresser.extremeantibot.utils.FileManager;
import ir.realstresser.extremeantibot.value.ValueHandler;
import lombok.experimental.UtilityClass;

import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;


@UtilityClass
public class InitHandler {
    public String Prefix = "";
    public boolean isDebug;
    public ExtremeAntiBot instance = ExtremeAntiBot.getInstance();

    public CopyOnWriteArrayList<ValueHandler> values = new CopyOnWriteArrayList<>();
    public void Init(){
        ValueHandler.init();
        FileManager.init();
        protection.init();
        Prefix = Objects.requireNonNull(ValueHandler.getValueByName("prefix")).getDefaultValStr();
        isDebug = Objects.requireNonNull(ValueHandler.getValueByName("debug")).getDefaultValBool();
    }
    public void Shutdown(){
    }
}
