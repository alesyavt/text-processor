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
public final class Paragraph extends AbstractElement {

  public static final String BOLD_BEG = "<b>";
  public static final String BOLD_END = "</b>";
  public static final char STAR = '*';
  public static final char SPACE = ' ';

  /**
   * Creates a new paragraph
   *
   * @param block the contents of the paragraph in html template style - may include text surrounded
   *        by * on both sides with no spaces to indicate that the text should be bolded
   * @return the created paragraph
   */
  public static Paragraph createParagraph(String block) {
    return Paragraph.parseParagraph(block);
  }

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
    return stringBuilder.append("<p>").append(this.getValue()).append("</p>\n").toString();
  }

  /**
   * Creates a paragraph with the given content
   *
   * @param block the contents of the paragraph
   * @return the created paragraph
   */
  private static Paragraph parseParagraph(String block) {
    StringBuilder p = new StringBuilder(block);
    int start = 0;
    int end = 0;

    for (int i = 0; i < (p.length() - 1); i++) {
      if ((p.charAt(i) == Paragraph.STAR) && (p.charAt(i + 1) != Paragraph.SPACE)) {
        start = i;
        p.replace(start, start + 1, Paragraph.BOLD_BEG);
        end = Paragraph.findEnd(p);
        if (end != -1) {
          p.replace(end, end + 1, Paragraph.BOLD_END);
          i = end + Paragraph.BOLD_END.length();
        } else {
          p.append(Paragraph.BOLD_END);
          i = p.length();
        }
      }
    }
    return new Paragraph(p.toString());
  }

  /**
   * Helper for parsing paragraph. Finds the index of the closing * for a piece of bolded text. If
   * closing star is missing. The text is bolded through the end of the paragraph.
   *
   * @param s a string of the paragraph
   * @return the index of the closing *
   */
  private static int findEnd(StringBuilder s) {

    for (int i = 0; i < s.length(); i++) {
      if ((s.charAt(i) == Paragraph.STAR) && (s.charAt(i - 1) != Paragraph.SPACE)) {
        return i;
      }
    }
    return -1;
  }

}
