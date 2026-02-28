/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package core;

import graphicker.Kolor;
import static graphicker.MageCons.printOn;
import static graphicker.MageCons.toImage;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author rash4
 */
@SuppressWarnings("StaticNonFinalUsedInInitialization")//WOW
public final class Materializing {
    private Materializing(){}
    private final static int SQR = 64;
    private final static int SID = 256;
    private static int MAX;
    private static int shldx = -6;
//    private final static Lock SHL_LOCK = new Lock();
    private static BufferedImage ECON, FOOD, ROCK, SPIRIT;
    private static BufferedImage[] ITEMS, DISPLAY_SHELF, BALL_SPRITE;
    private final static double[][] FR = {
        {0.00, 1.20, 0.80},
        {0.10, 1.10, 0.90},
        {0.25, 0.95, 1.05},
        {0.45, 0.92, 1.08},
        {0.70, 0.97, 1.03},
        {1.00, 1.00, 1.00},
        {0.70, 0.97, 1.03},
        {0.45, 0.92, 1.08},
        {0.25, 0.95, 1.05},
        {0.10, 1.10, 0.90},
        {0.00, 1.20, 0.80}
    };
    public static int offBoundery(){return MAX;}
    private static BufferedImage makeBallSprite(int fi) {
        return toImage(() -> {
            final var maho = new int[SID][SID];
            double yNorm = FR[fi][0];
            double sx    = FR[fi][1];
            double sy    = FR[fi][2];
            double w = SQR * sx;
            double h = SQR * sy;
            double x = (SQR * 2D) - w / 2D;
            double y = ((SID * 0.9D) - h) - (yNorm * SQR);
            Ellipse2D.Double e = new Ellipse2D.Double(x, y, w, h);
            for (int row = 0; row < SID; row++) {
                for (int col = 0; col < SID; col++) {
                    if (e.contains(col + 0.5, row + 0.5)) {
                        maho[row][col] = Kolor.getInt(
                            Kolor.kolos(220, 20),
                            Kolor.kolos(220, 20),
                            Kolor.kolos(220, 20)
                        );
                    }
                }
            }
            return maho;
        });
    }
    @SuppressWarnings("unused")
    private static void imagesKitchen(){
        final int cen = SQR*2, haf = SQR/2, qua = haf/2, dub = qua/2, sub = dub/2;
        final Color skino = new Color(240, 185, 130, 255),
                purr = new Color(155, 55, 255, 255);
        Area crescent = new Area(new Ellipse2D.Float(0, 0, SID, SID));
        final var shiny = toImage(()->{
            var grid = new int[SID][SID];
            for(int row = 0; row < SID; row++){
                for(int col = 0; col < SID; col++){
                    final int max = Math.max(col, row), min = Math.min(col, row);
                    if(crescent.contains(col, row)){
                        grid[row][col] = Kolor.getInt(20, Kolor.kolon(max), Kolor.kolor(min, max));
                    }else grid[row][col] = 0;
                }
            }
            return grid;
        });
        crescent.subtract(new Area(new Ellipse2D.Float(SID / dub, - SID / dub, SID, SID)));
        final var moony = toImage(()->{
            var grid = new int[SID][SID];
            for(int row = 0; row < SID; row++){
                for(int col = 0; col < SID; col++){
                    final int max = Math.max(col, row), min = Math.min(col, row);
                    if(crescent.contains(col, row)){
                        grid[row][col] = Kolor.getInt(20, Kolor.kolon(max), Kolor.kolor(min, max));
                    }
                    else grid[row][col] = 0;// Kolor.getInt(180, 120, Kolor.kolon(100));
                }
            }
            return grid;
        });
        Area ex = new Area(crescent.createTransformedArea(AffineTransform.getTranslateInstance(qua, 0)));
        var mir = ex.createTransformedArea(AffineTransform.getScaleInstance(-1, 1));
        ex.add(mir.createTransformedArea(AffineTransform.getTranslateInstance(SID, 0)));
        final var oroRee = new int[SID][SID];
        final var bush = toImage(()->{
            final int saba = 7*qua, sene = saba + haf;
            for(int row = 0; row < SID; row++){
                for(int col = 0; col < SID; col++){
                    boolean edge = ex.contains(col, row) && (
                        !ex.contains(col-1, row) ||
                        !ex.contains(col+1, row) ||
                        !ex.contains(col, row-1) ||
                        !ex.contains(col, row+1));
                    if (edge) {//cross's outline
                        oroRee[row][col] = Kolor.getInt(
                            60,
                            Kolor.kolon(180),
                            Kolor.kolor(40, 80)
                        );
                    } else if(ex.contains(col, row)){//cross's inline
                        oroRee[row][col] = Kolor.getInt(
                            30,
                            Kolor.kolon(120),
                            Kolor.kolor(20, 60)
                        );
                    } else if(!ex.contains(col, row) && (//pole XD
                            col >= saba && col <= sene &&
                            row >= haf)){
                        oroRee[row][col] = Kolor.getInt(
                            Kolor.kolor(140, 165), 30, Kolor.kolor(20, 33));
                    }
                }
            }
            return oroRee;
        });
        final var cocoTree = toImage(()->{
            final var coto = new int[SID][SID];
            final int lasro = SID - 1;
            for(int row = 0; row < SID; row++){
                System.arraycopy(oroRee[lasro - row], 0, coto[row], 0, SID);
            }
            return coto;
        });
        final var texturedGradient = toImage(()->{
            final var pers = new int[SID][SID];
            for(int row = 0; row < SID; row++){
                final int baser, range, bah = SID - qua;
                if(row < dub){
                    baser = bah;
                    range = dub;
                }else if(row > bah){
                    baser = 0;
                    range = sub;
                }else {
                    baser = SID - (row% SID) - qua;
                    range = qua;
                }
                for(int col = 0; col < SID; col++){
                    pers[row][col] = Kolor.getInt(
                            Kolor.kolor(60, 70),
                            Kolor.kolor(baser, baser + range),
                            Kolor.kolor(200, 210)
                    );
                }
            }
            return pers;
        });
        final var stone = toImage(() -> {
            final int[][] meiho = new int[SID][SID];
            final float cx = SID / 2f, cy = SID / 2f;
            final float radius = SID / 2f;
            // create a noob jagged stone path manually
            final Path2D.Float stonePath = new Path2D.Float();
            stonePath.moveTo(cx - radius * 0.6f, cy - radius * 0.8f);  // line 1
            stonePath.lineTo(cx + radius * 0.5f, cy - radius * 0.7f);  // line 2
            stonePath.lineTo(cx + radius * 0.8f, cy - radius * 0.2f);  // line 3
            stonePath.lineTo(cx + radius * 0.6f, cy + radius * 0.4f);  // line 4
            stonePath.lineTo(cx + radius * 0.1f, cy + radius * 0.8f);  // line 5
            stonePath.lineTo(cx - radius * 0.5f, cy + radius * 0.6f);  // line 6
            stonePath.lineTo(cx - radius * 0.8f, cy + radius * 0.2f);  // line 7
            stonePath.lineTo(cx - radius * 0.7f, cy - radius * 0.3f);  // line 8
            stonePath.closePath();
            // fill pixels inside the path
            final Area area = new Area(stonePath);
            for (int row = 0; row < SID; row++) {
                for (int col = 0; col < SID; col++) {
                    if (area.contains(col, row)) {
                        int gray = Kolor.kolor(100, 180);
                        meiho[row][col] = Kolor.getInt(gray, gray, gray);
                    }
                }
            }
            return meiho;
        });
        final var fortress = printOn(SID, SID, grr -> {
            // Example scales for back -> front
            float[] scales = {0.4f, 0.4f, 0.6f};
            // Example positions for back → front
            int[][] positions = {
                {SID / 4, haf},  // back-left
                {(int)(SID / 1.76), dub},  // back-right
                {cen - haf, SQR} // front-center (biggest)
            };
            for (int i = 0; i < 3; i++) {
                int treeWidth = (int)(SID * scales[i]);
                int treeHeight = (int)(SID * scales[i]);
                // drawImage(Image, x, y, width, height, null)
                grr.drawImage(cocoTree, 
                              positions[i][0], positions[i][1], 
                              treeWidth, treeHeight, 
                              null);
            }
        });
        final var stoneCluster = printOn(SID, SID, grr -> {
            // Example scales: back -> front (top is slightly smaller)
            float[] scales = {0.6f, 0.7f, 0.7f};
            // Example positions for back -> front (top, bottom-left, bottom-right)
            int[][] positions = {
                {SID / 2 - (int)(SID * scales[0] / 2), SID / 8}, // top (tiny smaller)
                {SID / 4, SID / 2},                              // bottom-left
                {SID / 2, SID / 2}                               // bottom-right
            };
            for (int i = 0; i < 3; i++) {
                int stoneWidth = (int)(SID * scales[i]);
                int stoneHeight = (int)(SID * scales[i]);
                grr.drawImage(stone, 
                              positions[i][0], positions[i][1], 
                              stoneWidth, stoneHeight, 
                              null);
            }
        });
        final var heart = printOn(SID, SID, grr->{
            final float str = 200F;
            final Area half = new Area(new Ellipse2D.Float(str, str, 300F, 500F));
            final var tr = AffineTransform.getRotateInstance(-Math.toRadians(45), str + 150, str + 250);
            half.transform(tr);
            half.subtract(new Area(new Rectangle((int)str + 150, 0, 300, 900)));
            half.transform(AffineTransform.getShearInstance(0, 0.1));
            final var hearty = new Area(half);
            float pivotX = str + 150 + 150;
            AffineTransform mirror = new AffineTransform();
            mirror.translate(pivotX * 2, 0);
            mirror.scale(-1, 1);
            mirror.translate(300, 0);
            half.transform(mirror);
            hearty.add(half);
            final var siz = hearty.getBounds().getSize();
            final float fx = (float)SID/((float)siz.width + 18F), fy = (float)SID/((float)siz.height + 18F);
            hearty.transform(AffineTransform.getScaleInstance(fx, fy));
            final var nes = hearty.getBounds().getLocation();
            hearty.transform(AffineTransform.getTranslateInstance(-nes.x + 6, -nes.y + 5));
            grr.setColor(Color.RED);
            grr.fill(hearty);
            grr.setColor(Color.ORANGE);
            grr.setStroke(new BasicStroke(8F));
            grr.draw(hearty);
//            grr.setStroke(new BasicStroke(2));
//            grr.setColor(Color.BLACK);
//            grr.drawRoundRect(1, 0, SID - 2, SID - 1, cen, cen);
        });
        final var circul = toImage(()->{
            final var grud = new int[SID][SID];
            final int cx = cen, cy = cen;
            final int clry = purr.getRGB(), utry = skino.getRGB();
            final int thky = 8;
            final int r = cen;
            final int t = thky;
            final int r2 = r * r;
            final int inner = (r - t) * (r - t);
//            final int outer = (r + t) * (r + t);
            for(int row = 0; row < SID; row++){
                for(int col = 0; col < SID; col++){
                    final int dsx = (col - cx)*(col - cx),
                            dsy = (row - cy)*(row - cy),
                            drdr = dsy + dsx;
                    if (drdr >= inner && drdr <= r2) grud[row][col] = utry;
                    else if (drdr <= r2) grud[row][col] = clry;
                    else grud[row][col] = 0;
                }
            }
            return grud;
        });
//        final var hexaStar = toImage(()->{
//            final int rows = 64, cols = 64;
//            final var grid = new int[rows][cols];
//            var poly = new elmath.Vector(32f, 0f); // top center
//            final var path = new Path2D.Float();
//            path.moveTo(poly.x(), poly.y()); // tip of arrow
//            poly = poly.addX(32F).addY(48F);          // bottom right
//            path.lineTo(poly.x(), poly.y());
//            poly = poly.addX(-64F).addY(0F);          // bottom left
//            path.lineTo(poly.x(), poly.y());
//            poly = poly.addX(32F).addY(-48F);         // back to tip (closing)
//            path.lineTo(poly.x(), poly.y());
//            path.closePath();
//            final var spit = new Area(path);
//            final var tran = new AffineTransform();
//            tran.translate(32, 32);
//            tran.scale(1, -1);
//            tran.translate(-32, -32);
//            spit.add(spit.createTransformedArea(tran));
//            for(int row = 0; row < rows; row++){
//                for(int col = 0; col < cols; col++){
//                    if(spit.contains(col, row))
//                        grid[row][col] = Kolor.getInt(230, Kolor.kolor(230, 238), 35);
//                    else grid[row][col] = 0;
//                }
//            }
//            return grid;
//        });
        final var rock = toImage(()->{
            final int rows = 64, cols = 64, half = 64/2, /*quar = 64/4,*/ eith = 64/8;
            final var grid = new int[rows][cols];
            for(int row = 0; row < rows; row++){
                for(int col = 0; col < cols; col++){
                    if(row > eith && row < half){
                        final int str = row - eith;
                        if(col >= (half - (str * 1.4F)/2f) && col <= (half + (str * 1.4F)/2F)){
                            grid[row][col] = Kolor.getInt(200, Kolor.kolor(180 + str, 200 + str), 190);
                        }
                    }else if(row >= half){
                        final int str = row - half + eith;
                        if(col >= str && col <= 64 - str){
                            grid[row][col] = Kolor.getInt(180, Kolor.kolor(180 + str, 200 + str), 185);
                        }
                    }else grid[row][col] = 0;
                }
            }
            return grid;
        });
//        final var badgyx = printOn(SID, SID, grr->{
//            grr.drawImage(circul, dub, dub, SID - qua, SID - qua, null);
//            grr.drawImage(hexaStar, 0, 0, SID, SID, null);
//        });
//        ECON = badgyx;
        FOOD = heart;// for now
        ROCK = circul;// for now
//        SPIRIT = badgyx;
        ITEMS = new BufferedImage[]{moony, 
            shiny, texturedGradient, bush, cocoTree, fortress, stone, stoneCluster, heart, circul, /*hexaStar,
            badgyx,*/ rock, 
        };
    }
    public static BufferedImage rock(){return ROCK;}
    public static BufferedImage food(){return FOOD;}
    public static BufferedImage spirit(){return SPIRIT;}
    public static BufferedImage getImage(int index){
        if(index >= MAX - 1)return ITEMS[MAX-1];
        else if(index <= 0) return ITEMS[0];
        else return ITEMS[index];
    }
    public static BufferedImage getSprite(int frame){
        int max = BALL_SPRITE.length;
        int i = frame % max;
        if (i < 0) i += max;
        return BALL_SPRITE[i];
    }
    public static void shuff(int i){
        if(i == 0) return;
        final var twis = DISPLAY_SHELF;
        final var main = ITEMS;
        final int shf = twis.length;
        shldx += i * shf; // increment or decrement by x6
        if(shldx >= 599 || shldx <= -599) shldx = 0; // avoid overflow.
        for(int j = 0; j < shf; j++){
            twis[j] = main[getDex(j)];
        }
//        synchronized(SHL_LOCK){
//        }
    }
    /**<p>Helper method for <b>empty-ring</b> like loop.</p>*/
    private static int getDex(int i){
        return ((shldx + i)%MAX + MAX)% MAX;
    }
    public static int sid(){return SID;}
//    public static Lock lockShelf(){return SHL_LOCK;}
    public static BufferedImage[] getShelf(){return DISPLAY_SHELF;}
    public static BufferedImage icon(){return ECON;}
    public static void preLoad(){
        DISPLAY_SHELF = new BufferedImage[6]; // display six images only.
        final int len = 11, x = SQR, y = (int)(SQR * 1.6D), w = SQR*2, h = SQR*2;
        BALL_SPRITE = new BufferedImage[len];
        for(int i = 0; i < len; i++){
            BALL_SPRITE[i] = makeBallSprite(i).getSubimage(x, y, w, h);
        }
        imagesKitchen();
        MAX = ITEMS.length;
        
        shuff(+1); // to populate it for the first time
        
    }
}