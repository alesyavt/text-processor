/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.ccs.cs5004.seattle.assignment9.html;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yoganandc
 */
public class BlockquoteTest {

    public BlockquoteTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testToPrettyString() {
        List<String> test = new ArrayList<>();
        test.add("> What are the resources allocated?");
        test.add(">> Are you asking in terms of headcount or machines?");
        test.add("just text");
        test.add(">>>Headcount");
        test.add("more text");
        test.add(">>>>> jump from 2nd to 5th");
        test.add("> 1st level again");

        String expectedOutput1 = "<blockquote><p> What are the resources allocated?</p>\n"
                + "<blockquote><p> Are you asking in terms of headcount or machines? just text >>>Headcount more text</p>\n"
                + "<blockquote><blockquote><blockquote><p> jump from 2nd to 5th</p>\n"
                + "</blockquote>\n"
                + "</blockquote>\n"
                + "</blockquote>\n"
                + "</blockquote>\n"
                + "<p> 1st level again</p>\n"
                + "</blockquote>\n";

        Blockquote bq = new Blockquote(test);
        assertEquals(expectedOutput1, bq.toPrettyString());
    }

    @Test
    public void testHashCode() {
    }

    @Test
    public void testEquals() {
    }

}
