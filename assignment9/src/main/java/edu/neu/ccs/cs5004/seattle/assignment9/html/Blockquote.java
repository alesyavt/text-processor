package edu.neu.ccs.cs5004.seattle.assignment9.html;

import java.util.List;

public class Blockquote extends AbstractParagraph {

  private List<AbstractParagraph> list;
  private int specialCharCount;
  private String specialChar = ">";


  public Blockquote(List<String> block) {
    this.specialCharCount = 1;

  }


  public Blockquote(List<String> block, int specialCount) {
    this.specialCharCount = specialCount;
    if (specialCount(block.get(0)) == this.specialCharCount) {
      int paragIndex = 0;
      StringBuilder parag = new StringBuilder();
      parag.append(block.get(0).substring(this.specialCharCount));
      while ((block.get(paragIndex) != null)
          && !block.get(paragIndex).startsWith(this.specialChar)) {
        parag.append(block.get(paragIndex));
        paragIndex++;
      }
      this.list.add(new Paragraph(parag.toString()));

      bqHelper(block.subList(paragIndex, block.size()), 0);
    }
  }


  private void bqHelper(List<String> block, int index) {
    if (!block.isEmpty()) {
      if (specialCount(block.get(index)) > this.specialCharCount) {
        bqHelper(block, index + 1);
      } else if (index > 0) {
        this.list.add(new Blockquote(block.subList(0, index), this.specialCharCount + 1));
      }

    }
  }


  private int specialCount(String s) {
    int specialCount = 0;
    while ((specialCount < s.length()) && (s.charAt(specialCount) == '>')) {
      specialCount++;
    }
    return specialCount;
  }



}
