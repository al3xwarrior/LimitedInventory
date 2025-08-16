package com.al3x.limitedInventory.Commands;

import com.al3x.limitedInventory.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.al3x.limitedInventory.Util.colorize;

public class LimitedInventoryCmd implements TabExecutor {

    private PlayerManager playerManager;

    public LimitedInventoryCmd(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if (!commandSender.hasPermission("limitedinventory.admin")) return false;

        if (strings.length == 3 && strings[0].equalsIgnoreCase("setslots")) {

            Player target = Bukkit.getPlayer(strings[1]);
            if (target == null) {
                commandSender.sendMessage(colorize("&cThat player is not online!"));
                return true;
            }

            int slots;
            try {
                slots = Integer.parseInt(strings[2]);
            } catch (NumberFormatException e) {
                commandSender.sendMessage(colorize("&cPlease enter a valid number between 0 and 36!"));
                return true;
            }
            if (slots < 0 || slots > 36) {
                commandSender.sendMessage(colorize("&cPlease enter a valid number between 0 and 36!"));
                return true;
            }

            playerManager.setPlayer(target, slots);
            commandSender.sendMessage(colorize("&aSet " + target.getName() + "'s limited slots to " + slots + "!"));

        } else {
            commandSender.sendMessage(colorize("&r"));
            commandSender.sendMessage(colorize("&6&lLimitedInventory &7by &bAl3x"));
            commandSender.sendMessage(colorize("&f/limitedinventory help &7(Displays this message)"));
            commandSender.sendMessage(colorize("&f/limitedinventory setslots <player> <0-36> &7(Sets the slots that are limited for a player)"));
            commandSender.sendMessage(colorize("&r"));
            return true;
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if (strings.length == 1) {
            return List.of("help", "setslots");
        } else if (strings.length == 2 && strings[0].equalsIgnoreCase("setslots")) {
            return Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();
        } else if (strings.length == 3 && strings[0].equalsIgnoreCase("setslots")) {
            List<String> numbers = new ArrayList<>();
            for (int i = 0; i <= 36; i++) {
                numbers.add(String.valueOf(i));
            }
            return numbers;
        }

        return List.of();
    }
}
