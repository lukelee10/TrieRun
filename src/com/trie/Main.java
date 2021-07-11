package com.trie;

import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        String[] samples = {"ACTG", "AACT", "TCAGG", "ACTG", "ACTG", "GGCG", "TTGGA"};
        var thisTrie = new Trie(null); // root node
        for (String sam : samples) {
            thisTrie.add(sam, 0);
        }
        System.out.println(thisTrie);
        var tobeMatched = "GC".chars().mapToObj(e->(char)e).collect(Collectors.toSet());
        var ans = thisTrie.getMatchedAndTotal(tobeMatched);
        System.out.println("ratio is " + ans[0] *1.0 / (ans[1] - thisTrie.getVisit()));

        samples = new String[] {"ACTG","AACT","TCAGG","TTGGA"};
        thisTrie.reset();
        for (String sam : samples) {
            thisTrie.add(sam, 0);
        }
        System.out.println(thisTrie);
        tobeMatched = "GC".chars().mapToObj(e->(char)e).collect(Collectors.toSet());
        ans = thisTrie.getMatchedAndTotal(tobeMatched);
        System.out.println("ratio is " + ans[0] *1.0 / (ans[1] - thisTrie.getVisit()));
    }
}
