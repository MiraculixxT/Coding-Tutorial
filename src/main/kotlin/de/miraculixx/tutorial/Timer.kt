package de.miraculixx.tutorial

import de.miraculixx.kpaper.extensions.broadcast
import de.miraculixx.kpaper.extensions.bukkit.cmp
import de.miraculixx.kpaper.extensions.bukkit.plus
import de.miraculixx.kpaper.extensions.onlinePlayers
import de.miraculixx.kpaper.runnables.task
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.minimessage.MiniMessage
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit

object Timer {
    private val miniMessages = MiniMessage.miniMessage()
    private var time = Duration.ZERO
    var paused = true
        set(value) {
            if (value) broadcast(cmp("Der Timer wurde ") + cmp("pausiert", NamedTextColor.RED, true))
            else broadcast(cmp("Der Timer wurde ") + cmp("gestartet", NamedTextColor.GREEN, true))
            field = value
        }
    private var offset = 0.0

    fun setTime(duration: Duration) {
        time = duration
    }

    private fun displayTimer() {
        val suffix = if (paused) "Timer pausiert ($time)" else "$time"
        val display = miniMessages.deserialize("<gradient:#707CF7:#F658CF:$offset><b>$suffix")
        onlinePlayers.forEach { player ->
            player.sendActionBar(display)
        }
    }

    private fun schedule() {
        task(false, 0, 1) {
            offset += 0.05
            if (offset > 1.0) offset -= 2

            displayTimer()
        }

        task(true, 0, 20) {
            if (paused) return@task
            time += 1.seconds
        }
    }

    init {
        schedule()
    }
}