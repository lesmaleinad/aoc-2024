import com.lesmaleinad.aoc2024.Day8
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day8Test {
    private val exampleInput =
        """
        ............
        ........0...
        .....0......
        .......0....
        ....0.......
        ......A.....
        ............
        ............
        ........A...
        .........A..
        ............
        ............
        """.trimIndent()

    private val inputFile by lazy { File("src/test/resources/day8.txt").readText() }

    @Test
    fun `example part 1`() {
        // given
        val input = exampleInput

        // when
        val result = Day8.part1(input)

        // then
        assertEquals(14, result)
    }

    @Test
    fun `real input part 1`() {
        // given
        val input = inputFile

        // when
        val result = Day8.part1(input)

        // then
        assertEquals(398, result)
    }

    @Test
    fun `example part 2`() {
        // given
        val input = exampleInput

        // when
        val result = Day8.part2(input)

        // then
        assertEquals(34, result)
    }

    @Test
    fun `real input part 2`() {
        // given
        val input = inputFile

        // when
        val result = Day8.part2(input)

        // then
        assertEquals(1333, result)
    }
}
