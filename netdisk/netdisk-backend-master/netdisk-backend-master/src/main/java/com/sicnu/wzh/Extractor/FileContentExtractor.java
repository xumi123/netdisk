package com.sicnu.wzh.Extractor;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileContentExtractor {
    public static String extractContent(File file) {
        String fileName = file.getName();
        if (fileName.endsWith(".txt")) {
            return extractTextContent(file);
        } else if (fileName.endsWith(".pdf") || fileName.endsWith(".doc") || fileName.endsWith(".docx")) {
            return extractDocumentContent(file);
        }
        return null;
    }

    private static String extractTextContent(File file) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    private static String extractDocumentContent(File file) {
        Tika tika = new Tika();
        try {
            return tika.parseToString(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (TikaException e) {
            throw new RuntimeException(e);
        }
    }
}