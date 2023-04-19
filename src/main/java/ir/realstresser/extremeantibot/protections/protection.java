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
    public static void saveData(String HeadKey,String key, String value){
        JsonObject json = new JsonObject();
        JsonObject json2 = new JsonObject();
        json2.addProperty(key, value);
        json.add(HeadKey, json2);
        try{
            PrintWriter save = new PrintWriter(new FileWriter(data));
            save.println(JsonUtil.PrettyGson.toJson(json));
            save.close();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Please report this lines to XyPz, this may have some debug informations.");
        }
    }
    public boolean loadDataBoolean(String headKey, String key){
        try {
            BufferedReader load = new BufferedReader(new FileReader(data));
            JsonObject json = (JsonObject) JsonUtil.jsonParser.parse(load);
            load.close();
            for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                JsonObject jsonModule = (JsonObject) entry.getValue();
                if (entry.getKey().equals(headKey)) {
                    return jsonModule.get(key).getAsBoolean();
                }
            }
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("Please report this lines to XyPz, this may have some debug informations.");
        }
        return false;
    }
    public int loadDataInt(String headKey, String key){
        try {
            BufferedReader load = new BufferedReader(new FileReader(data));
            JsonObject json = (JsonObject) JsonUtil.jsonParser.parse(load);
            load.close();
            for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                JsonObject jsonModule = (JsonObject) entry.getValue();
                if (entry.getKey().equals(headKey)) {
                    return jsonModule.get(key).getAsInt();
                }
            }
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("Please report this lines to XyPz, this may have some debug informations.");
        }
        return 0;
    }
    public String loadDataString(String headKey, String key){
        try {
            BufferedReader load = new BufferedReader(new FileReader(data));
            JsonObject json = (JsonObject) JsonUtil.jsonParser.parse(load);
            load.close();
            for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                JsonObject jsonModule = (JsonObject) entry.getValue();
                if (entry.getKey().equals(headKey)) {
                    return jsonModule.get(key).getAsString();
                }
            }
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("Please report this lines to XyPz, this may have some debug informations.");
        }
        return "";
    }

}
