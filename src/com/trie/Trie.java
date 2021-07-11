package com.trie;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Trie {
    private Character letter;
    private Map<Character, Trie> downline = new HashMap<Character, Trie>();
    private int end = 0; // incremented when reach to end of sourceString
    private int visit = 0; // increment when add routine visited

    public Trie(Character aChar) {
        letter = aChar;
    }
    void reset() {
        end = 0;
        visit = 0;
        downline.clear();
    }
    void add(String sourceString, int index) {
        visit++;
        if (index >= sourceString.length()) {
            end++;
            return;
        }
        var thisChar = sourceString.charAt(index);
        if (!this.downline.containsKey(thisChar)) {
            var next = new Trie(thisChar);
            this.downline.put(thisChar, next);
        }
        this.downline.get(thisChar).add(sourceString, index + 1);
    }
    int getVisit() { return visit;}

    public String toString() {
        var sb = new StringBuilder("{ name:'"+ letter +  ((end > 0) ? ("', end:" + end + ",") : "',") + "visit:"+visit);
        sb.append(", downline: {");
        for (Character name: downline.keySet()) {
            sb.append(name);
            sb.append(": ");
            sb.append(downline.get(name).toString());
            sb.append(",");
        }
        sb.append("}}");
        return sb.toString();
    }

    int[] getMatchedAndTotal(Set<Character> keyChars) {
        // first count is the matched visit and the 2nd is the visit regardless of matching
        int[] ans = keyChars.contains(letter) ? new int[]{visit, visit} : new int[]{0, visit};
        for (Trie subT : downline.values()) {
            int[] subAns = subT.getMatchedAndTotal(keyChars);
            ans[0] = ans[0] + subAns[0];
            ans[1] = ans[1] + subAns[1];
        }
        return ans;
    }

}

