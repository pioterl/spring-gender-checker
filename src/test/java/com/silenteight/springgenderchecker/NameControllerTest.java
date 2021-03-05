package com.silenteight.springgenderchecker;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.silenteight.springgenderchecker.NameRepository.FILE_FEMALE_NAMES;
import static com.silenteight.springgenderchecker.NameRepository.FILE_MALE_NAMES;
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

    @Test
    void checkMajorityInName() {
        String maleNamesMajority = "Jadwiga Jerzy Robert";
        String femaleNamesMajority = "Dariusz Gertruda Weronika";
        String inconclusiveNames = "Dariusz Rokita Weronika";

        assertEquals("male", nameController.checkMajorityInName(maleNamesMajority));
        assertEquals("female", nameController.checkMajorityInName(femaleNamesMajority));
        assertEquals("inconclusive", nameController.checkMajorityInName(inconclusiveNames));
    }

    @Test
    void getAllMaleTokens() {
        List<String> result = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(FILE_MALE_NAMES))) {
            result = lines.collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertEquals(result, nameController.getAllMaleTokens());
    }

    @Test
    void getAllFemaleTokens() {
        List<String> result = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(FILE_FEMALE_NAMES))) {
            result = lines.collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertEquals(result, nameController.getAllFemaleTokens());
    }
}