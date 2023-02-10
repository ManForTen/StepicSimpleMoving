import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Main {
    static JFrame frame = new JFrame();
    static JLabel l;
    static int width = 500, height = 500;
    static int change = 50;

    static public void move(KeyEvent e){ // Метод движения по клавиатуре
        switch (e.getKeyCode()){ // Проверка на границы формы
            case (KeyEvent.VK_LEFT):
                if (l.getX()-change>=0)
                    l.setLocation(l.getX()-change,l.getY());
                break;
            case (KeyEvent.VK_RIGHT):
                if (l.getX()+change<width-l.getWidth())
                    l.setLocation(l.getX()+change,l.getY());
                break;
            case (KeyEvent.VK_UP):
                if (l.getY()-change>=0)
                    l.setLocation(l.getX(),l.getY()-change);
                break;
            case (KeyEvent.VK_DOWN):
                if (l.getY()+change<height-l.getHeight())
                    l.setLocation(l.getX(),l.getY()+change);
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Перемещение");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);
        BufferedImage im = ImageIO.read(new URL("https://pp.userapi.com/c846124/v846124246/de82f/dbLAZsmnoy0.jpg?ava=1"));
        JPanel panel = new JPanel (new FlowLayout(FlowLayout.LEFT)); // Создаем панель, чтобы ей отлавливать события клавиатуры, ставим ее слева
        panel.setFocusable(true); // Делаем у панели возможность принимать фокус, иначе она не сможет отловить события клавиатуры
        l = new JLabel(new ImageIcon(im),JLabel.RIGHT); // Создаем объект слева
        panel.add(l, BorderLayout.NORTH); // Добавляем на панель
        frame.add(panel); // Добавляем панель на форму
        panel.addKeyListener(new KeyAdapter() { // Добавляем слушателя на панель
            public void keyReleased(KeyEvent e) { // Метод движения
                move(e);
            }
        });
        frame.setVisible(true);
    }
}