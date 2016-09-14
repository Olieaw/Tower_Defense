package ru.spbstu.icc.kspt.kuryakin.app;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class FirstTest {
    @Test

    public void TestTranslationValues(){
        FirstClass firstClass = new FirstClass();
        assertEquals(300, firstClass.TranslationValues(3));
    }
}
