package ir.realstresser.extremeantibot.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ir.realstresser.extremeantibot.InitHandler;
import ir.realstresser.extremeantibot.protections.protection;
import ir.realstresser.extremeantibot.value.ValueHandler;
import lombok.experimental.UtilityClass;

import java.io.*;
import java.util.Map;

@UtilityClass
public class FileManager {
    public File PluginsDir  = new File("plugins");
    public File RootDir  = new File(PluginsDir,"ExtremeAntibot");
    public File config = new File(RootDir, "config.json");
    public File data = new File(RootDir, "data.json");
    public void init() {
        if (!RootDir.exists()) RootDir.mkdirs();

        if (!config.exists()) {
            saveConfig();
        } else{
            loadConfig();
        }
        if (!protection.data.exists()) {
            protection.saveData("default", "default", "default");
        }
    }

    public void saveConfig(){
        JsonObject json = new JsonObject();
        for (ValueHandler values : InitHandler.values) {
            JsonObject json2 = new JsonObject();
            if (values.isBoolVal()) {
                json2.addProperty(values.getName(), values.getDefaultValBool());
            } else if(values.isIntVal()){
                json2.addProperty(values.getName(), values.getDefaultValInt());
            } else if (values.isStrVal()) {
                json2.addProperty(values.getName(), values.getDefaultValStr());
            }
            json.add(values.getCategoryName(), json2);
        }
        try{
            PrintWriter save = new PrintWriter(new FileWriter(config));
            save.println(JsonUtil.PrettyGson.toJson(json));
            save.close();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Please report this lines to XyPz, this may have some debug informations.");
        }
    }
    public void loadConfig(){
        try {
            BufferedReader load = new BufferedReader(new FileReader(config));
            JsonObject json = (JsonObject)JsonUtil.jsonParser.parse(load);
            load.close();
            for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                JsonObject jsonModule = (JsonObject) entry.getValue();
                for (ValueHandler value : InitHandler.values) {
                    if (entry.getKey().equals(value.getCategoryName())) {
                        if (value.isIntVal()) {
                            value.setDefaultValInt(jsonModule.get(value.getName()).getAsInt());
                        } else if (value.isBoolVal()) {
                            value.setDefaultValBool(jsonModule.get(value.getName()).getAsBoolean());
                        } else if (value.isStrVal()) {
                            value.setDefaultValStr(jsonModule.get(value.getName()).getAsString());
                        }
                    }
                }
            }
        //Module modul = Module.getModuleByName(entry.getKey());
                //if(modul != null && !moduleBlackList.contains(modul.getClass().getName())) {
                    //JsonObject jsonModule = (JsonObject) entry.getValue();
                    //boolean enabled = jsonModule.get("enabled").getAsBoolean();
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Please report this lines to XyPz, this may have some debug informations.");
        }
    }
}
