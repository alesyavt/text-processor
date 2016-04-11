/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package edu.neu.ccs.cs5004.seattle.assignment9.client;

import edu.neu.ccs.cs5004.seattle.assignment9.html.BlankLine;
import edu.neu.ccs.cs5004.seattle.assignment9.html.Item;
import edu.neu.ccs.cs5004.seattle.assignment9.html.OrderedList;
import edu.neu.ccs.cs5004.seattle.assignment9.html.Paragraph;
import edu.neu.ccs.cs5004.seattle.assignment9.html.Section;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author yoganandc
 */
public class DocumentTest {

    Document doc1;
    Document doc2;
    Document doc3;
    Document doc4;

    @Before
    public void setUp() {
        this.doc1 = Document.loadFromTemplate("test-1.txt");
        this.doc2 = Document.loadFromTemplate("test-2.txt");
        this.doc3 = Document.loadFromTemplate("test-1.txt");
        this.doc4 = Document.loadFromTemplate("test-1.txt");

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetRoot() {
        Document d = new Document("Title 1");
        Assert.assertEquals(null, d.getRoot());

        Document d2 = new Document("Title 2");
        d2.setRoot(new Section());
        Assert.assertEquals(new Section(), d2.getRoot());

    }

    @Test
    public void testSetRoot() {
        Document d = new Document("Title 1");
        Assert.assertEquals(null, d.getRoot());

        Document d2 = new Document("Title 2");
        d2.setRoot(new Section());
        Assert.assertEquals(new Section(), d2.getRoot());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor() {
        Document d = new Document(null);
    }

    @Test
    public void testGetTitle() {
        Document d = new Document("Title 1");
        Assert.assertEquals("Title 1", d.getTitle());

        Document d2 = new Document("Title 2");
        Assert.assertEquals("Title 2", d2.getTitle());
    }

    @Test
    public void testLoadFromTemplate() {
        Document d1 = Document.loadFromTemplate("test-1.txt");
        Document d2 = Document.loadFromTemplate("test-2.txt");

        Document output1 = new Document("test-1.txt");
        Section root1 = new Section();
        output1.setRoot(root1);
        Section first = new Section("Header at Level 1");
        root1.add(first);
        first.add(new BlankLine());
        String contents
                = "Headers appear on a line of their own. A header starts with the one or more occurrences of the symbol `#` followed\n"
                + "by one space followed by text and ends with a newline. \n"
                + "Headers are section titles. The number of `#` symbols indicate the nesting of headers.\n";
        first.add(new Paragraph(contents));

        Assert.assertEquals(output1, d1);

        Document output2 = new Document("test-2.txt");
        Section root2 = new Section();
        output2.setRoot(root2);
        Section first2 = new Section("Header at Level 1");
        root2.add(first2);
        first2.add(new BlankLine());
        Section second = new Section("This is a header at level 2");
        first2.add(second);
        Paragraph p = new Paragraph("Paragraphs are free form text. Paragraphs are separated by new lines. \n"
                + "This is still the first paragraph in this section\n");
        second.add(p);
        Section third = new Section("Numbered Lists");
        root2.add(third);
        OrderedList ol = new OrderedList();
        third.add(ol);
        ol.addItem(new Item("This is the first item of the outer list"));
        Item item = new Item("This is the second item of the outer list");
        ol.addItem(item);
        OrderedList sublist = new OrderedList();
        item.setSubList(sublist);
        sublist.addItem(new Item("This is the first item of the inner list"));
        sublist.addItem(new Item("This is the second item of the inner list"));
        ol.addItem(new Item("This is the third item of the outer list"));

        Assert.assertEquals(output2, d2);
    }

    @Test
    public void testToString() {
        Document d1 = Document.loadFromTemplate("test-1.txt");
        Document d2 = Document.loadFromTemplate("test-2.txt");

        String op1 = "<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "  <meta charset=\"utf-8\">\n"
                + "  <title>test-1.txt</title>\n" + "</head>\n" + "<body>\n" + "\n"
                + "<h1>Header at Level 1</h1>\n" + "\n" + "<br />\n" + "\n"
                + "<p>Headers appear on a line of their own. A header starts with the one or more occurrences of the symbol `#` followed\n"
                + "by one space followed by text and ends with a newline. \n"
                + "Headers are section titles. The number of `#` symbols indicate the nesting of headers.\n</p>\n"
                + "\n" + "</body>\n" + "</html>\n";
        String op2 = "<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "  <meta charset=\"utf-8\">\n"
                + "  <title>test-2.txt</title>\n" + "</head>\n" + "<body>\n" + "\n"
                + "<h1>Header at Level 1</h1>\n" + "\n" + "<br />\n" + "\n"
                + "<h2>This is a header at level 2</h2>\n" + "\n"
                + "<p>Paragraphs are free form text. Paragraphs are separated by new lines. \n"
                + "This is still the first paragraph in this section\n</p>\n" + "\n"
                + "<h1>Numbered Lists</h1>\n" + "\n" + "<ol>\n"
                + "<li>This is the first item of the outer list</li>\n"
                + "<li>This is the second item of the outer list\n" + "<ol>\n"
                + "<li>This is the first item of the inner list</li>\n"
                + "<li>This is the second item of the inner list</li>\n" + "</ol>\n" + "</li>\n"
                + "<li>This is the third item of the outer list</li>\n" + "</ol>\n" + "\n" + "</body>\n"
                + "</html>\n";
        System.out.println(op2);
        System.out.println("--------------");
        System.out.println(d2.toString());
        Assert.assertEquals(op1, d1.toString());
        Assert.assertEquals(op2, d2.toString());
    }

    @Test
    public void testMain() {
        Path p1 = FileSystems.getDefault().getPath("test-1.html");
        Path p2 = FileSystems.getDefault().getPath("test-2.html");

        String args[] = {"test-1.txt"};
        Document.main(args);

        BufferedReader reader = null;
        String fileContents = "";
        try {
            reader = Files.newBufferedReader(p1);
            String line;

            while ((line = reader.readLine()) != null) {
                fileContents += line + "\n";
            }
        } catch (IOException e) {
            System.err.println("Error while reading file");
        } finally {
            if (reader != null) {

                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println("Error while closing file");
                }

            }
        }

        Assert.assertEquals(this.doc1.toString(), fileContents);

        String args1[] = new String[0];
        Document.main(args1);

        String args2[] = {"test-1"};
        Document.main(args2);

        try {
            if (Files.exists(p1)) {
                Files.delete(p1);
            }
            if (Files.exists(p2)) {
                Files.delete(p2);
            }
        } catch (IOException e) {
            System.err.println("Error deleting file");
        }
    }

    @Test
    public void testHashCode() {
        Integer h1 = this.doc1.hashCode();
        for (Integer i = 0; i < 100; i++) {
            Assert.assertEquals((Object) h1, this.doc1.hashCode()); // CONSISTENT
        }

        Assert.assertEquals(this.doc1.hashCode(), this.doc3.hashCode()); // EQUAL OBJECTS PRODUCE EQUAL
        // HASHCODE
        Assert.assertFalse(this.doc1.equals(this.doc2));
    }

    @Test
    public void testEquals() {
        Assert.assertEquals(true, this.doc1.equals(this.doc1)); // REFLEXIVE

        Assert.assertEquals(true, this.doc1.equals(this.doc3));
        Assert.assertEquals(true, this.doc3.equals(this.doc1)); // SYMMETRIC

        Assert.assertEquals(true, this.doc1.equals(this.doc3));
        Assert.assertEquals(true, this.doc3.equals(this.doc4));
        Assert.assertEquals(true, this.doc1.equals(this.doc4)); // TRANSITIVE

        for (Integer i = 0; i < 100; i++) {
            Assert.assertEquals(false, this.doc1.equals(this.doc2)); // CONSISTENT
        }

        Assert.assertEquals(false, this.doc1.equals(null)); // NON-NULL REFERENCE MUST NOT BE EQUAL TO
        // NULL
        Assert.assertFalse(this.doc1.equals(new Object()));
    }
}
