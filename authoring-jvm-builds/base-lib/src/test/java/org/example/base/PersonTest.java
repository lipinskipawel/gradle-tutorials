package org.example.base;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

final class PersonTest implements WithAssertions {

    @Test
    void shouldTest() {
        assertThat(1).isEqualTo(1);
    }
}
