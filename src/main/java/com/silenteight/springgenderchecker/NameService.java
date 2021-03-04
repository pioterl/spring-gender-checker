package com.silenteight.springgenderchecker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.silenteight.springgenderchecker.NameRepository.FEMALE_NAMES;
import static com.silenteight.springgenderchecker.NameRepository.MALE_NAMES;

@Service
@RequiredArgsConstructor
public class NameService {

    private final NameRepository nameRepository;

    public String extractAndCompareName(String name) {
        String extractedName = nameRepository.firstWordExtractor(name);
        boolean maleNamePresent = nameRepository.isNamePresent(MALE_NAMES, extractedName);
        boolean femaleNamePresent = nameRepository.isNamePresent(FEMALE_NAMES, extractedName);
        return nameRepository.printGenderByFirstName(maleNamePresent, femaleNamePresent);
    }

    public String compareMajorityInName(String name) {

        int countedWords = nameRepository.countWords(name);
        List<String> listOfWords = nameRepository.wordsToList(name);

        if (listOfWords.size() < 3) {
            return "You need to type 3 words";
        }

        String name1 = listOfWords.get(0);
        String name2 = listOfWords.get(1);
        String name3 = listOfWords.get(2);

        boolean maleNamePresent1 = nameRepository.isNamePresent(MALE_NAMES, name1);
        boolean maleNamePresent2 = nameRepository.isNamePresent(MALE_NAMES, name2);
        boolean maleNamePresent3 = nameRepository.isNamePresent(MALE_NAMES, name3);

        boolean femaleNamePresent1 = nameRepository.isNamePresent(FEMALE_NAMES, name1);
        boolean femaleNamePresent2 = nameRepository.isNamePresent(FEMALE_NAMES, name2);
        boolean femaleNamePresent3 = nameRepository.isNamePresent(FEMALE_NAMES, name3);

        int maleNameCounter = 0;
        int femaleNameCounter = 0;

        if (maleNamePresent1) maleNameCounter++;
        if (maleNamePresent2) maleNameCounter++;
        if (maleNamePresent3) maleNameCounter++;

        if (femaleNamePresent1) femaleNameCounter++;
        if (femaleNamePresent2) femaleNameCounter++;
        if (femaleNamePresent3) femaleNameCounter++;

        String gender;
        if (maleNameCounter > femaleNameCounter) {
            gender = "male";
        } else if (maleNameCounter < femaleNameCounter) {
            gender = "female";
        } else {
            gender = "inconclusive";
        }

        return gender;
    }


}
