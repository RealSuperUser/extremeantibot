package ir.realstresser.extremeantibot.protections;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ir.realstresser.extremeantibot.InitHandler;
import ir.realstresser.extremeantibot.protections.impl.BotA;
import ir.realstresser.extremeantibot.utils.FileManager;
import ir.realstresser.extremeantibot.utils.JsonUtil;
import lombok.Getter;

import java.io.*;
import java.util.Map;

@Getter
public class protection {
    private ProtectionInformation protInfo;
    private String Name;
    public static File data = new File(FileManager.RootDir, "data.json");
    public protection(){
        if (this.getClass().isAnnotationPresent(ProtectionInformation.class)) {
            this.protInfo = this.getClass().getAnnotation(ProtectionInformation.class);
        } else {
            throw new RuntimeException("annotation not found on " + this.getClass().getSimpleName());
        }
        Name = protInfo.name();
    }
    public static void init(){
        InitHandler.instance.getProxy().getPluginManager().registerListener(InitHandler.instance, new BotA());
    }



}
