package TestRunner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
features ="src/test/java/Features",
	glue = {"StepDefinition"},
	tags="@activity4",
	publish =true,
	plugin = {
			"pretty",
			"html:src/reports/HTML_Report.html",
			"json:src/reports/JSON_Repory.json",
			"junit:src/reports/XML_Reports.xml"
	},
	monochrome =true
)
public class Activity6JSONreport {

}
