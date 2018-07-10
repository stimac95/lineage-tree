package main;

import lineage.LineageForest;
import lineage.exception.LineageLoopException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1){
            System.out.println("Received invalid number of arguments.");
            System.out.println("Expected a path to lineage tree file.");
            return;
        }
        Path inputFilePath = Paths.get(args[0]);
        inputFilePath = inputFilePath.toAbsolutePath().normalize();
        LineageForest lineage = new LineageForest();
        try (BufferedReader fileReader = Files.newBufferedReader(inputFilePath)){
            String line = null;
            while ((line = fileReader.readLine()) != null){
                String[] chunks = line.split(" ");
                if (chunks.length != 2){
                    throw new IllegalArgumentException("Expected line: child parent  received: " + line);
                } else {
                    lineage.addNode(chunks[1], chunks[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineageLoopException e) {
            e.printStackTrace();
            return;
        }
        lineage.printLineageForest();

    }
}
