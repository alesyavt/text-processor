/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package edu.neu.ccs.cs5004.seattle.assignment9.html;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author yoganandc
 */
public class BlankLineTest {

    BlankLine b;

    public BlankLineTest() {
    }

    @Before
    public void setUp() {
        this.b = new BlankLine();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetInstance() {
        Assert.assertEquals(this.b, new BlankLine());
    }

    @Test
    public void testToPrettyString() {
        Assert.assertEquals("<br />\n", this.b.toPrettyString());
    }

    @Test
    public void testSetDepth() {
        this.b.setDepth(2);
        Assert.assertEquals((Integer) 2, this.b.getDepth());

        this.b.setDepth(0);
        Assert.assertEquals((Integer) 0, this.b.getDepth());
    }

    @Test
    public void testGetDepth() {
        Assert.assertEquals((Integer) 0, this.b.getDepth());

        this.b.setDepth(2);
        Assert.assertEquals((Integer) 2, this.b.getDepth());
    }

}
