import com.lesmaleinad.aoc2024.Day16
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day16Test {
    private val exampleInput =
        """
        ###############
        #.......#....E#
        #.#.###.#.###.#
        #.....#.#...#.#
        #.###.#####.#.#
        #.#.#.......#.#
        #.#.#####.###.#
        #...........#.#
        ###.#.#####.#.#
        #...#.....#.#.#
        #.#.#.###.#.#.#
        #.....#...#.#.#
        #.###.#.#.#.#.#
        #S..#.....#...#
        ###############
        """.trimIndent()

    private val inputFile by lazy { File("src/test/resources/day16.txt").readText() }

    @Test
    fun `example part 1`() {
        // given
        val input = exampleInput

        // when
        val result = Day16.part1(input)

        // then
        assertEquals(7036, result)
    }

    @Test
    fun `real input part 1`() {
        // given
        val input = inputFile

        // when
        val result = Day16.part1(input)

        // then
        assertEquals(115500, result)
    }

    @Test
    fun `example part 2`() {
        // given
        val input = exampleInput

        // when
        val result = Day16.part2(input)

        // then
        assertEquals(45, result)
    }

    @Test
    fun `real input part 2`() {
        // given
        val input = inputFile

        // when
        val result = Day16.part2(input)

        // then
        assertEquals(679, result)
    }
}
