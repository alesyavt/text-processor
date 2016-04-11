package edu.neu.ccs.cs5004.seattle.assignment9.html;

public abstract class AbstractParagraph extends AbstractElement {

    public static String hyperLinkify(String value) {
        int textBegin = value.indexOf(TEXT_BEGIN);
        int textEnd = value.indexOf(TEXT_END);
        int linkBegin = value.indexOf(LINK_BEGIN);
        int linkEnd = value.indexOf(LINK_END);

        if (textBegin != -1 && textBegin < textEnd && textEnd < linkBegin && linkBegin < linkEnd) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(value.substring(0, textBegin));
            stringBuilder.append("<a href=\"");
            stringBuilder.append(value.substring(linkBegin + 1, linkEnd));
            stringBuilder.append("\">");
            stringBuilder.append(value.substring(textBegin + 1, textEnd));
            stringBuilder.append("</a>");
            stringBuilder.append(hyperLinkify(value.substring(linkEnd + 1, value.length())));
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
