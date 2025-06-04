import javax.swing.JFrame;


/**
 * The QR class serves as the starting point for running the QR Code Generator application.
 * It creates an instance of the QRDCodeGeneratorFrame class to provide a GUI for the QR code generation.
 * Configures the frame properties.
 */
public class QR {
    /**
     * The main method of QR which serves as the entry point for the application.
     *
     * @param args Command line arguments. (Not used in this application)
     */
    public static void main(String[] args) {
        QRCodeGeneratorFrame QRCodeGeneratorFrame = new QRCodeGeneratorFrame();

        // Configures the frame properties
        QRCodeGeneratorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        QRCodeGeneratorFrame.setSize(360, 360);
        QRCodeGeneratorFrame.setVisible(true);

        // Request focus for frame so text field isn't focused when application first starts
        QRCodeGeneratorFrame.requestFocusInWindow();
    }
}


