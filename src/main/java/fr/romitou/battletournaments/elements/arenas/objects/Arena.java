package fr.romitou.battletournaments.elements.arenas.objects;

import org.bukkit.Location;

public class Arena {

    private ArenaState state;
    private Location location;

    public Arena() {
    }

    public ArenaState getState() {
        return state;
    }

    public void setState(ArenaState state) {
        this.state = state;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
