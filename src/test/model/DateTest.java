package model;


//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class DateTest {
    private Date date;

    @BeforeEach
    public void setUp() {
        date = new Date(2021, 02, 14, 12, 45);
    }

    @Test
    public void testGetters() {
        assertEquals(2021, date.getYear());
        assertEquals(02, date.getMonth());
        assertEquals(14, date.getDay());
        assertEquals(12, date.getHour());
        assertEquals(45, date.getMinute());
    }

    @Test
    public void testReturnDate() {
        assertEquals("2021 / 02 / 14", date.returnDate());
    }

    @Test
    public void testReturnTime() {
        assertEquals("12 : 45", date.returnTime());
    }
}
