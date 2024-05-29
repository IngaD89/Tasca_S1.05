package org.example.n1exercici1_4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) {

        ManageDirectory manageDirectory = new ManageDirectory("/Users/test/IdeaProjects/Tasca_S1.05/example");
        File file = new File(manageDirectory.searchFiles().getName());
        manageDirectory.printFileNamesAsTree(file, 0);

        try (PrintWriter writer = new PrintWriter(new FileWriter("src/main/resources/saveLog.txt"))) {
            manageDirectory.saveLog(file, 0, writer);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }

        manageDirectory.searchTxtFiles(file);

        System.out.println("Esto es metodo serachFiles");

    }
}