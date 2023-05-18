package SnakeGame;

import java.awt.Image;

import javax.swing.ImageIcon;





// Encapsulation.
public class loadImages {
	
    public static Image ball;
    public static Image apple;
    public static Image head;
    public static Image bomb;
    public final static int RAND_POS = 29;
    public final static int DOT_SIZE = 10;
    
    private static String dot = new StringBuffer("./src/Resource/dot.png").toString();
    private static String apel = new StringBuffer("./src/Resource/apple.png").toString();
    private static String kepala = new StringBuffer("./src/Resource/head.png").toString();
    private static String bom = new StringBuffer("./src/Resource/head.png").toString();
    
	public static void loadGambar() {

        ImageIcon iid = new ImageIcon(dot);
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon(apel);
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon(kepala);
        head = iih.getImage();
        
        ImageIcon iib = new ImageIcon(bom);
        bomb = iib.getImage();
    }

    static abstract class lokasi {
	
        protected static int apple_x;
        protected static int apple_y;
        protected static int bomb_x;
        protected static int bomb_y;
        
        protected static void locateApple() {

            // type casting: force to be in int type
            int r = (int) (Math.random() * loadImages.RAND_POS);
            apple_x = ((r * loadImages.DOT_SIZE));

            r = (int) (Math.random() * loadImages.RAND_POS);
            apple_y = ((r * loadImages.DOT_SIZE));
        }

        protected static void locateBomb() {
            int b = (int) (Math.random() * loadImages.RAND_POS);
            bomb_x = ((b * loadImages.DOT_SIZE));

            b = (int) (Math.random() * loadImages.RAND_POS);
            bomb_y = ((b * loadImages.DOT_SIZE));
        }
    }

    static class spot extends lokasi {
        public static void location() {
            locateApple();
            locateBomb();

            }
        
    }
    


}
