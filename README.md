## Problem Statement
This program addresses the facilitation of encoding and decoding barcodes, specifically POSTNET and UPCA barcodes, along with providing a graphical user interface (GUI) for generating QR codes from user-provided URLs using the Java Swing library. 
<br> The POSTNET encoding handles ZIP, ZIP+4, and ZIP+4+Delivery codes from their zip code format into a binary and barcode representation. The decoding handles turning the binary representation back into its ZIP code format. </br>
<br> The UPCA encoding handles the input of an 11 digit UPCA product code into a 95 digit long binary representation. The UPCA decoding handles turning a 95 digit long binary representation back into its UPCA product code format. </br>
<br> The QR code generator allows for users to input a URL and see the generated QR code for the associated URL displayed within the application window. </br>

## Developer Documentation
<dl>
<dt> POSTNET and UPCA Encoder / Decoder </dt>
<p>

<details>

<summary>Main</summary>

 <em></em>The 'Main' class serves as the entry point for the application. It provides a command-line interface for users to select and perform various barcode encoding and decoding operations. I elected to user a 'Scanner' in order to read user input since it seemed to make the most sense for this application. The menu set up is simple and allows for users to select their choice of either encoding / decoding a POSTNET, encoding/decoding a UPCA, or quitting. The program continues to process user input until they decide to quit. The users choices get mapped to specific methods for either encoding or decoding the POSTNET or UPCA. The 'Main' class takes care of displaying the results in an easy and simple way to follow. 



</details>
</p>
<p>
<details>
<summary>EncodePostNet</summary>

 <em></em>The 'EncodePostNet' class is responsible for encoding ZIP, ZIP+4, and ZIP+4+Delivery codes into POSTNET barcodes. I decided to use a predefined array of POSTNET specific barcode encodings for digits 0 to 9 which allowed for a simpler approach when it came to designing the 'encodeZipCode' method. Using just a for loop to run through each of the digits in the user entered ZIP code I was able to pull out what that digit was, have it saved to a variable and use that value as an index to the correct encoding located in the 'ENCODE' array. Converting that into the barcode format was done  also using a for loop, but instead just checking each digit for either a '1' or '0'. Details related to this method, 'binaryToBarCode' can be seen within the documentation. For the getters and setters for this class, I thought it was crucial to include a validity check within the setter in order to ensure the user could not enter an integer that would not be able to get processed correctly. For this class, only 5, 9, or 11 digit long entries could work. I could have chosen any value to set the ZIP code to for an invalid entry, but I elected to go for '11111' to keep things simple. I thought in doing so it made it a little more obvious to the user that their entry was not correctly formatted. This is also assuming the user enters numbers only as well, writing this now I think I probably should have added a check for that, but I figured it might be obvious enough that a ZIP code is made up of only integers.



</details>
</p>
<p>
<details>
<summary>DecodePostNet</summary>

 The 'DecodePostNet' class is responsible for decoding a binary representation of the encoded ZIP back into its ZIP/ZIP+4/ZIP+4+Delivery code form. Similarly to 'EncodePostNet' I decided to use a string array to hold the different binary values corresponding from digits 0-9. This allowed for the 'getDigit' method to search that array and find the index where a chunk of binary digits was located based on the user inputted binary representation to decode. This method is used in the 'extractBinaryRepresentation' method that searches through the user inputted binary string, comparing 5 digit long chunks at a time. The index where that chunk was located happens to be the number value for which gets appended onto the 'zipCode' string that is eventually returned as the decoded POSTNET to the user. For each of the different types of ZIP codes to be decoded, I chose to set up three methods for each. This allowed to easily trim the binary representations down to a given length to be decoded so that only the binary numbers we care about to decode gets thrown into the 'extractBinaryRepresentation' method (not the guard bars and check digit). Depending on the type of ZIP code that the user wanted to decode, the 'Main' class would decide whether to run it through 'decodeZipcode', 'decodeZipPlusFour', or 'decodeZipPlusFourPlusDelivery'. This class also includes its own getter and setter for the user entered binary representation. Just like in the other classes, I decided to ensure a validity check inside the setter to allow for just the 32, 52, or 62 long binary strings. This ensured that the methods would not fail if an unexpected length of a string were to try and run through them.



</details>
</p>
<p>
<details>
<summary>EncodeUpca</summary>

 The 'EncodeUpca' class is responsible for encoding UPCA product codes into a binary representation. Unlike POSTNET where there are only guard bars on the left and right side, UPCA includes an additional guard bar in the middle of the barcode. The binary encodings for the left and right side are different. This difference is that the equivalent binary digit for a certain number on the right side, is the left side's inverted binary representation for that certain digit. So that '0' on the left side is '0001101' and '0' on the right side of this middle guard bar is '1110010'. I chose to put these encodings into a string array just like POSTNET, but I made one dedicated to the left side of the guard bar and another one for the right side. The encoding method is just like the POSTNET one, but it is split up into two different for loops, one for the left and one for the right side. When developing this, I thought why not use the already existing method I had except modified just a little to work for the necessary UPCA encodings. Just like the other classes, a getter and setter was used for setting the user inputted UPCA product code. The setter ensures that the input is 11 digits, since that is how long UPCA product codes need to be. This ensures the methods in this class do not break whenever an invalid entry is passed through. If there were to be an invalid entry, it would automatically be set to be '11111111111'. I did this so that it becomes obvious to the user if they happen to enter too short or too long of a product code. This is also assuming the user enters numbers only as well, writing this now I think I probably should have added a check for that, but I figured it might be obvious enough that a product code is made up of only integers.



</details>
</p>
<p>
<details>
<summary>DecodeUpca</summary>

 <em></em>The 'DecodeUpca' class is responsible for decoding a binary representation back into the UPCA product code format. Essentially it reverses what the 'EncodeUpca' class does. This being the last encode/decode class I worked on, I chose to copy the style of how I did 'DecodePostNet'. Because UPCA has the guard bar in the middle I had to split the methods up for the left and right side just like I mentioned in the encode UPCA class. Just like the decode POSTNET class there are methods that grab the index for where each digit is located at. Again, this is used in this class's own extractBinaryRepresentation methods, one for each side. Each left and right side method uses a different range for its for loop in order to grab the necessary numbers for its respective side. Just like everything else, this includes its own getter and setter for the user inputted binary representation. The setter has its own validity check to ensure the entered input is 95 digits long.   If not valid, the setter sets the binary rep to be '10100011010011001001001101100010100011010111101010101000010001001000010100010010011101011100101' which is what '11111111111' gets turned into. Like I previously mentioned, at the time of writing this I did not think about if a user were to enter a non numeric value like a letter or something. This is something I would consider going back to at a later date and adjusting for each of the encode and decode classes.

</p>
</details>



<br> </br>

<br> More information regarding POSTNET and UPCA can be found by clicking the links located in the user documentation.  </br>
<br> </br> 
<br> </br> 
<dt> QR Code Generator </dt>
<br> Main: QR </br>
<br> The 'QR' class serves as the entry point for running the QR Code Generator application. It creates an instance of the 'QRCodeGeneratorFrame' class to provide a graphical user interface (GUI) for the QR code generation and configures the frame properties. These properties include the title of the window, its size, and its default close operation. I chose to initialize and configure the GUI in its own class in order to keep the 'main' method clean and to just keep its focus on launching the application. A big problem I had while developing this was that the text field box would gain focus automatically once the application was run, and that was not what I was going for. I wanted the placeholder text, "Enter URL Here" to actually be seen before the text field had focus. In order to accomplish this, I added in the 'requestFocusInWindow' method which worked for my intended goal. </br>
<br> </br> 

<br> QRCodeGeneratorFrame </br>
<br> The 'QRCodeGeneratorFrame' class represents the GUI for generating QR codes from user inputted URLs. Users are able to enter their desired URL in the text field box, click the "Generate" button and view the corresponding QR code in the center of the screen. It uses the ZXING library for QR code generation and saves the images as PNG files to be displayed within the screen. </br>
 
<br> The ZXING library was chosen because of its reliability and popularity in QR code generation. Inside the 'GenerateQRCode' class a 'generate' method is defined. This method accepts in the URL, height, width, and output path for where the PNG will go. For this application, the PNG images are saved within a resources directory to make them easily retrievable and easy to locate. Within this 'generate' method the format of the image for the QR code is configured (PNG) as well as the error correction level (L). The ZXING's 'MultiFormatWriter' is utilized to encode the URL into a bit matrix which represents the QR code, as seen within the source code. Then in order to save the generated QR code, the 'MatrixToImageWriter' class from ZXING is used to save the image to the desired output path. </br>

<br> Within the constructor, the GUI components are intialized and configured. This includes the set up of the text field with its placeholder text, the configuration of the "Generate" button, and the initialization of the image panel and label. I wanted to add the focus listener to the text field in order to make it look more user friendly. Once the user clicks into the box, the placeholder text goes away and the text color changes from gray to black. Once the text field loses focus again the placeholder text reappears and the text color goes back to gray. I thought this made things look a little more professional. Once the "Generate" button is pressed, the entered URl is retrieved from the text field. The output path is constructed using a counter called, 'qrCodeCounter' to ensure unique file names for each QR code generated. This was a solution that I came across when developing  the QR code generator. For some reason, the image was unable to get updated when the image was always just set as "qrcode.png". Adding this counter and making each image named, "qrcode#.png" enabled the image to be updated and make the application function as intended. After generation the QR code, the image is loaded from file and displayed within the 'imageLabel' JLabel. The counter is incremented to prepare for the next image generation. I chose to organize the GUI components within JPanels with appropriate layouts (e.g. BorderLayout) to ensure a well-constructed and visually appealing interface. I also opted to add in an empty border around the text field to provide some spacing between the field and its adjacent components. I thought it allowed for the GUI to look not so cramped and easier to look at. </br>

<br> NOTE: Upon developing this application and how I set up the file system, I am well aware of how inefficient it is. As it is at the moment, everytime a user generates a new QR code a new image is saved to the resources folder. (e.g. qrcode0.png, qrcode1.png, etc.) Like I mentioned previously, when attempting to overwrite the same image file, it did not like that and would not for me as intended. For the context of this project, I figured it would not be the end of the world how it is set up at the moment. If I ever come back to this project, which I intend on doing so, I would definitely implement a feature that would clear the resources folder upon termination of the program. In terms of how it used right now, only a few QR code images are being generated so it is not a big deal as it does not take up a lot of storage and can easily be deleted without any repercussion.</br>
<br> </br> 

<br> More details regarding the specifics of each class, method, and variable can be found within the JavaDocs as well as in-line comments located in the source files. </br>





</dl>

![image](https://github.com/user-attachments/assets/54453a37-03b6-44e9-8dab-0adfa0435f93)


## JavaDocs
Java Documents are visible using a local server on the machine. You must have at least the doc folder on your local machine. To access them:
1. Open a terminal
2. Navigate to the `doc/` directory
   ```bash
   cd path\to\the\project\doc
   ```
3. Start a local server:
   ```bash
   python -m http.server 8000
   ```
4. Open your browser and vist:
   ```http://localhost:8000/package-summary.html```
 
[Java Docs can be accessed here!](https://github.com/jarkin0513/Java-Barcodes/blob/main/doc/package-summary.html) 
 
(Please keep in mind that this link will not work but you can find these docs by selecting the doc folder, finding the index.html, right click this -> Open in -> Browser)

## User Documentation
<dl>
 
 <dt> Launching the Barcode Encoder/Decoder Program </dt>
  <dd> To start the program navigate to the Main.java file and click the run button from your IDE. 
  <br> The run button should look like a green arrow, or something similar to that. </br>
  </dd>

 <dt> Using Program </dt>
 <dd> Once the program is running, you will be presented with a menu screen inside the command-line window that offers the following.
<br> 1. Encode ZIP Code to POSTNET </br>
<br> 2. Decode POSTNET to ZIP Code </br>
<br> 3. Encode UPCA </br>
<br> 4. Decode UPCA </br>
<br> 5. Exit </br>
 <br> To select an option, type the number associated with the feature that you want to use and press 'ENTER' within the command-line window that displays the menu. </br>
<br> </br>
 
<br> For encoding a ZIP Code to POSTNET: </br>
<br>After pressing '1' and hitting 'ENTER' from the menu, you will be prompted to enter either a 5, 9, or 11 digit long ZIP Code. </br>
<br> (A normal ZIP Code is 5 digits long, a ZIP+4 is 9 digits, and a ZIP+4+Delivery is 11 digits.) </br>
<br> NOTE:  If an invalid entry is entered, the program will automatically set the entered ZIP Code to be '11111'. </br>
<br> After entering a ZIP code, you will be presented with the ZIP Code you entered, a binary representation of it, as well as the barcode format. </br>
<br> You will then be taken back to the menu screen where you can either choose a different option, or exit. </br>
<br> </br>

<br> For decoding POSTNET to ZIP Code </br>
<br> After pressing '2' and hitting 'ENTER' from the menu, you will be prompted to enter either a 32, 52, or 62 digit long binary representation that you would like to decode. </br>
<br> NOTE: If an invalid entry is entered, the program will automatically set the entered binary form to be '11111111111111111111111111111111'. </br>
<br> After entering a binary representation, you will be presented with the binary that was entered, as well as the decoded ZIP Code for that associated binary. </br>
<br> You will then be taken back to the menu screen where you can either choose a different option, or exit. </br>
<br> </br>

<br> For encoding UPCA </br>
<br> After pressing '3' and hitting 'ENTER' from the menu, you will be prompted to enter a 11 digit long UPCA product code that you would like to encode. </br>
<br> NOTE: If an invalid entry is entered, the program will automatically set the entered product code to be '11111111111'. </br>
<br> After entering a UPCA product code, you will be presented with the encoded barcode given in a binary representation. </br>
<br> You will then be taken back to the menu screen where you can either choose a different option, or exit. </br>
<br> </br>

<br> For decoding UPCA </br>
<br> After pressing '4' and hitting 'ENTER' from the menu, you will be prompted to enter a 95 digit long barcode in binary representation that you would like to decode. </br>
<br> NOTE: If an invalid entry is entered, the program will automatically set the entered binary form to be '10100011010011001001001101100010100011010111101010101000010001001000010100010010011101011100101'. </br>
<br> After entering a barcode, you will be presented with the check digit and the decoded UPCA product code. </br>
<br> You will then be taken back to the menu screen where you can either choose a different option, or exit. </br>
<br> </br>

<br> **The check digit in the context of UPCA codes serves as a form of error-checking to ensure the accuracy of the data encoded in the barcode. This feature is used in real world applications for UPCA codes. For the purpose of this program, it is just another feature and is there for you to see and check for yourself if interested. The POSTNET encoder and decoder use this same feature and for a similar reason, but it uses a different algorithm to calculate it. Displaying the check digit for POSTNET was not necessary for this project and it just works in the background.  
<br> For more information: </br>
<br> [POSTNET](https://en.wikipedia.org/wiki/POSTNET) </br>
<br> [UPCA](https://en.wikipedia.org/wiki/Universal_Product_Code) </br>
<br> </br>

<dt> Exiting the Program </dt>
 <dd> To quit out of the application, press '5' and then press 'ENTER' from the menu.
 </dd>
<br> </br>

<dt> Launching the QR Code Generator </dt>
  <dd> To start the program navigate to the QR.java file and click the run button from your IDE. 
  <br> The run button should look like a green arrow, or something similar to that. </br>
  </dd>
<dt> Using the program </dt>
<dd> Once the program is running, a window should pop up on your screen. 
<br> Locate the "Enter URL Here" text box at the bottom of the window. Click inside the box to give it focus. </br>
<br> Type or paste the URL you want to encode into a QR code. Ensure it is a valid URL (e.g https://github.com). </br>
<br> NOTE: The entered URL can be in the format of "github.com", it does not need to include the "https://" in the front of it. </br>
<br> Press the "Generate" button located next to the text box with your mouse. </br>
<br> After pressing the generate button, the QR code for the associated URL will pop up in the window. </br>



<dt> Exiting the Program </dt>
 <dd> To quit out of the application, click on the 'X' located at the top right of the window. You can terminate the program by clicking the red box from inside your IDE.
 </dd>
 
</dl>

## Source Code
[Click here to view the source code](https://github.com/jarkin0513/Java-Barcodes/tree/main/src)
