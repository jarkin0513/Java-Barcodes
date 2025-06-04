import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;


/**
 * The GenerateQRCode class provides utility for generating QR code images based on the provided URL and parameters.
 * It uses the ZXING library to create QR codes and saves them as PNG images to be displayed in QRCodeGeneratorFrame.
 */
public class GenerateQRCode {
    /**
     * This method generates a QR code image from the given URL with the specified dimensions and saves it to the
     * given output path.
     * The generate method is used within the QRCodeGeneratorFrame class.
     *
     * @param enteredURL The URL for which the QR code is generated.
     * @param height     The height (in pixels) of the QR code image.
     * @param width      The width (in pixels) of the QR code image.
     * @param outputPath The path where the generated QR code image is saved to.
     * @throws WriterException If an error occurs during the QR code generation.
     * @throws IOException     If an error occurs while writing the QR code image to the output path.
     */
    public static void generate(String enteredURL, int height, int width, String outputPath)
            throws WriterException, IOException {

        // Define image format for QR code image
        String format = "PNG";

        // Create a map to specify tge QR code generation hints (error correction level)
        Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        // Create a BitMatrix representation of the QR code using the given parameters
        BitMatrix bitMatrix = new MultiFormatWriter().encode(enteredURL, BarcodeFormat.QR_CODE, width, height, hintMap);

        // Create a Path object for the output file location
        Path outputRelative = Path.of(outputPath);

        // Write the BitMatrix as an image to the specified output file location
        MatrixToImageWriter.writeToPath(bitMatrix, format, outputRelative);

    }
}

