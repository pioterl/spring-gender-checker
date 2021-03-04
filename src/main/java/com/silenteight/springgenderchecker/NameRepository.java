package com.silenteight.springgenderchecker;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class NameRepository {
    final static String MALE_NAMES = "src\\main\\resources\\male_names.txt";
    final static String FEMALE_NAMES = "src\\main\\resources\\female_names.txt";

    public String firstWordExtractor(String input) {
        return input.split(" ")[0];
    }

    public String printGenderByFirstName(boolean maleNamePresent, boolean femaleNamePresent) {
        if (maleNamePresent && femaleNamePresent) {
            return "name appears as male and female";
        } else if (maleNamePresent) {
            return "male";
        } else if (femaleNamePresent) {
            return "female";
        } else {
            return "inconclusive (no such name in both files)";
        }
    }

    public boolean isNamePresent(String file, String name) {
        String line;
        ArrayList<String> fileContent = new ArrayList<>();
        try {
            FileReader fReader = new FileReader(file);
            BufferedReader fileBuff = new BufferedReader(fReader);
            while ((line = fileBuff.readLine()) != null) {
                fileContent.add(line);
            }
            fileBuff.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return fileContent.contains(name);
    }
}
