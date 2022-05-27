package fr.romitou.battletournaments.commands;

import fr.romitou.battletournaments.BattleTournaments;
import fr.romitou.battletournaments.commands.annotations.Name;
import fr.romitou.battletournaments.commands.annotations.Permission;
import fr.romitou.battletournaments.commands.annotations.UsableBy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Annotation;
import java.util.*;

public class CommandManager implements TabExecutor {

    private final BattleTournaments plugin;
    private final Set<EngineBattleCommand> commands = new HashSet<>();
    private final Class<? extends Annotation>[] annotations = new Class[]{Name.class, Permission.class, UsableBy.class};

    public CommandManager(BattleTournaments plugin) {
        this.plugin = plugin;
    }

    public void registerCommand(AbstractBattleCommand command) {
        Class<? extends AbstractBattleCommand> clazz = command.getClass();
        for (Class<? extends Annotation> annotation : annotations) {
            if (!clazz.isAnnotationPresent(annotation)) {
                throw new IllegalArgumentException("The command " + clazz.getName() + " is not annotated with " + annotation.getName());
            }
        }
        Name name = clazz.getAnnotation(Name.class);
        for (EngineBattleCommand engineBattleCommand : commands) {
            if (engineBattleCommand.getName().equalsIgnoreCase(name.value())) {
                throw new IllegalArgumentException("The command " + clazz.getName() + " has the same name as the command " + engineBattleCommand.getClass().getName());
            }
        }
        Permission permission = clazz.getAnnotation(Permission.class);
        UsableBy usableBy = clazz.getAnnotation(UsableBy.class);

        command.setPlugin(plugin);
        EngineBattleCommand engineCommand = new EngineBattleCommand(command, name.value(), usableBy.value(), permission.value());
        commands.add(engineCommand);
        System.out.println(commands);
    }

    public Optional<EngineBattleCommand> getCommand(String name) {
        return commands.stream().filter(engineBattleCommand -> engineBattleCommand.getName().equalsIgnoreCase(name)).findFirst();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§cUsage: §7/§6" + label + " <command>");
            return false;
        }
        Optional<EngineBattleCommand> battleCommand = getCommand(args[0]);
        if (battleCommand.isEmpty()) {
            sender.sendMessage("Unknown command");
            return false;
        }
        EngineBattleCommand engineBattleCommand = battleCommand.get();
        switch (engineBattleCommand.getUsableBy()) {
            case PLAYER_ONLY -> {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("You must be a player to use this command");
                    return false;
                }
            }
            case CONSOLE_ONLY -> {
                if (!(sender instanceof ConsoleCommandSender)) {
                    sender.sendMessage("You must be a console to use this command");
                    return false;
                }
            }
            case PLAYER_OR_CONSOLE -> {
                if (!(sender instanceof Player) && !(sender instanceof ConsoleCommandSender)) {
                    sender.sendMessage("You must be a player or a console to use this command");
                    return false;
                }
            }
        }
        if (!sender.hasPermission(engineBattleCommand.getPermission())) {
            sender.sendMessage("You don't have the permission to use this command");
            return false;
        }
        return battleCommand.get().getBattleCommand().exec(sender, Arrays.copyOfRange(args, 1, args.length));
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Optional<EngineBattleCommand> battleCommand = getCommand(command.getName());
        if (battleCommand.isEmpty()) {
            return List.of();
        }
        return battleCommand.get().getBattleCommand().list(sender, label, args);
    }
}
