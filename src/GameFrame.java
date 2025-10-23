import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * GameFrame class that sets up the UI.
 * Calls for the BackgroundPanel and Snake.
 */

public final class GameFrame extends JFrame {

    // Make buttons fields
    private final JButton[] leftButtons = new JButton[3];
    private final JButton[] rightButtons = new JButton[3];
    private final JButton startButton = new JButton();
    // default settings: gridsize, speed, food,(and scale for gridsize)
    int[] currentSettings = { 0, 0, 0, 0 };
    private Timer colorTimer;
    private float hue = 0f;
    private Color selectedSnakeColor = Color.GREEN;
    private JLabel mapImageLabel;
    private JLabel speedImageLabel;
    private JLabel foodImageLabel;

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
        getContentPane().setBackground(Color.green); // background color

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
            leftButtons[i].setBounds(30, 30 + i * 100, 50, 50);
            leftButtons[i].addActionListener(
                    (e) -> {
                        if (currentSettings[index] > 0) {
                            currentSettings[index]--;
                            updateDisplayedImage(index);
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
            rightButtons[i].setBounds(300, 30 + i * 100, 50, 50);
            rightButtons[i].addActionListener(
                    (e) -> {
                        if (currentSettings[index] < 2) {
                            currentSettings[index]++;
                            updateDisplayedImage(index);
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
        startButton.addActionListener((ActionEvent e) -> {
            colorTimer.stop();
            // Turn visibiliy off the panel and start the game
            settingsPanel.setVisible(false);

            JLayeredPane layers = new JLayeredPane();
            currentSettings = getSettings();
            layers.add(new Background(currentSettings), Integer.valueOf(0));
            layers.add(new GamePanel(selectedSnakeColor, currentSettings),
                    Integer.valueOf(1));
            setSize((int) Math.round(32 * currentSettings[0]
                    * currentSettings[3] / 10 + 16),
                    (int) Math.round(32 * currentSettings[0]
                            * currentSettings[3] / 10 + 38));
            setLocationRelativeTo(null);
            setContentPane(layers);
        });

        JButton buttonColorPicker = new JButton("Pick a Snake Color");
        buttonColorPicker.setBackground(selectedSnakeColor);
        buttonColorPicker.addActionListener(
                (e) -> {
                    JColorChooser colorChooser = new JColorChooser();
                    Color color = colorChooser.showDialog(null,
                            "Pick a snake color", selectedSnakeColor);
                    if (color != null) {
                        selectedSnakeColor = color;
                        buttonColorPicker.setBackground(selectedSnakeColor);
                    }
                });

        settingsPanel.add(buttonColorPicker);
        buttonColorPicker.setBounds(115, 320, 150, 50);


        ImageIcon mediumImage = new ImageIcon("assets/images/SpeedMedium.png");
        Image image1 = mediumImage.getImage();
        Image scaled1 = image1.getScaledInstance(100, 50, Image.SCALE_SMOOTH); 
        ImageIcon scaledMediumImage = new ImageIcon(scaled1);

        
        ImageIcon mediumImage2 = new ImageIcon("assets/images/MapMedium.png");
        Image image2 = mediumImage2.getImage();
        Image scaled2 = image2.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledMediumImage2 = new ImageIcon(scaled2);

        ImageIcon appleImage = new ImageIcon("assets/images/Apple.png");
        Image image3 = appleImage.getImage();
        Image scaled3 = image3.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledAppleImage = new ImageIcon(scaled3);



        JPanel imagePanel1 = new JPanel();
        mapImageLabel = new JLabel(scaledMediumImage2);
        imagePanel1.add(mapImageLabel);
        imagePanel1.setOpaque(false);
        



        JPanel imagePanel2 = new JPanel();
        speedImageLabel = new JLabel(scaledMediumImage);
        imagePanel2.add(speedImageLabel);
        imagePanel2.setOpaque(false);



        JPanel imagePanel3 = new JPanel();
        foodImageLabel = new JLabel(scaledAppleImage);
        imagePanel3.add(foodImageLabel);
        imagePanel3.setOpaque(false);

        settingsPanel.add(imagePanel1);
        settingsPanel.add(imagePanel2);
        settingsPanel.add(imagePanel3);

        imagePanel1.setBounds(140, 30, 100, 60);
        imagePanel2.setBounds(140, 130, 100, 60);
        imagePanel3.setBounds(140, 230, 100, 60);

        
    }

    private void updateDisplayedImage(int settingIndex) {
        int value = currentSettings[settingIndex];
        
        switch (settingIndex) {
            case 0 -> { // Map size
                ImageIcon smallImage = new ImageIcon("assets/images/MapSmall.png");
                ImageIcon mediumImage2 = new ImageIcon("assets/images/MapMedium.png");
                ImageIcon largeImage = new ImageIcon("assets/images/MapLarge.png");
                
                Image image1 = switch (value) {
                    case 0 -> mediumImage2.getImage();
                    case 1 -> smallImage.getImage();
                    case 2 -> largeImage.getImage();
                    default -> mediumImage2.getImage();
                };
                Image scaled1 = image1.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
                mapImageLabel.setIcon(new ImageIcon(scaled1));
            }
            case 1 -> { // Speed
                ImageIcon slowImage = new ImageIcon("assets/images/SpeedSlow.png");
                ImageIcon mediumImage = new ImageIcon("assets/images/SpeedMedium.png");
                ImageIcon fastImage = new ImageIcon("assets/images/SpeedFast.png");
                
                Image image2 = switch (value) {
                    case 0 -> mediumImage.getImage();
                    case 1 -> slowImage.getImage();
                    case 2 -> fastImage.getImage();
                    default -> mediumImage.getImage();
                };
                Image scaled2 = image2.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
                speedImageLabel.setIcon(new ImageIcon(scaled2));
            }
            case 2 -> { // Food type
                ImageIcon appleImage = new ImageIcon("assets/images/Apple.png");
                ImageIcon goldenImage = new ImageIcon("assets/images/GoldenApple.png");
                ImageIcon crystalImage = new ImageIcon("assets/images/CrystalApple.png");
                
                Image image3 = switch (value) {
                    case 0 -> appleImage.getImage();
                    case 1 -> goldenImage.getImage();
                    case 2 -> crystalImage.getImage();
                    default -> appleImage.getImage();
                };
                Image scaled3 = image3.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
                foodImageLabel.setIcon(new ImageIcon(scaled3));
            }
            default -> {
            }
        }
        
    }

    public int[] getSettings() {
        switch (currentSettings[0]) {
            case 0 -> {
                currentSettings[0] = 15;
                currentSettings[3] = 15; // scale *10 <-- breakes when not multiple of 5
            }
            case 1 -> {
                currentSettings[0] = 9;
                currentSettings[3] = 20;
            }
            case 2 -> {
                currentSettings[0] = 21;
                currentSettings[3] = 10;
            }
            default -> {
            }

        }
        switch (currentSettings[1]) {
            case 0 -> {
                currentSettings[1] = 150;
            }
            case 1 -> {
                currentSettings[1] = 250;
            }
            case 2 -> {
                currentSettings[1] = 50;
            }
            default -> {
            }
        }
        return currentSettings;
    }
}