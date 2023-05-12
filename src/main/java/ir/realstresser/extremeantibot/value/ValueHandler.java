package ir.realstresser.extremeantibot.value;

import ir.realstresser.extremeantibot.ConsoleHandler;
import ir.realstresser.extremeantibot.InitHandler;
import ir.realstresser.extremeantibot.protections.ProtectionInformation;
import ir.realstresser.extremeantibot.value.impl.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValueHandler {
    private String CategoryName;
    private String Name;
    private String DefaultValStr;
    private Boolean DefaultValBool;
    private Integer DefaultValInt;

    private boolean isStrVal;
    private boolean isBoolVal;
    private boolean isIntVal;
    private ValueInformation valueInfo;
    ProtectionInformation protInfo;

    public ValueHandler(int i){
        if (this.getClass().isAnnotationPresent(ValueInformation.class)) {
            this.valueInfo = this.getClass().getAnnotation(ValueInformation.class);
        } else {
            throw new RuntimeException("annontion not found on " + this.getClass().getSimpleName());
        }


        if (this.getClass().isAnnotationPresent(ProtectionInformation.class)) {
            this.protInfo = this.getClass().getAnnotation(ProtectionInformation.class);
            CategoryName = protInfo.name();
        } else {
            CategoryName = valueInfo.CategoryName();
        }
        Name = valueInfo.name();
        DefaultValInt = valueInfo.DefaultValInt();
        isIntVal = true;
    }
    /*
    DefaultValBool = valueInfo.DefaultValBool();
    DefaultValStr = valueInfo.DefaultValStr();*/
    public ValueHandler(boolean b){
        if (this.getClass().isAnnotationPresent(ValueInformation.class)) {
            this.valueInfo = this.getClass().getAnnotation(ValueInformation.class);
        } else {
            throw new RuntimeException("ModuleInfo annotation not found on " + this.getClass().getSimpleName());
        }
        if (this.getClass().isAnnotationPresent(ProtectionInformation.class)) {
            this.protInfo = this.getClass().getAnnotation(ProtectionInformation.class);
            CategoryName = protInfo.name();
        } else {
            CategoryName = valueInfo.CategoryName();
        }
        Name = valueInfo.name();
        DefaultValBool = valueInfo.DefaultValBool();
        isBoolVal  = true;
    }
    public ValueHandler(String s){
        if (this.getClass().isAnnotationPresent(ValueInformation.class)) {
            this.valueInfo = this.getClass().getAnnotation(ValueInformation.class);
        } else {
            throw new RuntimeException("ModuleInfo annotation not found on " + this.getClass().getSimpleName());
        }
        if (this.getClass().isAnnotationPresent(ProtectionInformation.class)) {
            this.protInfo = this.getClass().getAnnotation(ProtectionInformation.class);
            CategoryName = protInfo.name();
        } else {
            CategoryName = valueInfo.CategoryName();
        }
        Name = valueInfo.name();
        DefaultValStr = valueInfo.DefaultValStr();
        isStrVal  = true;
    }
    public static void init(){
        InitHandler.values.add(new prefix());
        InitHandler.values.add(new debug());
    }

    public static ValueHandler getValueByName(String moduleName) {
        for(ValueHandler m : InitHandler.values){
            if(!m.getName().trim().equalsIgnoreCase(moduleName) && !m.toString().equalsIgnoreCase(moduleName.trim())) {
                continue;
            }
            return m;
        }
        return null;
    }
}
