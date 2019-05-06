package com.occ.code.util;

import com.occ.code.util.impl.NameRankCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class NameRankerUnitTest {

    @InjectMocks
    IRankCalculator ranker = new NameRankCalculator();

    @Test
    public void whenStringIsA_scoreIsCalculated() {
        long result = ranker.calculateScore("A", 4);
        assertTrue(result == 4);
    }

    @Test
    public void whenNameEmptyOrNull_scoreIsZero() {
        String emptyString = "";
        assertTrue(ranker.calculateScore(emptyString, 1) == 0);
        assertTrue(ranker.calculateScore(null, 1) == 0);
    }

    @Test
    public void whenNameIsSpace_scoreIsZero() {
        String space = " ";
        assertTrue(ranker.calculateScore(space, 1) == 0);
    }

    @Test
    public void whenSanitizeAndSortIsCalled_listIsSanitized() {
        String one = "\"MARY\",\"PATRICIA\",\"LINDA\",\"BARBARA\"";
        String two = "\"VINCENZO\",\"SHON\",\"LYNWOOD\",\"JERE\",\"HAI\"";
        List<String> content = Arrays.asList(one, two);
        String[] result = ranker.sanitizeAndSort(content);
        assertTrue(result.length == 9);
        assertTrue(result[0].equals("BARBARA") && result[8].equals("VINCENZO"));
    }

    @Test
    public void whenGenerateScoreIsCalled_scoreIsReturned() {
        List<String> list = Arrays.asList("\"MARY\",\"PATRICIA\",\"LINDA\",\"BARBARA\",\"VINCENZO\",\"SHON\",\"LYNWOOD\",\"JERE\",\"HAI\"");
        String result = ranker.generateFileScore(list);
        assertTrue(result.equals("3194"));
    }

}
