package edu.neu.ccs.cs5004.seattle.assignment9.html;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents the HTML element blockquote
 *
 * @author yoganandc alesyavt
 */
public class Blockquote extends AbstractParagraph {

  private List<AbstractParagraph> list;
  private int specialCharCount;

  /**
   * Creates a blockquote with the given block
   *
   * @param block a list of strings beginning with one or more blockquote special characters
   */
  public Blockquote(List<String> block) {
    this(block, 1);
  }

  /**
   *
   * @param block a list of strings beginning with one or more blockquote special characters ">",
   *        followed by a space. Each ">" represents an extra nesting level of the blockquote
   * @param specialCount the nesting level of this blockquote
   */
  Blockquote(List<String> block, int specialCount) {
    this.list = new ArrayList<>();
    this.specialCharCount = specialCount;
    if (specialCount(block.get(0)) == this.specialCharCount) {
      addParag(block.get(0));
      bqHelper(block.subList(1, block.size()), 0);
    } else {
      bqHelper(block, 0);
    }
  }


  /**
   * Adds the given string as a paragraph to this blockquote
   *
   * @param s the string to be added as a paragraph to this blockquote
   */
  private void addParag(String s) {
    StringBuilder parag = new StringBuilder();
    parag.append(s.substring(this.specialCharCount));

    this.list.add(new Paragraph(parag.toString()));
  }

  /**
   * Helper for creating regular/nested blockquotes
   *
   * @param block the list to be processed and added to this blockquote
   * @param index an index tracker for keeping track of block's strings
   */
  private void bqHelper(List<String> block, int index) {
    if ((index < block.size()) && (specialCount(block.get(index)) > this.specialCharCount)) {
      bqHelper(block, index + 1);
    } else if (index > 0) {
      this.list.add(new Blockquote(block.subList(0, index), this.specialCharCount + 1));
      if (index < block.size()) {
        addParag(block.get(0));
        bqHelper(block.subList(index + 1, block.size()), 0);
      }
    } else if (!block.isEmpty()) {
      addParag(block.get(0));
      bqHelper(block.subList(1, block.size()), 0);
    }
  }


  /**
   * Return the count of ">" at the beginning of given string s
   *
   * @param s a string beginning with one or more ">" followed by a space
   * @return the number of ">" that s begins with
   */
  private int specialCount(String s) {
    int specialCount = 0;
    while ((specialCount < s.length()) && (s.charAt(specialCount) == '>')) {
      specialCount++;
    }
    return specialCount;
  }

  /**
   * {@inheritDoc }
   */
  @Override
  public String toPrettyString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("<blockquote>");
    for (AbstractParagraph p : this.list) {
      stringBuilder.append(AbstractElement.emphasize(p.toPrettyString()));
    }
    stringBuilder.append("</blockquote>\n");
    return stringBuilder.toString();
  }

  /**
   * {@inheritDoc }
   */
  @Override
  public int hashCode() {
    int hash = 7;
    hash = (67 * hash) + Objects.hashCode(this.list);
    hash = (67 * hash) + this.specialCharCount;
    return hash;
  }

  /**
   * {@inheritDoc }
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
    final Blockquote other = (Blockquote) obj;
    if (this.specialCharCount != other.specialCharCount) {
      return false;
    }
    if (!Objects.equals(this.list, other.list)) {
      return false;
    }
    return true;
  }
}
