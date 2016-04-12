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
  private final static String SPECIAL_CHAR = ">";

  /**
   *
   * @param block
   */
  public Blockquote(List<String> block) {
    this(block, 1);
  }

  /**
   *
   * @param block
   * @param specialCount
   */
  Blockquote(List<String> block, int specialCount) {
    this.list = new ArrayList<>();
    this.specialCharCount = specialCount;
    if (specialCount(block.get(0)) == this.specialCharCount) {
      addParag(block);
      bqHelper(block.subList(1, block.size()), 0);
    } else {
      bqHelper(block, 0);
    }
  }


  /**
   *
   * @param block
   */
  private void addParag(List<String> block) {
    StringBuilder parag = new StringBuilder();
    parag.append(block.get(0).substring(this.specialCharCount));

    this.list.add(new Paragraph(parag.toString()));
  }

  /**
   *
   * @param block
   * @param index
   */
  private void bqHelper(List<String> block, int index) {
    if ((index < block.size()) && (specialCount(block.get(index)) > this.specialCharCount)) {
      bqHelper(block, index + 1);
    } else if (index > 0) {
      this.list.add(new Blockquote(block.subList(0, index), this.specialCharCount + 1));
      if (index < block.size()) {
        addParag(block.subList(index, block.size()));
        bqHelper(block.subList(index + 1, block.size()), 0);
      }
    } else if (!block.isEmpty()) {
      addParag(block.subList(index, block.size()));
      bqHelper(block.subList(1, block.size()), 0);
    }
  }


  /**
   *
   * @param s
   * @return
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
