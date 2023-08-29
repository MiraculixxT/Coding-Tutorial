package de.miraculixx.tutorial.commands

import de.miraculixx.kpaper.extensions.bukkit.cmp
import dev.jorel.commandapi.StringTooltip
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.arguments.LocationType
import dev.jorel.commandapi.kotlindsl.*
import org.bukkit.Location
import org.bukkit.Material
import kotlin.jvm.optionals.getOrNull

class FirstCommand {
    private val someList = listOf("a", "b", "c")

    val command = commandTree("test") {


        literalArgument("goldify") {
            withRequirement {
                it.name == "Miraculixx"
            }

            locationArgument("block", LocationType.BLOCK_POSITION, true) {
                playerExecutor { player, args ->
                    val loc = args.getOptional(0).getOrNull() as? Location
                    if (loc == null) {
                        player.sendMessage(cmp("Du hast keine Position angeben!"))
                        return@playerExecutor
                    }
                    loc.block.type = Material.GOLD_BLOCK
                }
            }
        }

        literalArgument("hey") {
            playerExecutor { player, args ->
                player.sendMessage(cmp("Hey"))
            }
        }

        literalArgument("select") {
            stringArgument("selection") {
                replaceSuggestions(
                    ArgumentSuggestions.stringsWithTooltips(
                        StringTooltip.ofString(
                            "test",
                            "Hey, ich bin ein Tooltip"
                        ), StringTooltip.ofString("test2", "Zweiter Test")
                    )
                )
                playerExecutor { player, commandArguments -> }
            }
        }



        literalArgument("numbers") {

            literalArgument("int") {
                integerArgument("int", 0, 10) {
                    anyExecutor { commandSender, commandArguments -> }
                }
            }

            literalArgument("double") {
                doubleArgument("double", -1.0, 5.5) {
                    anyExecutor { commandSender, commandArguments -> }
                }
            }
        }

        literalArgument("text") {

            literalArgument("string") {
                stringArgument("simple-text") {
                    anyExecutor { commandSender, commandArguments -> }
                }
            }

            literalArgument("text") {
                textArgument("special-text") {
                    anyExecutor { commandSender, commandArguments -> }
                }
            }

            literalArgument("greedy") {
                greedyStringArgument("infinit-text") {
                    anyExecutor { commandSender, commandArguments -> }
                }
            }
        }

        literalArgument("selector") {

            literalArgument("manyEntities") {
                entitySelectorArgumentManyEntities("many-entities") {
                    anyExecutor { commandSender, commandArguments -> }
                }
            }

            literalArgument("onePlayer") {
                entitySelectorArgumentOnePlayer("one-player") {
                    anyExecutor { commandSender, commandArguments -> }
                }
            }
        }

        literalArgument("types") {

            literalArgument("itemStack") {
                itemStackArgument("item") {
                    anyExecutor { commandSender, commandArguments -> }
                }
            }

            literalArgument("entityType") {
                entityTypeArgument("entity") {
                    anyExecutor { commandSender, commandArguments -> }
                }
            }

            literalArgument("sound") {
                soundArgument("sound") {
                    anyExecutor { commandSender, commandArguments -> }
                }
            }
        }
    }
}