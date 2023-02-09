package lesson2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OverflowTest {

    @Test
    void TestisOverflow2Add() {
        int x = 2147483000;
        int y = 648;
        assertTrue((x>>31 & y>>31) != Overflow.isOverflow2(x, y, ((a, b) -> a + b))>>31);

    }
    @Test
    void TestisOverflow2Sub() {
        int x = -2147483646;
        int y = 2;
        assertTrue((x>>31 & y>>31) != Overflow.isOverflow2(x, y, ((a, b) -> a - b))>>31);

    }

    @Test
    void TestisOverflow2Mul() {
        int x = 2147483600;
        int y = 8;
        assertTrue((x>>31 & y>>31) != Overflow.isOverflow2(x, y, ((a, b) -> a * b))>>31);

    }

    @Test
    void TestisOverflow2AddFalse() {
        int x = 22222;
        int y = 5;
        assertFalse((x>>31 & y>>31) != Overflow.isOverflow2(x, y, ((a, b) -> a + b))>>31);

    }

    @Test
    void TestisOverflow2MulFalse() {
        int x = 22222;
        int y = 5;
        assertFalse((x>>31 & y>>31) != Overflow.isOverflow2(x, y, ((a, b) -> a * b))>>31);

    }

    @Test
    void TestisOverflow() {
        int x = 2147483646;
        int y = 2;
        assertTrue(Overflow.isOverflow(x, y, (a, b) -> a + b));
    }
}