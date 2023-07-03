package de.miraculixx.tutorial

import de.miraculixx.kpaper.event.listen
import de.miraculixx.kpaper.extensions.bukkit.cmp
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerMoveEvent

object DamageEvent {
    val onDamage = listen<EntityDamageEvent> {
        val player = it.entity
        if (player !is Player) return@listen

        player.sendMessage(cmp("Du hast Schaden bekommen (${it.finalDamage})", NamedTextColor.GOLD))
    }

    val onMove = listen<PlayerMoveEvent> {
        if (it.from.block != it.to.block) {
            it.isCancelled = true
            it.player.sendMessage(cmp("Du darfst dich nicht mehr als einen Block bewegen!", NamedTextColor.RED, bold = true))
        }
    }

    val onChat = listen<AsyncChatEvent> {
        val message = it.message()
        val rawMessage = PlainTextComponentSerializer.plainText().serialize(message)
        if (rawMessage == "ping") it.message(cmp("pong"))
    }
}