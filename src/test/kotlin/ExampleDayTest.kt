import com.lesmaleinad.aoc2024.ExampleDay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class ExampleDayTest {
    private val exampleInput =
        """
        """.trimIndent()

    private val inputFile by lazy { File("src/test/resources/exampleDay.txt").readText() }

    @Test
    fun `example part 1`() {
        // given
        val input = exampleInput

        // when
        val result = ExampleDay.part1(input)

        // then
        assertEquals(0, result)
    }

    @Test
    fun `real input part 1`() {
        // given
        val input = inputFile

        // when
        val result = ExampleDay.part1(input)

        // then
        println("Part 1: $result")
        // assertEquals(0, result)
    }

    @Test
    fun `example part 2`() {
        // given
        val input = exampleInput

        // when
        val result = ExampleDay.part2(input)

        // then
        assertEquals(0, result)
    }

    @Test
    fun `real input part 2`() {
        // given
        val input = inputFile

        // when
        val result = ExampleDay.part2(input)

        // then
        println("Part 2: $result")
        // assertEquals(0, result)
    }
}
