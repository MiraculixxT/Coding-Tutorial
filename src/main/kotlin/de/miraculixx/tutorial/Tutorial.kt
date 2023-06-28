package de.miraculixx.tutorial

import de.miraculixx.kpaper.main.KPaper
import net.kyori.adventure.text.Component

class Tutorial : KPaper() {


    override fun load() {
        server.consoleSender.sendMessage(Component.text("Hey, ich wurde geladen!"))
    }

    override fun startup() {
        DamageEvent
    }

    override fun shutdown() {
        server.consoleSender.sendMessage(Component.text("Ich wurde deaktiviert :("))
    }
}