import org.junit.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.concurrent.TimeUnit;
import java.net.URL;
import io.appium.java_client.windows.WindowsDriver;

class MetatraderTest {
    var calculatorSession: WindowsDriver<*>? = null
    var calculatorResult: WebElement? = null
    companion object {
        @BeforeClass
        @JvmStatic
        fun setup() {
            try {
                val capabilities = DesiredCapabilities()
                capabilities.setCapability("app", "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App")
                val calculatorSession = WindowsDriver<WebElement>(URL("http://127.0.0.1:4723"), capabilities)
                calculatorSession!!.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)

                val calculatorResult = calculatorSession!!.findElementByAccessibilityId("CalculatorResults")
                Assert.assertNotNull(calculatorResult)
            } catch (e: Exception) {
                    e.printStackTrace()
            }
        }

            @AfterClass
            @JvmStatic
            fun tearDown() {
            }
        }

        @Before
        fun clear() {
            calculatorSession?.findElementByName("Clear")?.click()
            Assert.assertEquals("0", getCalculatorResultText())
        }

        @Test
        fun addition() {
            calculatorSession?.findElementByName("One")?.click()
            calculatorSession?.findElementByName("Plus")?.click()
            calculatorSession?.findElementByName("Seven")?.click()
            calculatorSession?.findElementByName("Equals")?.click()
            Assert.assertEquals("8", getCalculatorResultText())
        }

        @Test
        fun combination() {
            calculatorSession?.findElementByName("Seven")?.click()
            calculatorSession?.findElementByName("Multiply by")?.click()
            calculatorSession?.findElementByName("Nine")?.click()
            calculatorSession?.findElementByName("Plus")?.click()
            calculatorSession?.findElementByName("One")?.click()
            calculatorSession?.findElementByName("Equals")?.click()
            calculatorSession?.findElementByName("Divide by")?.click()
            calculatorSession?.findElementByName("Eight")?.click()
            calculatorSession?.findElementByName("Equals")?.click()
            Assert.assertEquals("8", getCalculatorResultText())
        }

        @Test
        fun division() {
            calculatorSession?.findElementByName("Eight")?.click()
            calculatorSession?.findElementByName("Eight")?.click()
            calculatorSession?.findElementByName("Divide by")?.click()
            calculatorSession?.findElementByName("One")?.click()
            calculatorSession?.findElementByName("One")?.click()
            calculatorSession?.findElementByName("Equals")?.click()
            Assert.assertEquals("8", getCalculatorResultText())
        }

        @Test
        fun multiplication() {
            calculatorSession?.findElementByName("Nine")?.click()
            calculatorSession?.findElementByName("Multiply by")?.click()
            calculatorSession?.findElementByName("Nine")?.click()
            calculatorSession?.findElementByName("Equals")?.click()
            Assert.assertEquals("81", getCalculatorResultText())
        }

        @Test
        fun subtraction() {
            calculatorSession?.findElementByName("Nine")?.click()
            calculatorSession?.findElementByName("Minus")?.click()
            calculatorSession?.findElementByName("One")?.click()
            calculatorSession?.findElementByName("Equals")?.click()
            Assert.assertEquals("8", getCalculatorResultText())
        }

        private fun getCalculatorResultText(): String {
            return calculatorResult?.text?.replace("Display is", "")?.trim() ?: ""
        }
    }
