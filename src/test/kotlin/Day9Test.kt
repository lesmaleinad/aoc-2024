import com.lesmaleinad.aoc2024.Day9
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day9Test {
    private val exampleInput =
        """
        2333133121414131402
        """.trimIndent()

    private val inputFile by lazy { File("src/test/resources/day9.txt").readText() }

    @Test
    fun `example part 1`() {
        // given
        val input = exampleInput

        // when
        val result = Day9.part1(input)

        // then
        assertEquals(1928, result)
    }

    @Test
    fun `real input part 1`() {
        // given
        val input = inputFile

        // when
        val result = Day9.part1(input)

        // then
        assertEquals(6346871685398, result)
    }

    @Test
    fun `example part 2`() {
        // given
        val input = exampleInput

        // when
        val result = Day9.part2(input)

        // then
        assertEquals(2858, result)
    }

    @Test
    fun `real input part 2`() {
        // given
        val input = inputFile

        // when
        val result = Day9.part2(input)

        // then
        assertEquals(6373055193464, result)
    }
}
