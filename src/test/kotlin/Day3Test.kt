import com.lesmaleinad.aoc2024.Day3
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day3Test {
    @Test
    fun `example part 1 works`() {
        // given
        val input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"

        // when
        val result = Day3.part1(input)

        // then
        assertEquals(161, result)
    }

    @Test
    fun `real input part 1`() {
        // given
        val input = File("src/test/resources/day3.txt").readText()

        // when
        val result = Day3.part1(input)

        // then
        assertEquals(160672468, result)
    }

    @Test
    fun `example part 2 works`() {
        // given
        val input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"

        // when
        val result = Day3.part2(input)

        // then
        assertEquals(48, result)
    }

    @Test
    fun `real input part 2`() {
        // given
        val input = File("src/test/resources/day3.txt").readText()

        // when
        val result = Day3.part2(input)

        // then
        assertEquals(84893551, result)
    }
}
