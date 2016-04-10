package edu.neu.ccs.cs5004.seattle.assignment9.html;
/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */


/**
 * Represents a list item in HTML with its subList (if present)
 *
 * @author yoganandc
 */
public class Item {

  private String val;
  private AbstractList subList;

  /**
   * Creates a new instance of Node
   *
   * @param value The value at this node.
   * @throws NullPointerException if the value is null
   */
  public Item(String value) {
    if (value == null) {
      throw new NullPointerException();
    }
    this.val = value.trim();
    this.subList = null;
  }

  /**
   * Get this item's sub-list
   *
   * @return The sublist
   */
  public AbstractList getSubList() {
    return this.subList;
  }

  /**
   * Set this item's sublist
   *
   * @param subList The sublist which is to come under this item
   */
  public void setSubList(AbstractList subList) {
    this.subList = subList;
  }

  /**
   * Return a HTML-formatted string for this element
   *
   * @return The HTML-formatted string
   */
  public String toPrettyString() {
    StringBuilder stringBuilder = new StringBuilder();
    if (this.subList == null) {
      stringBuilder.append("<li>").append(this.val).append("</li>\n");
    } else {
      stringBuilder.append("<li>").append(this.val).append("\n");
      stringBuilder.append(this.subList.toPrettyString());
      stringBuilder.append("</li>\n");
    }
    return stringBuilder.toString();
  }

  /**
   *
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((this.subList == null) ? 0 : this.subList.hashCode());
    result = (prime * result) + ((this.val == null) ? 0 : this.val.hashCode());
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
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Item other = (Item) obj;
    if (this.subList == null) {
      if (other.subList != null) {
        return false;
      }
    } else if (!this.subList.equals(other.subList)) {
      return false;
    }
    if (this.val == null) {
      if (other.val != null) {
        return false;
      }
    } else if (!this.val.equals(other.val)) {
      return false;
    }
    return true;
  }
}
