package edu.neu.ccs.cs5004.seattle.assignment9.html;
/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */


/**
 * Represents any generic HTML element with its value and depth
 *
 * @author yoganandc
 */
public abstract class AbstractElement {

  public static final Integer NODE_DEPTH = 0;

  private final String value;
  private Integer depth;

  /**
   * Creates a new instance of AbstractElement
   *
   * @param value The value for this element
   */
  protected AbstractElement(String value) {
    if (value != null) {
      value = value.trim();
    }
    this.value = value;
    this.depth = AbstractElement.NODE_DEPTH;
  }

  /**
   * Retrieves the value stored in this Element
   *
   * @return The value
   */
  public String getValue() {
    return this.value;
  }

  /**
   * Get this element's depth
   *
   * @return The depth
   */
  protected Integer getDepth() {
    return this.depth;
  }

  /**
   * Set this element's depth
   *
   * @param depth The depth
   */
  void setDepth(Integer depth) {
    this.depth = depth;
  }

  /**
   * Return a HTML-formatted string for this element
   *
   * @return The HTML-formatted string
   */
  public abstract String toPrettyString();

  /**
   *
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((this.depth == null) ? 0 : this.depth.hashCode());
    result = (prime * result) + ((this.value == null) ? 0 : this.value.hashCode());
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
    AbstractElement other = (AbstractElement) obj;
    if (this.depth == null) {
      if (other.depth != null) {
        return false;
      }
    } else if (!this.depth.equals(other.depth)) {
      return false;
    }
    if (this.value == null) {
      if (other.value != null) {
        return false;
      }
    } else if (!this.value.equals(other.value)) {
      return false;
    }
    return true;
  }
}
