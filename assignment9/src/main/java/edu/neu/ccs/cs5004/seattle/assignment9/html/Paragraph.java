package edu.neu.ccs.cs5004.seattle.assignment9.html;
/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */


/**
 * Represents the HTML paragraph element.
 *
 * @author yoganandc
 */
public class Paragraph extends AbstractElement {

  /**
   * Creates a new instance of Paragraph
   *
   * @param value The contents of this paragraph.
   * @throws NullPointerException when value is null
   */
  Paragraph(String value) {
    super(value);
    if (value == null) {
      throw new NullPointerException();
    }
  }

  /**
   * {@inheritDoc }
   */
  @Override
  public String toPrettyString() {
    StringBuilder stringBuilder = new StringBuilder();
    return stringBuilder.append("<p>").append(AbstractElement.emphasize(this.getValue()))
        .append("</p>\n").toString();
  }

}
