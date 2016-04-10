package edu.neu.ccs.cs5004.seattle.assignment9.html;
/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */


/**
 * Represents an Unordered List in HTML
 *
 * @author yoganandc
 */
public class UnorderedList extends AbstractList {

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
    return stringBuilder.toString();
  }
}
