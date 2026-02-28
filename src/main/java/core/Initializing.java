/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package core;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static core.Commanding.parts;

/**
 *
 * @author rash4
 */
public final class Initializing {
    private Initializing(){}
    // maybe just in the preload call the initialize method.
//    public static void daPlay(){ // not finished at all
//        final var valley = new Valley("Game").initValley();
//    }
//    public static void animate(){
//        final Nums i = new Nums(0);
//        final var an = Scene.emptyScene("Animated");
//        an      .setPreLoader(an::setTitle)
//                .setRenders(grr->{
//                    grr.drawImage(Materializing.getSprite(i.value/4), 8, 64, null);
//                }).setUpdates(() -> {
//                    i.postIncrement();
//                })
//                .add();
//    }
//    public static void displayShelf(){
//        final var ds = Scene.emptyScene("Shelf");
//        ds
//                .setPreLoader(()->{
//                    ds.setTitle();
//                }).setKeys(new KeyAdapter(){
//                    @Override public void keyPressed(KeyEvent ke){
//                        switch(ke.getKeyCode()){
//                            case KeyEvent.VK_UP->{
//                                elfactory.Materializing.shuff(-1);
//                                Launcher.repaint();
//                            }
//                            case KeyEvent.VK_DOWN->{
//                                elfactory.Materializing.shuff(+1);
//                                Launcher.repaint();
//                            }
//                        }
//                    }
//                }).setRenders(grr->{
//                    final var slf = elfactory.Materializing.getShelf();
//                    synchronized(elfactory.Materializing.lockShelf()){
//                        final int mrg = 0, mx = slf.length,
//                                zis = elfactory.Materializing.sid();
//                        for(int i = 0; i < mx; i++){
//                            final int col = i%(mx/2), row = i/(mx/2);
//                            grr.drawImage(slf[i], 16 + ((zis + mrg) * col),
//                                    64 + ((zis + mrg) * row), 256, 256, null);
//                        }
//                    }
//                }).add();
//    }
    public static void commandSeries(){
        final var cons = Launcher.console();
        final var sayNothing = new Command(str->{
            cons.appendl(">> Brugh...!");
            return true;
        });
        final var hello = new Command(str->{
            cons.appendl(">> Hello.");
            return true;
        });
        final var wello = new Command(str->{
            final var prts = parts(str);
            cons.appendl(">> Welcome ~ " + ((prts[0].isBlank())? "niggar!" : prts[0]));
            return true;
        });
        final var say = new CommandTree();
        say.setCom("hello", hello).setCom("welcome", wello).setDirectCom(sayNothing);
        
        final var scl = new Command(str->{
            final var parts = Commanding.parts(str);
            String facto = null;
            for(var prt: parts){
                if(!prt.isBlank()) {
                    facto = prt;
                    break;
                }
            }
            if(facto == null){
                cons.appendl(">> scale command must pass a param.");
                return false;
            }
            try{
                Launcher.setSceler(Float.parseFloat(facto));
            }catch(NumberFormatException e){
                return false;
            }
            return true;
        });
        
        final var clear = new Command(str->{
            cons.clear();
            return true;
        });
        
//        final var remove = new Command(str->{
//            if(str == null || str.isBlank()){
//                Launcher.console().appendl(">> Scene name can't be empty/blank");
//            }
//            final String part = Commanding.parts(str)[0];
//            final boolean success = Launcher.removey(part);
//            if(success) Launcher.console().appendl(">> Scene " + part + " has been removed successfully.");
//            else Launcher.console().appendl(">> Scene " + part + " is currently active or doesn't exist.");
//            return success;
//        });
        
        final var git = new CommandTree();
        git.setDirectCom(new Command(str->{
            cons.appendl("""
                         >> Git is a list of commands, doesn't have a direct command...
                            --here's a list for the 'Git' commands list:
                         """);
            for(var ln : git.getList()){
                cons.appendl("  --" + ln);
            }
            return false;
        }));
        git.setCom("scaler", new Command(str->{
            cons.appendl("Current scale factor = "+Launcher.escalator);
            return true;
        }));
        // to the ROOT.
        Launcher.CMDR.addCommand(new Command(Launcher.CMDR::fullHelpList), "full");
        Launcher.CMDR.addCommand(new Command(Launcher.CMDR::getHelp), "help");
//        Launcher.CMDR.addCommand(remove, "remove");
        Launcher.CMDR.addCommand(say, "say");
        Launcher.CMDR.addCommand(clear, "clear");
        Launcher.CMDR.addCommand(scl, "scale");
        Launcher.CMDR.addCommand(git, "git");
        Launcher.console().setCommands(Launcher.CMDR);
    }
//    public static void debuqer(){
//        final Scene console = new Scene("Console");
//        console.setRenders(grr->{
//            final var siz = Launcher.size();
//            final int tel = 32;
//            grr.setColor(Color.ORANGE);
//            grr.fillRect(0, siz.height - tel, siz.width, tel);
//        }).
//                setUpdates(Scene.DEF_UPDATER).
//                setResizer(()->{Launcher.console().resizing(Launcher.size());}).
//                setPreLoader(()->{
//                    console.setTitle();
//                    Launcher.console().setStats(true);
//                }).
//                setPostLeave(()->{Launcher.console().setStats(false);});
//        Launcher.addFirstScene(console);
//    }
}