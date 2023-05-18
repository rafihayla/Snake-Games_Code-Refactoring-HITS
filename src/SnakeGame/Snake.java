package SnakeGame;

import java.awt.EventQueue;
import java.awt.event.*;

import javax.sound.sampled.Clip;
import javax.swing.*;

public class Snake extends JFrame {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    static Clip lagu;
    
	public Snake() {

        initUI(); 
        
        // on space key close frame to restart the game, this can be considered as Abstract
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "Cancel"); //$NON-NLS-1$
        getRootPane().getActionMap().put("Cancel", new AbstractAction()
        { 

            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
                new Snake();
            }
       });

    }

    private void initUI() {

        add(new Board());

        setResizable(false);
        pack();
        this.setVisible(true);
        

        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Snake();
            ex.setVisible(true);
            lagu = ambil.LoadSound();
            lagu.loop(Clip.LOOP_CONTINUOUSLY);
        
            
        });

        
    }

    }