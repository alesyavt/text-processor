package edu.neu.ccs.cs5004.seattle.assignment9.html;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParagraphTest {

    Paragraph p1;
    Paragraph p2;
    Paragraph p3;
    Paragraph p4;

    @Before
    public void setUp() throws Exception {
        this.p1 = new Paragraph(
                "Headers appear on a line of their own. A header starts with one or more occurrences of the symbol # followed\n"
                + "by one space followed by text and ends with a newline. \n"
                + "Headers are section titles. The number of # symbols indicate the nesting of headers.");

        this.p2 = new Paragraph("Headers appear on a line of their own.");

        this.p3 = new Paragraph(
                "Headers appear on a line of their own. A header starts with one or more occurrences of the symbol # followed\n"
                + "by one space followed by text and ends with a newline. \n"
                + "Headers are section titles. The number of # symbols indicate the nesting of headers.");

        this.p4 = new Paragraph(
                "Headers appear on a line of their own. A header starts with one or more occurrences of the symbol # followed\n"
                + "by one space followed by text and ends with a newline. \n"
                + "Headers are section titles. The number of # symbols indicate the nesting of headers.");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test(expected = NullPointerException.class)
    public void testConstructor() {
        Paragraph invalidPara = new Paragraph(null);
    }

    @Test
    public void testToPrettyString() {
        String expected
                = "<p>Headers appear on a line of their own. A header starts with one or more occurrences of the symbol # followed\n"
                + "by one space followed by text and ends with a newline. \n"
                + "Headers are section titles. The number of # symbols indicate the nesting of headers.</p>\n";

        Assert.assertEquals(expected, this.p1.toPrettyString());

        String test1
                = "This paragraph should look like normal *except for this bit* which should be in bold";
        String output1
                = "<p>This paragraph should look like normal <strong>except for this bit</strong> which should be in bold</p>\n";
        Assert.assertEquals(output1, new Paragraph(test1).toPrettyString());

        String test2 = "Nothing in this paragraph should be in bold";
        String output2 = "<p>Nothing in this paragraph should be in bold</p>\n";
        Assert.assertEquals(output2, new Paragraph(test2).toPrettyString());

        String test3 = "This paragraph *should have two* sections in *bold*";
        String output3 = "<p>This paragraph <strong>should have two</strong> sections in <strong>bold</strong></p>\n";
        Assert.assertEquals(output3, new Paragraph(test3).toPrettyString());

        String test4 = "The rest of this sentence after this point *should be in bold";
        String output4 = "<p>The rest of this sentence after this point <strong>should be in bold</strong></p>\n";
        Assert.assertEquals(output4, new Paragraph(test4).toPrettyString());

        String test5 = "You can see an example [Confused](https://media.giphy.com/media/wi9yHmX7Sztuw/giphy.gif). \n"
                + "You can read more [here](https://en.wikipedia.org/wiki/Confusion)";
        String output5 = "<p>You can see an example <a href=\"https://media.giphy.com/media/wi9yHmX7Sztuw/giphy.gif\">Confused</a>. \n"
                + "You can read more <a href=\"https://en.wikipedia.org/wiki/Confusion\">here</a></p>\n";
        Assert.assertEquals(output5, new Paragraph(test5).toPrettyString());
    }

    @Test
    public void testHashCode() {
        Integer h1 = this.p1.hashCode();

        for (Integer i = 0; i < 100; i++) {
            Assert.assertEquals((Object) h1, this.p1.hashCode()); // CONSISTENT
        }

        Assert.assertEquals(this.p1.hashCode(), this.p3.hashCode()); // EQUAL OBJECTS PRODUCE EQUAL
        // HASHCODE
        Assert.assertFalse(this.p1.equals(this.p2));
    }

    @Test
    public void testEquals() {
        Assert.assertEquals(true, this.p1.equals(this.p1)); // REFLEXIVE

        Assert.assertEquals(true, this.p1.equals(this.p3));
        Assert.assertEquals(true, this.p3.equals(this.p1)); // SYMMETRIC

        Assert.assertEquals(true, this.p1.equals(this.p3));
        Assert.assertEquals(true, this.p3.equals(this.p4));
        Assert.assertEquals(true, this.p1.equals(this.p4)); // TRANSITIVE

        for (Integer i = 0; i < 100; i++) {
            Assert.assertEquals(false, this.p1.equals(this.p2)); // CONSISTENT
        }

        Assert.assertEquals(false, this.p1.equals(null)); // NON-NULL REFERENCE MUST NOT BE EQUAL TO
        // NULL
        Assert.assertFalse(this.p1.equals(new Object()));
    }

}
