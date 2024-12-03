import com.lesmaleinad.aoc2024.Day2
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day2Test {
    @Test
    fun `example part 1`() {
        // given
        val input =
            """
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9
            
            """.trimIndent()

        // when
        val output = Day2.part1(input)

        // then
        assertEquals(2, output)
    }

    @Test
    fun `real input part 1`() {
        // given
        val input = File("src/test/resources/day2.txt").readText()

        // when
        val output = Day2.part1(input)

        // then
        assertEquals(379, output)
    }

    @Test
    fun `example part 2`() {
        // given
        val input =
            """
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9
            
            """.trimIndent()

        // when
        val output = Day2.part2(input)

        // then
        assertEquals(4, output)
    }

    @Test
    fun `real input part 2`() {
        // given
        val input = File("src/test/resources/day2.txt").readText()

        // when
        val output = Day2.part2(input)

        // then
        assertEquals(430, output)
    }
}
