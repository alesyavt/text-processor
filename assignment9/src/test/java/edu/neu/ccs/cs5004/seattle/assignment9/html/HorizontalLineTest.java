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
public class HorizontalLineTest {

  HorizontalLine b;

  public HorizontalLineTest() {}

  @Before
  public void setUp() {
    this.b = new HorizontalLine();
  }

  @After
  public void tearDown() {}

  @Test
  public void testToPrettyString() {
    Assert.assertEquals("<hr />\n", this.b.toPrettyString());
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
