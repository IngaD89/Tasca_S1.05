package org.example.n1exercici1_4;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ReadDirectory {

    private final String directoryPath;

    public ReadDirectory(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public File searchFiles() {
        File directory = null;
        try {
            directory = new File(directoryPath);
            if (directory.isDirectory()) {
                String[] files = directory.list();
                if (files != null) {
                    Arrays.sort(files);
                }
            }
        } catch (Exception e) {
            System.out.println("File not found");
        }
        return directory;
    }

    public void printFileNamesAsTree(File directory, int grade) {
        try {
            if (directory.isDirectory()) {
                System.out.println(colorText(tabulation(grade) + "D--- " + directory.getName(), "CYAN")
                        + colorText(" ____last modification___: ", "YELLOW")
                        + colorText(updatedAt(directory), "GREEN"));
                File[] files = directory.listFiles();
                if (files != null) {
                    Arrays.sort(files);
                    for (File file : files) {
                        if (file.isDirectory()) {
                            printFileNamesAsTree(file, grade + 1);
                        } else {
                            System.out.println(
                                    colorText(tabulation(grade + 1) + "F--- " + file.getName(), "CYAN")
                                    + colorText(" ___last modification of file___", "YELLOW")
                                    + colorText(updatedAt(file), "GREEN"));
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al imprimir el árbol de archivos: " + e.getMessage());
        }
    }

    public void saveLog(File directory, int grade, PrintWriter writer) {
        try {
            if (directory.isDirectory()) {
                writer.println(tabulation(grade) + "D--- " + directory.getName()
                        + " ____last modification___: "
                        + updatedAt(directory));
                File[] files = directory.listFiles();
                if (files != null) {
                    Arrays.sort(files);
                    for (File file : files) {
                        if (file.isDirectory()) {
                            saveLog(file, grade + 1, writer);
                        } else {
                            writer.println(
                                    tabulation(grade + 1) + "F--- " + file.getName()
                                            + " ___last modification of file___"
                                            + updatedAt(file));
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al imprimir el árbol de archivos: " + e.getMessage());
        }
    }

    public void searchTxtFiles(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    searchTxtFiles(file);
                } else if (file.getName().toLowerCase().endsWith(".txt")) {
                    System.out.println("File: " + file.getName());
                    System.out.println("Last modification: " + updatedAt(file));
                    System.out.println("Content: " + readTxt(file));
                    System.out.println("--------------------------------------------");
                }
            }
        }
    }

    private String readTxt(File file) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            return "Error al leer el contenido del archivo: " + e.getMessage();
        }
        return content.toString();
    }

    private String tabulation(int grade) {
        StringBuilder tab = new StringBuilder();
        for (int i = 0; i < grade; i++) {
            tab.append("|  ");
        }
        return tab.toString();
    }

    private String updatedAt(File file) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(file.lastModified()));
    }

    private String colorText(String text, String color) {
        String colorCode;
        switch (color.toUpperCase()) {
            case "CYAN":
                colorCode = "\u001B[36m";
                break;
            case "YELLOW":
                colorCode = "\u001B[33m";
                break;
            case "GREEN":
                colorCode = "\u001B[32m";
                break;
            default:
                colorCode = "\u001B[0m";
                break;
        }
        return colorCode + text + "\u001B[0m";
    }

}


