package fr.romitou.battletournaments.elements.battlePlayers;

import fr.romitou.battletournaments.BattleTournaments;
import fr.romitou.battletournaments.elements.AbstractManager;
import fr.romitou.battletournaments.elements.battlePlayers.objects.BattlePlayer;
import org.bukkit.entity.Player;

import static com.mongodb.client.model.Filters.eq;

public class PlayerManager extends AbstractManager<BattlePlayer> {

    public PlayerManager(BattleTournaments plugin) {
        super(plugin);
    }

    public BattlePlayer from(Player player) {
        BattlePlayer battlePlayer = getMongoCollection().find(eq("uuid", player.getUniqueId())).first();
        if (battlePlayer != null)
            return battlePlayer;
        battlePlayer = new BattlePlayer(player.getName(), player.getUniqueId());
        this.create(battlePlayer);
        return battlePlayer;
    }

    public void create(BattlePlayer battlePlayer) {
        if (battlePlayer.getId() != null)
            return;
        getMongoCollection().insertOne(battlePlayer);
    }

    public void save(BattlePlayer battlePlayer) {
        getMongoCollection().findOneAndReplace(eq("id", battlePlayer.getId()), battlePlayer);
    }

    @Override
    public Class<BattlePlayer> getPojoClass() {
        return BattlePlayer.class;
    }
}
