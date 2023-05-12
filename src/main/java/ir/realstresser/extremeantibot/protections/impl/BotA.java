package ir.realstresser.extremeantibot.protections.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ir.realstresser.extremeantibot.ConsoleHandler;
import ir.realstresser.extremeantibot.InitHandler;
import ir.realstresser.extremeantibot.protections.ProtectionInformation;
import ir.realstresser.extremeantibot.protections.protection;
import ir.realstresser.extremeantibot.utils.JsonUtil;
import jdk.nashorn.internal.parser.JSONParser;
import net.md_5.bungee.api.event.PlayerHandshakeEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.Map;

@ProtectionInformation(name = "Bot A")
public class BotA extends protection implements Listener {

    @EventHandler
    public void onJoin(PreLoginEvent e) {
        if (InitHandler.isDebug) {
            ConsoleHandler.info("Handling new username, username is: " + e.getConnection().getName());
        }
        if (!data.exists()) {
            if (InitHandler.isDebug) {
                ConsoleHandler.info("Creating a data.json");
            }
            JsonObject json = new JsonObject();
            JsonObject json2 = new JsonObject();
            json2.addProperty("accounts", 1);
            json2.addProperty("username", e.getConnection().getName());
            json.add(e.getConnection().getAddress().getAddress().getHostAddress(), json2);
            try {
                if (InitHandler.isDebug) {
                    ConsoleHandler.info("saving a data.json");
                }
                PrintWriter save = new PrintWriter(new FileWriter(data));
                save.println(JsonUtil.PrettyGson.toJson(json));
                save.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Please report this lines to XyPz, this may have some debug informations.");
            }
        } else {
            if (InitHandler.isDebug) {
                ConsoleHandler.info("Setting alreadyAccountExists value...");
            }
            boolean alreadyAccountExists = false;
            try {
                BufferedReader load = new BufferedReader(new FileReader(data));
                JsonObject json = (JsonObject) JsonUtil.jsonParser.parse(load);
                load.close();
                for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                    JsonObject jsonModule = (JsonObject) entry.getValue();
                    if (entry.getKey().contains(e.getConnection().getAddress().getAddress().getHostAddress())) {
                        if (InitHandler.isDebug) {
                            ConsoleHandler.info("Account " + e.getConnection().getAddress().getAddress().getHostAddress() + " Already exists");
                        }
                        alreadyAccountExists = true;
                    } else {
                        alreadyAccountExists = false;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Please report this lines to XyPz, this may have some debug informations.");
            }

            try {
                if (!alreadyAccountExists) {
                    if (InitHandler.isDebug) {
                        ConsoleHandler.info("Account is not already in data.json, adding it......");
                    }
                    JsonElement jsonElement;// = JsonUtil.jsonParser.parse(new FileReader(data));
                    try (FileReader fileReader = new FileReader(data)) {
                        jsonElement = JsonUtil.gson.fromJson(fileReader, JsonElement.class);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        System.out.println("Please report this lines to XyPz, this may have some debug informations.");
                        return;
                    }
                    JsonObject json = jsonElement.getAsJsonObject();
                    JsonObject json2 = new JsonObject();
                    json2.addProperty("accounts", 1);
                    json2.addProperty("username", e.getConnection().getName());
                    json.add(e.getConnection().getAddress().getAddress().getHostAddress(), json2);
                    try (FileWriter fileWriter = new FileWriter(data)) {
                        if (InitHandler.isDebug) {
                            ConsoleHandler.info("writing changes to data.json (add account to it)");
                        }
                        JsonUtil.gson.toJson(json, fileWriter);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        System.out.println("Please report this lines to XyPz, this may have some debug informations.");
                    }
                } else {
                    if (InitHandler.isDebug) {
                        ConsoleHandler.info("checking for same username by one ip.");
                    }
                    boolean isSameUsername = false;
                    int count = 1;
                    BufferedReader load = new BufferedReader(new FileReader(data));
                    JsonObject json = (JsonObject) JsonUtil.jsonParser.parse(load);
                    load.close();
                    for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                        JsonObject jsonModule = (JsonObject) entry.getValue();
                        if (entry.getKey().contains(e.getConnection().getAddress().getAddress().getHostAddress())) {
                            count = jsonModule.get("accounts").getAsInt();
                            if (jsonModule.get("username").getAsString().equalsIgnoreCase(e.getConnection().getName())) {
                                isSameUsername = true;
                                if (InitHandler.isDebug) {
                                    ConsoleHandler.info("Detected the same username.");
                                }
                            } else {
                                isSameUsername = false;
                            }
                        } else {
                        }

                    }
                    if (InitHandler.isDebug) {
                        ConsoleHandler.info("Doing after same username tasks...");
                    }
                    if (isSameUsername) {
                        if (InitHandler.isDebug) {
                            ConsoleHandler.info("Doing after same username tasks...");
                        }
                        JsonObject jsonObject;
                        try (FileReader fileReader = new FileReader(data)) {
                            jsonObject = JsonUtil.gson.fromJson(fileReader, JsonObject.class);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            System.out.println("Please report this lines to XyPz, this may have some debug informations.");
                            return;
                        }
                        if (jsonObject.has(e.getConnection().getAddress().getAddress().getHostAddress())) {
                            JsonObject nestedObject = jsonObject.getAsJsonObject(e.getConnection().getAddress().getAddress().getHostAddress());
                            if (nestedObject.has("accounts")) {
                                nestedObject.addProperty("accounts", count++);
                            }
                        }
                        try (FileWriter writer = new FileWriter(data)) {
                            JsonUtil.gson.toJson(jsonObject, writer);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
