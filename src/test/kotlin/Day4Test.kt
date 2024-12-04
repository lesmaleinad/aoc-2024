import com.lesmaleinad.aoc2024.Day4
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day4Test {
    private val inputFile by lazy { File("src/test/resources/day4.txt").readText() }

    @Test
    fun `example part 1`() {
        // given
        val input =
            """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX
            """.trimIndent()

        // when
        val result = Day4.part1(input)

        // then
        assertEquals(18, result)
    }

    @Test
    fun `real input part 1`() {
        // given
        val input = inputFile

        // when
        val result = Day4.part1(input)

        // then
        assertEquals(2530, result)
    }

    @Test
    fun `example part 2`() {
        // given
        val input =
            """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX
            """.trimIndent()

        // when
        val result = Day4.part2(input)

        // then
        assertEquals(9, result)
    }

    @Test
    fun `real input part 2`() {
        // given
        val input = inputFile

        // when
        val result = Day4.part2(input)

        // then
        assertEquals(1921, result)
    }
}
