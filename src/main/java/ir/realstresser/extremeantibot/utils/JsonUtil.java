package ir.realstresser.extremeantibot.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonUtil {
    public Gson gson = new Gson();
    public Gson PrettyGson = new GsonBuilder().setPrettyPrinting().create();
    public JsonParser jsonParser = new JsonParser();
}
