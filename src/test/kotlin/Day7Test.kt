import com.lesmaleinad.aoc2024.Day7
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day7Test {
    private val inputFile by lazy { File("src/test/resources/day7.txt").readText() }

    @Test
    fun `example part 1`() {
        // given
        val input =
            """
            190: 10 19
            3267: 81 40 27
            83: 17 5
            156: 15 6
            7290: 6 8 6 15
            161011: 16 10 13
            192: 17 8 14
            21037: 9 7 18 13
            292: 11 6 16 20
            """.trimIndent()

        // when
        val result = Day7.part1(input)

        // then
        assertEquals(3749, result)
    }

    @Test
    fun `real input part 1`() {
        // given
        val input = inputFile

        // when
        val result = Day7.part1(input)

        // then
        assertEquals(3312271365652, result)
    }

    @Test
    fun `example part 2`() {
        // given
        val input =
            """
            190: 10 19
            3267: 81 40 27
            83: 17 5
            156: 15 6
            7290: 6 8 6 15
            161011: 16 10 13
            192: 17 8 14
            21037: 9 7 18 13
            292: 11 6 16 20
            """.trimIndent()

        // when
        val result = Day7.part2(input)

        // then
        assertEquals(11387, result)
    }

    @Test
    fun `real input part 2`() {
        // given
        val input = inputFile

        // when
        val result = Day7.part2(input)

        // then
        assertEquals(509463489296712, result)
    }
}
