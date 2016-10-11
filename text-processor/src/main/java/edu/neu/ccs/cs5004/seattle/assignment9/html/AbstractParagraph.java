package edu.neu.ccs.cs5004.seattle.assignment9.html;

/**
 * Represents any block level text element
 *
 * @author yoganandc alesyavt
 */
public abstract class AbstractParagraph extends AbstractElement {

  /**
   * Static function to replace all markdown links with HTML hyperlinks
   *
   * @param value The string containing link in markdown syntax
   * @return The string containing the same links but in HTML
   */
  public static String hyperLinkify(String value) {
    int textBegin = value.indexOf(AbstractParagraph.TEXT_BEGIN);
    int textEnd = value.indexOf(AbstractParagraph.TEXT_END);
    int linkBegin = value.indexOf(AbstractParagraph.LINK_BEGIN);
    int linkEnd = value.indexOf(AbstractParagraph.LINK_END);

    if ((textBegin != -1) && (textBegin < textEnd) && (textEnd < linkBegin)
        && (linkBegin < linkEnd)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(value.substring(0, textBegin));
      stringBuilder.append("<a href=\"");
      stringBuilder.append(value.substring(linkBegin + 1, linkEnd));
      stringBuilder.append("\">");
      stringBuilder.append(value.substring(textBegin + 1, textEnd));
      stringBuilder.append("</a>");
      stringBuilder
          .append(AbstractParagraph.hyperLinkify(value.substring(linkEnd + 1, value.length())));
      return stringBuilder.toString();
    } else {
      return value;
    }
  }

  private static final String LINK_END = ")";
  private static final String LINK_BEGIN = "(";
  private static final String TEXT_END = "]";
  private static final String TEXT_BEGIN = "[";

}
