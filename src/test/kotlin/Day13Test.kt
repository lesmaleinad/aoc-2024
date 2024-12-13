import com.lesmaleinad.aoc2024.Day13
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day13Test {
    private val exampleInput =
        """
        Button A: X+94, Y+34
        Button B: X+22, Y+67
        Prize: X=8400, Y=5400

        Button A: X+26, Y+66
        Button B: X+67, Y+21
        Prize: X=12748, Y=12176

        Button A: X+17, Y+86
        Button B: X+84, Y+37
        Prize: X=7870, Y=6450

        Button A: X+69, Y+23
        Button B: X+27, Y+71
        Prize: X=18641, Y=10279
        """.trimIndent()

    private val inputFile by lazy { File("src/test/resources/day13.txt").readText() }

    @Test
    fun `example part 1`() {
        // given
        val input = exampleInput

        // when
        val result = Day13.part1(input)

        // then
        assertEquals(480, result)
    }

    @Test
    fun `real input part 1`() {
        // given
        val input = inputFile

        // when
        val result = Day13.part1(input)

        // then
        assertEquals(40369, result)
    }

    @Test
    fun `real input part 2`() {
        // given
        val input = inputFile

        // when
        val result = Day13.part2(input)

        // then
        assertEquals(72587986598368, result)
    }
}
