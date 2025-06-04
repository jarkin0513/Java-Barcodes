import org.junit.jupiter.api.*;

class PostnetTest {

    @Test
    public void testEncodeConstructor() {
        new EncodePostNet();
        Assertions.assertEquals("", EncodePostNet.getZipCode());
    }

    @Test
    public void testEncodeGetZipcode() {
        EncodePostNet.setZipCode("60056");
        Assertions.assertEquals("60056", EncodePostNet.getZipCode());
        EncodePostNet.setZipCode("52240");
        Assertions.assertEquals("52240", EncodePostNet.getZipCode());
        EncodePostNet.setZipCode("0");
        Assertions.assertEquals("11111", EncodePostNet.getZipCode());
        EncodePostNet.setZipCode("123456789101112");
        Assertions.assertEquals("11111", EncodePostNet.getZipCode());
    }

    @Test
    public void testEncodeZipCode() {
        EncodePostNet.setZipCode("90210");
        Assertions.assertEquals("11010011000001010001111000100101", EncodePostNet.encodeZipCode(EncodePostNet.getZipCode()));
        EncodePostNet.setZipCode("23202");
        Assertions.assertEquals("10010100110001011100000101000111", EncodePostNet.encodeZipCode(EncodePostNet.getZipCode()));
    }

    @Test
    public void testEncodeBinaryToBarCode() {
        EncodePostNet.setZipCode("12345");
        Assertions.assertEquals("|...||..|.|..||..|..|.|.|..|.|.|",
                EncodePostNet.binaryToBarCode(EncodePostNet.encodeZipCode(EncodePostNet.getZipCode())));
        EncodePostNet.setZipCode("54321");
        Assertions.assertEquals("|.|.|..|..|..||...|.|...||.|.|.|",
                EncodePostNet.binaryToBarCode(EncodePostNet.encodeZipCode(EncodePostNet.getZipCode())));
    }

    @Test
    public void testDecodeConstructor() {
        new DecodePostNet();
        Assertions.assertEquals("", DecodePostNet.getBinaryRep());
    }

    @Test
    public void testDecodeGetBinaryRep() {
        DecodePostNet.setBinaryRep("11111111111111111111111111111111");
        Assertions.assertEquals("11111111111111111111111111111111", DecodePostNet.getBinaryRep());
        DecodePostNet.setBinaryRep("1");
        Assertions.assertEquals("11111111111111111111111111111111", DecodePostNet.getBinaryRep());
    }

    @Test
    public void testDecodeZip() {
        DecodePostNet.setBinaryRep("11010011000001010001111000100101");
        Assertions.assertEquals("90210", DecodePostNet.decodeZipcode(DecodePostNet.getBinaryRep()));
        DecodePostNet.setBinaryRep("1000110010100011001010001100101000110010100011100011");
        Assertions.assertEquals("121212121", DecodePostNet.decodeZipPlusFour(DecodePostNet.getBinaryRep()));
        DecodePostNet.setBinaryRep("10001100101001100100101010011001000110010101001100000011010011");
        Assertions.assertEquals("12345678901", DecodePostNet.decodeZipPlusFourPlusDelivery(DecodePostNet.getBinaryRep()));
    }

}
