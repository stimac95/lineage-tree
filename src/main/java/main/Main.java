package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1){
            System.out.println("Received invalid number of arguments.");
            System.out.println("Expected a path to lineage tree file");
            return;
        }
        Path inputFilePath = Paths.get(args[0]);
        try (BufferedReader fileReader = Files.newBufferedReader(inputFilePath)){
            fileReader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
