package edu.neu.ccs.cs5004.seattle.assignment9.html;

/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * A section is an element that can have elements nested within it.
 *
 * @author yoganandc
 */
public final class Section extends AbstractElement {

    private final List<AbstractElement> nodes;
    private final String value;

    /**
     * Creates a new instance of Section
     *
     * @param value The header for this section.
     * @throws NullPointerException if value is null
     */
    public Section(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        this.value = value;
        this.nodes = new ArrayList<>();
    }

    public Section(ArrayList<String> lines) {
        this.value = null;
        this.nodes = new ArrayList<>();
        String lastEl = lines.remove(lines.size() - 1);
        lastEl = lastEl + "\n";
        lines.add(lastEl);
        lines.add("\n");
        parse(lines);
    }

    /**
     * Creates a new instance of Section with value set to null. Only used by
     * createRoot().
     */
    public Section() {
        this.value = null;
        this.nodes = new ArrayList<>();
    }

    /**
     * Retrieve the elements in this section
     *
     * @return A list of the elements contained in this Section
     */
    List<AbstractElement> getNodes() {
        return this.nodes;
    }

    /**
     * Add an element to this section.
     *
     * @param n The element to be added
     * @throws NullPointerException if n is null
     * @throws IllegalArgumentException if an Element that is not a Section is
     * added to the root Section.
     */
    public void add(AbstractElement n) {
        if (n == null) {
            throw new NullPointerException();
        }
        n.setDepth(this.getDepth());
        this.nodes.add(n);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    void setDepth(Integer depth) {
        super.setDepth(++depth);
    }

    /**
     * Getter method for the this section's header
     *
     * @return The contents of this section's header
     */
    public String getValue() {
        return value;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String toPrettyString() {
        StringBuilder stringBuilder = new StringBuilder();

        if (this.getValue() != null) {
            stringBuilder.append("<h");
            stringBuilder.append(this.getDepth());
            stringBuilder.append(">");
            stringBuilder.append(this.getValue());
            stringBuilder.append("</h");
            stringBuilder.append(this.getDepth());
            stringBuilder.append(">\n\n");
        }
        for (AbstractElement n : this.nodes) {
            if (this.getDepth().equals(n.getDepth())) {
                stringBuilder.append(n.toPrettyString()).append("\n");
            } else {
                stringBuilder.append(n.toPrettyString());
            }
        }
        return stringBuilder.toString();
    }

    private static int getHeaderDepth(String line) {
        int i = 0;
        while (line.charAt(i) == '#') {
            i++;
        }
        return i;
    }

    private void parse(ArrayList<String> lines) {

        boolean getNext = true;
        String line = null;

        Iterator<String> it = lines.iterator();
        while (it.hasNext()) {

            if (getNext) {
                line = it.next();
            } else {
                getNext = true;
            }

            // HANDLE BLANKLINE
            if (line.equals("\n")) {
                this.add(new BlankLine());

            } // HANDLE HEADERS
            else if (line.startsWith("#")) {

                // 1. CALCULATE HEADER DEPTH
                int headerDepth = Section.getHeaderDepth(line);

                // 2. CREATE HIDDEN SECTIONS (IF REQUIRED)
                int depthDiff = headerDepth - this.getDepth();
                Section last = this;
                while (depthDiff > 1) {
                    Section s = new Section();
                    last.add(s);
                    last = s;
                    depthDiff--;
                }

                // 3. EXTRACT THE CONTENTS OF HEADER OF THIS NEW SECTION
                String headerPrefix = line.substring(0, headerDepth);
                String headerContent = line.substring(headerDepth + 1, line.indexOf("\n"));
                Section newSection = new Section(headerContent);
                last.add(newSection);

                // 4. DETERMINE THE NUMBER OF LINES CONTAINED IN THIS SECTION
                ArrayList<String> subLines = new ArrayList<>();
                while (it.hasNext()) {
                    line = it.next();
                    if (line.startsWith("#")) {
                        int newHeaderDepth = Section.getHeaderDepth(line);
                        if (newHeaderDepth <= newSection.getDepth()) {
                            getNext = false;
                            break;
                        } else {
                            subLines.add(line);
                        }
                    } else {
                        subLines.add(line);
                    }
                }
                newSection.parse(subLines);
            } // HANDLE LISTS
            else if (line.startsWith("1. ") || line.startsWith("* ")) {
                List<String> listLines = new ArrayList<>();
                listLines.add(line);
                while (it.hasNext()) {
                    line = it.next();
                    boolean cond1 = !line.trim().startsWith("1. ");
                    boolean cond2 = !line.trim().startsWith("* ");
                    if (cond1 && cond2 && !line.equals("\n")) {
                        throw new ParseException("Incorrectly formatted list");
                    }
                    if (!line.equals("\n")) {
                        listLines.add(line);
                    } else {
                        this.add(AbstractList.createList(listLines));
                        break;
                    }
                }
            } // HANDLE PARAGRAPHS
            else {
                StringBuilder paragraph = new StringBuilder();
                paragraph.append(line);
                while (it.hasNext()) {
                    line = it.next();
                    if (!line.equals("\n")) {
                        paragraph.append(line);
                    } else {
                        this.add(new Paragraph(paragraph.toString()));
                        break;
                    }
                }
            }
        }
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = (59 * hash) + Objects.hashCode(this.nodes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Section other = (Section) obj;
        if (!Objects.equals(this.nodes, other.nodes)) {
            return false;
        }
        return true;
    }

    /**
     * Splits a given string into a list of strings delimited by the NL
     * character
     *
     * @param block The block to be split
     * @return An ArrayList of strings
     */
    public static ArrayList<String> split(String block) {
        return new ArrayList<>(Arrays.asList(block.replaceFirst("\\s+$", "").split("(?<=\n)")));
    }
}
