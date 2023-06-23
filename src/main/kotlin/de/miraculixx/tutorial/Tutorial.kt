package de.miraculixx.tutorial

import net.kyori.adventure.text.Component
import org.bukkit.plugin.java.JavaPlugin

class Tutorial: JavaPlugin() {

    override fun onLoad() {
        server.consoleSender.sendMessage(Component.text("Hey, ich wurde geladen!"))
    }

    override fun onEnable() {

    }

    override fun onDisable() {
        server.consoleSender.sendMessage(Component.text("Ich wurde deaktiviert :("))
    }
}