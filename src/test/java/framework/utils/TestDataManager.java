package framework.utils;

import java.io.InputStream;
import java.util.Properties;

public class TestDataManager {

    private static Properties data;

    private TestDataManager() {}

    public static Properties load(String fileName) {
        if (data != null) return data;

        try (InputStream is = TestDataManager.class
                .getClassLoader()
                .getResourceAsStream("testdata/" + fileName)) {

            Properties p = new Properties();
            p.load(is);
            data = p;
            return p;

        } catch (Exception e) {
            throw new RuntimeException("Unable to load test data: " + fileName, e);
        }
    }
}
