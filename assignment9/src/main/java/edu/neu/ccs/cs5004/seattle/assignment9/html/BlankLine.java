package edu.neu.ccs.cs5004.seattle.assignment9.html;
/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */


/**
 *
 * @author yoganandc
 */
public class BlankLine extends AbstractElement {

  public BlankLine() {
    super(null);
  }

  @Override
  public String toPrettyString() {
    return "<br />\n";
  }
}
