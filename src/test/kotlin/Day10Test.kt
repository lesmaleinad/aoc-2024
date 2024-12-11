import com.lesmaleinad.aoc2024.Day10
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day10Test {
    private val exampleInput =
        """
        89010123
        78121874
        87430965
        96549874
        45678903
        32019012
        01329801
        10456732
        """.trimIndent()

    private val inputFile by lazy { File("src/test/resources/day10.txt").readText() }

    @Test
    fun `example part 1`() {
        // given
        val input = exampleInput

        // when
        val result = Day10.part1(input)

        // then
        assertEquals(36, result)
    }

    @Test
    fun `real input part 1`() {
        // given
        val input = inputFile

        // when
        val result = Day10.part1(input)

        // then
        assertEquals(611, result)
    }

    @Test
    fun `example part 2`() {
        // given
        val input = exampleInput

        // when
        val result = Day10.part2(input)

        // then
        assertEquals(81, result)
    }

    @Test
    fun `real input part 2`() {
        // given
        val input = inputFile

        // when
        val result = Day10.part2(input)

        // then
        assertEquals(1380, result)
    }
}
