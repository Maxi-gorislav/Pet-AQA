package common;

import helper.clean_up.CleanUpExtension;
import io.qameta.allure.Link;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Link(name = "allure", type = "mylink")
@ExtendWith(CleanUpExtension.class)
@Execution(ExecutionMode.SAME_THREAD)
public class BaseTest {
}
