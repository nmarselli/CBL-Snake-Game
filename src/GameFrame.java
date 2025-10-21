import java.awt.*;
import javax.swing.*;

/**
 * GameFrame class that sets up the UI.
 * Calls for the BackgroundPanel and Snake.
 */

public final class GameFrame extends JFrame {

    // Make buttons fields
    private final JButton[] leftButtons = new JButton[4];
    private final JButton[] rightButtons = new JButton[4];
    private final JButton startButton = new JButton();
    int[] currentSettings = { 0, 0, 0, 0, 0}; // default settings: gridsize, speed, food, game mode (and scale for gridsize)
    private Timer colorTimer;
    private float hue = 0f;
    private GamePanel gamePanel;
    private Color selectedSnakeColor = Color.GREEN;

    /**
     * Constructor for GameFrame.
     * Sets up the frame properties and calls for settings panel.
     */
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
        getContentPane().setBackground(Color.gray); // background color

        // 3. Layout manager
        setLayout(null);

        // 4. Build UI components
        settings();
        // Make frame visible
        setVisible(true);
    }

    /**
     * Renders the settings panel.
     * Adds buttons, to change settings and start the game.
     */
    public void settings() {
        JPanel settingsPanel = new JPanel();

        this.add(settingsPanel);
        settingsPanel.setLayout(null);
        settingsPanel.setBounds(0, 0, 400, 600);

        colorTimer = new Timer(50, e -> {
            hue += 0.01f;
            if (hue > 1f) {
                hue = 0f;
            }
            settingsPanel.setBackground(Color.getHSBColor(hue, 0.6f, 0.9f));
        });
        colorTimer.start();

        ImageIcon arrowLeftBlack = new ImageIcon("assets/images/ArrowLeftBlack.png");
        Image scaledLeft = arrowLeftBlack.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon left = new ImageIcon(scaledLeft);

        for (int i = 0; i < leftButtons.length; i++) {
            final int index = i; // final version of i
            leftButtons[i] = new JButton();
            leftButtons[i].setIcon(left);
            leftButtons[i].setBorder(BorderFactory.createEmptyBorder());
            leftButtons[i].setContentAreaFilled(false);
            leftButtons[i].setBounds(30, 30 + i * 75, 50, 50);
            leftButtons[i].addActionListener(
                    (e) -> {
                        if (currentSettings[index] > 0) {
                            currentSettings[index]--;
                            System.out.println("Setting " + index
                                    + " changed to " + currentSettings[index]); // Temporary

                        }
                    });

            settingsPanel.add(leftButtons[i]);

        }

        ImageIcon arrowRightBlack = new ImageIcon("assets/images/ArrowRightBlack.png");
        Image scaledRight = arrowRightBlack.getImage().getScaledInstance(
                50, 50, Image.SCALE_SMOOTH);
        ImageIcon right = new ImageIcon(scaledRight);

        for (int i = 0; i < leftButtons.length; i++) {
            final int index = i;
            rightButtons[i] = new JButton();
            rightButtons[i].setIcon(right);
            rightButtons[i].setBorder(BorderFactory.createEmptyBorder());
            rightButtons[i].setContentAreaFilled(false);
            rightButtons[i].setBounds(300, 30 + i * 75, 50, 50);
            rightButtons[i].addActionListener(
                    (e) -> {
                        if (currentSettings[index] < 3) {
                            currentSettings[index]++;
                            System.out.println("Setting " + index
                                    + " changed to " + currentSettings[index]); // Temporary
                        }
                    });

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
        startButton.addActionListener(
                (e) -> {
                    colorTimer.stop();
                    // Turn visibiliy off the panel and start the game
                    settingsPanel.setVisible(false);

                    JLayeredPane layers = new JLayeredPane();
                    layers.add(new Background(), Integer.valueOf(0));
                    gamePanel = new GamePanel(selectedSnakeColor);
                    layers.add(gamePanel, Integer.valueOf(1));
                    gamePanel.requestFocusInWindow();
                    setSize((int)Math.round(32*15*1.5+16), (int)Math.round(32*15*1.5+38));
                    setLocationRelativeTo(null);
                    setContentPane(layers);
                });

        JButton buttonColorPicker = new JButton("Pick a color");
        buttonColorPicker.setBackground(selectedSnakeColor);
        buttonColorPicker.addActionListener(
                (e) -> {
                    JColorChooser colorChooser = new JColorChooser();
                    Color color = colorChooser.showDialog(null, "Pick a snake color", selectedSnakeColor );
                    if (color != null) {
                        selectedSnakeColor = color;
                        buttonColorPicker.setBackground(selectedSnakeColor);
                    }
                });

        settingsPanel.add(buttonColorPicker);
        buttonColorPicker.setBounds(115, 320, 150, 50);

    }
    public int[] getSettings() {
        if (currentSettings[0] == 0) {
            currentSettings[0] = 15;
            currentSettings[4] = 15;
        } else if (currentSettings[0] == 1) {
            currentSettings[0] = 9;
            currentSettings[4] = 15;
        } else {
            currentSettings[0] = 21;
            currentSettings[4] = 15;
        }
        if (currentSettings[1] == 0) {
            currentSettings[1] = 100;
        } else if (currentSettings[1] == 1) {
            currentSettings[1] = 150;
        } else {
            currentSettings[1] = 50;
        }
        return currentSettings;
    }
}