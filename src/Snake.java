import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;
import static javafx.scene.paint.Color.color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Snake extends javax.swing.JPanel implements ActionListener {

    int ancho = 605, alto = 610, xf, yf, eated = 0, play, scoreR = 0;
    Timer timer;
    JLabel score;
    char direction = 'd';
    boolean choque = false, repetida = false;
    int[] x = new int[30 * 30],
          y = new int[30 * 30];

    public Snake() {

        initComponents();
        setSize(ancho, alto);
        setBackground(Color.white);
        setFocusable(true);
        requestFocus();
        
        score = new JLabel();
        score.setBounds(10, 10, 100, 30);
        score.setText("SCORE: "+ scoreR);
        score.setFont(new Font("Digital-7", Font.BOLD, 14));
        score.setForeground(Color.black);
        score.setHorizontalAlignment(JLabel.CENTER);
        add(score);
        
        xf = new Random().nextInt(600 / 20) * 20;
        yf = new Random().nextInt(600 / 20) * 20;

        x[0] = 300;
        y[0] = 300;

        timer = new Timer(120, (ActionListener) this);
        timer.start();

    }

    public void paint(Graphics g) {
        
            super.paint(g);

            g.setColor(Color.green);
            for (int i = 0; i < eated + 1; i++) {
                g.fillRect(x[i], y[i], 20, 20);
            }

            g.setColor(Color.red);
            g.fillRect(xf, yf, 20, 20);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (direction != 'd') {
                    direction = 'u';
                }
                break;
            case KeyEvent.VK_DOWN:
                if (direction != 'u') {
                    direction = 'd';
                }
                break;
            case KeyEvent.VK_LEFT:
                if (direction != 'r') {
                    direction = 'l';
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != 'l') {
                    direction = 'r';
                }
                break;
        }
    }//GEN-LAST:event_formKeyPressed
    @Override
    public void actionPerformed(ActionEvent ae) {     
        score.setText("SCORE: "+ scoreR);
        for (int i = 1; i < eated+1; i++) {
                if (x[0] == x[i] && y[0] == y[i]) {
                    choque = true;
                }     
            } 
        for (int i = eated + 1; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];       
        }
        if ((x[0] < ancho-5 && x[0] > 5) && (y[0] < alto-10 && y[0] > 5)) {
            if (!choque) {        
                switch (direction) {

                    case 'd':
                        y[0] += 20;
                        break;

                    case 'u':
                        y[0] -= 20;
                        break;

                    case 'l':
                        x[0] -= 20;
                        break;

                    case 'r':
                        x[0] += 20;
                        break;
                }
                if (x[0] == xf && y[0] == yf) {
                    eated++;
                    scoreR++;
                    do {
                        xf = new Random().nextInt(600 / 20) * 20;
                        yf = new Random().nextInt(600 / 20) * 20;
                        
                            if (eated >= 1) {
                                for (int i = 0; i <= eated; i++) {
                                    if (xf == x[i] && yf == y[i]) {
                                        repetida = true;  
                                    }  
                                }
                              if (xf < 20 || xf > 580)repetida = true;   
                            }  
                    } while (repetida);
                }               
                repaint();
            }
        }else if(y[0] >= 240 && y[0] <=360){
            if(x[0] <= 5 || x[0] >= 100){
                if(direction == 'r'){
                    x[0] = 20;
                }else x[0] = 580;
                repaint();
            }
            }else choque = true;
        if (choque) {
            JOptionPane.showMessageDialog(this, "Perdiste"+"\n"+ "Score: "+scoreR,null, JOptionPane.DEFAULT_OPTION);
            play = JOptionPane.showConfirmDialog(this, "Jugar otra vez", null, JOptionPane.DEFAULT_OPTION);
            if (play == 1) {
                System.exit(0);
            }
            x[0] = 300;
            y[0] = 300;
            eated = 0;
            choque = false;
            scoreR = 0;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
