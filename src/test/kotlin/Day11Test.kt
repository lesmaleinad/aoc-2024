import com.lesmaleinad.aoc2024.Day11
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day11Test {
    private val exampleInput =
        """125 17""".trimIndent()

    private val inputFile by lazy { File("src/test/resources/day11.txt").readText() }

    @Test
    fun `example part 1`() {
        // given
        val input = exampleInput

        // when
        val result = Day11.part1(input)

        // then
        assertEquals(55312, result)
    }

    @Test
    fun `real input part 1`() {
        // given
        val input = inputFile

        // when
        val result = Day11.part1(input)

        // then
        assertEquals(233875, result)
    }

    @Test
    fun `real input part 2`() {
        // given
        val input = inputFile

        // when
        val result = Day11.part2(input)

        // then
        assertEquals(277444936413293, result)
    }
}
