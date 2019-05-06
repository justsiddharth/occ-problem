package com.occ.code.util;

import java.util.List;


/**
 * Reusable functions to calculate the score of a list of names.
 *
 */
public interface IRankCalculator {

    String generateFileScore(List<String> content);

    String[] sanitizeAndSort(List<String> content);

    long calculateScore(String name, long position);
}
