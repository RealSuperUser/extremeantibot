package ir.realstresser.extremeantibot.protections.impl;

import ir.realstresser.extremeantibot.protections.ProtectionInformation;
import ir.realstresser.extremeantibot.protections.protection;
import net.md_5.bungee.api.event.PlayerHandshakeEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

@ProtectionInformation(name = "Bot A")
public class BotA extends protection implements Listener {

    @EventHandler
    public void onJoin(PlayerHandshakeEvent e){
        saveData(e.getConnection().getName(),
                "ip",
                e.getConnection().getSocketAddress().toString());
    }
}
