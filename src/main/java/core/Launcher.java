/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package core;

//import engine.Scene;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rash4
 */
public final class Launcher {
    private Launcher(){}
    /**(20 * 16) x40*/
    public final static Dimension STARTING_SIZE = new Dimension(800, 640);
    private final static Launcher CHAINER = new Launcher();
    private static int indexTracker = 0;
    private final static String TITLE = "MAWE!";
    /**
     * Divide-able -> mainly (2 to 16) but also...
     * <br>.                (1,  2,  3,  4,  5,  6,  7,  8, 9, 
     * <br>.                 10, 11, 12, 13, 14, 15, 16, 18, 
     * <br>.                 20, 21, 22, 24, 26, 28, 
     * <br>.                 30, 33, 35, 36, 39,
     * <br>.                 40, 42, 44, 45, 48, 52)
     */
    public final static int MAX_INDEX_ITERATOR = 720_720; // 720720
    public static int indicator = 0;
    public static float escalator = 1F;
    public static int fps = 60, ups = 120;
    
    public static void focus(){GAME.panel.requestFocusInWindow();}
    public static void repaint(){GAME.panel.repaint();}
    public static void dispose(){GAME.dispose();}
    public static void setConsole(){VIRTUAL_CONSOLE.setup(GAME.panel);}
    
    public static void setSceler(float factor){
        escalator = factor;
    }
//    public Launcher add(Scene member){return addScene(member);}
//    public static Launcher addScene(Scene<?> member){
//        if(member != null && currentScene != null){
//            SCENES.add(member);
//        }return CHAINER;
//    }
//    public static boolean addFirstScene(Scene firstMember){
//        if(SCENES.remove(Scene.ZERO)){
//            SCENES.add(0, firstMember);
//            launch(0);
//            return true;
//        }return false;
//    }
//    public static boolean removey(String scename){
//        final int len = SCENES.size();
//        if(scename == null || scename.isBlank()) return false;
//        for(int i = 0; i < len; i++){
//            final var target = SCENES.get(i);
//            if(target.name().equalsIgnoreCase(scename)){
//                // prevent removing current scene, the console for now.
//                if(currentScene == target)return false;
//                // for extra sefety
//                final boolean paused = !LOOP.isRunning();
//                if(!paused)LOOP.stops();
//                SCENES.remove(i);
//                if(!paused)LOOP.start();
//                return true;
//            }
//        }
//        return false;
//    }
//    public static Scene  launch(int move){
//        final int len = SCENES.size();
//        if(len < 1)return null;
//        final int really = ((indexTracker + move)%len + len)% len;
//        if(really == indexTracker)return currentScene;
//        final boolean running = LOOP.isRunning();
//        boolean success = false;
//        try{
//            if(running) LOOP.stops();
//            currentScene().onLeave(GAME.panel); // before updating the currentindex.
//            indexTracker = really;
//            currentScene = SCENES.get(really);
//            currentScene.onLoad(GAME.panel);
//            success = true;
//        }finally{
//            if(running && success) LOOP.start();
//        }
//        return currentScene;
//    }
    
    public static String title(){return TITLE;}
    public static Dimension size(){return GAME.panel.getSize();}
    public static Font font(){return FONT_GOTHIC;}
    
    public static Console console(){return VIRTUAL_CONSOLE;}
//    public static Scene currentScene(){return currentScene;}
//    
    private final static Font FONT_GOTHIC;
    private final static Console VIRTUAL_CONSOLE;
//    private static Scene<?> currentScene;
//    
    public final static Front GAME;
    public final static Looping LOOP;
//    public final static List<Scene<?>> SCENES;
    
    final static Commanding CMDR;
    
    static{
        FONT_GOTHIC = new Font("Showcard Gothic", Font.PLAIN, 16);
//        SCENES = new ArrayList<>();
        LOOP = new Looping();
//        SCENES.add(currentScene = Scene.ZERO); // ghost Scene -> for convenience
        GAME = new Front();
        CMDR = new Commanding();
        VIRTUAL_CONSOLE = new Console();
    }
}