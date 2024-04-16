package org.example;

import org.assertj.core.api.WithAssertions;
import org.example.base.Simpsons;
import org.junit.jupiter.api.Test;

final class PersonTest implements WithAssertions, Simpsons {

    @Test
    void shouldCreatePerson() {
        assertThat(MAT).isEqualTo(aPerson("Mat", "Wart"));
    }
}
