package edu.neu.ccs.cs5004.seattle.assignment9.html;
/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */

import java.util.List;

/**
 * Represents an ordered list in HTML
 *
 * @author yoganandc
 */
public class OrderedList extends AbstractList {

  public OrderedList() {
    super("");
  }

  public OrderedList(List<String> input, String leadingSpace) {
    super(leadingSpace);
    listHelper(null, input, leadingSpace);
  }


  /**
   *
   * {@inheritDoc}
   */
  @Override
  protected void listHelper(Item lastItem, List<String> input, String leadingSpace) {
    if (!input.isEmpty()) {
      int i = 0; // number of lines that don't match the current nesting level and/or special char
      int k = 0; // mixed list start index
      while ((i < input.size())
          && !input.get(i).startsWith(leadingSpace + AbstractList.ORDERED_CHAR)) {
        if (input.get(i).startsWith(leadingSpace + AbstractList.UNORDERED_CHAR)) {
          k = i;
        }
        i++;
      }
      int j = 0; // count number of lines with same nesting level but different with UNORDERED_CHAR
      while ((j < input.size())
          && input.get(j).startsWith(leadingSpace + AbstractList.UNORDERED_CHAR)) {
        j++;
      }
      if (i == 0) { // first line is the next item in the current list
        Item item =
            new Item(input.get(0).substring((leadingSpace + AbstractList.ORDERED_CHAR).length()));
        this.itemList.add(item);
        listHelper(item, input.subList(1, input.size()), leadingSpace);

      } else if (j > 0) { /// un-nested mixed list starts with first line
        this.mixedList = new UnorderedList(input, leadingSpace);

      } else if (k > 0) { // un-nested mixed list starting at line k, with nested list (0,k)
        this.mixedList = new UnorderedList(input.subList(k, input.size()), leadingSpace);
        lastItem.setSubList(
            addSublist(input.subList(0, k), leadingSpace + AbstractList.NESTING_SPACES));

      } else { // nested sublist (0,i) and continuing current list (i,end)
        lastItem.setSubList(
            addSublist(input.subList(0, i), leadingSpace + AbstractList.NESTING_SPACES));
        if ((i) < input.size()) {
          listHelper(lastItem, input.subList(i, input.size()), leadingSpace);
        }
      }
    }
  }



  /**
   * {@inheritDoc }
   */
  @Override
  public String toPrettyString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("<ol>\n");
    for (Item el : this.itemList) {
      stringBuilder.append(el.toPrettyString());
    }
    stringBuilder.append("</ol>\n");
    if (this.mixedList != null) {
      stringBuilder.append(this.mixedList.toPrettyString());
    }
    return stringBuilder.toString();
  }
}
