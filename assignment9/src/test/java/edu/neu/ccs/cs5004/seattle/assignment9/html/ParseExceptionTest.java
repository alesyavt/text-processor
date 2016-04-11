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
public class ParseExceptionTest {

  ParseException p1, p2;

  public ParseExceptionTest() {

  }

  @Before
  public void setUp() {
    this.p1 = new ParseException("Incorrectly formatted list");
    this.p2 = new ParseException("Header formatted incorrectly.");
  }

  @After
  public void tearDown() {}

  @Test
  public void testGetMessage() {
    Assert.assertEquals("Incorrectly formatted list", this.p1.getMessage());
    Assert.assertEquals("Header formatted incorrectly.", this.p2.getMessage());
  }

}
