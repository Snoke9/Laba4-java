import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.Random;

public class Main
{
    static double xSpeed = 10, ySpeed = 10, xPos = 0, yPos = 0; static int width = 1500, height = 800, radius = 40;
    static Random random = new Random();

    public static void main(String[] args)
    {
        JFrame fr = new JFrame("Кружочек");
        fr.setPreferredSize(new Dimension(width, height));
        final JPanel pan = new JPanel();
        fr.add(pan);
        fr.setVisible(true);
        fr.setFocusable(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.pack();

        pan.setFocusable(true);
        pan.requestFocusInWindow();

        pan.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_UP)
                {
                    xSpeed *= 1.2;
                    ySpeed *= 1.2;
                }
                else if (e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    xSpeed *= 0.8;
                    ySpeed *= 0.8;
                }
            }
        });

        Timer tm = new Timer(5, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                Graphics2D gr = (Graphics2D) pan.getRootPane().getGraphics();
                pan.update(gr);

                xPos += xSpeed;
                yPos += ySpeed;

                if (xPos - radius < -width / 2 || xPos + radius > width / 2)
                {
                    xPos = Math.max(-width / 2 + radius, Math.min(width / 2 - radius, xPos));
                    xSpeed = -xSpeed;
                    RandomAngle();
                }
                if (yPos - radius < -height / 2 || yPos + radius > height / 2)
                {
                    yPos = Math.max(-height / 2 + radius, Math.min(height / 2 - radius, yPos));
                    ySpeed = -ySpeed;
                    RandomAngle();
                }

                gr.translate(750 + xPos, 400 + yPos);

                Ellipse2D.Double circle = new Ellipse2D.Double(-radius, -radius, 2 * radius, 2 * radius);
                gr.setColor(Color.GREEN);
                gr.fill(circle);
                gr.setColor(Color.GREEN);
                gr.draw(circle);
            }
        });
        tm.start();
    }

    static void RandomAngle()
    {
        double angle = (random.nextDouble() - 0.5) * Math.PI / 2;
        double newXSpeed = xSpeed * Math.cos(angle) - ySpeed * Math.sin(angle);
        double newYSpeed = xSpeed * Math.sin(angle) + ySpeed * Math.cos(angle);
        xSpeed = newXSpeed;
        ySpeed = newYSpeed;
    }
}
