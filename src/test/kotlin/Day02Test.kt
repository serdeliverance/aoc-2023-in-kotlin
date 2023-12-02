import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.Test

class Day02Test {
    @Test
    fun parseTest() {
        val game = parseGame("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")
        assertTrue(Game(1, listOf(GameSet(3, 4, 0), GameSet(6, 1, 2), GameSet(0, 0, 2))) == game)
    }
}
