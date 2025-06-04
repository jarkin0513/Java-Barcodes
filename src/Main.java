import java.util.Scanner;


/**
 * The Main class provides a command-line interface for selecting and performing various barcode encoding and decoding
 * methods. It supports encoding ZIP Codes into POSTNET barcodes, decoding POSTNET barcodes into ZIP Codes, encoding
 * UPCAs, and decoding UPCA barcodes.
 */
public class Main {

    /**
     * The main method that serves as the entry point for the barcode encoding and decoding application.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        // Display a menu and process user input until the user wants to exit
        while (true) {

            System.out.println("Select an Option:");
            System.out.println("1. Encode ZIP Code to POSTNET");
            System.out.println("2. Decode POSTNET to ZIP Code");
            System.out.println("3. Encode UPCA");
            System.out.println("4. Decode UPCA");
            System.out.println("5. Exit");

            int choice = scanner.nextInt(); // Read user's choice
            scanner.nextLine();     // Consume newline character

            // Perform selected operation based on user's choice
            switch (choice) {
                case 1:
                    encodePostnet(scanner);
                    break;
                case 2:
                    decodePostnet(scanner);
                    break;
                case 3:
                    encodeUpca(scanner);
                    break;
                case 4:
                    decodeUpca(scanner);
                    break;
                case 5:
                    System.out.println("Goodbye");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    /**
     * Encodes a ZIP Code to POSTNET format and displays the provided zip code, its binary representation, and the
     * barcode representation.
     *
     * @param scanner Scanner for user input.
     */
    private static void encodePostnet(Scanner scanner) {

        new EncodePostNet();
        System.out.println("Enter ZIP Code (5, 9, or 11 digits): ");
        String inputEncodePOSTNET = scanner.nextLine();
        EncodePostNet.setZipCode(inputEncodePOSTNET);

        String postNetBarcode = EncodePostNet.getBarCode(EncodePostNet.getZipCode());
        String postNetBinary = EncodePostNet.encodeZipCode(EncodePostNet.getZipCode());
        System.out.println("POSTNET Encoding");
        System.out.println("Zip Code: " + EncodePostNet.getZipCode());
        System.out.println(postNetBinary);
        System.out.println(postNetBarcode + "\n");

    }

    /**
     * Decodes a POSTNET barcode to a ZIP code and displays the provided binary representation and the zip code.
     *
     * @param scanner Scanner for user input.
     */
    private static void decodePostnet(Scanner scanner) {

        new DecodePostNet();
        System.out.println("Enter Binary Form (32, 52, or 62 digits): ");
        String inputDecodePOSTNET = scanner.nextLine();
        DecodePostNet.setBinaryRep(inputDecodePOSTNET);
        String zipCode = "";
        if (inputDecodePOSTNET.length() == 32) {
            zipCode = DecodePostNet.decodeZipcode(DecodePostNet.getBinaryRep());
        } else if (inputDecodePOSTNET.length() == 52) {
            zipCode = DecodePostNet.decodeZipPlusFour(DecodePostNet.getBinaryRep());
        } else if (inputDecodePOSTNET.length() == 62) {
            zipCode = DecodePostNet.decodeZipPlusFourPlusDelivery(DecodePostNet.getBinaryRep());
        }

        System.out.println("POSTNET Decoding");
        System.out.println("Zip Code Binary Representation: " + DecodePostNet.getBinaryRep());
        System.out.println("Zipcode: " + zipCode + "\n");

    }

    /**
     * Encodes a UPCA code and displays the encoded binary representation.
     *
     * @param scanner Scanner for user input.
     */
    private static void encodeUpca(Scanner scanner) {

        new EncodeUpca();
        System.out.println("Enter UPCA (11 digits): ");
        String inputEncodeUPCA = scanner.nextLine();
        String encodedUPCA = "";
        EncodeUpca.setProductCode(inputEncodeUPCA);
        encodedUPCA = EncodeUpca.getBinaryRep(EncodeUpca.getProductCode());
        System.out.println(encodedUPCA + "\n");

    }

    /**
     * Decodes a UPCA barcode and displays the check digit and the decoded product code.
     *
     * @param scanner Scanner for user input.
     */
    private static void decodeUpca(Scanner scanner) {

        new DecodeUpca();
        System.out.println("Enter Barcode to Decode (95 digits): ");
        String inputDecodeUPCA = scanner.nextLine();
        DecodeUpca.setBinaryRep(inputDecodeUPCA);
        String decoded = DecodeUpca.getDecoded(DecodeUpca.getBinaryRep());
        String checkDigit = DecodeUpca.getCheckDigit(DecodeUpca.getBinaryRep());
        System.out.println("Check Digit: " + checkDigit);
        System.out.println("Product Code: " + decoded + "\n");

    }


}





