package edu.neu.ccs.cs5004.seattle.assignment9.html;

import java.util.Objects;

/**
 * Represents the HTML paragraph element.
 *
 * @author yoganandc alesyavt
 */
public class Paragraph extends AbstractParagraph {

  private final String value;

  /**
   * Creates a new instance of Paragraph
   *
   * @param value The contents of this paragraph.
   * @throws NullPointerException when value is null
   */
  public Paragraph(String value) {
    if (value == null) {
      throw new NullPointerException();
    }
    this.value = value;
  }

  /**
   * Getter method for this Paragraph's contents
   *
   * @return The contents of this paragraph
   */
  public String getValue() {
    return this.value;
  }

  /**
   * {@inheritDoc }
   */
  @Override
  public String toPrettyString() {
    StringBuilder stringBuilder = new StringBuilder();
    return stringBuilder.append("<p>")
        .append(AbstractElement.emphasize(AbstractParagraph.hyperLinkify(this.getValue())))
        .append("</p>\n").toString();
  }

  /**
   * {@inheritDoc }
   */
  @Override
  public int hashCode() {
    int hash = 7;
    hash = (59 * hash) + Objects.hashCode(this.value);
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
    final Paragraph other = (Paragraph) obj;
    if (!Objects.equals(this.value, other.value)) {
      return false;
    }
    return true;
  }

}
