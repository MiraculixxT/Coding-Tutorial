package de.miraculixx.tutorial.commands

import de.miraculixx.kpaper.extensions.bukkit.cmp
import de.miraculixx.tutorial.Timer
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.literalArgument
import dev.jorel.commandapi.kotlindsl.textArgument
import net.kyori.adventure.text.format.NamedTextColor
import kotlin.time.Duration

class TimerCommand {
    val timerCommand = commandTree("timer") {
        literalArgument("pause") {
            anyExecutor { _, _ ->
                Timer.paused = true
            }
        }

        literalArgument("resume") {
            anyExecutor { _, _ ->
                Timer.paused = false
            }
        }

        literalArgument("settime") {
            textArgument("time") {
                anyExecutor { sender, args -> // /timer settime "1m 3s"
                    val timeString = args[0] as String

                    val time = try {
                        Duration.parse(timeString)
                    } catch (_: IllegalArgumentException) {
                        sender.sendMessage(cmp("Du hast keine gültige Zeit angeben!", NamedTextColor.RED))
                        return@anyExecutor
                    }

                    Timer.setTime(time)
                }
            }
        }
    }
}