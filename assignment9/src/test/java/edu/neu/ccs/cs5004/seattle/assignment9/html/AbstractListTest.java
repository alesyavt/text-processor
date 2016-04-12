/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package edu.neu.ccs.cs5004.seattle.assignment9.html;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author yoganandc
 */
public class AbstractListTest {

  AbstractList l1, l2, l3, l4, l5, l6, l7, l8;

  public AbstractListTest() {}

  @Before
  public void setUp() {
    List<String> list1 = new ArrayList<>();
    list1.add("1. This is item 1");
    list1.add("1. This is item 2");
    list1.add("  1. This is inner item 1");
    list1.add("  1. This is inner item 2");
    list1.add("1. This is item 3");
    this.l1 = AbstractList.createList(list1);

    List<String> list2 = new ArrayList<>();
    list2.add("* Item 1");
    list2.add("* Item 2");
    this.l2 = AbstractList.createList(list2);

    List<String> list3 = new ArrayList<>();
    list3.add("1. This is item 1");
    list3.add("1. This is item 2");
    list3.add("  1. This is inner item 1");
    list3.add("  1. This is inner item 2");
    list3.add("1. This is item 3");
    this.l3 = AbstractList.createList(list3);

    List<String> list4 = new ArrayList<>();
    list4.add("1. This is item 1");
    list4.add("1. This is item 2");
    list4.add("  1. This is inner item 1");
    list4.add("  1. This is inner item 2");
    list4.add("1. This is item 3");
    this.l4 = AbstractList.createList(list4);

    List<String> list5 = new ArrayList<>();
    list5.add("1. one ");
    list5.add("1. two  ");
    list5.add("  * inner one ");
    list5.add("  * inner two ");
    list5.add("    1. inner inner one ");
    list5.add("    1. inner inner two ");
    list5.add("    * inner inner mixed one ");
    list5.add("    * inner inner mixed two ");
    list5.add("  * inner three");
    list5.add("1. three ");
    this.l5 = AbstractList.createList(list5);
    this.l6 = AbstractList.createList(list5);
    this.l7 = AbstractList.createList(list5);

    List<String> list5_1 = new ArrayList<>();
    list5_1.add("1. one ");
    list5_1.add("1. two  ");
    list5_1.add("  * inner one ");
    list5_1.add("  * inner two ");
    list5_1.add("    1. inner inner one ");
    list5_1.add("    1. inner inner two ");
    list5_1.add("    * inner inner mixed one ");
    list5_1.add("    * inner inner mixed two ");
    list5_1.add("    1. inner inner mixed two ");
    list5_1.add("  * inner three");
    list5_1.add("    * inner three");
    list5_1.add("1. three ");

    this.l8 = AbstractList.createList(list5_1);

  }

  @After
  public void tearDown() {}

  @Test
  public void testCreateList() {
    List<String> list = new ArrayList<>();
    list.add("1. This is item 1");
    list.add("1. This is item 2");
    list.add("  1. This is inner item 1");
    list.add("  1. This is inner item 2");
    list.add("1. This is item 3");
    AbstractList ol = AbstractList.createList(list);

    String output1 = "<ol>\n" + "<li>This is item 1</li>\n" + "<li>This is item 2\n" + "<ol>\n"
        + "<li>This is inner item 1</li>\n" + "<li>This is inner item 2</li>\n" + "</ol>\n"
        + "</li>\n" + "<li>This is item 3</li>\n" + "</ol>\n";
    Assert.assertEquals(output1, ol.toPrettyString());

    OrderedList output2 = new OrderedList();
    output2.addItem(new Item("This is item 1"));
    Item item2 = new Item("This is item 2");
    output2.addItem(item2);
    OrderedList sublist = new OrderedList();
    item2.setSubList(sublist);
    sublist.addItem(new Item("This is inner item 1"));
    sublist.addItem(new Item("This is inner item 2"));
    output2.addItem(new Item("This is item 3"));
    Assert.assertEquals(output2, ol);

    List<String> list2 = new ArrayList<>();
    list2.add("*  Element 1");
    list2.add("*  Element 2");
    list2.add("  *  Element 3");
    list2.add("  *  Element 4");
    list2.add("    1. Element 5");
    list2.add("      * Element 6");
    list2.add("      * Element 7");
    list2.add("      * Element 8");
    list2.add("  *  Element 9");
    list2.add("    1. Element 10");
    list2.add("*  Element 11");
    list2.add("*  Element 12");
    AbstractList ul = AbstractList.createList(list2);

    String output3 = "<ul>\n" + "<li>Element 1</li>\n" + "<li>Element 2\n" + "<ul>\n"
        + "<li>Element 3</li>\n" + "<li>Element 4\n" + "<ol>\n" + "<li>Element 5\n" + "<ul>\n"
        + "<li>Element 6</li>\n" + "<li>Element 7</li>\n" + "<li>Element 8</li>\n" + "</ul>\n"
        + "</li>\n" + "</ol>\n" + "</li>\n" + "<li>Element 9\n" + "<ol>\n" + "<li>Element 10</li>\n"
        + "</ol>\n" + "</li>\n" + "</ul>\n" + "</li>\n" + "<li>Element 11</li>\n"
        + "<li>Element 12</li>\n" + "</ul>\n";
    Assert.assertEquals(output3, ul.toPrettyString());

    UnorderedList output4 = new UnorderedList();
    output4.addItem(new Item("Element 1"));
    Item item3 = new Item("Element 2");
    output4.addItem(item3);
    UnorderedList sublist2 = new UnorderedList();
    item3.setSubList(sublist2);
    sublist2.addItem(new Item("Element 3"));
    Item item4 = new Item("Element 4");
    sublist2.addItem(item4);
    OrderedList sublist3 = new OrderedList();
    item4.setSubList(sublist3);
    Item item5 = new Item("Element 5");
    sublist3.addItem(item5);
    UnorderedList sublist4 = new UnorderedList();
    item5.setSubList(sublist4);
    sublist4.addItem(new Item("Element 6"));
    sublist4.addItem(new Item("Element 7"));
    sublist4.addItem(new Item("Element 8"));
    Item item6 = new Item("Element 9");
    sublist2.addItem(item6);
    OrderedList sublist5 = new OrderedList();
    item6.setSubList(sublist5);
    sublist5.addItem(new Item("Element 10"));
    output4.addItem(new Item("Element 11"));
    output4.addItem(new Item("Element 12"));

    Assert.assertEquals(output4, ul);

    List<String> list3 = new ArrayList<>();
    list3.add("1. one");
    list3.add("1. two");
    list3.add("  * inner one");
    list3.add("  * inner two");
    list3.add("  1. inner three");
    list3.add("  1. inner four");
    list3.add("  * inner five");
    list3.add("1. three");
    AbstractList mixed = AbstractList.createList(list3);
    System.out.println("");
    OrderedList output5 = new OrderedList();
    output5.addItem(new Item("one"));
    Item item7 = new Item("two");
    output5.addItem(item7);
    UnorderedList sublist6 = new UnorderedList();
    item7.setSubList(sublist6);
    sublist6.addItem(new Item("inner one"));
    sublist6.addItem(new Item("inner two"));
    sublist6.mixedList = new OrderedList();
    sublist6.mixedList.addItem(new Item("inner three"));
    sublist6.mixedList.addItem(new Item("inner four"));
    sublist6.mixedList.mixedList = new UnorderedList();
    sublist6.mixedList.mixedList.addItem(new Item("inner five"));
    output5.addItem(new Item("three"));
  }

  @Test
  public void testAddItem1() {
    List<String> list = new ArrayList<>();
    list.add("1. Item 1");
    list.add("1. Item 2");
    AbstractList ol = AbstractList.createList(list);
    Assert.assertEquals((Integer) 2, (Integer) ol.itemList.size());

    ol.addItem(new Item("Item 3"));
    Assert.assertEquals((Integer) 3, (Integer) ol.itemList.size());
    Assert.assertTrue(ol.itemList.contains(new Item("Item 3")));
  }

  @Test(expected = NullPointerException.class)
  public void testAddItem2() {
    List<String> list = new ArrayList<>();
    list.add("1. Item 1");
    list.add("1. Item 2");
    AbstractList ol = AbstractList.createList(list);
    ol.addItem(null);
  }

  @Test
  public void testHashCode() {
    Integer h1 = this.l1.hashCode();

    for (Integer i = 0; i < 100; i++) {
      Assert.assertEquals((Object) h1, this.l1.hashCode()); // CONSISTENT
    }

    Assert.assertEquals(this.l1.hashCode(), this.l3.hashCode()); // EQUAL OBJECTS PRODUCE EQUAL
    // HASHCODE
    Assert.assertEquals(this.l6.hashCode(), this.l7.hashCode());
    Assert.assertFalse(this.l1.equals(this.l2));
    Assert.assertFalse(this.l8.equals(this.l7));
  }

  @Test
  public void testEquals() {
    Assert.assertEquals(true, this.l1.equals(this.l1)); // REFLEXIVE
    Assert.assertEquals(true, this.l5.equals(this.l5));

    Assert.assertEquals(true, this.l1.equals(this.l3));
    Assert.assertEquals(true, this.l3.equals(this.l1)); // SYMMETRIC
    Assert.assertEquals(false, this.l5.equals(this.l8));
    Assert.assertEquals(false, this.l8.equals(this.l5)); // SYMMETRIC

    Assert.assertEquals(true, this.l1.equals(this.l3));
    Assert.assertEquals(true, this.l3.equals(this.l4));
    Assert.assertEquals(true, this.l1.equals(this.l4)); // TRANSITIVE
    Assert.assertEquals(true, this.l5.equals(this.l6));
    Assert.assertEquals(true, this.l6.equals(this.l7));
    Assert.assertEquals(true, this.l5.equals(this.l7)); // TRANSITIVE

    for (Integer i = 0; i < 100; i++) {
      Assert.assertEquals(false, this.l1.equals(this.l2)); // CONSISTENT
    }
    for (Integer i = 0; i < 100; i++) {
      Assert.assertEquals(false, this.l5.equals(this.l3)); // CONSISTENT
    }

    Assert.assertEquals(false, this.l1.equals(null)); // NON-NULL REFERENCE MUST NOT BE EQUAL TO
    // NULL
    Assert.assertEquals(false, this.l5.equals(null));
    Assert.assertFalse(this.l1.equals(new Object()));
    Assert.assertFalse(this.l5.equals(new OrderedList()));
    Assert.assertFalse(new UnorderedList().equals(new OrderedList()));
  }

  @Test
  public void testToPrettyString() {
    String l5String = "<ol>\n" + "<li>one</li>\n" + "<li>two\n" + "<ul>\n" + "<li>inner one</li>\n"
        + "<li>inner two\n" + "<ol>\n" + "<li>inner inner one</li>\n" + "<li>inner inner two</li>\n"
        + "</ol>\n" + "<ul>\n" + "<li>inner inner mixed one</li>\n"
        + "<li>inner inner mixed two</li>\n" + "</ul>\n" + "</li>\n" + "<li>inner three</li>\n"
        + "</ul>\n" + "</li>\n" + "<li>three</li>\n</ol>\n";
    System.out.println(l5String);
    System.out.println(this.l5.toPrettyString());
    Assert.assertEquals(l5String, this.l5.toPrettyString());
  }
}
