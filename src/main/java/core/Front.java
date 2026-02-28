/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package core;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author rash4
 */
public final class Front implements Initializer{
    Front(){}
    
    public JPanel contentPane(){return this.panel;}
    public void setTitle(String name){this.frame.setTitle(name);}
    public void setIcon(BufferedImage ico){if(ico != null) this.frame.setIconImage(ico);}
    public void dispose(){this.frame.dispose();}
    
    private final class ZFrame extends JFrame{
        private ZFrame(){
            super.setTitle(Launcher.title());
            super.setContentPane(Front.this.panel);
            super.pack();
            super.setLocationRelativeTo(null);
            super.setResizable(true);
            super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            super.addWindowFocusListener(new WindowFocusListener(){
                @Override public void windowGainedFocus(WindowEvent e) {
                    Front.this.panel.requestFocusInWindow();
                }
                @Override public void windowLostFocus(WindowEvent e) {}
            });
        }
        @Override public void dispose(){
            Launcher.LOOP.stops();
            super.dispose();
        }
    }
    private final class ZPanel extends JPanel{
        private ZPanel(){
            super.setPreferredSize(Launcher.STARTING_SIZE);
            super.setLayout(null);
            super.addComponentListener(new ComponentListener(){
                @Override public void componentResized(ComponentEvent e) {
                    General.resize();
                }
                @Override public void componentMoved(ComponentEvent e) {}
                @Override public void componentShown(ComponentEvent e) {}
                @Override public void componentHidden(ComponentEvent e) {}
            });
            super.addKeyListener(new KeyListener(){
                @Override public void keyTyped(KeyEvent e) {}
                @Override public void keyReleased(KeyEvent e) {}
                @Override public void keyPressed(KeyEvent e) {
                    switch(e.getKeyCode()){
                        case KeyEvent.VK_ESCAPE -> Front.this.frame.dispose();
                        case KeyEvent.VK_LEFT -> {
//                            Launcher.launch(-1);
                            Front.this.panel.requestFocusInWindow();
                        }
                        case KeyEvent.VK_RIGHT -> {
//                            Launcher.launch(+1);
                            Front.this.panel.requestFocusInWindow();
                        }
                        case KeyEvent.VK_P -> Launcher.LOOP.toggle();
                    }
                }
            });
        }
        @Override protected void paintComponent(Graphics g){
            super.paintComponent(g);
            General.render((Graphics2D) g);
        }
    }
    
    private JFrame frame;
    JPanel panel;
    
    @Override public void initialize(){
        this.panel = new ZPanel();
        this.frame = new ZFrame();
        
        this.frame.setVisible(true);
    }
}