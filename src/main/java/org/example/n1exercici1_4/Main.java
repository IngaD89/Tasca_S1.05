package org.example.n1exercici1_4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) {

        ReadDirectory readDirectory = new ReadDirectory("/Users/test/IdeaProjects/Tasca_S1.05/example");
        File file = new File(readDirectory.searchFiles().getName());
        readDirectory.printFileNamesAsTree(file, 0);

        try (PrintWriter writer = new PrintWriter(new FileWriter("src/main/resources/saveLog.txt"))) {
            readDirectory.saveLog(file, 0, writer);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }

        readDirectory.searchTxtFiles(file);

    }
}