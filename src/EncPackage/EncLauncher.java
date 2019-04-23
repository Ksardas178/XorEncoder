package EncPackage;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.IOException;

public class EncLauncher {

    @Option(name = "-c", metaVar = "encryption", usage = "Encryption code")
    private String codeKey;

    @Option(name = "-d", metaVar = "decryption", usage = "Decryption code")
    private String decodeKey;

    @Argument(usage = "input.txt", metaVar = "inputName", required = true)
    private String inputName;

    @Option(name = "-o", metaVar = "outputName", usage = "Output file name")
    private String outputName;

    public static void main(String[] args) {
        new EncLauncher().launch(args);
    }

    private void rename() {
        int number = 1;
        String oldName = inputName.replace(".txt", "(");
        String newName = oldName + number + ").txt";
        while (new File(newName).isFile()) {
            number++;
            newName = oldName + number + ").txt";
        }
        outputName = newName;
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar XorEncoder.jar [-c key] [-d key] inputName.txt [-o OutputName.txt]");
            parser.printUsage(System.err);
            return;
        }

        if (outputName == null) rename();
        if (codeKey == null) codeKey = decodeKey;//Алгоритм работает одинаково в обе стороны
        if (codeKey == null) {
            System.err.println("Отсутствует ключ (де)кодирования");
            System.err.println("java -jar XorEncoder.jar [-c key] [-d key] inputName.txt [-o OutputName.txt]");
        } else {
            EncXOR recoder = new EncXOR(codeKey, inputName, outputName);
            try {
                int result = recoder.recode();
                System.out.println("Total of " + result + " symbols recoded");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}