package com.lesmaleinad.aoc2024

import com.lesmaleinad.aoc2024.utils.fromTheEnd

object Day9 {
    private data class BlockFile(val blocks: List<Block>, val totalSize: Int)

    private sealed class Block(val initialCount: Int) {
        var count = initialCount

        class File(val id: Int, count: Int) : Block(count) {
            fun getChecksum(current: Int): Long {
                return (current until current + count).sumOf {
                    it.toLong() * id
                }
            }

            override fun toString(): String {
                return "File $id ($count)"
            }
        }

        class Empty(count: Int) : Block(count) {
            override fun toString(): String {
                return "Empty ($count)"
            }
        }
    }

    private fun readInput(input: String): BlockFile {
        val blocks: List<Block> =
            input.mapIndexed { i, char ->
                when (i % 2) {
                    0 -> Block.File(id = i / 2, count = char.digitToInt())
                    else -> Block.Empty(count = char.digitToInt())
                }
            }
        return BlockFile(blocks, totalSize = blocks.sumOf { it.count })
    }

    /**
     * The disk map uses a dense format to represent the layout of files and free space on the disk.
     * The digits alternate between indicating the length of a file and the length of free space.
     *
     * The amphipod would like to move file blocks one at a time from the end of the disk to the
     * leftmost free space block (until there are no gaps remaining between file blocks).
     *
     * The final step of this file-compacting process is to update the filesystem checksum.
     * To calculate the checksum, add up the result of multiplying each of these blocks' position
     * with the file ID number it contains. The leftmost block is in position 0.
     * If a block contains free space, skip it instead.
     */
    fun part1(input: String): Long {
        val blockFile = readInput(input)
        val blocks = blockFile.blocks

        var current = 0
        var endIndex = blockFile.totalSize - 1

        // F, e, F    -- starts at 0 offset
        // F, e, F, e -- starts at 1 offset
        var endOffset = 1 - blocks.size % 2
        var lastBlock = blocks.fromTheEnd(endOffset) as Block.File

        return blocks.sumOf { block ->
            if (block.count == 0) {
                0L
            } else {
                (current until current + block.count).sumOf { position ->
                    position.toLong() *
                        if (endIndex < current) {
                            0
                        } else {
                            when (block) {
                                is Block.Empty -> {
                                    while (lastBlock.count == 0) {
                                        endIndex -= blocks.fromTheEnd(endOffset + 1)!!.count

                                        endOffset += 2
                                        lastBlock = blocks.fromTheEnd(endOffset) as Block.File
                                    }
                                    endIndex--
                                    lastBlock.count--
                                    current++
                                    lastBlock.id
                                }

                                is Block.File -> {
                                    current++
                                    block.id
                                }
                            }
                        }
                }
            }
        }
    }

    /**
     * Rather than move individual blocks, he'd like to try compacting the files
     * on his disk by moving whole files instead.
     */
    fun part2(input: String): Long {
        val blockFile = readInput(input)
        val blocks = blockFile.blocks

        var current = 0
        var currentIdx = 0

        return blocks.sumOf { block ->
            if (block.count == 0) {
                current += block.initialCount
                0L
            } else {
                when (block) {
                    is Block.Empty -> {
                        var acc = 0L
                        while (block.count > 0) {
                            val valid =
                                blocks.findLast {
                                    it is Block.File &&
                                        it.id > currentIdx &&
                                        it.count in 1..block.count
                                } as Block.File? ?: break
                            acc += valid.getChecksum(current)
                            current += valid.count
                            block.count -= valid.count
                            valid.count = 0
                        }
                        acc
                    }

                    is Block.File -> {
                        currentIdx = block.id
                        block.getChecksum(current)
                    }
                }.also {
                    current += block.count
                }
            }
        }
    }
}
