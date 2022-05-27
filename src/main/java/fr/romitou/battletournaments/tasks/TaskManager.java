package fr.romitou.battletournaments.tasks;

import fr.romitou.battletournaments.BattleTournaments;
import fr.romitou.battletournaments.tasks.tasks.BattleEventTask;
import fr.romitou.battletournaments.tasks.tasks.BattleTask;

public record TaskManager(BattleTournaments plugin) {

    public void runTask(BattleTask battleTask) {
        battleTask.setPlugin(plugin);
        logTask("running task: " + battleTask.getClass().getSimpleName());
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, battleTask);
    }

    public <T> void runEventTask(BattleEventTask<T> battleEventTask, T event) {
        battleEventTask.setPlugin(plugin);
        battleEventTask.setEvent(event);
        logTask("running event task: " + battleEventTask.getClass().getSimpleName() + " for event: " + event.getClass().getSimpleName());
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, battleEventTask);
    }

    private void logTask(String message) {
        plugin.getLogger().info("TaskManager: " + message);
    }

}
