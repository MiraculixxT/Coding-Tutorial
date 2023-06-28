package de.miraculixx.tutorial

import net.kyori.adventure.text.Component
import org.bukkit.plugin.java.JavaPlugin

class Tutorial: JavaPlugin() {

    override fun onEnable() {
        server.consoleSender.sendMessage(Component.text("Hallo Server"))
    }

    override fun onDisable() {
        server.consoleSender.sendMessage(Component.text("Good bye :("))
    }
}