import javax.swing.*;
import java.awt.*;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.net.URL;


/**
 * The QRCodeGeneratorFrame class represents a GUI for generating QR codes from user input URLs.
 * Users can enter a URL, click on the "Generate" button, and view the corresponding QR code that appears in the middle
 * of the screen.
 */
public class QRCodeGeneratorFrame extends JFrame {
    /**
     * The text field for entering URLs.
     */
    private final JTextField textField;
    /**
     * The button for generating QR codes.
     */
    private final JButton button;
    /**
     * The panel for displaying the QR code image.
     */
    private final JPanel imagePanel;
    /**
     * The label for displaying QR code image.
     */
    private final JLabel imageLabel;
    /**
     * The entered URL given by the user.
     */
    private String enteredURL;
    /**
     * The counter for tracking the number of generated QR codes.
     */
    private int qrCodeCounter = 0;
    /**
     * The flag indicating whether the text field has recieved focus.
     */
    private boolean textFieldFocused = false;


    /**
     * The QRCodeGeneratorFrame constructor.
     * Initializes the GUI components, including a test field for URL input, a button to generate QR codes, and
     * a panel for displaying the QR code image.
     * Sets up event listeners for the text field for focusability functionality and for the button to generate the
     * QR codes.
     */
    public QRCodeGeneratorFrame() {
        super("QR Code Generator"); // Set title for application window
        setLayout((new BorderLayout()));    // Set layout manager for the main frame


        // Initialize and configure text field for URL input
        JPanel textFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        textField = new JTextField("Enter URL Here", 20);
        textField.setForeground(Color.GRAY);

        // Focus listener for button
        // Sets the text field to be empty when focus is gained and "Enter URL Here" when focus is lost
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (!textFieldFocused) {
                    textField.requestFocus();               // Request focus on text field
                    textField.setText("");                  // Clear the text field
                    textField.setForeground(Color.BLACK);   // Set text color to black
                    textFieldFocused = true;                   // Mark that text field has received focus
                }

            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText("Enter URL Here");    // Restore placeholder text
                    textField.setForeground(Color.GRAY);    // Set text color to gray
                    textFieldFocused = false;               // Mark that text field has lost focus
                }

            }
        });

        textFieldPanel.add(textField);  // Add text field to the textFieldPanel


        // Initialize and configure the image panel for displaying QR codes
        imagePanel = new JPanel(new BorderLayout());        // Create new JPanel with BorderLayout
        imageLabel = new JLabel();                          // Create new JLabel to display the QR code image
        imagePanel.add(imageLabel, BorderLayout.CENTER);    // Add imageLabel to center of imagePanel
        add(imagePanel, BorderLayout.CENTER);               // Add imagePanel to center of the frame


        // Initialize and configure the button for generating QR codes
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));  // Panel for button with right-aligned layout
        button = new JButton("Generate");                              // Create new "Generate" button
        buttonPanel.add(button);                                           // Add button to the buttonPanel

        // Action listener to handle QR code generation and display
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                enteredURL = textField.getText();       // Gets URL from text field given  by user input

                // Set output path to retrieve QR code images
                String outputPath = "oral_exam1/JUnit/resources/qrcode" + qrCodeCounter + ".png";

                try {

                    // Generate aa QR code based on entered URL
                    GenerateQRCode.generate(enteredURL, 350, 350, outputPath);

                    // Load the generated QR code image and set it to display
                    File qrCodeFile = new File(outputPath);
                    URL qrCodeImageUrl = qrCodeFile.toURI().toURL();
                    ImageIcon qrIcon = new ImageIcon(qrCodeImageUrl);
                    imageLabel.setIcon(qrIcon);

                    qrCodeCounter++;    // Increment counter for the next image that will be generated
                    repaint();          // Repaint the window

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        // Configure layout and add components to the frame
        JPanel bottomPanel = new JPanel(new BorderLayout());    // Create new panel with BorderLayout for bottom of screen
        bottomPanel.add(textFieldPanel, BorderLayout.WEST);     // Add textFieldPanel to left side of bottomPanel
        bottomPanel.add(buttonPanel, BorderLayout.EAST);        // Add the buttonPanel to the right side of bottomPanel

        // Set an empty border on the text field for spacing
        textField.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        add(bottomPanel, BorderLayout.SOUTH);   // Add bottomPanel to the bottom of the frame

    }
}
