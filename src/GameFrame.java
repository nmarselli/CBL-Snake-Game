
import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {

    // Make buttons fields
    private JButton[] leftButtons = new JButton[5];
    private JButton[] rightButtons = new JButton[5];
    private JButton startButton;

    GameFrame() {
        // 1. Frame title and icon
        setTitle("Snake");
        ImageIcon image = new ImageIcon("C:\\Users\\nmars\\OneDrive - TU Eindhoven\\" 
                + "Desktop\\Snake Game\\assets\\images\\FrameIcon.jpg");
        setIconImage(image.getImage());

        // 2. Frame basic properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 600);        // size first
        setLocationRelativeTo(null);         // then center on screen
        getContentPane().setBackground(new Color(0, 255, 0)); // background color

        // 3. Layout manager
        setLayout(null); 

        // 4. Build UI components
        Settings();

        //Make frame visible
        setVisible(true);
    }

    public void Settings() {
    
    }

}
