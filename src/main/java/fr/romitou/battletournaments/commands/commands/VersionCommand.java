package fr.romitou.battletournaments.commands.commands;

import fr.romitou.battletournaments.commands.AbstractBattleCommand;
import fr.romitou.battletournaments.commands.CommandUsability;
import fr.romitou.battletournaments.commands.annotations.Name;
import fr.romitou.battletournaments.commands.annotations.Permission;
import fr.romitou.battletournaments.commands.annotations.UsableBy;
import org.bukkit.command.CommandSender;

import java.util.List;

@Name("version")
@UsableBy(CommandUsability.PLAYER_OR_CONSOLE)
@Permission("battletournaments.version")
public class VersionCommand extends AbstractBattleCommand {
    @Override
    public boolean exec(CommandSender commandSender, String[] args) {
        commandSender.sendMessage("§aBattleTournaments v" + getPlugin().getDescription().getVersion());
        return true;
    }

    @Override
    public List<String> list(CommandSender commandSender, String label, String[] args) {
        return List.of();
    }
}
