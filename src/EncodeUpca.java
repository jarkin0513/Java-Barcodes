/**
 * The EncodeUpca class provides methods to encode UPCA product codes into binary representations.
 */
public class EncodeUpca {

    /** The UPCA product code to be encoded. */
    private static String productCode;

    /**
     * Constructor for EncodeUpca class.
     * Initializes the productCode to be an empty string.
     */
    public EncodeUpca() {
        productCode = "";
    }

    /**
     * Array of binary encoding patterns for the left side of the UPCA barcode.
     * Used by other methods to encode into binary representation.
     */
    public static final String[] ENCODE_LEFT = {
            "0001101", "0011001", "0010011", "0111101", "0100011", "0110001",
            "0101111", "0111011", "0110111", "0001011"
    };
    /**
     * Array of binary encoding patterns for the right side of the UPCA barcode.
     * Used by others methods to encode into binary representation.
     */
    public static final String[] ENCODE_RIGHT = {
            "1110010", "1100110", "1101100", "1000010", "1011100", "1001110",
            "1010000", "1000100", "1001000", "1110100"
    };

    /**
     * Setter for setting the product code.
     * This method checks for a valid productCode entry. productCode must be 11 digits long.
     * If valid, product code is set to provided value.
     * If not valid, product code is set as a default "11111111111" value.
     *
     * @param productCode product code to bet set.
     */
    public static void setProductCode(String productCode) {
        if (productCode.length() == 11) {
            EncodeUpca.productCode = productCode;
        } else {
            EncodeUpca.productCode = "11111111111";
        }
    }

    /**
     * Getter for the currently set productCode.
     *
     * @return productCode as a string.
     */
    public static String getProductCode() {
        return productCode;
    }

    /**
     * Calculate the check digit for the product code.
     * This method takes the product code string as input and calculates its check digit value based off of UPCA
     * encoding rules. The check digit is added at the end of the product code to form a complete UPCA code.
     *
     * @param productCode The UPCA product code string to calculate its check digit value.
     * @return The calculated check digit as in integer.
     */
    public static int getCheckDigit(String productCode) {
        int oddSum = 0; // Initialize variable to store sum of odd positioned digits
        for (int i = 0; i < productCode.length(); i += 2) {
            // Iterate through odd number positions and add their numeric value to oddSum
            oddSum += Character.getNumericValue(productCode.charAt(i));
        }
        oddSum *= 3;    // Multiply the result of oddSum by 3

        int evenSum = 0;    // Initialize variable to store sum of even positioned digits
        for (int i = 1; i < productCode.length() - 1; i += 2) {
            // Iterate through even number positions and add their numeric value to evenSum
            evenSum += Character.getNumericValue(productCode.charAt(i));
        }

        int totalSum = oddSum + evenSum;    // Acquire total sum of odd and even sums
        int m = totalSum % 10;  // Calculate remainder of the total sum when divided by 10
        if (m == 0) {
            return m;   // If remainder is 0, return 0 as check digit value
        } else {
            return 10 - m;  // Else, return 10 minus the remainder as check digit value
        }
    }

    /**
     * Encode a UPCA product code and its check digit into a binary representation.
     * This method takes a product code as input. Its check digit gets calculated and added onto the end of itself
     * in a new string called productCodeWithCheckDigit.
     * StringBuilder is used to make the encoded string. It first adds '101' to the front as the left 'frame'.
     * Encodes the left side of the product code, adds in the center 'frame' and then encodes the right side.
     * '101' is then added as the right 'frame' and the complete encoded UPCA is returned as a string.
     *
     * @param productCode product code string to be encoded.
     * @return The binary representation of the UPCA code.
     */
    public static String getBinaryRep(String productCode) {
        // Calculate check digit for the UPCA code
        String productCodeWithCheckDigit = productCode + getCheckDigit(productCode);

        // Initialize StringBuilder to build binary representation string. Add left guard bar
        StringBuilder encodedUPCA = new StringBuilder("101");

        // Encode  left side of UPCA code (first 6 digits)
        for (int i = 0; i <= 5; i++) {
            // Get the numeric value of the current digit
            int numberValue = Character.getNumericValue(productCodeWithCheckDigit.charAt(i));
            // Append the binary encoding at the numberValue index of ENCODE_LEFT
            encodedUPCA.append(ENCODE_LEFT[numberValue]);
        }

        encodedUPCA.append("01010");    // Append center guard bar

        // Encode right side of UPCA code (last 5 digits)
        for (int i = 6; i < productCodeWithCheckDigit.length(); i++) {
            // Get the numeric value of the current digit
            int numberValue = Character.getNumericValue(productCodeWithCheckDigit.charAt(i));
            // Append the binary encoding at the numberValue index of ENCODE_RIGHT
            encodedUPCA.append(ENCODE_RIGHT[numberValue]);
        }

        encodedUPCA.append("101");  // Append right guard bar

        return encodedUPCA.toString();
    }
}
