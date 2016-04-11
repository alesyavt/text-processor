package edu.neu.ccs.cs5004.seattle.assignment9.html;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Blockquote extends AbstractParagraph {

    private List<AbstractParagraph> list;
    private int specialCharCount;
    private final static String SPECIAL_CHAR = ">";

    public Blockquote(List<String> block) {
        this(block, 1);
    }

    public Blockquote(List<String> block, int specialCount) {
        this.list = new ArrayList<>(); /// toPS
        this.specialCharCount = specialCount;
        if (specialCount(block.get(0)) == this.specialCharCount) {
            int paragIndex = addParag(block);
            bqHelper(block.subList(paragIndex, block.size()), 0);
        } else {
            bqHelper(block, 0);
        }
    }

    private int addParag(List<String> block) {
        int paragIndex = 1;
        StringBuilder parag = new StringBuilder();
        parag.append(block.get(0).substring(this.specialCharCount));
        while ((paragIndex < block.size()) && (!block.get(paragIndex).startsWith(Blockquote.SPECIAL_CHAR)
                || (specialCount(block.get(paragIndex)) == this.specialCharCount)
                || (specialCount(block.get(paragIndex)) == -1))) {
            if (specialCount(block.get(paragIndex)) == -1) {
                (parag.append(" ")).append(block.get(paragIndex));
            } else {
                parag.append(block.get(paragIndex).substring(this.specialCharCount));
            }
            paragIndex++;
        }
        this.list.add(new Paragraph(parag.toString()));
        return paragIndex;
    }

    private void bqHelper(List<String> block, int index) {
        // if (!block.isEmpty()) {
        if ((index < block.size()) && ((specialCount(block.get(index)) > this.specialCharCount)
                || (specialCount(block.get(index)) == -1))) {
            bqHelper(block, index + 1);
        } else if (index > 0) {
            this.list.add(new Blockquote(block.subList(0, index), this.specialCharCount + 1));
            if (index < block.size()) {
                int paragIndex = addParag(block.subList(index, block.size()));
                bqHelper(block.subList(paragIndex + index, block.size()), 0);
            }
        } else if (!block.isEmpty()) {
            int paragIndex = addParag(block.subList(index, block.size()));
            bqHelper(block.subList(paragIndex, block.size()), 0);
        }
        // }
    }

    private int specialCount(String s) {
        int specialCount = 0;
        while ((specialCount < s.length()) && (s.charAt(specialCount) == '>')) {
            specialCount++;
        }
        if (s.charAt(specialCount) != ' ') {
            return -1;
        }
        return specialCount;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String toPrettyString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<blockquote>");
        for (AbstractParagraph p : this.list) {
            stringBuilder.append(AbstractElement.emphasize(p.toPrettyString()));
        }
        stringBuilder.append("</blockquote>\n");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        List<String> test = new ArrayList<>();
        test.add("> What are the resources allocated?");
        test.add(">> Are you asking in terms of headcount or machines?");
        test.add("just text");
        test.add(">>>Headcount");
        test.add("more text");
        test.add(">>>>> jump from 2nd to 5th");
        test.add("> 1st level again");

        Blockquote bq = new Blockquote(test);
        System.out.println(bq.toPrettyString());
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.list);
        hash = 67 * hash + this.specialCharCount;
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
        final Blockquote other = (Blockquote) obj;
        if (this.specialCharCount != other.specialCharCount) {
            return false;
        }
        if (!Objects.equals(this.list, other.list)) {
            return false;
        }
        return true;
    }

}
