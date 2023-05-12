package ir.realstresser.extremeantibot.protections.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ir.realstresser.extremeantibot.ConsoleHandler;
import ir.realstresser.extremeantibot.protections.ProtectionInformation;
import ir.realstresser.extremeantibot.protections.protection;
import ir.realstresser.extremeantibot.utils.JsonUtil;
import jdk.nashorn.internal.parser.JSONParser;
import net.md_5.bungee.api.event.PlayerHandshakeEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@ProtectionInformation(name = "Bot A")
public class BotA extends protection implements Listener {

    @EventHandler
    public void onJoin(PlayerHandshakeEvent e){
        if (!data.exists()) {
            JsonObject json = new JsonObject();
            JsonObject json2 = new JsonObject();
            json2.addProperty("accounts", 1);
            json.add(e.getConnection().getSocketAddress().toString(), json2);
            try{
                PrintWriter save = new PrintWriter(new FileWriter(data));
                save.println(JsonUtil.PrettyGson.toJson(json));
                save.close();
            }catch(Exception ex){
                ex.printStackTrace();
                System.out.println("Please report this lines to XyPz, this may have some debug informations.");
            }
        } else{
            try{
                JsonElement jsonElement;// = JsonUtil.jsonParser.parse(new FileReader(data));
                try (FileReader fileReader = new FileReader(data)) {
                    jsonElement = JsonUtil.gson.fromJson(fileReader, JsonElement.class);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    return;
                }
                JsonObject json = jsonElement.getAsJsonObject();
                JsonObject json2 = new JsonObject();
                json2.addProperty("accounts", 1);
                json.add(e.getConnection().getSocketAddress().toString(), json2);
                try (FileWriter fileWriter = new FileWriter(data)) {
                    JsonUtil.gson.toJson(json, fileWriter);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            } catch(Exception ex){
                ex.printStackTrace();
                System.out.println("Please report this lines to XyPz, this may have some debug informations.");
            }

        }

    }
}
