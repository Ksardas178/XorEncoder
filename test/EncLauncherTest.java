import EncPackage.EncXOR;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class EncLauncherTest {

    public void assertFileEquals(String first, String second) {
        try (FileInputStream in = new FileInputStream(first)) {
            try (FileInputStream out = new FileInputStream(second)) {
                int symIn = in.read();
                int symOut = out.read();
                while (symIn != -1 && symOut != 1) {
                    assertEquals(symIn, symOut);
                    symIn = in.read();
                    symOut = out.read();
                }
            }
        } catch (Exception e) {
            fail();
        }
    }

    @org.junit.jupiter.api.Test
    void mainTest() {
        try {
            EncXOR recoder = new EncXOR("ключ", "files/testInput.txt", "files/testExpectedOutput(1).txt");
            recoder.recode();
            assertFileEquals("files/testExpectedOutput(1).txt", "files/testExpectedOutput.txt");
            new File("files/testExpectedOutput(1).txt").delete();

            recoder = new EncXOR("s,dvnbjvbsdknvkdsbvflbdvnirbvbdsjvlre",
                    "files/testInput2.txt", "files/testExpectedOutput(2).txt");
            recoder.recode();
            assertFileEquals("files/testExpectedOutput(2).txt", "files/testExpectedOutput2.txt");
            new File("files/testExpectedOutput(2).txt").delete();

            recoder = new EncXOR("3164614946", "files/testInput3.txt", "files/testExpectedOutput(3).txt");
            recoder.recode();
            assertFileEquals("files/testExpectedOutput(3).txt", "files/testExpectedOutput3.txt");
            new File("files/testExpectedOutput(3).txt").delete();

            recoder = new EncXOR("3164614946", "files/testInput4.txt", "files/testExpectedOutput(4).txt");
            recoder.recode();
            assertFileEquals("files/testExpectedOutput(4).txt", "files/testExpectedOutput4.txt");
            new File("files/testExpectedOutput(4).txt").delete();

            recoder = new EncXOR("xbvcsbjvbsdks13svd342378645927*^&*&$*^)&*^hisgfi",
                    "files/testInput5.txt", "files/testExpectedOutput(5).txt");
            recoder.recode();
            assertFileEquals("files/testExpectedOutput(5).txt", "files/testExpectedOutput5.txt");
            new File("files/testExpectedOutput(5).txt").delete();
        } catch (IOException e) {
            fail();
        }

    }
}