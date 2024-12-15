import com.lesmaleinad.aoc2024.Day14
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day14Test {
    private val exampleInput =
        """
        p=0,4 v=3,-3
        p=6,3 v=-1,-3
        p=10,3 v=-1,2
        p=2,0 v=2,-1
        p=0,0 v=1,3
        p=3,0 v=-2,-2
        p=7,6 v=-1,-3
        p=3,0 v=-1,-2
        p=9,3 v=2,3
        p=7,3 v=-1,2
        p=2,4 v=2,-3
        p=9,5 v=-3,-3
        """.trimIndent()

    private val inputFile by lazy { File("src/test/resources/day14.txt").readText() }

    @Test
    fun `example part 1`() {
        // given
        val input = exampleInput

        // when
        val result = Day14.part1(input)

        // then
        assertEquals(12, result)
    }

    @Test
    fun `real input part 1`() {
        // given
        val input = inputFile

        // when
        val result = Day14.part1(input)

        // then
        assertEquals(231852216, result)
    }

    @Test
    fun `real input part 2`() {
        // given
        val input = inputFile

        // when
        val result = Day14.part2(input)

        // then
        assertEquals(8159L, result)
    }
}
