/**
 * DecodePostNet class provides methods for decoding a binary representation string into
 * ZIP/ZIP+4/DIP+4+Delivery codes.
 */
public class DecodePostNet {

    /**
     * The binary representation to be decoded.
     */
    private static String binaryRep;

    /**
     * Constructor for DecodePostNet.
     * Initializes binaryRep as an empty string.
     */
    public DecodePostNet() {
        binaryRep = "";
    }

    /**
     * Array of binary number encodings for digits 0 to 9.
     * Used for decoding binary representation into numeric equivalent in methods within class.
     */
    public static final String[] BINARY_NUMBERS = {
            "11000", "00011", "00101", "00110", "01001", "01010",
            "01100", "10001", "10010", "10100"
    };

    /**
     * The getter for the currently set binaryRep.
     *
     * @return binaryRep as a string
     */
    public static String getBinaryRep() {
        return binaryRep;
    }

    /**
     * The setter for setting the binaryRep.
     * This method checks for a valid binaryRep entry. binaryRep must be 32, 52, or 62 digits long.
     * If valid, binaryRep is set to provided value.
     * If not valid, binaryRep is set as a default "11111111111111111111111111111111" value.
     *
     * @param binaryRep binary representation to set
     */
    public static void setBinaryRep(String binaryRep) {
        if (binaryRep.length() != 32 && binaryRep.length() != 52 && binaryRep.length() != 62) {
            DecodePostNet.binaryRep = "11111111111111111111111111111111";
        } else {
            DecodePostNet.binaryRep = binaryRep;
        }
    }

    /**
     * Get the decimal value of binary digits.
     * This method takes a chunk of binary digits 5 digits long and runs through the indexes of BINARY_NUMBERS.
     * If the chunk matches with one of the indexes of BINARY_NUMBERS, its index is returned which will be its
     * decimal value. This method is utilized within extractBinaryRepresentation method.
     *
     * @param binaryDigits The binary digits to convert.
     * @return The index of where the chunk of binary digits was found within BINARY_NUMBERS.
     * Or -1 if nothing is found.
     */
    public static int getDigit(String binaryDigits) {
        for (int i = 0; i < BINARY_NUMBERS.length; i++) {
            if (BINARY_NUMBERS[i].equals(binaryDigits)) {
                return i;
            }
        }
        return -1;      // Returns -1 if invalid binary digit found
    }

    /**
     * Decode a binary representation into a ZIP code.
     * This method takes a binary representation as input and trims off the borders and the check sum.
     * Uses the extractBinaryRepresentation method to decode into ZIP code.
     *
     * @param binaryRep The binary representation to be decoded.
     * @return The decoded ZIP code as a string.
     */
    public static String decodeZipcode(String binaryRep) {
        binaryRep = binaryRep.substring(1, 26);     // Trim off frame bars on each side and check sum
        return extractBinaryRepresentation(binaryRep);
    }

    /**
     * Decode a binary representation into a ZIP+4 code.
     * This method takes a binary representation as input and trims off the borders and the check sum.
     * Uses the extractBinaryRepresentation method to decode into ZIP code.
     *
     * @param binaryRep The binary representation to be decoded.
     * @return The decoded ZIP+4 code as a string.
     */
    public static String decodeZipPlusFour(String binaryRep) {
        binaryRep = binaryRep.substring(1, 46); // Trim off frame bars on each side and check sum
        return extractBinaryRepresentation(binaryRep);
    }

    /**
     * Decode a binary representation into a ZIP+4+Delivery code.
     * This method takes a binary representation as input and trims off the borders and the check sum.
     * Uses the extractBinaryRepresentation method to decode into ZIP code.
     *
     * @param binaryRep The binary representation to be decoded.
     * @return The decoded ZIP+4+Delivery code as a string.
     */
    public static String decodeZipPlusFourPlusDelivery(String binaryRep) {
        binaryRep = binaryRep.substring(1, 56); // Trim off frame bars on each side and check sum
        return extractBinaryRepresentation(binaryRep);
    }

    /**
     * Extract binary digits and decode into a numeric ZIP code representation.
     * This method takes a binary representation string as input.
     * Extracts binary digits in chunks of 5 with the use of substring. Uses the getDigit method to decode these chunks
     * of binary digits. getDigit returns the index of where the chunk was found within BINARY_NUMBERS which happens
     * to be the numeric value which is appended to the zipCode string.
     * The finished zipCode string is returned.
     * Uses StringBuilder to make the zipCode string.
     *
     * @param binaryRep The binary representation to extract and decode.
     * @return The decoded ZIP code as a string.
     */
    private static String extractBinaryRepresentation(String binaryRep) {
        StringBuilder zipCode = new StringBuilder();

        for (int i = 0; i < binaryRep.length(); i += 5) {
            String binaryDigits = binaryRep.substring(i, i + 5); // Grabs 5 digits at a time
            int digitValue = getDigit(binaryDigits);    // Numeric equivalent of binary chunk
            zipCode.append(digitValue); // Add numeric value to zipCode string
        }
        return zipCode.toString();
    }

}
