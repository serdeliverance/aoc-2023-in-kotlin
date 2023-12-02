import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class Day01KtTest {

    @Test
    fun getDigitTest() {
        assertEquals(9 , getDigit("9vnxqtjjrsg", possibleNumberFirstLetterWhenReversed, numbersAsLettersReversed))
    }
}