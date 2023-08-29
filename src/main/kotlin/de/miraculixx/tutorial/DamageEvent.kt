package de.miraculixx.tutorial

import de.miraculixx.kpaper.event.listen
import de.miraculixx.kpaper.extensions.bukkit.cmp
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent

object DamageEvent {
    val onDamage = listen<EntityDamageEvent> {
        val player = it.entity
        if (player !is Player) return@listen

        player.sendMessage(cmp("Du hast Schaden bekommen (${it.finalDamage})", NamedTextColor.GOLD))
    }
}