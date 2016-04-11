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
public class AbstractListTest {

    public AbstractListTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

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
    }

    @Test
    public void testAddItem() {
    }

    @Test
    public void testHashCode() {
    }

    @Test
    public void testEquals() {
    }
}
