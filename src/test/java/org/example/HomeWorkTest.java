package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeWorkTest {

    HomeWork homeWork = new HomeWork();

    @Test
    void check() {
        assertEquals(3.0, homeWork.calculate("1 + 2"), 0.0001);
        assertEquals(6.0, homeWork.calculate("2 + 2 * 2"), 0.0001);
        assertEquals(9.0, homeWork.calculate("pow ( 3 , 2 )"), 0.0001);
        assertEquals(25.0, homeWork.calculate("pow ( 3 + 2 , 2 )"), 0.0001);
        assertEquals(1.0, homeWork.calculate("sin ( 1.5708 )"), 0.0001);
        assertEquals(1.0, homeWork.calculate("pow ( sin ( 1.5708 ) * 2 , cos ( 1.5708 )"), 0.0001);
        assertEquals(5.0, homeWork.calculate("10 / ( sin ( 1.5708 ) + 1 )"), 0.0001);
        assertEquals(25.0, homeWork.calculate("sqr ( 10 / ( sin ( 1.5708 ) + 1 ) )"), 0.0001);
    }

}