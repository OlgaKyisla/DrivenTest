package tests;
import object.CalculatorPage;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class DataDrivenTest extends WebDriverSet {
    private static CalculatorPage calc;

    @Parameterized.Parameter
    public String expression;

    @Parameterized.Parameter(1)
    public String expected;

    @BeforeClass
    public static void setup() {
        calc = new CalculatorPage(driver);
        calc.open();
    }

    @Before
    public void cleanup() {
        calc.clear();
    }

    @Parameterized.Parameters(name = "Test: {0}={1}")
    public static Collection <Object[]> data(){
        return Arrays.asList(new Object[][]{
                { "",""},
                { "0+999","999"},
                { "0*-10","0"},
                { "0/-10","0"},
                { "0-1000","-1000"},
                { "1111*1111","1234321"},
                { "-2--2","0"},
                { "-2-+2","-4"},
                { "0.001+0.023","0.024"},
                { "10/0.1","100"},
                { "100000000000/0.000000000001","1e23"},
                { "9999+1111","11110"},
                { "0.10*10","1"},
                { "15//","15//"},
                { "-1/-1","1"},
                { "15/0","Error"},
                { "0/0","Error"},
                { "0.00000000001*0.00000000001","1e-22"},
                { "10000000000+9999999999","19999999999"},
                { "1.+1.1", "2.1" },
                { ".3+2", "2.3" }

        });
    }

    @Test
    public void calculator_DataDrivenTest(){
        Assert.assertEquals(expected, calc.calculate(expression));
    }

    @AfterClass
    public static void stop() {
        driver.quit();
    }

}
