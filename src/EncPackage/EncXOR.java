package EncPackage;

import java.io.*;

public class EncXOR {

    private final String inputName;
    private final String key;
    private final String outputName;

    public EncXOR(String key, String inputName, String outputName) {
        this.key = key;
        this.inputName = inputName;
        this.outputName = (outputName == null ? inputName : outputName);
    }

    public int recodeStream(InputStream in, OutputStream out) throws IOException {
        char[] charKey = key.toCharArray();
        int sym = in.read();
        int count = 0;
        while (sym != -1) {
            sym = charKey[count % charKey.length] ^ sym;
            out.write(sym);
            count++;
            sym = in.read();
        }
        return count;
    }

    public int recode() throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputName)) {
            try (FileOutputStream outputStream = new FileOutputStream(outputName)) {
                return recodeStream(inputStream, outputStream);
            }
        }
    }
}