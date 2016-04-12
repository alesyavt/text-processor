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
 * @author yoganandc alesyavt
 */
public final class Section extends AbstractElement {

  private static final String HL_MATCHER = "^\\*\\*\\*\\*\\**\\ *\\n$";
  private static final String BQ_MATCHER = "^>+\\ .*\\n$";
  private static final String HD_MATCHER = "^#+\\ .*\\n$";
  private static final String UL_MATCHER = "^\\*\\ .*\\n$";
  private static final String OL_MATCHER = "^1\\.\\ .*\\n$";
  private static final String NEW_LINE = "\n";
  private static final char CHAR_SPACE = ' ';
  private static final char CHAR_POUND = '#';
  private static final int HEADER_MAXDEPTH = 6;

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

  /**
   * Creates a new instance of Section from the given list of strings that contain lines of Markdown
   *
   * @param lines The lines of Markdown to be converted into an HTML section
   */
  public Section(ArrayList<String> lines) {
    this.value = null;
    this.nodes = new ArrayList<>();
    String lastEl = lines.remove(lines.size() - 1);
    lastEl = lastEl + Section.NEW_LINE;
    lines.add(lastEl);
    lines.add(Section.NEW_LINE);
    parse(lines);
  }

  /**
   * Creates a new instance of Section with value set to null.
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
   * @throws IllegalArgumentException if an Element that is not a Section is added to the root
   *         Section.
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
    return this.value;
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
        stringBuilder.append(n.toPrettyString()).append(Section.NEW_LINE);
      } else {
        stringBuilder.append(n.toPrettyString());
      }
    }
    return stringBuilder.toString();
  }

  /**
   * Parses the given lists of strings and adds parsed elements to this Section
   *
   * @param lines The lines of Markdown to be parsed
   */
  private void parse(ArrayList<String> lines) {

    String lineRead = null;
    String line = null;

    Iterator<String> it = lines.iterator();
    while (it.hasNext()) {

      if (lineRead == null) {
        line = it.next();
      } else {
        line = lineRead;
        lineRead = null;
      }

      // HANDLE BLANKLINE
      if (line.equals(Section.NEW_LINE)) {
        this.add(new BlankLine());

      } // HANDLE <HR>
      else if (line.matches(Section.HL_MATCHER)) {
        this.add(new HorizontalLine());

      } // HANDLE HEADERS
      else if (line.matches(Section.HD_MATCHER)) {
        lineRead = this.handleSection(line, it);

      } // HANDLE LISTS
      else if (line.matches(Section.UL_MATCHER) || line.matches(Section.OL_MATCHER)) {
        this.handleList(line, it);

      } // HANDLE BLOCKQUOTE
      else if (line.matches(Section.BQ_MATCHER)) {
        lineRead = this.handleBlockquote(line, it);

      } // HANDLE PARAGRAPHS
      else {
        this.handleParagraph(line, it);
      }
    }
  }

  /**
   * Given the current line and an Iterator (over the remaining lines to processed), identifies all
   * the lines to be parsed as part of this list. The created list is then added to this Section
   *
   * @param line The first line of the new list
   * @param it The iterator (over the lines to be processed)
   */
  private void handleList(String line, Iterator<String> it) {
    List<String> listLines = new ArrayList<>();
    listLines.add(line);
    int prevSpaces = 0;
    while (it.hasNext()) {
      line = it.next();

      // VALIDATE SPACING
      int curSpaces = 0;
      while ((curSpaces < line.length()) && (line.charAt(curSpaces) == Section.CHAR_SPACE)) {
        curSpaces++;
      }
      if (((prevSpaces % 2) != 0)
          || ((curSpaces > prevSpaces) && ((curSpaces - prevSpaces) != 2))) {
        throw new ParseException("Incorrectly formatted list");
      } else {
        prevSpaces = curSpaces;
      }

      // VALIDATE OTHER FORMATTING
      boolean cond1 = !line.trim().concat(Section.NEW_LINE).matches(Section.OL_MATCHER);
      boolean cond2 = !line.trim().concat(Section.NEW_LINE).matches(Section.UL_MATCHER);
      if (cond1 && cond2 && !line.equals(Section.NEW_LINE)) {
        throw new ParseException("Incorrectly formatted list");
      }

      // CHECK IF END OF LIST
      if (!line.equals(Section.NEW_LINE)) {
        listLines.add(line);
      } else {
        this.add(AbstractList.createList(listLines));
        break;
      }
    }
  }

  /**
   * Returns the depth of header contained in given string
   *
   * @param line The line containing the header
   * @return Given header's depth
   */
  private static int getHeaderDepth(String line) {
    int i = 0;
    while (line.charAt(i) == Section.CHAR_POUND) {
      i++;
    }
    if (i > Section.HEADER_MAXDEPTH) {
      return Section.HEADER_MAXDEPTH;
    } else {
      return i;
    }
  }

  /**
   * Given a string containing a header, returns the number of # signs preceding it
   *
   * @param line The string containing the header
   * @return The number of # signs
   */
  private static int getActualHeaderDepth(String line) {
    int i = 0;
    while (line.charAt(i) == Section.CHAR_POUND) {
      i++;
    }
    return i;
  }

  /**
   * Given the current line and an Iterator (over the remaining lines to processed), identifies all
   * the lines to be parsed as part of the new Section. The created Section is then added to this
   * Section
   *
   * @param line The first line of the new Section
   * @param it The iterator (over the lines to be processed)
   * @return The next after all lines that were processed
   */
  private String handleSection(String line, Iterator<String> it) {
    String ret = null;

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
    String headerContent =
        line.substring(Section.getActualHeaderDepth(line) + 1, line.indexOf(Section.NEW_LINE));
    Section newSection = new Section(headerContent);
    last.add(newSection);

    // 4. DETERMINE THE NUMBER OF LINES CONTAINED IN THIS SECTION
    ArrayList<String> subLines = new ArrayList<>();
    while (it.hasNext()) {
      line = it.next();
      if (line.matches(Section.HD_MATCHER)) {
        int newHeaderDepth = Section.getHeaderDepth(line);
        if (newHeaderDepth <= newSection.getDepth()) {
          ret = line;
          break;
        } else {
          subLines.add(line);
        }
      } else {
        subLines.add(line);
      }
    }
    newSection.parse(subLines);
    return ret;
  }

  private String handleBlockquote(String line, Iterator<String> it) {
    String ret = null;
    List<String> newBlock = new ArrayList<>();
    newBlock.add(line);
    while (it.hasNext()) {
      line = it.next();
      if (line.matches(Section.BQ_MATCHER)) {
        newBlock.add(line);
      } else {
        this.add(new Blockquote(newBlock));
        ret = line;
        break;
      }
    }
    return ret;
  }

  /**
   * Given the current line and an Iterator (over the remaining lines to processed), identifies all
   * the lines to be parsed as part of the new Paragraph. The created paragraph is then added to
   * this Section
   *
   * @param line The first line of the new paragraph
   * @param it The iterator (over the lines to be processed)
   */
  private void handleParagraph(String line, Iterator<String> it) {
    StringBuilder paragraph = new StringBuilder();
    paragraph.append(line);
    while (it.hasNext()) {
      line = it.next();
      if (!line.equals(Section.NEW_LINE)) {
        paragraph.append(line);
      } else {
        this.add(new Paragraph(paragraph.toString()));
        break;
      }
    }
  }

  /**
   * {@inheritDoc }
   */
  @Override
  public int hashCode() {
    int hash = super.hashCode();
    hash = (59 * hash) + Objects.hashCode(this.nodes);
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
   * Splits a given string into a list of strings delimited by the NL character
   *
   * @param block The block to be split
   * @return An ArrayList of strings
   */
  public static ArrayList<String> split(String block) {
    return new ArrayList<>(Arrays.asList(block.replaceFirst("\\s+$", "").split("(?<=\n)")));
  }
}
