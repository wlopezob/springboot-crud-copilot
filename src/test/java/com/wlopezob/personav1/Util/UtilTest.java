package com.wlopezob.personav1.Util;

import com.wlopezob.personav1.model.dto.PersonRequestDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    void convertObjectToJson() {
        assertNotNull(Util
                .convertObjectToJson(PersonRequestDto.builder().id(1L).build()));
    }

    @Test
    void convertJsonToObject() {
        assertNotNull(Util
                .convertJsonToObject("personRequestDtoRequest.json", PersonRequestDto.class));
    }
}