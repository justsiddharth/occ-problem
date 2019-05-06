package com.occ.code.util.impl;

import com.occ.code.util.IRankCalculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 *
 * This class is a Helper class which contains the implemtation of Name Scoring Algorithm.
 * The class implements IRankCalculator, which is an interface that defines utility functions.
 *
 */
public class NameRankCalculator implements IRankCalculator {
    private static final Logger log = LoggerFactory.getLogger(NameRankCalculator.class);
    private static Map<Character, Integer> scoreMap = new HashMap<>();

    public NameRankCalculator() {
        initialize();
    }

    /**
     * The function reads, sanitizes and loops through all names adding their score and returning the final score.
     *
     * The function uses addAndGet method which atomically adds the given value to the current value of a field
     * or array element within the given object.
     *
     * AddAndGet has a better performance than using reduction.
     *
     * @param content is a List of String read from the file.
     * @return Score of the file, as a String
     */
    @Override
    public String generateFileScore(List<String> content) {
        long startTime = System.currentTimeMillis();
        int weight = 1;

        String[] names = sanitizeAndSort(content);

        AtomicLong score = new AtomicLong();
        for(String name : names) {
            score.addAndGet(calculateScore(name, weight));
            weight++;
        }
        log.info("Algorithm Runtime : {}", System.currentTimeMillis() - startTime);
        return score.toString();
    }

    /**
     * The function streams through the list of Strings read from the file, sanitizes and removes special characters,
     * in this case : '"' and then splits it into a String array on "," delimiter.
     *
     * @param content is the list of Strings, read from the file that have to be sanitized.
     * @return a String array of Sanitized names
     */
    @Override
    public String[] sanitizeAndSort(List<String> content) {
        List<String> collection = content.stream()
                .map(line -> line.replace("\"",""))
                .map(line -> line.replace(" ",""))
                .collect(Collectors.toList());
        String[] names = String.join(",", collection).split("\\,");
        Arrays.sort(names);
        return names;
    }

    /**
     * Implements the calculateScore Algorithm, where it uses the score for the alphabet between 1-26,
     * and the respective position in the array to generate a score.
     *
     * @param name is the name read from the array
     * @param position is the position of that name in the array
     * @return the score of the name
     */
    @Override
    public long calculateScore(String name, long position) {
        int result = 0;
        if(name == null || name.isEmpty()) {
            return result;
        }
        char[] nameArray = name.toCharArray();
        for(char n : nameArray) {
            result+= scoreMap.get(n);
        }
        return Math.toIntExact(result * position);
    }

    public void initialize() {
        scoreMap.put(' ', 0);
        scoreMap.put('A', 1);
        scoreMap.put('B', 2);
        scoreMap.put('C', 3);
        scoreMap.put('D', 4);
        scoreMap.put('E', 5);
        scoreMap.put('F', 6);
        scoreMap.put('G', 7);
        scoreMap.put('H', 8);
        scoreMap.put('I', 9);
        scoreMap.put('J', 10);
        scoreMap.put('K', 11);
        scoreMap.put('L', 12);
        scoreMap.put('M', 13);
        scoreMap.put('N', 14);
        scoreMap.put('O', 15);
        scoreMap.put('P', 16);
        scoreMap.put('Q', 17);
        scoreMap.put('R', 18);
        scoreMap.put('S', 19);
        scoreMap.put('T', 20);
        scoreMap.put('U', 21);
        scoreMap.put('V', 22);
        scoreMap.put('W', 23);
        scoreMap.put('X', 24);
        scoreMap.put('Y', 25);
        scoreMap.put('Z', 26);
    }
}
