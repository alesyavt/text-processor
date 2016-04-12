package edu.neu.ccs.cs5004.seattle.assignment9.html;
/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */

import java.util.List;

/**
 * Represents an Unordered List in HTML
 *
 * @author yoganandc
 */
public class UnorderedList extends AbstractList {

  public UnorderedList() {
    super("");
  }

  public UnorderedList(List<String> input, String leadingSpace) {
    super(leadingSpace);
    listHelper(this, null, input, leadingSpace, AbstractList.UNORDERED_CHAR);
  }

  protected void listHelper(AbstractList resultingList, Item lastItem, List<String> input,
      String leadingSpace, String specialChar) {
    if (!input.isEmpty()) {
      int i = 0;
      while ((i < input.size())
          && !input.get(i).startsWith(leadingSpace + AbstractList.UNORDERED_CHAR)) {
        i++;
      }
      int j = 0; // count number of lines with same nesting level but different with ORDERED_CHAR
      while ((j < input.size())
          && input.get(j).startsWith(leadingSpace + AbstractList.ORDERED_CHAR)) {
        j++;
      }

      if (i == 0) { // item has no sublist
        Item item =
            new Item(input.get(0).substring((leadingSpace + AbstractList.UNORDERED_CHAR).length()));
        this.list.add(item);
        listHelper(resultingList, item, input.subList(1, input.size()), leadingSpace,
            AbstractList.UNORDERED_CHAR);
      } else if (j > 0) { /// un-nested mixed list
        this.mixedList = new OrderedList(input, leadingSpace);
      } else {
        String itemLeadingSpace = leadingSpace + AbstractList.NESTING_SPACES;
        AbstractList sublist = null;
        if (input.get(0).substring(itemLeadingSpace.length())
            .startsWith(AbstractList.UNORDERED_CHAR)) {
          sublist = new UnorderedList(input.subList(0, i), itemLeadingSpace);
        } else {
          sublist = new OrderedList(input.subList(0, i), itemLeadingSpace);
        }
        lastItem.setSubList(sublist);

        if ((i) < input.size()) {
          listHelper(resultingList, lastItem, input.subList(i, input.size()), leadingSpace,
              AbstractList.UNORDERED_CHAR);
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
    stringBuilder.append("<ul>\n");
    for (Item el : this.list) {
      stringBuilder.append(el.toPrettyString());
    }
    stringBuilder.append("</ul>\n");
    if (this.mixedList != null) {
      stringBuilder.append(this.mixedList.toPrettyString());
    }
    return stringBuilder.toString();
  }
}
