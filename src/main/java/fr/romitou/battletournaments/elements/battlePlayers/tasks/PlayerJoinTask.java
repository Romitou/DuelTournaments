package fr.romitou.battletournaments.elements.battlePlayers.tasks;

import fr.romitou.battletournaments.elements.battlePlayers.objects.BattlePlayer;
import fr.romitou.battletournaments.tasks.tasks.BattleEventTask;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinTask extends BattleEventTask<PlayerJoinEvent> {

    @Override
    public void run() {
        Player player = getEvent().getPlayer();
        BattlePlayer convertedPlayer = getPlugin().getPlayerManager().from(player);
        getEvent().joinMessage(Component.text("&a" + convertedPlayer.getName() + " &7joined the battle!"));
    }
}
