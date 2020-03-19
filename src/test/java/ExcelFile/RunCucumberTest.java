package ExcelFile;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty" }, glue = { "ExcelFile" }, monochrome = true)
public class RunCucumberTest {
}
