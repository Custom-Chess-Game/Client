package tests.configuration;

import com.github.smuddgge.results.ResultChecker;
import com.github.smuddgge.results.ResultNotNull;
import com.github.smudgge.configuration.Config;
import org.junit.jupiter.api.Test;

/**
 * Used to test the config class
 */
public class TestConfig {

    @Test
    public void testScreenSize() throws Exception {
        Config config = new Config();

        new ResultChecker().expect(config.getScreenSize(), new ResultNotNull());
    }
}
