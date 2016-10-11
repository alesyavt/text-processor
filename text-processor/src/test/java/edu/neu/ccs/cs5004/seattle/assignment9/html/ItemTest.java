package edu.neu.ccs.cs5004.seattle.assignment9.html;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemTest {
  Item item1;
  Item item2;
  Item item3;
  Item item4;

  @Before
  public void setUp() throws Exception {
    this.item1 = new Item("cat");
    List<String> catSublist = new ArrayList<String>();
    catSublist.add("* kitty");
    catSublist.add("* kotik");
    this.item1.setSubList(AbstractList.createList(catSublist));
    this.item2 = new Item("hedgie");
    this.item3 = new Item("cat");
    this.item3.setSubList(AbstractList.createList(catSublist));
    this.item4 = new Item("cat");
    this.item4.setSubList(AbstractList.createList(catSublist));
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testToPrettyString() {
    Assert.assertEquals("<li>hedgie</li>\n", this.item2.toPrettyString());
    Assert.assertEquals("<li>cat\n<ul>\n<li>kitty</li>\n<li>kotik</li>\n</ul>\n</li>\n",
        this.item1.toPrettyString());
  }

  @Test
  public void testGetSubList() {
    List<String> catSublist = new ArrayList<String>();
    catSublist.add("* kitty");
    catSublist.add("* kotik");
    AbstractList actualCatSub = AbstractList.createList(catSublist);
    Assert.assertEquals(this.item1.getSubList(), actualCatSub);
  }

  @Test
  public void testSetSubList() {
    List<String> hedgieSub = new ArrayList<String>();
    hedgieSub.add("1. ezhik");
    AbstractList expectedHedgieSub = AbstractList.createList(hedgieSub);
    this.item2.setSubList(expectedHedgieSub);
    Assert.assertEquals(expectedHedgieSub, this.item2.getSubList());
  }

  @Test
  public void testHashCode() {
    Integer item1 = this.item1.hashCode();

    for (Integer i = 0; i < 100; i++) {
      Assert.assertEquals((Object) item1, this.item1.hashCode()); // CONSISTENT
    }

    Assert.assertEquals(this.item1.hashCode(), this.item3.hashCode()); // EQUAL OBJECTS PRODUCE
                                                                       // EQUAL HASHCODE
    Assert.assertFalse(this.item1.equals(this.item2));
  }

  @Test
  public void testEquals() {
    Assert.assertEquals(true, this.item1.equals(this.item1)); // REFLEXIVE
    Assert.assertEquals(true, this.item2.equals(this.item2));

    Assert.assertEquals(true, this.item1.equals(this.item3));
    Assert.assertEquals(true, this.item3.equals(this.item1)); // SYMMETRIC

    Assert.assertEquals(true, this.item1.equals(this.item3));
    Assert.assertEquals(true, this.item3.equals(this.item4));
    Assert.assertEquals(true, this.item1.equals(this.item4)); // TRANSITIVE

    for (Integer i = 0; i < 100; i++) {
      Assert.assertEquals(false, this.item1.equals(this.item2)); // CONSISTENT
    }
    for (Integer i = 0; i < 100; i++) {
      Assert.assertEquals(true, this.item1.equals(this.item3)); // CONSISTENT
    }

    Assert.assertEquals(false, this.item1.equals(null)); // NON-NULL REFERENCE MUST NOT BE EQUAL TO
    // NULL
    Assert.assertFalse(this.item1.equals(new Object()));

    Assert.assertFalse(this.item1.equals(this.item2));
  }
}
