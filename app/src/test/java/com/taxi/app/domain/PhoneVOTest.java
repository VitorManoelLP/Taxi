package com.taxi.app.domain;

import com.taxi.app.domain.object.PhoneVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PhoneVOTest {

    private final PhoneVO phoneVO = new PhoneVO(44, 555544444L, 55);

    @Test
    public void shouldFormatPhone() {
        assertEquals(phoneVO.toString(), "5544555544444");
    }

    @Test
    public void shouldThrowWhenDDDHasMoreThan2Digits(){
        assertThrows("Invalid DDD", IllegalArgumentException.class, () -> new PhoneVO(444, 555544444L, 55));
    }

    @Test
    public void shouldThrowWhenCountryCodeHasMoreThan2Digits(){
        assertThrows("Invalid Country Code", IllegalArgumentException.class, () -> new PhoneVO(44, 555544444L, 554));
    }

    @Test
    public void shouldThrowWhenPhoneNumberCodeHasMoreThan11Digits(){
        assertThrows("Invalid Phone Number", IllegalArgumentException.class, () -> new PhoneVO(44, 55554444432344L, 55));
    }

    @Test
    public void shouldSplitPhoneByString() {
        final PhoneVO phone = PhoneVO.of("5544555544444");
        assertEquals(phone.getDdd(), 44);
        assertEquals(phone.getCountryCode(), 55);
        assertEquals(phone.getNumber(), 555544444);
    }

}
