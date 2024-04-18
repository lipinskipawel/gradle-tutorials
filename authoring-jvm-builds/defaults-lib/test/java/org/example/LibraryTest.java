package org.example;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class LibraryTest implements WithAssertions {

    @Test
    void someLibraryMethodReturnsTrue() {
        Library classUnderTest = new Library();

        assertThat(classUnderTest.someLibraryMethod()).isTrue();
    }

    @Test
    void should_fail() {
        final var number = someMethod();

        assertThat(number).isEqualTo(2);
    }

    private int someMethod() {
        return anotherMethod();
    }

    private int anotherMethod() {
        return new Library().zeroOrOne();
    }

    @Test
    @Disabled
    void should_skip() {
        fail("This test should be skipped");
    }

    @Test
    void should_use_java21() {
        final var text = "hello";
        final var expected = STR."custom text: \{text}";

        final var result = new Library().format(text);

        assertThat(result).isEqualTo(expected);
    }
}
