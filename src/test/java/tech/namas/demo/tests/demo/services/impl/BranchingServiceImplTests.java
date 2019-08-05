package tech.namas.demo.tests.demo.services.impl;

import org.junit.Before;
import org.junit.Test;
import tech.namas.demo.tests.demo.services.BranchingService;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BranchingServiceImplTests {

    private BranchingService service;

    @Before
    public void setup() {
        this.service = new BranchingServiceImpl();
    }

    @Test
    public void testDetermineGender() {
        assertEquals("M", this.service.determineGender("Mr"));

        assertEquals("F", this.service.determineGender("Ms"));
        assertEquals("F", this.service.determineGender("Mrs"));
        assertEquals("F", this.service.determineGender("Miss"));

        assertEquals("?", this.service.determineGender("GODZILLA"));
    }

    @Test
    public void testIsEven() {
        assertTrue(this.service.isEven(10));
        assertFalse(this.service.isEven(11));
    }

    @Test
    public void testGenerateSequence() {
        Integer count = 5;
        List<Integer> actual = this.service.generateSequence(count);

        assertEquals(count.intValue(), actual.size());
        for (int i = 0; i < count; i++) {
            assertNotNull(actual.get(i));
            assertEquals(i, actual.get(i).intValue());
        }
    }

    @Test
    public void testFizzBuzz() {
        Integer count = 16;
        List<String> result = Arrays.asList(
            "FizzBuzz", "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8",
            "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz"
        );

        List<String> actual = this.service.fizzBuzz(count);
        assertEquals(count.intValue(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            assertNotNull(result.get(i));
            assertNotNull(actual.get(i));
            assertEquals(result.get(i), actual.get(i));
        }
    }
}
