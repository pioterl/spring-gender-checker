package com.silenteight.springgenderchecker;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class NameControllerTest {

    @Autowired
    private NameController nameController;

    @Test
    void checkName() {
        String maleName = "Robert";
        String femaleName = "Grażyna";
        String inconclusiveName = "Asadasd";

        assertEquals("male", nameController.checkName(maleName));
        assertEquals("female", nameController.checkName(femaleName));
        assertEquals("inconclusive (no such name in both files)", nameController.checkName(inconclusiveName));
    }
}