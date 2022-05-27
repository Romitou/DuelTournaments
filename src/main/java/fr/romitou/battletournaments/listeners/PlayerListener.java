package fr.romitou.battletournaments.listeners;

import fr.romitou.battletournaments.BattleTournaments;
import fr.romitou.battletournaments.elements.battlePlayers.tasks.PlayerJoinTask;
import fr.romitou.battletournaments.tasks.TaskManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    private final TaskManager taskManager;

    public PlayerListener(BattleTournaments plugin) {
        this.taskManager = plugin.getTaskManager();
    }

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent event) {
        taskManager.runEventTask(new PlayerJoinTask(), event);
    }

}
