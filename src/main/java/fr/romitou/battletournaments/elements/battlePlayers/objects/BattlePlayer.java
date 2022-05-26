package fr.romitou.battletournaments.elements.battlePlayers.objects;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.util.UUID;

public class BattlePlayer {

    @BsonId
    private ObjectId id;

    private String name;
    private UUID uuid;

    public BattlePlayer() {
    }

    public BattlePlayer(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
