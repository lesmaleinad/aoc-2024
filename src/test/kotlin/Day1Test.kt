import com.lesmaleinad.aoc2024.Day1
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day1Test {
    @Test
    fun `example works part 1`() {
        // given
        val input =
            """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
            """.trimIndent()

        // when
        val result = Day1.part1(input)

        // then
        assertEquals(11, result)
    }

    @Test
    fun `real input part 1`() {
        // given
        val input = File("src/test/resources/day1.txt").readText()

        // when
        val result = Day1.part1(input)

        // then
        assertEquals(3508942, result)
    }

    @Test
    fun `example works part 2`() {
        // given
        val input =
            """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
            """.trimIndent()

        // when
        val result = Day1.part2(input)

        // then
        assertEquals(31, result)
    }

    @Test
    fun `real input part 2`() {
        // given
        val input = File("src/test/resources/day1.txt").readText()

        // when
        val result = Day1.part2(input)

        // then
        println(result)
    }
}
