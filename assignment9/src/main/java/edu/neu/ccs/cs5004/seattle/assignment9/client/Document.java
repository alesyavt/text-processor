/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package edu.neu.ccs.cs5004.seattle.assignment9.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.util.Objects;

import edu.neu.ccs.cs5004.seattle.assignment9.html.Section;

/**
 * Represents an HTML document with its root section
 *
 * @author yoganandc
 */
public class Document {
    
    private static final String BEFORE_TITLE
            = "<!DOCTYPE html>\n<html>\n<head>\n  <meta charset=\"utf-8\">\n  <title>";
    private static final String AFTER_TITLE = "</title>\n</head>\n<body>\n\n";
    private static final String FOOTER = "</body>\n</html>\n";
    
    private Section root;
    private final String title;

    /**
     * Creates a new empty document
     *
     * @param title the filename
     */
    public Document(String title) {
        if (title == null) {
            throw new IllegalArgumentException();
        }
        this.title = title;
    }

    /**
     * Retrieve the root section for this Document
     *
     * @return The root section
     */
    public Section getRoot() {
        return this.root;
    }

    /**
     * Sets the root of this section
     *
     * @param root The section to be set as root of this Document
     */
    public void setRoot(Section root) {
        this.root = root;
    }

    /**
     * Retrieve this document's title
     *
     * @return The title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Reads a template file and populates this document with parsed elements.
     * If the document has already been loaded with elements, those elements are
     * removed.
     *
     * @param filename The filename of the template file
     * @return An instance of Document representing the parsed template.
     */
    public static Document loadFromTemplate(String filename) {
        Document ret = new Document(filename);
        
        BufferedReader reader = null;
        String file = "";
        StringBuilder stringBuilder = new StringBuilder();
        
        try {
            reader = Files.newBufferedReader(FileSystems.getDefault().getPath(filename));
            String line;
            
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            
            file = stringBuilder.toString();
            ret.setRoot(new Section(Section.split(file)));

            // System.out.println(file);
        } catch (IOException e) {
            System.err.println("Error opening template file.");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println("Error closing stream");
                }
            }
        }
        
        return ret;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append(Document.BEFORE_TITLE);
        stringBuilder.append(this.title);
        stringBuilder.append(Document.AFTER_TITLE);
        
        stringBuilder.append(this.root.toPrettyString());
        stringBuilder.append(Document.FOOTER);
        
        return stringBuilder.toString();
    }

    /**
     * Entry point for this program
     *
     * @param args The command-line arguments
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Error: Filename not supplied");
            return;
        }
        BufferedWriter writer = null;
        // Path p = null;
        try {
            String inputFilename = args[0].trim();
            Document d = Document.loadFromTemplate(inputFilename);
            
            String outputFilename;
            if (inputFilename.endsWith(Document.EXT_TEXT)) {
                StringBuilder stringBuilder = new StringBuilder(inputFilename);
                int extensionIndex = stringBuilder.lastIndexOf(Document.EXT_TEXT);
                stringBuilder.replace(extensionIndex, stringBuilder.length(), Document.EXT_HTML);
                outputFilename = stringBuilder.toString();
            } else {
                outputFilename = inputFilename + Document.EXT_HTML;
            }
            
            writer = Files.newBufferedWriter(FileSystems.getDefault().getPath(outputFilename),
                    StandardCharsets.UTF_8);
            writer.write(d.toString());
            System.out.println("Successfully parsed file. Output file: " + outputFilename);
        } catch (InvalidPathException ipe) {
            System.err.println("Error: Invalid path");
            return;
        } catch (IOException e) {
            System.err.println("Error: I/O error while opening / creating output file");
        } finally {
            
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.err.println("Error: I/O error while closing output file");
                }
            }
            
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = (37 * hash) + Objects.hashCode(this.root);
        hash = (37 * hash) + Objects.hashCode(this.title);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Document other = (Document) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.root, other.root)) {
            return false;
        }
        return true;
    }
    
    private static final String EXT_HTML = ".html";
    private static final String EXT_TEXT = ".txt";
}
