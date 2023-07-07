/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.map.ilsestosenso.games;

import di.uniba.map.ilsestosenso.GameDescription;
import di.uniba.map.ilsestosenso.parser.ParserOutput;
import di.uniba.map.ilsestosenso.type.AdvObject;
import di.uniba.map.ilsestosenso.type.AdvObjectContainer;
import di.uniba.map.ilsestosenso.type.Command;
import di.uniba.map.ilsestosenso.type.CommandType;
import di.uniba.map.ilsestosenso.type.Room;
import java.io.PrintStream;
import java.util.Iterator;

/**
 * ATTENZIONE: La descrizione del gioco è fatta in modo che qualsiasi gioco
 * debba estendere la classe GameDescription. L'Engine è fatto in modo che possa
 * eseguire qualsiasi gioco che estende GameDescription, in questo modo si
 * possono creare più gioci utilizzando lo stesso Engine.
 *
 * Diverse migliorie possono essere applicate: - la descrizione del gioco
 * potrebbe essere caricate da file o da DBMS in modo da non modificare il
 * codice sorgente - l'utilizzo di file e DBMS non è semplice poiché all'interno
 * del file o del DBMS dovrebbe anche essere codificata la logica del gioco
 * (nextMove) oltre alla descrizione di stanze, oggetti, ecc...
 *
 * @author pierpaolo
 */
public class FireHouseGame extends GameDescription {

    @Override
    public void init() throws Exception {
        //Commands
        Command nord = new Command(CommandType.NORD, "nord");
        nord.setAlias(new String[]{"n", "N", "Nord", "NORD"});
        getCommands().add(nord);
        Command iventory = new Command(CommandType.INVENTORY, "inventario");
        iventory.setAlias(new String[]{"inv"});
        getCommands().add(iventory);
        Command sud = new Command(CommandType.SOUTH, "sud");
        sud.setAlias(new String[]{"s", "S", "Sud", "SUD"});
        getCommands().add(sud);
        Command est = new Command(CommandType.EAST, "est");
        est.setAlias(new String[]{"e", "E", "Est", "EST"});
        getCommands().add(est);
        Command ovest = new Command(CommandType.WEST, "ovest");
        ovest.setAlias(new String[]{"o", "O", "Ovest", "OVEST"});
        getCommands().add(ovest);
        Command end = new Command(CommandType.END, "end");
        end.setAlias(new String[]{"end", "fine", "esci", "muori", "ammazzati", "ucciditi", "suicidati","exit"});
        getCommands().add(end);
        Command look = new Command(CommandType.LOOK_AT, "osserva");
        look.setAlias(new String[]{"guarda", "vedi", "trova", "cerca", "descrivi"});
        getCommands().add(look);
        Command pickup = new Command(CommandType.PICK_UP, "raccogli");
        pickup.setAlias(new String[]{"prendi"});
        getCommands().add(pickup);
        Command open = new Command(CommandType.OPEN, "apri");
        open.setAlias(new String[]{});
        getCommands().add(open);
        Command push = new Command(CommandType.PUSH, "premi");
        push.setAlias(new String[]{"spingi","attiva"});
        getCommands().add(push);
        //Rooms
        Room entranceHall = new Room(0, "Atrio", "Sei nell atrio, all'ingresso c'è un tappeto." +
                "A destra trovi un appendiabiti e di fronte c'è una cassettiera con sopra un piattino" +
                "e un guinzaglio per cani nero.", "Prima volta atrio");
        Room livingRoom = new Room(1, "Soggiorno", "Sei nel soggiorno, al centro della" +
                "stanza c'è un sofa e un tavolino con sopra un foglio e un servizio da the. Di fronte" +
                "appesa al muro ci sono una tv e un quadro e a sinistra nella stanza c'è un ampia libreria.", "Prima volta soggiorno");
        Room kitchen = new Room(2, "Cucina", "Sei in cucina, al centro c'è una grande isola " +
                "con un piano di lavoro e i fornelli. Il resto della cucina di fronte è composta da un frigorifero, " +
                "una credenza, un pensile e altri mobili. Sotto la cucina si trovano le ciotole del cibo e dell'acqua del cane, " +
                "mentre a sinistra della stanza c'è un calendario appeso.", "Prima volta cucina");
        Room walkinCloset = new Room(3, "Cabina armadio", "Sei nella cabina armadio, di fronte ci sono " +
                "due armadi, una scarpiera con diverse paia di scarpe e a terra sotto la scarpiera si trova una forchetta.", "Prima volta cabina armadio");
        Room bedRoom = new Room(4, "Camera da letto", "Sei nella camera da letto, sul muro a sinistra c'è un " +
                "letto matrimoniale a doppia piazza con due comodini e un quadro appeso sopra. Di fronte c'è una scrivania con " +
                "sopra un pc e una poltrona.", "Prima volta camera da letto");
        Room diningRoom = new Room(5, "Sala da pranzo", "Sei nella sala da pranzo, al centro c'è un grande tavolo con 4 sedie, " +
                "appesa al muro c'è una tv, a destra c'è un portavini e di fronte c'è una spaziosa finestra.", "Prima volta sala da pranzo");
        Room bathroom = new Room(6, "Bagno", "Sei nel bagno, di fronte c'è il lavabo, uno specchio con uno sportello " +
                "in cui ci sono varie medicine e la doccia. A destra invece ci sono il wc, il bidet e una finestra.", "Prima volta bagno");
        Room backyard = new Room(7, "Cortile", "Sei nel cortile, al centro c'è un tavolo con delle sedie. A destra c'è la cuccia del cane " +
                "mentre a sinistra ci sono un altalena e uno scivolo per bambini accanto a vari alberi.", "Prima volta cortile");
        entranceHall.setEast(kitchen);
        entranceHall.setNorth(livingRoom);
        livingRoom.setNorth(backyard);
        livingRoom.setEast(bedRoom);
        livingRoom.setSouth(entranceHall);
        kitchen.setWest(entranceHall);
        kitchen.setNorth(diningRoom);
        walkinCloset.setSouth(bedRoom);
        bedRoom.setNorth(walkinCloset);
        bedRoom.setEast(bathroom);
        bedRoom.setWest(livingRoom);
        diningRoom.setNorth(bathroom);
        diningRoom.setSouth(kitchen);
        bathroom.setSouth(diningRoom);
        bathroom.setWest(bedRoom);
        backyard.setSouth(livingRoom);
        getRooms().add(entranceHall);
        getRooms().add(livingRoom);
        getRooms().add(kitchen);
        getRooms().add(walkinCloset);
        getRooms().add(bedRoom);
        getRooms().add(diningRoom);
        getRooms().add(bathroom);
        getRooms().add(backyard);
        
        //obejcts of entrance hall
        AdvObject carpet = new AdvObject(1, "tappeto", "tappeto rosso");
        carpet.setAlias(new String[]{"zerbino"});
        carpet.setPush(true);
        entranceHall.getObjects().add(carpet);
        
        AdvObject hangers = new AdvObject(2, "appendiabiti", "appendiabiti in ferro");
        hangers.setAlias(new String[]{"appendiabiti"});
        carpet.setPickupable(false);
        entranceHall.getObjects().add(hangers);
        
        AdvObject dresser = new AdvObject(3, "cassettiera", "cassettiera in legno di quercia");
        dresser.setAlias(new String[]{});
        dresser.setOpenable(true);
        entranceHall.getObjects().add(dresser);
        
        AdvObject keySaucer = new AdvObject(4, "piattino", "piattino per le chiavi");
        keySaucer.setAlias(new String[]{});
        entranceHall.getObjects().add(keySaucer);
        
        AdvObject door = new AdvObject(5, "porta", "porta blindata");
        door.setAlias(new String[]{});
        door.setOpenable(true);
        door.setOpen(true);
        entranceHall.getObjects().add(door);
        
        AdvObject leash = new AdvObject(6, "guinzaglio", "guinzaglio nero in stoffa per cani");
        leash.setAlias(new String[]{});
        entranceHall.getObjects().add(leash);
        
        
        
        //objects of living room
        AdvObject paper = new AdvObject(7, "foglio", "foglio di carta");
        paper.setAlias(new String[]{});
        livingRoom.getObjects().add(paper);
        
        AdvObject sofa = new AdvObject(8, "divano", "divano in pelle");
        sofa.setAlias(new String[]{"sofa"});
        sofa.setPushable(true);
        sofa.setPickupable(false);
        livingRoom.getObjects().add(sofa);
        
        AdvObject sideTable = new AdvObject(9, "tavolino", "tavolino con base in vetro");
        sideTable.setAlias(new String[]{});
        sideTable.setPickupable(false);
        livingRoom.getObjects().add(sideTable);
        
        AdvObject teaService = new AdvObject(10, "servizio da the", "servizio da the in porcellana");
        teaService.setAlias(new String[]{});
        livingRoom.getObjects().add(teaService);
        
        AdvObject tv = new AdvObject(11, "tv", "smart tv da 50 pollici");
        tv.setAlias(new String[]{"televisione", "televisore"});
        tv.setPickupable(false);
        livingRoom.getObjects().add(tv);
        
        AdvObject picture1 = new AdvObject(12, "quadro", "quadro di picasso");
        picture1.setAlias(new String[]{"dipinto", "tela"});
        livingRoom.getObjects().add(picture1);
        
        AdvObject picture2 = new AdvObject(13, "quadro", "quadro di giotto");
        picture2.setAlias(new String[]{"dipinto", "tela"});
        livingRoom.getObjects().add(picture2);
        
        AdvObject library = new AdvObject(14, "libreria", "libreria in legno piena di libri");
        library.setAlias(new String[]{});
        livingRoom.getObjects().add(library);
        
        
        //objects of kitchen
        AdvObject kitchenIsland = new AdvObject(15, "isola", "isola con piano di lavoro");
        kitchenIsland.setAlias(new String[]{});
        kitchenIsland.setPickupable(false);
        kitchen.getObjects().add(kitchenIsland);
        
        AdvObject stoves = new AdvObject(16, "fornelli", "fornelli del piano cottura");
        stoves.setAlias(new String[]{});
        stoves.setPickupable(false);
        kitchen.getObjects().add(stoves);
        
        AdvObject waterBowl = new AdvObject(17, "ciotola", "ciotola dell'acqua per il cane");
        waterBowl.setAlias(new String[]{});
        kitchen.getObjects().add(waterBowl);
        
        AdvObject foodBowl = new AdvObject(18, "ciotola", "ciotola del cibo per il cane");
        foodBowl.setAlias(new String[]{});
        kitchen.getObjects().add(foodBowl);
        
        AdvObject calendar = new AdvObject(19, "calendario", "calendario anno 2023");
        calendar.setAlias(new String[]{});
        kitchen.getObjects().add(calendar);
        
        AdvObject fridge = new AdvObject(20, "frigorifero", "frigorifero con freezer integrato");
        fridge.setAlias(new String[]{"frigo"});
        fridge.setOpenable(true);
        kitchen.getObjects().add(fridge);
        
        AdvObject wallUnit = new AdvObject(21, "pensile", "pensile con cibo");
        wallUnit.setAlias(new String[]{});
        wallUnit.setOpenable(true);
        kitchen.getObjects().add(wallUnit);
        
        AdvObject sideboard = new AdvObject(22, "credenza", "credenza");
        sideboard.setAlias(new String[]{});
        sideboard.setOpenable(true);
        kitchen.getObjects().add(sideboard);
        
        
        
        //objects of walkin closet
        AdvObject fork = new AdvObject(23, "forchetta", "forchetta");
        fork.setAlias(new String[]{});
        walkinCloset.getObjects().add(fork);
        
        AdvObject book = new AdvObject(24, "libro", "libro di George Owell");
        book.setAlias(new String[]{});
        book.setOpenable(true);
        walkinCloset.getObjects().add(book);
        
        AdvObject skirting = new AdvObject(25, "battiscopa", "battiscopa con apertura");
        skirting.setAlias(new String[]{});
        skirting.setPickupable(false);
        walkinCloset.getObjects().add(skirting); 
        
        AdvObject closet1 = new AdvObject(26, "armadio estivo", "armadio per guardaroba estivo");
        closet1.setAlias(new String[]{});
        closet1.setOpenable(true);
        walkinCloset.getObjects().add(closet1);
        
        AdvObject closet2 = new AdvObject(27, "armadio", "armadio per guardaroba invernale");
        closet2.setAlias(new String[]{});
        closet2.setOpenable(true);
        walkinCloset.getObjects().add(closet2);
        
        AdvObject shoes = new AdvObject(28, "scarpe", "ci sono 10 paia di scarpe diverse");
        shoes.setAlias(new String[]{"stivali", "tacchi"});
        shoes.setPickupable(false);
        walkinCloset.getObjects().add(book);
        
        
        
        //objects of bedroom
        AdvObject bed = new AdvObject(29, "letto", "letto matrimoniale a doppia piazza");
        bed.setAlias(new String[]{});
        bed.setPickupable(false);
        bedRoom.getObjects().add(bed);
        
        AdvObject nightstand1 = new AdvObject(30, "comodino", "comodino dal lato del marito");
        nightstand1.setAlias(new String[]{"comò"});
        nightstand1.setOpenable(true);
        bedRoom.getObjects().add(nightstand1);
        
        AdvObject nightstand2 = new AdvObject(31, "comodino", "comodino dal lato della moglie");
        nightstand2.setAlias(new String[]{"comò"});
        nightstand1.setOpenable(true);
        bedRoom.getObjects().add(nightstand2);
        
        AdvObject picture3 = new AdvObject(32, "quadro", "quadro camera da letto situato sopra il letto matrimoniale");
        picture3.setAlias(new String[]{"dipinto", "tela"});
        bedRoom.getObjects().add(picture3);
        
        AdvObject armchair = new AdvObject(33, "poltrona", "poltrona in pelle situata all angolo del letto matrimoniale");
        armchair.setAlias(new String[]{});
        armchair.setPickupable(false);
        bedRoom.getObjects().add(armchair);
        
        AdvObject desk = new AdvObject(34, "scrivania", "scrivania per il pc");
        desk.setAlias(new String[]{});
        desk.setPickupable(false);
        bedRoom.getObjects().add(desk);
        
        AdvObject pc = new AdvObject(35, "pc", "pc fisso");
        pc.setAlias(new String[]{"computer"});
        bedRoom.getObjects().add(pc);
        
        AdvObject backyardDoor = new AdvObject(36, "porta finestra", "porta finestra in vetro che porta al cortile");
        backyardDoor.setAlias(new String[]{"porta"});
        backyardDoor.setOpenable(true);
        backyardDoor.setPickupable(false);
        bedRoom.getObjects().add(backyardDoor);
        
        
        
        //objects of dining room
        AdvObject table = new AdvObject(37, "tavolo", "tavolo da pranzo in legno");
        table.setAlias(new String[]{});
        table.setPickupable(false);
        diningRoom.getObjects().add(table);
        
        AdvObject chair = new AdvObject(38, "sedia", "ci sono 4 sedie");
        chair.setAlias(new String[]{});
        chair.setPickupable(false);
        diningRoom.getObjects().add(chair);
        
        AdvObject tv2 = new AdvObject(39, "tv", "tv sala da pranzo");
        tv2.setAlias(new String[]{"televisione", "televisore"});
        tv2.setPickupable(false);
        diningRoom.getObjects().add(tv2);
        
        AdvObject window = new AdvObject(40, "finestra", "finestra");
        window.setAlias(new String[]{});
        window.setOpenable(true);
        diningRoom.getObjects().add(window);
        
        AdvObject wineRack = new AdvObject(41, "porta vini", "porta vini piena di vini pregiati");
        wineRack.setAlias(new String[]{"cantina"});
        diningRoom.getObjects().add(wineRack);
        
        
        
        //objects of bathroom
        AdvObjectContainer mirror = new AdvObjectContainer(42, "specchio", "specchio con sportello");
        mirror.setAlias(new String[]{});
        mirror.setOpenable(true);
        bathroom.getObjects().add(mirror);
        
        AdvObject washbasin = new AdvObject(43, "lavabo", "lavabo doppio");
        washbasin.setAlias(new String[]{"lavandino"});
        washbasin.setPickupable(false);
        bathroom.getObjects().add(washbasin);
        
        AdvObject wc = new AdvObject(44, "wc", "water closet");
        wc.setAlias(new String[]{"water", "gabinetto"});
        wc.setPickupable(false);
        bathroom.getObjects().add(wc);
        
        AdvObject bidet = new AdvObject(45, "bidet", "bidet");
        bidet.setAlias(new String[]{});
        bidet.setPickupable(false);
        bathroom.getObjects().add(bidet);
        
        AdvObject shower = new AdvObject(46, "doccia", "doccia");
        shower.setAlias(new String[]{});
        shower.setPickupable(false);
        bathroom.getObjects().add(shower);
        
        AdvObject medicines = new AdvObject(47, "medicine", "medicine");
        medicines.setAlias(new String[]{"compresse", "pillole", "bustine"});
        mirror.add(medicines);
        
        AdvObject window2 = new AdvObject(48, "finestra", "finestra del bagno");
        window2.setAlias(new String[]{});
        window2.setOpenable(true);
        bathroom.getObjects().add(window2);
        
        
        
        //objects of backyard
        AdvObject backyardTable = new AdvObject(49, "tavolo", "tavolo del cortile");
        backyardTable.setAlias(new String[]{});
        backyardTable.setPickupable(false);
        backyard.getObjects().add(backyardTable);
        
        AdvObject backyardChair = new AdvObject(50, "sedia", "ci sono 2 sedie");
        backyardChair.setAlias(new String[]{});
        backyardChair.setPickupable(false);
        backyard.getObjects().add(backyardChair);
        
        AdvObject kennel = new AdvObject(51, "cuccia", "cuccia del cane");
        kennel.setAlias(new String[]{});
        backyard.getObjects().add(kennel);
        
        AdvObject swing = new AdvObject(52, "altalena", "altalena");
        swing.setAlias(new String[]{});
        swing.setPickupable(false);
        backyard.getObjects().add(swing);
        
        AdvObject slide = new AdvObject(53, "scivolo", "scivolo");
        slide.setAlias(new String[]{});
        slide.setPickupable(false);
        backyard.getObjects().add(slide);
        
        AdvObject woods = new AdvObject(54, "alberi", "ci sono 8 alberi");
        woods.setAlias(new String[]{});
        woods.setPickupable(false);
        backyard.getObjects().add(woods);
        //set starting room
        setCurrentRoom(bedRoom);
    }

    public void firstDescription(PrintStream out){
        if (getCurrentRoom().isFirstTime()) {
            getCurrentRoom().setFirstTime(false);
            out.println(getCurrentRoom().getDescriptionFirstTime());
        }
    }

    @Override
    public void nextMove(ParserOutput p, PrintStream out) {
        if (p.getCommand() == null) {
            out.println("Non ho capito cosa devo fare! Prova con un altro comando.");
        } else {
            //move
            boolean noroom = false;
            boolean move = false;
            if (p.getCommand().getType() == CommandType.NORD) {
                if (getCurrentRoom().getNorth() != null) {
                    setCurrentRoom(getCurrentRoom().getNorth());
                    firstDescription(out);
                    move = true;
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.SOUTH) {
                if (getCurrentRoom().getSouth() != null) {
                    setCurrentRoom(getCurrentRoom().getSouth());
                    firstDescription(out);
                    move = true;
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.EAST) {
                if (getCurrentRoom().getEast() != null) {
                    setCurrentRoom(getCurrentRoom().getEast());
                    firstDescription(out);
                    move = true;
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.WEST) {
                if (getCurrentRoom().getWest() != null) {
                    setCurrentRoom(getCurrentRoom().getWest());
                    firstDescription(out);
                    move = true;
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.INVENTORY) {
                out.println("Nel tuo inventario ci sono:");
                for (AdvObject o : getInventory()) {
                    out.println(o.getName() + ": " + o.getDescription());
                }
            } else if (p.getCommand().getType() == CommandType.LOOK_AT) {
                if (getCurrentRoom().getLook() != null) {
                    out.println(getCurrentRoom().getLook());
                } else {
                    out.println("Non c'è niente di interessante qui.");
                }
            } else if (p.getCommand().getType() == CommandType.PICK_UP) {
                if (p.getObject() != null) {
                    if (p.getObject().isPickupable()) {
                        getInventory().add(p.getObject());
                        getCurrentRoom().getObjects().remove(p.getObject());
                        out.println("Hai raccolto: " + p.getObject().getDescription());
                    } else {
                        out.println("Non puoi raccogliere questo oggetto.");
                    }
                } else {
                    out.println("Non c'è niente da raccogliere qui.");
                }
            } else if (p.getCommand().getType() == CommandType.OPEN) {
                /*ATTENZIONE: quando un oggetto contenitore viene aperto, tutti gli oggetti contenuti
                * vengongo inseriti nella stanza o nell'inventario a seconda di dove si trova l'oggetto contenitore.
                * Potrebbe non esssere la soluzione ottimale.
                 */
                if (p.getObject() == null && p.getInvObject() == null) {
                    out.println("Non c'è niente da aprire qui.");
                } else {
                    if (p.getObject() != null) {
                        if (p.getObject().isOpenable() && p.getObject().isOpen() == false) {
                            if (p.getObject() instanceof AdvObjectContainer) {
                                out.println("Hai aperto: " + p.getObject().getName());
                                AdvObjectContainer c = (AdvObjectContainer) p.getObject();
                                if (!c.getList().isEmpty()) {
                                    out.print(c.getName() + " contiene:");
                                    Iterator<AdvObject> it = c.getList().iterator();
                                    while (it.hasNext()) {
                                        AdvObject next = it.next();
                                        getCurrentRoom().getObjects().add(next);
                                        out.print(" " + next.getName());
                                        it.remove();
                                    }
                                    out.println();
                                }
                            } else {
                                out.println("Hai aperto: " + p.getObject().getName());
                                p.getObject().setOpen(true);
                            }
                        } else {
                            out.println("Non puoi aprire questo oggetto.");
                        }
                    }
                    if (p.getInvObject() != null) {
                        if (p.getInvObject().isOpenable() && p.getInvObject().isOpen() == false) {
                            if (p.getInvObject() instanceof AdvObjectContainer) {
                                AdvObjectContainer c = (AdvObjectContainer) p.getInvObject();
                                if (!c.getList().isEmpty()) {
                                    out.print(c.getName() + " contiene:");
                                    Iterator<AdvObject> it = c.getList().iterator();
                                    while (it.hasNext()) {
                                        AdvObject next = it.next();
                                        getInventory().add(next);
                                        out.print(" " + next.getName());
                                        it.remove();
                                    }
                                    out.println();
                                }
                            } else {
                                p.getInvObject().setOpen(true);
                            }
                            out.println("Hai aperto nel tuo inventario: " + p.getInvObject().getName());
                        } else {
                            out.println("Non puoi aprire questo oggetto.");
                        }
                    }
                }
            } else if (p.getCommand().getType() == CommandType.PUSH) {
                //ricerca oggetti pushabili
                if (p.getObject() != null && p.getObject().isPushable()) {
                    out.println("Hai premuto: " + p.getObject().getName());
                    if (p.getObject().getId() == 3) {
                        end(out);
                    }
                } else if (p.getInvObject() != null && p.getInvObject().isPushable()) {
                    out.println("Hai premuto: " + p.getInvObject().getName());
                    if (p.getInvObject().getId() == 3) {
                        end(out);
                    }
                } else {
                    out.println("Non ci sono oggetti che puoi premere qui.");
                }
            }
            if (noroom) {
                out.println("Da quella parte non si può andare c'è un muro!\nNon hai ancora acquisito i poteri per oltrepassare i muri...");
            } else if (move) {
                out.println(getCurrentRoom().getName());
                out.println("================================================");
                out.println(getCurrentRoom().getDescription());
            }
        }
    }

    private void end(PrintStream out) {
        out.println("Premi il pulsante del giocattolo e in seguito ad una forte esplosione la tua casa prende fuoco...\ntu e tuoi famigliari cercate invano di salvarvi e venite avvolti dalle fiamme...\nè stata una morte CALOROSA...addio!");
        System.exit(0);
    }
}
