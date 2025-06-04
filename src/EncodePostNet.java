/**
 * EncodePostNet class for encoding ZIP, ZIP+4, and ZIP+4+Delivery codes into POSTNET barcodes.
 */
public class EncodePostNet {

    private static String zipCode;

    /**
     * Constructor for EncodePostNet.
     * Initializes zipCode as an empty string.
     */
    public EncodePostNet() {
        zipCode = "";
    }

    /**
     * Array of POSTNET barcode encodings for digits 0 to 9.
     */
    public static final String[] ENCODE = {
            "11000", "00011", "00101", "00110", "01001", "01010",
            "01100", "10001", "10010", "10100"
    };

    /**
     * Encode a ZIP code into a POSTNET barcode.
     * This method takes a ZIP code string as input, calculates the check sum, and encodes the ZIP code into
     * a binary representation using the predefined binary encodings from ENCODE and StringBuilder.
     *
     * @param zipCode The ZIP code string to be encoded.
     * @return The binary representation of the ZIP code.
     */
    public static String encodeZipCode(String zipCode) {

        // Calculate sum for check sum computation
        int sum = 0;
        for (char digit : zipCode.toCharArray()) {
            sum += Character.getNumericValue(digit);
        }

        // Calculate check sum
        int checkSum = (10 - (sum % 10)) % 10;

        // Initialize a StringBuilder to build the binary representation
        StringBuilder encodedZip = new StringBuilder("1");
        for (char digit : zipCode.toCharArray()) {
            int numberValue = Character.getNumericValue(digit); // Convert current digit to numeric value to be used as index for ENCODE
            encodedZip.append(ENCODE[numberValue]); // Use the numberValue index to access correct binary representation from ENCODE
        }
        // Append check sum binary representation and border
        encodedZip.append(ENCODE[checkSum]);
        encodedZip.append("1");

        // Convert encodedZip to string
        return encodedZip.toString();
    }

    /**
     * Convert a binary string representation to a POSTNET barcode string.
     * This method takes a binary string as input and converts it into a POSTNET barcode string by replacing '0' with
     * '.' for a half bar and '1' with '|' for a full bar.
     *
     * @param binaryString The binary string to be converted.
     * @return The POSTNET barcode representation of the binary string
     */
    public static String binaryToBarCode(String binaryString) {

        // Initialize a StringBuilder to build the barcode representation
        StringBuilder barCode = new StringBuilder();

        // Goes through each digit and if a '0' is found append '.' to barCode string and if a '1' is found append
        // '|' to barCode string
        for (char digit : binaryString.toCharArray()) {
            if (digit == '0') {
                barCode.append(".");
            } else if (digit == '1') {
                barCode.append("|");
            }
        }

        // Return the barcode representation as string
        return barCode.toString();
    }

    /**
     * Get the POSTNET barcode for given ZIP code.
     * This method encodes the provided ZIP code and returns its POSTNET barcode representation. This method
     * utilizes the encodeZipCode and binaryToBarCode methods.
     *
     * @param zipCode The ZIP code to generate the bar code for.
     * @return The POSTNET barcode representation of the ZIP code as a string.
     */
    public static String getBarCode(String zipCode) {
        String encodedZip = encodeZipCode(zipCode);

        return binaryToBarCode(encodedZip);
    }


    /**
     * Getter for getting the currently set ZIP code.
     * This method returns the zipCode string currently stored in the class
     *
     * @return zipCode as a string
     */
    public static String getZipCode() {
        return zipCode;
    }

    /**
     * The setter for setting the zip code.
     * This method checks for a valid zip code entry. Zip code must be 5, 9, or 11 digits long.
     * If valid, zipCode is set to provided value.
     * If not valid, zipCode gets set as a default "11111" value.
     *
     * @param zipCode zipCode string to set
     */
    public static void setZipCode(String zipCode) {
        if (zipCode.length() != 5 && zipCode.length() != 9 && zipCode.length() != 11) {
            EncodePostNet.zipCode = "11111";
        } else {
            EncodePostNet.zipCode = zipCode;
        }

    }
}
