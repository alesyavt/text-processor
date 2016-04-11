package edu.neu.ccs.cs5004.seattle.assignment9.html;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SectionTest {

    Section root1;
    Section root2;
    Section root3;
    Section root4;

    @Before
    public void setUp() {

        this.root1 = new Section();
        Section section1 = new Section("Header at Level 1");
        this.root1.add(section1);
        section1.add(new BlankLine());
        section1.add(new Paragraph(
                "Headers appear on a line of their own. A header starts with the one or more occurrences of the symbol `#` followed"
                + "\nby one space followed by text and ends with a newline."
                + "\nHeaders are section titles. The number of `#` symbols indicate the nesting of headers.\n"));
        List<String> list1 = new ArrayList<>();

        list1.add("*  Element 1");
        list1.add("*  Element 2");
        list1.add("  *  Element 3");
        list1.add("    1. Element 4");
        list1.add("    1. Element 5");
        list1.add("    1. Element 6");

        section1.add(AbstractList.createList(list1));

        this.root2 = new Section();
        Section section2 = new Section("Header at Level 1");
        this.root2.add(section2);
        section2.add(new Section("header within section"));
        section2.add(new Paragraph("Headers appear on a line of their own."));

        this.root3 = new Section();
        Section section11 = new Section("Header at Level 1");
        this.root3.add(section11);
        section11.add(new BlankLine());
        section11.add(new Paragraph(
                "Headers appear on a line of their own. A header starts with the one or more occurrences of the symbol `#` followed"
                + "\nby one space followed by text and ends with a newline."
                + "\nHeaders are section titles. The number of `#` symbols indicate the nesting of headers.\n"));

        section11.add(AbstractList.createList(list1));

        this.root4 = new Section();
        Section section12 = new Section("Header at Level 1");
        this.root4.add(section12);
        section12.add(new BlankLine());
        section12.add(new Paragraph(
                "Headers appear on a line of their own. A header starts with the one or more occurrences of the symbol `#` followed"
                + "\nby one space followed by text and ends with a newline."
                + "\nHeaders are section titles. The number of `#` symbols indicate the nesting of headers.\n"));

        section12.add(AbstractList.createList(list1));

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToPrettyString() {
        String root1PrettyString = "<h1>Header at Level 1</h1>\n\n" + "<br />\n\n"
                + "<p>Headers appear on a line of their own. A header starts with the one or more occurrences of the symbol `#` followed"
                + "\nby one space followed by text and ends with a newline.\n"
                + "Headers are section titles. The number of `#` symbols indicate the nesting of headers.\n</p>\n\n"
                + "<ul>\n" + "<li>Element 1</li>\n" + "<li>Element 2\n" + "<ul>\n" + "<li>Element 3\n"
                + "<ol>\n" + "<li>Element 4</li>\n" + "<li>Element 5</li>\n" + "<li>Element 6</li>\n"
                + "</ol>\n</li>\n</ul>\n</li>\n</ul>\n\n";
        // System.out.println(root1PrettyString);
        // System.out.println(this.root1.toPrettyString());
        Assert.assertEquals(root1PrettyString, this.root1.toPrettyString());
    }

    @Test
    public void testParse() {

        String block1 = "# Header at Level 1\n\n"
                + "Headers appear on a line of their own. A header starts with the one or more occurrences of the symbol `#` followed"
                + "\nby one space followed by text and ends with a newline.\n"
                + "Headers are section titles. The number of `#` symbols indicate the nesting of headers.\n\n"
                + "*  Element 1\n" + "*  Element 2\n" + "  *  Element 3\n" + "    1. Element 4\n"
                + "    1. Element 5\n" + "    1. Element 6\n\n";

        Section result1 = new Section(Section.split(block1));
        Assert.assertEquals(this.root1, result1);
    }

//  @Test(expected = NullPointerException.class)
//  public void testConstructor() {
//    Section s = new Section(null);
//  }
    @Test
    public void testGetDepth() {
        Section root = new Section();
        Section depth1 = new Section("H1");
        Section depth2 = new Section("H2");
        root.add(depth1);
        depth1.add(depth2);

        Assert.assertEquals((Integer) 0, root.getDepth());
        Assert.assertEquals((Integer) 1, depth1.getDepth());
        Assert.assertEquals((Integer) 2, depth2.getDepth());
    }

    @Test
    public void testHashCode() {
        Integer root1 = this.root1.hashCode();

        for (Integer i = 0; i < 100; i++) {
            Assert.assertEquals((Object) root1, this.root1.hashCode()); // CONSISTENT
        }

        Assert.assertEquals(this.root1.hashCode(), this.root3.hashCode()); // EQUAL OBJECTS PRODUCE
        // EQUAL HASHCODE
        Assert.assertFalse(this.root1.equals(this.root2));
    }

    @Test
    public void testSetDepth() {
        Section depth1 = new Section("H1");
        Section depth2 = new Section("H2");

        depth1.setDepth(0);
        depth2.setDepth(1);

        Assert.assertEquals((Integer) 1, depth1.getDepth());
        Assert.assertEquals((Integer) 2, depth2.getDepth());
    }

    @Test
    public void testEquals() {
        Assert.assertEquals(true, this.root1.equals(this.root1)); // REFLEXIVE

        Assert.assertEquals(true, this.root1.equals(this.root3));
        Assert.assertEquals(true, this.root3.equals(this.root1)); // SYMMETRIC

        Assert.assertEquals(true, this.root1.equals(this.root3));
        Assert.assertEquals(true, this.root3.equals(this.root4));
        Assert.assertEquals(true, this.root1.equals(this.root4)); // TRANSITIVE

        for (Integer i = 0; i < 100; i++) {
            Assert.assertEquals(false, this.root1.equals(this.root2)); // CONSISTENT
        }
        for (Integer i = 0; i < 100; i++) {
            Assert.assertEquals(true, this.root1.equals(this.root3)); // CONSISTENT
        }

        Assert.assertEquals(false, this.root1.equals(null)); // NON-NULL REFERENCE MUST NOT BE EQUAL TO
        // NULL
        Assert.assertFalse(this.root1.equals(new Object()));

        Assert.assertFalse(this.root1.equals(this.root2));
    }

    @Test
    public void testAdd() {
        Section root = new Section();
        Section head = new Section("H1");
        Paragraph p = new Paragraph("ezhik kotik");
        root.add(head);
        root.add(p);
        Assert.assertTrue(root.getNodes().contains(head));
        Assert.assertTrue(root.getNodes().contains(p));
        Assert.assertEquals((Integer) 1, head.getDepth());
        Assert.assertEquals((Integer) 0, p.getDepth());
    }

    @Test
    public void testGetNodes() {
        Section root = new Section();
        List<AbstractElement> list = new ArrayList<>();
        Assert.assertEquals(list, root.getNodes());

        Section s = new Section("HEADING");
        root.add(s);
        list.add(s);
        Assert.assertEquals(list, root.getNodes());
    }

    @Test(expected = NullPointerException.class)
    public void testAdd2() {
        Section root = new Section();
        root.add(null);
    }

}
