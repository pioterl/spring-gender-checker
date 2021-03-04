package com.silenteight.springgenderchecker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.silenteight.springgenderchecker.NameRepository.FEMALE_NAMES;
import static com.silenteight.springgenderchecker.NameRepository.MALE_NAMES;

@Service
@RequiredArgsConstructor
public class NameService {

    private final NameRepository nameRepository;

    public String readName(String name) {
        String extractedName = nameRepository.firstWordExtractor(name);
        boolean maleNamePresent = nameRepository.isNamePresent(MALE_NAMES, extractedName);
        boolean femaleNamePresent = nameRepository.isNamePresent(FEMALE_NAMES, extractedName);
        return nameRepository.printGenderByFirstName(maleNamePresent, femaleNamePresent);
    }

}
