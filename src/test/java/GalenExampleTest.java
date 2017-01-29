import com.galenframework.api.Galen;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by can on 29/01/17.
 */
public class GalenExampleTest
{
    private WebDriver driver;

    @Before
    public void setUp()
    {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200, 800));
        driver.get("http://www.swtestacademy.com/");
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void homePageLayoutTest() throws IOException
    {
        LayoutReport layoutReport = Galen.checkLayout(driver, "specs/homepage.gspec", Arrays.asList("desktop"));

        List<GalenTestInfo> tests = new LinkedList<GalenTestInfo>();

        GalenTestInfo test = GalenTestInfo.fromString("homepage layout");
        test.getReport().layout(layoutReport, "check homepage layout");
        tests.add(test);

        HtmlReportBuilder htmlReportBuilder = new HtmlReportBuilder();
        htmlReportBuilder.build(tests, "target");

        if (layoutReport.errors() > 0)
        {
            Assert.fail("Layout test failed");
        }
    }
}