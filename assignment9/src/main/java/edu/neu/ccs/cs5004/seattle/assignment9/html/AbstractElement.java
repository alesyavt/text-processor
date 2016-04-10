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
  public static final String BOLD_BEG = "<strong>";
  public static final String BOLD_END = "</strong>";
  public static final String EM_BEG = "<em>";
  public static final String EM_END = "</em>";
  public static final char STAR = '*';
  public static final char UNDERSCORE = '_';
  public static final char SPACE = ' ';

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

  /**
   * Parses the given string, replacing text enclosed by the "*" and "_" characters to corresponding
   * html tags for bolded and italicized text. An opening star must not be followed by a space, and
   * a closing star must not be preceded by a space.
   *
   * @param block a string of text
   * @return the text with html tags
   */
  static String emphasize(String block) {
    StringBuilder text = new StringBuilder(block);
    for (int i = 0; i < (text.length() - 1); i++) {
      if ((text.charAt(i) == AbstractElement.STAR)
          && (text.charAt(i + 1) != AbstractElement.SPACE)) {
        AbstractElement.replace(text, AbstractElement.STAR, AbstractElement.BOLD_BEG,
            AbstractElement.BOLD_END, i);
      } else if (text.charAt(i) == AbstractElement.UNDERSCORE) {
        AbstractElement.replace(text, AbstractElement.UNDERSCORE, AbstractElement.EM_BEG,
            AbstractElement.EM_END, i);
      }
    }
    return text.toString();
  }

  /**
   *
   * @param text
   * @param specialChar
   * @param tagBeg
   * @param tagEnd
   * @param start
   */
  public static void replace(StringBuilder text, char specialChar, String tagBeg, String tagEnd,
      int start) {
    text.replace(start, start + 1, tagBeg);
    int end = AbstractElement.findEnd(text, specialChar, start + tagBeg.length());
    if (end != -1) {
      text.replace(end, end + 1, tagEnd);
    } else {
      text.append(tagEnd);
    }

  }

  /**
   * Helper for parsing text. Finds the index of the closing specialChar for a piece of bolded text.
   * If closing specialChar is missing, the text is bolded through the end of the line.
   *
   * @param s a string of text
   * @param specialChar a special character which represents an emphasis for enclosed text
   * @param index the index from which to begin searching for the closing specialChar
   * @return the index of the closing specialChar
   *
   */

  private static int findEnd(StringBuilder s, char specialChar, int index) {

    for (int i = index; i < s.length(); i++) {
      if ((s.charAt(i) == specialChar) && (s.charAt(i - 1) != AbstractElement.SPACE)) {
        return i;
      }
      if (s.charAt(i) == '\n') {
        return i;
      }
    }
    return -1;
  }
}
