package com.silenteight.springgenderchecker;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class NameController {

    private final NameService nameService;

    @PostMapping("/first-variant")
    public String checkName(@RequestBody String name) {
        return nameService.extractAndCompareName(name);
    }

    @PostMapping("/second-variant")
    public String checkMajorityInName(@RequestBody String name) {
        return nameService.compareMajorityInName(name);
    }

    @GetMapping("/male-tokens")
    public List<String> getAllMaleTokens() {
        return nameService.getAllMaleNames();
    }

    @GetMapping("/female-tokens")
    public List<String> getAllFemaleTokens() {
        return nameService.getAllFemaleNames();
    }

}
