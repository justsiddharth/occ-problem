package com.occ.code;

import com.occ.code.util.impl.NameRankCalculator;
import com.occ.code.util.impl.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

/**
 * Run `./gradlew clean build` to compile, run tests and build jar.
 * Run `java -jar ./build/libs/occ-1.0-SNAPSHOT.jar` to run the Application.
 *
 * run() - Takes input of the file path from the User and
 *         calls generateFileScore() function of NameRankCalculator class and prints the file Score.
 */
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private static NameRankCalculator rankCalculator = new NameRankCalculator();

    public static void main(String[] args) {
        Application application = new Application();
        application.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        log.info("Please provide the file location : ");
        String fileName = scanner.nextLine();
        scanner.close();
        List<String> content = FileUtil.read(fileName);
        String result = rankCalculator.generateFileScore(content);
        log.info("The File score is : {}", result);
    }
}