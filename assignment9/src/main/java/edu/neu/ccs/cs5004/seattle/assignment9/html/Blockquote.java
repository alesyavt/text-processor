package edu.neu.ccs.cs5004.seattle.assignment9.html;

import java.util.List;

public class Blockquote extends Paragraph {

  private List<Paragraph> parag;
  private Blockquote blockq;
  private int specialCharCount;


  public Blockquote(List<String> block) {



  }

  private bqHelper(List<String> block) {
   if (!block.isEmpty()) {
   int specialCount = 0;
   while ((block[specialCount] != null) && (block[specialCount] == '>')) {
   specialCount++;
   }



   }



}
