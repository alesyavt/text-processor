package edu.neu.ccs.cs5004.seattle.assignment9.html;
/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */


/**
 * Represents an ordered list in HTML
 *
 * @author yoganandc
 */
public class OrderedList extends AbstractList {

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
    return stringBuilder.toString();
  }
}
