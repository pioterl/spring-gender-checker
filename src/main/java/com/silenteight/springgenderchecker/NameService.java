package com.silenteight.springgenderchecker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.silenteight.springgenderchecker.NameRepository.FILE_FEMALE_NAMES;
import static com.silenteight.springgenderchecker.NameRepository.FILE_MALE_NAMES;

@Service
@RequiredArgsConstructor
public class NameService {

    private final NameRepository nameRepository;

    public String extractAndCompareName(String name) {
        String shortenedName = nameRepository.firstWordExtractor(name);
        boolean maleNamePresent = nameRepository.isNamePresentInFile(FILE_MALE_NAMES, shortenedName);
        boolean femaleNamePresent = nameRepository.isNamePresentInFile(FILE_FEMALE_NAMES, shortenedName);
        return nameRepository.printGenderByFirstName(maleNamePresent, femaleNamePresent);
    }

    public String compareMajorityInName(String name) {

        List<String> listOfWords = nameRepository.wordsToList(name);

        if (listOfWords.size() < 3) {
            return "You need to type minimum 3 words";
        }

        int maleNameCounter = 0;
        int femaleNameCounter = 0;

        for (String word : listOfWords) {
            if (nameRepository.isNamePresentInFile(FILE_MALE_NAMES, word))
                maleNameCounter++;
            else if (nameRepository.isNamePresentInFile(FILE_FEMALE_NAMES, word))
                femaleNameCounter++;
        }

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


    public List<String> getAllMaleNames() {
        return nameRepository.getAllNames(FILE_MALE_NAMES);
    }

    public List<String> getAllFemaleNames() {
        return nameRepository.getAllNames(FILE_FEMALE_NAMES);
    }
}
