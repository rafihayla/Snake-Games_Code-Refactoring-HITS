package SnakeGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

import SnakeGame.loadImages.lokasi;
import SnakeGame.loadImages.spot;

public class Board extends JPanel implements ActionListener {

    /**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    private final int ALL_DOTS = 900;
    private final int DELAY = 140;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;
    private int score = 0;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;
    public static boolean inPaused;
    

    private Timer timer;
    
    public Board() {
        inPaused = false;
        initBoard();
    }
    
    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        ambil.gambar();        
        initGame();
    }
 


    private void initGame() {

        dots = 3;

        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
        

        spot.location();


        timer = new Timer(DELAY, this);
        timer.start();

         
           
         
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
        
        if (inGame) {
        	
                if (inPaused) {
                    pauseGame(g);
        	}
            g.drawImage(loadImages.apple, lokasi.apple_x, lokasi.apple_y, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(loadImages.head, x[z], y[z], this);
                } else {
                    g.drawImage(loadImages.ball, x[z], y[z], this);
                }
            }
            
            g.drawImage(loadImages.bomb, lokasi.bomb_x, lokasi.bomb_y, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(loadImages.head, x[z], y[z], this);
                } else {
                    g.drawImage(loadImages.ball, x[z], y[z], this);
                }
                
            }

            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
        }        
    }

    
    private void gameOver(Graphics g) {
        
        String msg = "Game Over";
        String scoremsg= "Score: " + score;
        String restart = "Press SPACEBAR to restart";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, (B_HEIGHT * 2) /6);
        g.drawString(scoremsg, (B_WIDTH - metr.stringWidth(scoremsg)) / 2, B_HEIGHT / 2);
        g.drawString(restart, (B_WIDTH - metr.stringWidth(restart)) / 2, (B_HEIGHT * 2) / 3);
       
        }
        
    private void pauseGame (Graphics g) {
    	
    	String pause = "Pause";
    	String enter = "Press Enter to Continue";
    	
    	Font small = new Font("Helvetica", Font.BOLD, 14);
    	FontMetrics metr = getFontMetrics(small);
    	
    	g.setColor(Color.white);
    	g.setFont(small);
    	g.drawString(pause, (B_WIDTH - metr.stringWidth(pause)) / 2, (B_HEIGHT * 2) / 5);
    	g.drawString(enter, (B_WIDTH - metr.stringWidth(enter)) / 2, (B_HEIGHT * 2) / 4);
    }
    

    // When the Apple gets eaten, Snake becomes longer and add 1 score.
    private void checkApple() {

        if ((x[0] == lokasi.apple_x) && (y[0] == lokasi.apple_y)) {

            dots++;
            score++;
            
            spot.location();
        }
    
        // when the snake hits the bomb, the game is over
        if ((x[0] == lokasi.bomb_x) && (y[0] == lokasi.bomb_y)) { 
        
        	inGame = false;
            timer.stop();
        }

        }
        

    private void move() {

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= loadImages.DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += loadImages.DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= loadImages.DOT_SIZE;
        }

        if (downDirection) {
            y[0] += loadImages.DOT_SIZE;
        }
    }

    private void checkCollision() {

    	// to make snake can go through the border
        for (int z = dots; z > 0; z--) {

            if ((z >= 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            y[0] = 0;
        }

        if (y[0] < 0) {
            y[0] = B_HEIGHT;
        }

        if (x[0] >= B_WIDTH) {
            x[0] = 0;
        }

        if (x[0] < 0) {
            x[0] = B_WIDTH;
        }
        
        if (!inGame) {
            timer.stop();
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
        	
            int key = e.getKeyCode();

            handleDirectionKeys(key);
            handleSpecialKeys(key);
            
        }

        private void handleDirectionKeys(int key) {
        	
            if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
                handleLeftKey();
            }
            
            else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
                handleRightKey();
            }
            
            else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
                handleUpKey();
            }
            
            else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
                handleDownKey();
            }
            
        }

        private void handleSpecialKeys(int key) {
        	
            if (key == KeyEvent.VK_ESCAPE) {
                handleEscapeKey();
            }
            
            else if (key == KeyEvent.VK_ENTER) {
                handleEnterKey();
            }
            
            else if (key == KeyEvent.VK_R) {
                handleRKey();
            }
            
        }

        private void handleLeftKey() {
        	
            if (!rightDirection) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }
            
        }

        private void handleRightKey() {
        	
            if (!leftDirection) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }
            
        }

        private void handleUpKey() {
        	
            if (!downDirection) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
            
        }

        private void handleDownKey() {
        	
            if (!upDirection) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
            
        }

        private void handleEscapeKey() {
        	
            timer.stop();
            inPaused = true;
            repaint();
            
        }

        private void handleEnterKey() {
        	
            timer.start();
            inPaused = false;
            repaint();
            
        }

        private void handleRKey() {
        	
        	new Snake();
        	
        }
        
    }
    
}