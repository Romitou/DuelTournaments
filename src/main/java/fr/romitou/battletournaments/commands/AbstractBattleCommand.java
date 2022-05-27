package fr.romitou.battletournaments.commands;

import fr.romitou.battletournaments.BattleTournaments;
import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class AbstractBattleCommand {

    private BattleTournaments plugin;

    public BattleTournaments getPlugin() {
        return plugin;
    }

    public void setPlugin(BattleTournaments plugin) {
        this.plugin = plugin;
    }

    public abstract boolean exec(CommandSender commandSender, String[] args);

    public abstract List<String> list(CommandSender commandSender, String label, String[] args);

}
