package fr.romitou.battletournaments.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import fr.romitou.battletournaments.BattleTournaments;
import org.bson.UuidRepresentation;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoManager {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;

    public MongoManager(BattleTournaments plugin) {

        String databaseName = plugin.getConfig().getString("mongodb.database", "battleTournaments");
        String rawConnectionString = plugin.getConfig().getString("mongodb.uri", "mongodb://localhost:27017");
        ConnectionString connectionString;
        try {
            connectionString = new ConnectionString(rawConnectionString);
        } catch (IllegalArgumentException exception) {
            plugin.getLogger().severe("Invalid Mongo connection string: " + exception.getMessage());
            return;
        }

        mongoClient = MongoClients.create(MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .build());
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        mongoDatabase = mongoClient.getDatabase(databaseName).withCodecRegistry(pojoCodecRegistry);
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

}
