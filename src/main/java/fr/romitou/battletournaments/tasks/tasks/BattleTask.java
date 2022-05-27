package fr.romitou.battletournaments.tasks.tasks;

import fr.romitou.battletournaments.BattleTournaments;

public abstract class BattleTask implements Runnable {

    private BattleTournaments plugin;

    public BattleTournaments getPlugin() {
        return plugin;
    }

    public void setPlugin(BattleTournaments plugin) {
        this.plugin = plugin;
    }


}
