package fr.romitou.battletournaments.commands.annotations;

import fr.romitou.battletournaments.commands.CommandUsability;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface UsableBy {
    CommandUsability value();
}
