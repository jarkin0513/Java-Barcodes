import org.junit.jupiter.api.*;

public class UpcaTest {

    @Test
    public void testEncodeConstructor() {
        new EncodeUpca();
        Assertions.assertEquals("", EncodeUpca.getProductCode());
    }

    @Test
    public void testEncodeGetProductCode() {
        new EncodeUpca();
        EncodeUpca.setProductCode("1");
        Assertions.assertEquals("11111111111", EncodeUpca.getProductCode());
        EncodeUpca.setProductCode("01254667375");
        Assertions.assertEquals("01254667375", EncodeUpca.getProductCode());
        EncodeUpca.setProductCode("12345678901");
        Assertions.assertEquals("12345678901", EncodeUpca.getProductCode());
    }

    @Test
    public void testEncodeCheckDigit() {
        new EncodeUpca();
        EncodeUpca.setProductCode("01254667375");
        Assertions.assertEquals(4, EncodeUpca.getCheckDigit(EncodeUpca.getProductCode()));
    }

    @Test
    public void testEncodeGetBinaryRep() {
        new EncodeUpca();
        EncodeUpca.setProductCode("01254667375");
        Assertions.assertEquals("10100011010011001001001101100010100011010111101010101000010001001000010100010010011101011100101",
                EncodeUpca.getBinaryRep(EncodeUpca.getProductCode()));
    }

    @Test
    public void testDecodeConstructor() {
        new DecodeUpca();
        Assertions.assertEquals("", DecodeUpca.getBinaryRep());
    }

    @Test
    public void testDecodeGetProductCode() {
        DecodeUpca.setBinaryRep("10100011010011001001001101100010100011010111101010101000010001001000010100010010011101011100101");
        Assertions.assertEquals("10100011010011001001001101100010100011010111101010101000010001001000010100010010011101011100101", DecodeUpca.getBinaryRep());
        DecodeUpca.setBinaryRep("1");
        Assertions.assertEquals("10100011010011001001001101100010100011010111101010101000010001001000010100010010011101011100101",
                DecodeUpca.getBinaryRep());
    }

    @Test
    public void testDecodeGetCheckDigit() {
        DecodeUpca.setBinaryRep("10100011010011001001001101100010100011010111101010101000010001001000010100010010011101011100101");
        Assertions.assertEquals("4", DecodeUpca.getCheckDigit(DecodeUpca.getBinaryRep()));
    }

    @Test
    public void testGetDecoded() {
        DecodeUpca.setBinaryRep("10100011010011001001001101100010100011010111101010101000010001001000010100010010011101011100101");
        Assertions.assertEquals("01254667375", DecodeUpca.getDecoded(DecodeUpca.getBinaryRep()));
    }

    @Test
    public void testExtractBinaryRepresentationLeft() {
        DecodeUpca.setBinaryRep("10100011010011001001001101100010100011010111101010101000010001001000010100010010011101011100101");
        Assertions.assertEquals("012546", DecodeUpca.extractBinaryRepresentationLeft("000110100110010010011011000101000110101111"));
    }

    @Test
    public void testExtractBinaryRepresentationRight() {
        DecodeUpca.setBinaryRep("10100011010011001001001101100010100011010111101010101000010001001000010100010010011101011100101");
        Assertions.assertEquals("67375", DecodeUpca.extractBinaryRepresentationRight("101000010001001000010100010010011101011100"));
    }


}
