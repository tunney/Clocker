package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

public class ClockerTest {

    @Test
    public void randomString(){
        Clocker m = new Clocker("here.there.com","jj", "oo");
        String padding = m.randomPadding();
        Assertions.assertEquals(16, padding.length());
    }

    @Test
    public void getToConvert() throws UnsupportedEncodingException {
        String password1234 = "Password1234";
        Clocker m = new Clocker("here.there.com","jj", password1234);
        String toConvert = m.getConvertedPassword(password1234);
        Assertions.assertEquals(0, (m.asciiToHex(toConvert).length() % 56));
    }
}
