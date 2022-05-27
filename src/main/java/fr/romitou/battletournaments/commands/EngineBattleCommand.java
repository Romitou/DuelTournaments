package fr.romitou.battletournaments.commands;

import org.bukkit.command.CommandSender;

public class EngineBattleCommand {

    private AbstractBattleCommand battleCommand;
    private String name;
    private CommandUsability usableBy;
    private String permission;

    public EngineBattleCommand(AbstractBattleCommand battleCommand, String name, CommandUsability usableBy, String permission) {
        this.battleCommand = battleCommand;
        this.name = name;
        this.usableBy = usableBy;
        this.permission = permission;
    }

    public AbstractBattleCommand getBattleCommand() {
        return battleCommand;
    }

    public void setBattleCommand(AbstractBattleCommand battleCommand) {
        this.battleCommand = battleCommand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommandUsability getUsableBy() {
        return usableBy;
    }

    public void setUsableBy(CommandUsability usableBy) {
        this.usableBy = usableBy;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "EngineBattleCommand{" +
                "battleCommand=" + battleCommand +
                ", name='" + name + '\'' +
                ", usableBy=" + usableBy +
                ", permission='" + permission + '\'' +
                '}';
    }
}
