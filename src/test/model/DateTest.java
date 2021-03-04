package model;


//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DateTest {
    private Date date;

    @BeforeEach
    public void setUp() {
        date = new Date(2021, 02, 14);
    }

    @Test
    public void testGetters() {
        assertEquals(2021, date.getYear());
        assertEquals(02, date.getMonth());
        assertEquals(14, date.getDay());

    }

    @Test
    public void testReturnDate() {
        assertEquals("2021 / 02 / 14", date.returnDate());
    }
}


