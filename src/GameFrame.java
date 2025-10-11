import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {

    // Make buttons fields
    private JButton[] leftButtons = new JButton[5];
    private JButton[] rightButtons = new JButton[5];
    private JButton startButton = new JButton();

    GameFrame() {
        // 1. Frame title and icon
        setTitle("Snake");
        ImageIcon image = new ImageIcon("assets/images/FrameIcon.jpg");
        setIconImage(image.getImage());
        // 2. Frame basic properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(400, 600); // size first
        setLocationRelativeTo(null); // then center on screen
        getContentPane().setBackground(Color.WHITE); // background color

        // 3. Layout manager
        setLayout(null);

        // 4. Build UI components
        settings();
        // Make frame visible
        setVisible(true);
    }

    private void settings() {
        JPanel settingsPanel = new JPanel();

        settingsPanel.setBackground(Color.WHITE);
        this.add(settingsPanel);
        settingsPanel.setLayout(null);
        settingsPanel.setBounds(0, 0, 400, 600);
        
        ImageIcon arrowLeftBlack = new ImageIcon("assets/images/ArrowLeftBlack.png");
        Image scaledLeft = arrowLeftBlack.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon left = new ImageIcon(scaledLeft);

        for (int i = 0; i < leftButtons.length; i++) {
            leftButtons[i] = new JButton();
            leftButtons[i].setIcon(left);
            leftButtons[i].setBorder(BorderFactory.createEmptyBorder());
            leftButtons[i].setContentAreaFilled(false);
            leftButtons[i].setBounds(30, 30 + i * 75, 50, 50);
            
            settingsPanel.add(leftButtons[i]);

        }

        ImageIcon arrowRightBlack = new ImageIcon("assets/images/ArrowRightBlack.png");
        Image scaledRight = arrowRightBlack.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon right = new ImageIcon(scaledRight);

        for (int i = 0; i < leftButtons.length; i++) {
            rightButtons[i] = new JButton();
            rightButtons[i].setIcon(right);
            rightButtons[i].setBorder(BorderFactory.createEmptyBorder());
            rightButtons[i].setContentAreaFilled(false);
            rightButtons[i].setBounds(300, 30 + i * 75, 50, 50);

            settingsPanel.add(rightButtons[i]);

        }


        ImageIcon start = new ImageIcon("assets/images/start.png");
        Image scaledStart = start.getImage().getScaledInstance(210, 100, Image.SCALE_SMOOTH);
        ImageIcon startImg = new ImageIcon(scaledStart);

        settingsPanel.add(startButton);
        startButton.setIcon(startImg);
        startButton.setBorder(BorderFactory.createEmptyBorder());
        startButton.setContentAreaFilled(false);
        startButton.setBounds(85, 400, 210, 100);

    }
}
