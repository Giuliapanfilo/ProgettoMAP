package di.uniba.map.ilsestosenso.parser;

import di.uniba.map.ilsestosenso.Utils;
import di.uniba.map.ilsestosenso.type.AdvObject;
import di.uniba.map.ilsestosenso.type.Command;

import java.util.List;
import java.util.Set;

/**
 * @author IlSestoSenso
 */
public class Parser {

    private final Set<String> stopwords;

    public Parser(Set<String> stopwords) {
        this.stopwords = stopwords;
    }

    private int checkForCommand(String token, List<Command> commands) {
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i).getName().equals(token) || commands.get(i).getAlias().contains(token)) {
                return i;
            }
        }
        return -1;
    }

    private int checkForObject(String token, List<AdvObject> obejcts) {
        for (int i = 0; i < obejcts.size(); i++) {
            if (obejcts.get(i).getName().equals(token) || obejcts.get(i).getAlias().contains(token)) {
                return i;
            }
        }
        return -1;
    }

    /* ATTENZIONE: il parser è implementato in modo abbastanza independete dalla lingua, ma riconosce solo
     * frasi semplici del tipo <azione> <oggetto> <oggetto>. Eventuali articoli o preposizioni vengono semplicemente
     * rimossi.
     */
    public ParserOutput parse(String command, List<Command> commands, List<AdvObject> objects, List<AdvObject> inventory) {
        List<String> tokens = Utils.parseString(command, stopwords);
        if (!tokens.isEmpty()) {
            int ic = checkForCommand(tokens.get(0), commands);
            if (ic > -1) {
                if (tokens.size() > 1) {
                    int io = checkForObject(tokens.get(1), objects);
                    int ioinv = -1;
                    int code = -1;
                    if (io < 0 && tokens.size() > 2) {
                        io = checkForObject(tokens.get(2), objects);
                    }
                    if (io < 0) {
                        ioinv = checkForObject(tokens.get(1), inventory);
                        if (ioinv < 0 && tokens.size() > 2) {
                            ioinv = checkForObject(tokens.get(2), inventory);
                        }
                    }

                    if(ioinv == -1){
                        try{
                            code = Integer.parseInt(tokens.get(2));
                        } catch (Exception e){
                            code = -1;
                        }
                    }

                    if (io > -1 && ioinv > -1) {
                        return new ParserOutput(commands.get(ic), objects.get(io), inventory.get(ioinv), code);
                    } else if (io > -1) {
                        return new ParserOutput(commands.get(ic), objects.get(io), null, code);
                    } else if (ioinv > -1) {
                        return new ParserOutput(commands.get(ic), null, inventory.get(ioinv), code);
                    } else {
                        return new ParserOutput(commands.get(ic), null, null, code);
                    }
                } else {
                    return new ParserOutput(commands.get(ic), null);
                }
            } else {
                return new ParserOutput(null, null);
            }
        } else {
            return null;
        }
    }

}
