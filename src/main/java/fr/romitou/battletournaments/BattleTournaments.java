package fr.romitou.battletournaments;

import fr.romitou.battletournaments.database.MongoManager;
import fr.romitou.battletournaments.elements.battlePlayers.PlayerManager;
import fr.romitou.battletournaments.engine.TaskManager;
import fr.romitou.battletournaments.listeners.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class BattleTournaments extends JavaPlugin {

    private MongoManager mongoManager;
    private TaskManager taskManager;
    private PlayerManager playerManager;

    public void onEnable() {
        getLogger().info("BattleTournaments is loading...");

        getLogger().info("Loading config...");
        saveDefaultConfig();

        getLogger().info("Loading task manager...");
        taskManager = new TaskManager(this);

        getLogger().info("Loading player manager...");
        playerManager = new PlayerManager(this);

        getLogger().info("Connecting to MongoDB...");
        mongoManager = new MongoManager(this);

        getLogger().info("Registering listeners...");
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);

        getLogger().info("BattleTournaments is loaded!");
    }

    public void onDisable() {
        getLogger().info("BattleTournaments has been disabled!");
    }

    public @NotNull MongoManager getMongoManager() {
        return mongoManager;
    }

    public @NotNull TaskManager getTaskManager() {
        return taskManager;
    }

    public @NotNull PlayerManager getPlayerManager() {
        return playerManager;
    }
}
