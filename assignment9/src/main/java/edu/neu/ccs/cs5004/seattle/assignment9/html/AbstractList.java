package edu.neu.ccs.cs5004.seattle.assignment9.html;
/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.List;

/**
 * Represents any list element in HTML
 *
 * @author yoganandc
 */
public abstract class AbstractList extends AbstractElement {

  private static final String ORDERED_CHAR = "1.";
  private static final int NESTING_SPACES = 2;

  protected final List<Item> list;

  /**
   * Creates an html list
   *
   * @param input a list of strings formatted in html list-template style, with ordered lists
   *        beginning with special character "1." and unordered lists with special character "* ".
   *        Every two leading spaces represent an extra nesting level. The first level has zero
   *        leading spaces.
   * @return an abstract list in html format
   */
  public static AbstractList createList(List<String> input) {
    return AbstractList.parseList(input);
  }

  /**
   * Creates a new instance of AbstractList
   */
  protected AbstractList() {
    super(null);
    this.list = new ArrayList<>();
  }

  /**
   * Add a ListElement to this List
   *
   * @param el The element to be added
   * @throws NullPointerException if el is null
   */
  public void addItem(Item el) {
    if (el == null) {
      throw new NullPointerException();
    }
    this.list.add(el);
  }

  /**
   * Parses the given list of strings and converts to html format based on the html list template
   *
   * @param input a list of strings formatted in html list-template style
   * @return the list in html format
   */
  private static AbstractList parseList(List<String> input) {
    AbstractList resultingList = null;

    String orderedChar = "1.";
    String unorderedChar = "* ";

    if (input.get(0).startsWith(orderedChar)) {
      resultingList = new OrderedList();
      AbstractList.listHelper(resultingList, null, input, orderedChar);
    } else {
      resultingList = new UnorderedList();
      AbstractList.listHelper(resultingList, null, input, unorderedChar);
    }
    return resultingList;
  }

  /**
   *
   * @param resultingList an accumulator for the input list elements that have been converted into
   *        html structured format
   * @param lastItem the most recently added item of resultingList
   * @param input a list of strings formatted in html list-template style
   * @param leadingSpace the leading space of the list item, including special characters "1." or
   *        "* "
   */
  private static void listHelper(AbstractList resultingList, Item lastItem, List<String> input,
      String leadingSpace) {
    if (!input.isEmpty()) {
      int i = 0;
      while ((i < input.size()) && !input.get(i).startsWith(leadingSpace)) {
        i++;
      }
      if (i == 0) { // item has no sublist
        Item item = new Item(input.get(0).substring(leadingSpace.length()));
        resultingList.addItem(item);
        AbstractList.listHelper(resultingList, item, input.subList(1, input.size()), leadingSpace);
      } else {
        String itemLeadingSpace =
            input.get(0).substring(0, leadingSpace.length() + AbstractList.NESTING_SPACES);
        AbstractList sublist = null;
        if (itemLeadingSpace.contains(AbstractList.ORDERED_CHAR)) {
          sublist = new OrderedList();
          AbstractList.listHelper(sublist, lastItem, input.subList(0, i), itemLeadingSpace);
        } else {
          sublist = new UnorderedList();
          AbstractList.listHelper(sublist, lastItem, input.subList(0, i), itemLeadingSpace);
        }
        lastItem.setSubList(sublist);
        if ((i) < input.size()) {
          AbstractList.listHelper(resultingList, lastItem, input.subList(i, input.size()),
              leadingSpace);
        }
      }
    }
  }

  /**
   *
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = (prime * result) + ((this.list == null) ? 0 : this.list.hashCode());
    return result;
  }

  /**
   *
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    AbstractList other = (AbstractList) obj;
    if (this.list == null) {
      if (other.list != null) {
        return false;
      }
    } else if (!this.list.equals(other.list)) {
      return false;
    }
    return true;
  }
}
