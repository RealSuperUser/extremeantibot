package ir.realstresser.extremeantibot.value;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ValueInformation {
    String name();
    String DefaultValStr();

    String CategoryName();
    boolean DefaultValBool();
    int DefaultValInt();
}
