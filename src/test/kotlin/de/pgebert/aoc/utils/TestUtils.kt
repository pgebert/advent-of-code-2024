package de.pgebert.aoc.utils

import org.junit.jupiter.api.Assertions


/**
 * Asserts that the receiver object is equal to the expected object.
 *
 * Usage:
 * actualValue shouldBe expectedValue
 *
 * @param expected The object to be compared with the receiver.
 *
 * @throws AssertionError if the receiver object is not equal to the actual object.
 */
infix fun <T> T.shouldBe(expected: T) = Assertions.assertEquals(expected, this)
