package ir.realstresser.extremeantibot;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConsoleHandler {
    public void info(String s){
        InitHandler.instance.getProxy().getLogger().info(InitHandler.Prefix + " " +s );
    }
    public void warn(String s){
        InitHandler.instance.getProxy().getLogger().warning(InitHandler.Prefix + " " +s );
    }
    public void error(String s){
       InitHandler.instance.getProxy().getLogger().warning(InitHandler.Prefix + " [ERROR] " +s );
    }
}
