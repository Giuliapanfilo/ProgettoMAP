/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.map.ilsestosenso;

import di.uniba.map.ilsestosenso.parser.Parser;
import di.uniba.map.ilsestosenso.parser.ParserOutput;
import di.uniba.map.ilsestosenso.type.CommandType;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ATTENZIONE: l'Engine è molto spartano, in realtà demanda la logica alla
 * classe che implementa GameDescription e si occupa di gestire I/O sul
 * terminale.
 *
 * @author pierpaolo
 */
public class Engine extends Thread {

    private final GameDescription game;

    private Parser parser;

    private String command = "-1";

    private boolean inGame = true;

    /**
     * -1 indica fine partita per resa/uscita anticipata
     * 1 indica fine partita regolare il cui risultato può essere salvato.
     */
    private int exitCode = -1;

    public boolean isInGame() {
        return inGame;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getExitCode() {
        return exitCode;
    }
    
    public GameDescription getGame(){
        return game;
    }

    public Engine(GameDescription game) {
        this.game = game;
        try {
            this.game.init();
        } catch (Exception ex) {
            System.err.println(ex);
        }
        try {
            Set<String> stopwords = Utils.loadFileListInSet(new File("./resources/stopwords"));
            parser = new Parser(stopwords);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    @Override
    public void run() {
        System.out.println("================================");
        System.out.println("* Il Sesto Senso v. 1.0 - 2023 *");
        System.out.println("================================");
        System.out.println(game.getCurrentRoom().getName());
        System.out.println();
        System.out.println(game.getCurrentRoom().getDescriptionFirstTime());
        game.getCurrentRoom().setFirstTime(false);
        System.out.println();

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!command.equals("-1")) {
                ParserOutput p = parser.parse(command, game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
                if (p == null || p.getCommand() == null) {
                    System.out.println("Non capisco quello che mi vuoi dire.\n");
                } else if (p.getCommand() != null && p.getCommand().getType() == CommandType.END) {
                    System.out.println("Addio!");
                    exitCode = -1;
                    break;
                } else {
                    game.nextMove(p, System.out);
                    System.out.println();
                }
                
                if(game.isOver()){
                    exitCode = 1;
                    break;
                }

                command = "-1";
            }
        }

        inGame = false;
    }
}
