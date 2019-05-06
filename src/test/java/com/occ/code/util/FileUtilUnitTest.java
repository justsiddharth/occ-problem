package com.occ.code.util;

import com.occ.code.exception.EmptyFileException;
import com.occ.code.exception.InvalidFilePathException;
import com.occ.code.util.impl.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class FileUtilUnitTest {

    @InjectMocks
    FileUtil fileUtil;

    @Test(expected = InvalidFilePathException.class)
    public void whenFilePathIsNull_throwIllegalArgumentException() {
        fileUtil.read(null);
    }

    @Test(expected = InvalidFilePathException.class)
    public void whenFileNotPresent_throwNoSuchFileException() {
        String path = "dummy/path";
        fileUtil.read(path);
    }

    @Test(expected = EmptyFileException.class)
    public void whenTestFileIsEmpty_throwIOException() {
        String path = "src/test/resources/FileIO_Empty.txt";
        fileUtil.read(path);
    }

    @Test
    public void whenTestFileIsValid_returnResultList() {
        String path = "src/test/resources/FileIO_ValidInput.txt";
        String line = "\"MARY\",\"PATRICIA\",\"LINDA\",\"BARBARA\",\"VINCENZO\",\"SHON\",\"LYNWOOD\",\"JERE\",\"HAI\"";
        List<String> result = fileUtil.read(path);
        assertTrue(result.size() == 1);
        assertTrue(result.get(0).equals(line));
    }
}
