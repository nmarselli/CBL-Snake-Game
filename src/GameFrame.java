import java.awt.*;
import java.io.File;
import javax.swing.*;

public class GameFrame extends JFrame {

    // Make buttons fields
    private JButton[] leftButtons = new JButton[5];
    private JButton[] rightButtons = new JButton[5];
    private JButton startButton;

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

        settingsPanel.setBackground(Color.blue);
        this.add(settingsPanel);
        settingsPanel.setLayout(null);
        settingsPanel.setBounds(0, 0, 400, 600);
        
        ImageIcon ArrowLeftBlack = new ImageIcon("assets/images/ArrowLeftBlack.jpg");
        for (int i = 0; i < leftButtons.length; i++) {
            leftButtons[i] = new JButton();
            leftButtons[i].setIcon(ArrowLeftBlack);
            leftButtons[i].setBorder(BorderFactory.createEmptyBorder());
            leftButtons[i].setContentAreaFilled(false);
            leftButtons[i].setBounds(30, 30 + i * 75, 50, 50);
            //

            settingsPanel.add(leftButtons[i]);

            /*
             * leftButtons[i].addActionListener(new ActionListener() {
             * 
             * @Override
             * public void actionPerformed(ActionEvent e) {
             * //your actions
             */
        }
    }

}
