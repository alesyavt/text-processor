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

  protected static final String ORDERED_CHAR = "1. ";
  protected static final String UNORDERED_CHAR = "* ";
  protected static final String NESTING_SPACES = "  ";

  protected final List<Item> list;
  protected AbstractList mixedList;
  protected String leadingSpace;

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
    if (input.get(0).startsWith(AbstractList.ORDERED_CHAR)) {
      return new OrderedList(input, "");
    } else {
      return new UnorderedList(input, "");
    }
  }

  /**
   * Creates a new instance of AbstractList
   */
  protected AbstractList(String leadingSpace) {
    this.list = new ArrayList<>();
    this.mixedList = null;
    this.leadingSpace = leadingSpace;
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
   * !!!!!
   *
   * @param input
   * @param leadingSpace
   * @return
   */
  protected AbstractList addSublist(List<String> input, String leadingSpace) {
    if (input.get(0).substring(leadingSpace.length()).startsWith(AbstractList.UNORDERED_CHAR)) {
      return new UnorderedList(input, leadingSpace);
    } else {
      return new OrderedList(input, leadingSpace);
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
