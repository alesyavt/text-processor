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

    private final String value;

    /**
     * Creates a new instance of Paragraph
     *
     * @param value The contents of this paragraph.
     * @throws NullPointerException when value is null
     */
    Paragraph(String value) {
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
        return stringBuilder.append("<p>").append(AbstractElement.emphasize(this.getValue()))
                .append("</p>\n").toString();
    }

}
