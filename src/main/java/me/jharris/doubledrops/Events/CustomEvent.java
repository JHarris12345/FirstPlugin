package me.jharris.doubledrops.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;


// We will be making a custom event for a theoretical minigame when someone wins a game
public class CustomEvent extends Event {

    /* Every custom event needs this chunk of code that you can copy and paste from https://bukkit.fandom.com/wiki/Event_API_Reference#Creating_Custom_Events/
    Scroll down to "Creating Custom Events" and you'll find this code:

        private static final HandlerList handlers = new HandlerList();

        public HandlerList getHandlers() {
            return handlers;
        }

        public static HandlerList getHandlerList() {
            return handlers;
        }

      This is shown below (although the below code is after I've added to the code for the purpose of creating the event
     */

    private static final HandlerList handlers = new HandlerList();

    private Player winner;
    private Player loser;
    private int finalscore;


    public CustomEvent(Player winner, Player loser, int finalscore) {
        this.winner = winner;
        this.loser = loser;
        this.finalscore = finalscore;
    }

    // The following 3 sections that say "getX" can be easily constructed by right clicking > generate > getter > select all your objects and click ok
    public Player getWinner() {
        return winner;
    }

    public Player getLoser() {
        return loser;
    }

    public int getFinalscore() {
        return finalscore;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
