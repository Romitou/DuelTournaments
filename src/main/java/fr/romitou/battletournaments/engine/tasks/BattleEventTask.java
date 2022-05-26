package fr.romitou.battletournaments.engine.tasks;

public abstract class BattleEventTask<T> extends BattleTask {

    private T event;

    public T getEvent() {
        return event;
    }

    public void setEvent(T event) {
        this.event = event;
    }

}
