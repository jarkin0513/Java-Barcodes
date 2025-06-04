/**
 * The DecodeUpca class provides methods to decode a binary representation of a UPCA code back into its product code.
 */
public class DecodeUpca {

    /**
     * The binary representation string to be decoded.
     */
    private static String binaryRep;

    /**
     * Constructor for DecodeUpca class.
     * Initializes the binaryRep to be an empty string.
     */
    public DecodeUpca() {
        binaryRep = "";
    }

    /**
     * Array of binary representations for the left side of the UPCA code.
     * Used by other methods to decode into UPCA code.
     */
    public static final String[] BINARY_NUMBERS_LEFT = {
            "0001101", "0011001", "0010011", "0111101", "0100011", "0110001",
            "0101111", "0111011", "0110111", "0001011"
    };
    /**
     * Array of binary representations for the right side of the UPCA code.
     * Used by other methods to decode into UPCA code.
     */
    public static final String[] BINARY_NUMBERS_RIGHT = {
            "1110010", "1100110", "1101100", "1000010", "1011100", "1001110",
            "1010000", "1000100", "1001000", "1110100"
    };

    /**
     * Getter for the currently set binaryRep.
     *
     * @return binaryRep as a string.
     */
    public static String getBinaryRep() {
        return binaryRep;
    }

    /**
     * Setter for setting the binary representation.
     * This method checks for a valid binaryRep entry. binaryRep must be 95 digits long.
     * If valid, binary representation is set to provided value.
     * If not valid, binary representation is set to a default value of
     * '10100011010011001001001101100010100011010111101010101000010001001000010100010010011101011100101'.
     *
     * @param binaryRep binary representation to be set
     */
    public static void setBinaryRep(String binaryRep) {
        if (binaryRep.length() == 95) {
            DecodeUpca.binaryRep = binaryRep;
        } else {
            DecodeUpca.binaryRep = "10100011010011001001001101100010100011010111101010101000010001001000010100010010011101011100101";
        }
    }

    /**
     * Get the check digit from binary representation.
     * This method takes the binary representation string as input, trims it to be only the right side after the center
     * guard bar, decodes the same way as extractBinaryRepresentationLeft/Right, and trims the final string to
     * only include the check digit in decimal form.
     *
     * @param binaryRep The binary representation to extract the check digit from.
     * @return The check digit as a string.
     */
    public static String getCheckDigit(String binaryRep) {

        StringBuilder checkDigit = new StringBuilder(); // Initialize StringBuilder to build decoded UPCA
        binaryRep = binaryRep.substring(50, 92);    // Trim the string to only include the right side

        for (int i = 0; i < 42; i += 7) {
            String binaryDigits = binaryRep.substring(i, i + 7);    // Grabs 7 digits at a time
            int digitValue = getDigitRight(binaryDigits);       // Numeric equivalent of binary chunk
            checkDigit.append(digitValue);          // Add numeric value to productCode string
        }
        return checkDigit.substring(5);     // Returns trimmed product code that only includes check digit
    }

    /**
     * Get the decimal value of binary digits on left side of binary string.
     * This method takes a chunk of binary digits 7 digits long and runs through the indexes of BINARY_NUMBERS_LEFT.
     * If the chunk matches with one of the indexes of BINARY_NUMBERS_LEFT, its index is returned which will be its
     * decimal value. This method is utilized within extractBinaryRepresentationLeft/Right methods.
     *
     * @param binaryDigits The binary digits to convert.
     * @return The index of where the chunk of binary digits was found within BINARY_NUMBERS.
     * Or -1 if nothing is found.
     */
    public static int getDigitLeft(String binaryDigits) {
        for (int i = 0; i < BINARY_NUMBERS_LEFT.length; i++) {
            if (BINARY_NUMBERS_LEFT[i].equals(binaryDigits)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Get the decimal value of binary digits on right side of binary string.
     * This method takes a chunk of binary digits 7 digits long and runs through the indexes of BINARY_NUMBERS_RIGHT.
     * If the chunk matches with one of the indexes of BINARY_NUMBERS_RIGHT, its index is returned which will be its
     * decimal value. This method is utilized within extractBinaryRepresentationLeft/Right methods.
     *
     * @param binaryDigits The binary digits to convert.
     * @return The index of where the chunk of binary digits was found within BINARY_NUMBERS_RIGHT.
     * Or -1 if nothing is found.
     */
    public static int getDigitRight(String binaryDigits) {
        for (int i = 0; i < BINARY_NUMBERS_RIGHT.length; i++) {
            if (BINARY_NUMBERS_RIGHT[i].equals(binaryDigits)) {
                return i;
            }
        }
        return -1;  // Returns -1 if invalid binary digit found
    }

    /**
     * Decode the left side of a UPCA code from its binary representation.
     * This method first trims binaryRep to include only from the end of the left guard bar up until the center guard.
     * Uses extractBinaryRepresentationLeft method to decode.
     *
     * @param binaryRep String to be decoded.
     * @return Decoded UPCA code string (left half)
     */
    public static String decodeUpcaLeft(String binaryRep) {
        binaryRep = binaryRep.substring(3, 45);
        return extractBinaryRepresentationLeft(binaryRep);
    }

    /**
     * Decode the right side of a UPCA code from its binary representation.
     * This method first trims binaryRep to include only from the end of the center guard bar up until the check digit
     * in its binary form.
     * Uses extractBinaryRepresentationRight method to decode.
     *
     * @param binaryRep String to be decoded.
     * @return Decoded UPCA code string (right half)
     */
    public static String decodeUpcaRight(String binaryRep) {
        binaryRep = binaryRep.substring(50, 85);
        return extractBinaryRepresentationRight(binaryRep);
    }

    /**
     * Gets the complete decoded UPCA code.
     * Combines what is returned from decodeUpcaLeft/Right and returns as full decoded UPCA product code.
     *
     * @param binaryRep String to be decoded.
     * @return Complete decoded UPCA code string.
     */
    public static String getDecoded(String binaryRep) {
        return decodeUpcaLeft(binaryRep) + decodeUpcaRight(binaryRep);
    }

    /**
     * Extract binary digits and decode into a numeric UPCA product code representation from left side of binaryRep.
     * This method takes a binary representation string as input.
     * Extracts binary digits in chunks of 7 with the use of substring. Uses the getDigitLeft method to decode these chunks
     * of binary digits. getDigitLeft returns the index of where the chunk was found within BINARY_NUMBERS_LEFT which happens
     * to be the numeric value which is appended to the product code string.
     * Iterates 6 times because left side contains 6 sets of 7 digit long binary representations.
     * The finished UPCA product code string is returned.
     * Uses StringBuilder to make the productCode string.
     *
     * @param binaryRep The binary representation to extract and decode.
     * @return The decoded UPCA product code as a string.
     */
    public static String extractBinaryRepresentationLeft(String binaryRep) {
        StringBuilder productCode = new StringBuilder();    // Initialize StringBuilder to build decoded UPCA

        for (int i = 0; i < 42; i += 7) {
            String binaryDigits = binaryRep.substring(i, i + 7);    // Grabs 7 digits at a time
            int digitValue = getDigitLeft(binaryDigits);    // Numeric equivalent of binary chunk
            productCode.append(digitValue); // Add numeric value to productCode string
        }
        return productCode.toString();
    }

    /**
     * Extract binary digits and decode into a numeric UPCA product code representation from right side of binaryRep.
     * This method takes a binary representation string as input.
     * Extracts binary digits in chunks of 7 with the use of substring. Uses the getDigitRight method to decode these chunks
     * of binary digits. getDigitRight returns the index of where the chunk was found within BINARY_NUMBERS_RIGHT which happens
     * to be the numeric value which is appended to the product code string.
     * Iterates only 5 times because right side only contains 5 sets of 7 digit long binary representations.
     * The finished zipCode string is returned.
     * Uses StringBuilder to make the product code string.
     *
     * @param binaryRep The binary representation to extract and decode.
     * @return The decoded UPCA product code as a string.
     */
    public static String extractBinaryRepresentationRight(String binaryRep) {
        StringBuilder productCode = new StringBuilder();    // Initialize StringBuilder to build decoded UPCA

        for (int i = 0; i < 35; i += 7) {
            String binaryDigits = binaryRep.substring(i, i + 7);    // Grabs 7 digits at a time
            int digitValue = getDigitRight(binaryDigits);   // Numeric equivalent of binary chunk
            productCode.append(digitValue); // Add numeric value to productCode string
        }
        return productCode.toString();
    }


}
