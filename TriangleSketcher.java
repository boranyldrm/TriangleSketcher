

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Boran1 on 23.3.2016.
 */

public class TriangleSketcher extends JPanel {

    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setTitle("Triangle Sketcher");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.add(new TriangleSketcher());
        frame.pack();
        frame.setVisible(true);
    }

    //properties
    MouseListener mouseClick = new DotListener();
    protected int numClicks = 0;
    protected int coordinateX;
    protected int coordinateY;
    protected int secondCoordinateX;
    protected int secondCoordinateY;
    protected int thirdCoordinateX;
    protected int thirdCoordinateY;
    protected TriangleSketcher restartSketcher;

    //constructor
    public TriangleSketcher(){
        setLayout(new FlowLayout());
        setBackground(Color.CYAN);
        setPreferredSize(new Dimension(500, 500));
        addMouseListener(mouseClick);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        if (numClicks == 1)
            g.drawLine(coordinateX, coordinateY, coordinateX + 1, coordinateY + 1);
        else if (numClicks == 2)
            g.drawLine(coordinateX + 1, coordinateY + 1, secondCoordinateX, secondCoordinateY);
        else if (numClicks == 3) {
            g.drawLine(coordinateX + 1, coordinateY + 1, secondCoordinateX, secondCoordinateY);
            g.drawLine(secondCoordinateX, secondCoordinateY, thirdCoordinateX, thirdCoordinateY );
            g.drawLine(thirdCoordinateX, thirdCoordinateY, coordinateX, coordinateY);
        }
    }

    public void restart(){
        restartSketcher = new TriangleSketcher();
        removeAll();
        repaint();
    }

    //mouseListener class
    private class DotListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e ){
            numClicks++;
            if (numClicks == 1) {
                coordinateX = e.getX();
                coordinateY = e.getY();
                repaint();
            }
            else if (numClicks == 2){
                secondCoordinateX = e.getX();
                secondCoordinateY = e.getY();
                repaint();
            }
            else if (numClicks == 3){
                thirdCoordinateX = e.getX();
                thirdCoordinateY = e.getY();
                repaint();
            }
            else if (numClicks == 4){
                numClicks = 0;
                restart();
            }
        }
    }
}
