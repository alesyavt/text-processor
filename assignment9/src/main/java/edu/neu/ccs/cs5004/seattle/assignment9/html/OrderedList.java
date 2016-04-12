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
    listHelper(this, null, input, leadingSpace, AbstractList.ORDERED_CHAR);
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
  // @Override
  protected void listHelper(AbstractList resultingList, Item lastItem, List<String> input,
      String leadingSpace, String specialChar) {
    if (!input.isEmpty()) {
      int i = 0;
      int k = 0;
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
      if (i == 0) { // item has no sublist
        Item item =
            new Item(input.get(0).substring((leadingSpace + AbstractList.ORDERED_CHAR).length()));
        this.list.add(item);
        listHelper(resultingList, item, input.subList(1, input.size()), leadingSpace,
            AbstractList.ORDERED_CHAR);
      } else if (j > 0) { /// un-nested mixed list
        this.mixedList = new UnorderedList(input, leadingSpace);
      } else if (k > 0) {
        this.mixedList = new UnorderedList(input.subList(k, input.size()), leadingSpace);
        String itemLeadingSpace = leadingSpace + AbstractList.NESTING_SPACES;
        AbstractList sublist = null;
        if (input.get(0).substring(itemLeadingSpace.length())
            .startsWith(AbstractList.ORDERED_CHAR)) {
          sublist = new OrderedList(input.subList(0, k), itemLeadingSpace);
        } else {
          sublist = new UnorderedList(input.subList(0, k), itemLeadingSpace);
        }
        lastItem.setSubList(sublist);
      } else {
        String itemLeadingSpace = leadingSpace + AbstractList.NESTING_SPACES;
        AbstractList sublist = null;
        if (input.get(0).substring(itemLeadingSpace.length())
            .startsWith(AbstractList.ORDERED_CHAR)) {
          sublist = new OrderedList(input.subList(0, i), itemLeadingSpace);
        } else {
          sublist = new UnorderedList(input.subList(0, i), itemLeadingSpace);
        }
        lastItem.setSubList(sublist);
        if ((i) < input.size()) {
          listHelper(resultingList, lastItem, input.subList(i, input.size()), leadingSpace,
              AbstractList.ORDERED_CHAR);
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
    for (Item el : this.list) {
      stringBuilder.append(el.toPrettyString());
    }
    stringBuilder.append("</ol>\n");
    if (this.mixedList != null) {
      stringBuilder.append(this.mixedList.toPrettyString());
    }
    return stringBuilder.toString();
  }
}
