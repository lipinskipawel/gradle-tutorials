package org.example;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

class LibraryTest implements WithAssertions {

    @Test
    void someLibraryMethodReturnsTrue() {
        Library classUnderTest = new Library();

        assertThat(classUnderTest.someLibraryMethod()).isTrue();
    }
}
