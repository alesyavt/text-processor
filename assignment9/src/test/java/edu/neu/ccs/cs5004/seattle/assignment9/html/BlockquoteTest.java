/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.ccs.cs5004.seattle.assignment9.html;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yoganandc
 */
public class BlockquoteTest {

    Blockquote b1, b2, b3, b4;

    public BlockquoteTest() {
    }

    @Before
    public void setUp() {
        List<String> list1 = new ArrayList<>();
        list1.add("> You can see an example [Confused](https://media.giphy.com/media/wi9yHmX7Sztuw/giphy.gif).");
        list1.add(">> You can read more [here](https://en.wikipedia.org/wiki/Confusion)");
        b1 = new Blockquote(list1, 1);

        List<String> list2 = new ArrayList<>();
        list2.add("> So here is some *bold* text and some _italicized_ text.");
        list2.add("> We can also have *_this_*, as well as well as _*this*_.");
        b2 = new Blockquote(list2);

        List<String> list3 = new ArrayList<>();
        list3.add("> You can see an example [Confused](https://media.giphy.com/media/wi9yHmX7Sztuw/giphy.gif).");
        list3.add(">> You can read more [here](https://en.wikipedia.org/wiki/Confusion)");
        b3 = new Blockquote(list3, 1);

        List<String> list4 = new ArrayList<>();
        list4.add("> You can see an example [Confused](https://media.giphy.com/media/wi9yHmX7Sztuw/giphy.gif).");
        list4.add(">> You can read more [here](https://en.wikipedia.org/wiki/Confusion)");
        b4 = new Blockquote(list4, 1);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testToPrettyString() {
        List<String> test = new ArrayList<>();
        test.add("> What are the resources allocated?");
        test.add(">> Are you asking in terms of headcount or machines?");
        test.add(">>> Headcount");
        test.add(">>>>> jump from 3rd to 5th");

        String expectedOutput1
                = "<blockquote>"
                + "<p> What are the resources allocated?</p>\n"
                + "<blockquote>"
                + "<p> Are you asking in terms of headcount or machines?</p>\n"
                + "<blockquote>"
                + "<p> Headcount</p>\n"
                + "<blockquote>"
                + "<blockquote>"
                + "<p> jump from 3rd to 5th</p>\n"
                + "</blockquote>\n"
                + "</blockquote>\n"
                + "</blockquote>\n"
                + "</blockquote>\n"
                + "</blockquote>\n";
        Blockquote bq = new Blockquote(test);
        assertEquals(expectedOutput1, bq.toPrettyString());
    }

    @Test
    public void testHashCode() {
        Integer h1 = this.b1.hashCode();

        for (Integer i = 0; i < 100; i++) {
            Assert.assertEquals((Object) h1, this.b1.hashCode()); // CONSISTENT
        }

        Assert.assertEquals(this.b1.hashCode(), this.b3.hashCode()); // EQUAL OBJECTS PRODUCE EQUAL
        // HASHCODE
        Assert.assertFalse(this.b1.equals(this.b2));
    }

    @Test
    public void testEquals() {
        Assert.assertEquals(true, this.b1.equals(this.b1)); // REFLEXIVE

        Assert.assertEquals(true, this.b1.equals(this.b3));
        Assert.assertEquals(true, this.b3.equals(this.b1)); // SYMMETRIC

        Assert.assertEquals(true, this.b1.equals(this.b3));
        Assert.assertEquals(true, this.b3.equals(this.b4));
        Assert.assertEquals(true, this.b1.equals(this.b4)); // TRANSITIVE

        for (Integer i = 0; i < 100; i++) {
            Assert.assertEquals(false, this.b1.equals(this.b2)); // CONSISTENT
        }

        Assert.assertEquals(false, this.b1.equals(null)); // NON-NULL REFERENCE MUST NOT BE EQUAL TO
        // NULL
        Assert.assertFalse(this.b1.equals(new Object()));
    }

}
