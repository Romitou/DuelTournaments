package fr.romitou.battletournaments.elements;

import com.mongodb.client.MongoCollection;
import fr.romitou.battletournaments.BattleTournaments;

public abstract class AbstractManager<T> {

    private final BattleTournaments plugin;

    public AbstractManager(BattleTournaments plugin) {
        this.plugin = plugin;
    }

    public abstract Class<T> getPojoClass();

    public MongoCollection<T> getMongoCollection() {
        return plugin.getMongoManager().getMongoDatabase().getCollection("battlePlayers", getPojoClass());
    }

}
