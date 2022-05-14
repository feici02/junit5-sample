package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {
    User user = new User("Harry",
            20,
            false,
            LocalDate.now().minusYears(20));

    // should be static
    @BeforeAll
    static void setup() {
        System.out.println("setup...");
    }

    @AfterAll
    static void teardown() {
        System.out.println("teardown...");
    }

    @BeforeEach
    void setupEach() {
        System.out.println("setup each...");
    }

    @AfterEach
    void afterEach() {
        System.out.println("teardown each...");
    }

    @Test
    @Tag("sample")
    @DisplayName("user should be older than 18")
    void userShouldBeOlderThan18() {
        assertTrue(user.age() > 18);
        assertEquals("Harry", user.name());

        assertThat(user.age()).isGreaterThanOrEqualTo(18);
        assertThat(user.name()).startsWith("Ha");

        assertThat(user.blocked())
                .as("user %s should not be blocked", user.name())
                .isFalse();
    }

    @ParameterizedTest
    @DisplayName("all users should be older than 18")
    @ValueSource(ints = {20, 30, 40})
    void allUsersShouldBeOlderThan18(int age) {
        assertThat(age).isGreaterThanOrEqualTo(18);
    }

    @ParameterizedTest
    @DisplayName("all users from csv file should be older than 18")
    @CsvFileSource(resources = "/users.csv", numLinesToSkip = 1)
    void allUsersFromCsvFileShouldBeOlderThan18(String name, int age) {
        System.out.printf("name: %s, age: %d%n", name, age);
        assertThat(age).isGreaterThanOrEqualTo(18);
    }
}