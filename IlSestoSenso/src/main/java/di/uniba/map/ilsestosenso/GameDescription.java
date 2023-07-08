/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.map.ilsestosenso;

import di.uniba.map.ilsestosenso.parser.ParserOutput;
import di.uniba.map.ilsestosenso.type.AdvObject;
import di.uniba.map.ilsestosenso.type.Command;
import di.uniba.map.ilsestosenso.type.Room;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pierpaolo
 */
public abstract class GameDescription {

    private final List<Room> rooms = new ArrayList<>();

    private final List<Command> commands = new ArrayList<>();

    private final List<AdvObject> inventory = new ArrayList<>();

    private Room currentRoom;
    
    private boolean over = false;
    
    public boolean isOver(){
        return over;
    }
    
    public void setOver(boolean b){
        over = b;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public List<AdvObject> getInventory() {
        return inventory;
    }

    public abstract void init() throws Exception;

    public abstract void nextMove(ParserOutput p, PrintStream out);

}
