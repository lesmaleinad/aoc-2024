import com.lesmaleinad.aoc2024.Day6
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day6Test {
    private val inputFile by lazy { File("src/test/resources/day6.txt").readText() }

    @Test
    fun `example part 1`() {
        // given
        val input =
            """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...
            """.trimIndent()

        // when
        val result = Day6.part1(input)

        // then
        assertEquals(41, result)
    }

    @Test
    fun `real input part 1`() {
        // given
        val input = inputFile

        // when
        val result = Day6.part1(input)

        // then
        println("Part 1: $result")
        // assertEquals(0, result)
    }

    @Test
    fun `example part 2`() {
        // given
        val input =
            """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...
            """.trimIndent()

        // when
        val result = Day6.part2(input)

        // then
        assertEquals(6, result)
    }

    @Test
    fun `real input part 2`() {
        // given
        val input = inputFile

        // when
        val result = Day6.part2(input)

        // then
        println("Part 2: $result")
        // assertEquals(0, result)
    }
}
