import com.lesmaleinad.aoc2024.Day5
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day5Test {
    private val inputFile by lazy { File("src/test/resources/day5.txt").readText() }

    @Test
    fun `example part 1`() {
        // given
        val input =
            """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13

            75,47,61,53,29
            97,61,53,29,13
            75,29,13
            75,97,47,61,53
            61,13,29
            97,13,75,29,47
            """.trimIndent()

        // when
        val result = Day5.part1(input)

        // then
        assertEquals(143, result)
    }

    @Test
    fun `real input part 1`() {
        // given
        val input = inputFile

        // when
        val result = Day5.part1(input)

        // then
        assertEquals(5087, result)
    }

    @Test
    fun `example part 2`() {
        // given
        val input =
            """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13

            75,47,61,53,29
            97,61,53,29,13
            75,29,13
            75,97,47,61,53
            61,13,29
            97,13,75,29,47
            """.trimIndent()

        // when
        val result = Day5.part2(input)

        // then
        assertEquals(123, result)
    }

    @Test
    fun `real input part 2`() {
        // given
        val input = inputFile

        // when
        val result = Day5.part2(input)

        // then
        assertEquals(4971, result)
    }
}
