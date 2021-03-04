package com.silenteight.springgenderchecker;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class NameController {

    private final NameService nameService;

    @PostMapping("/first-variant-check")
    public String checkName(@RequestBody String name) {
        return nameService.extractAndCompareName(name);
    }

    @PostMapping("/second-variant-check")
    public int checkMajorityInName(@RequestBody String name) {
        return nameService.compareMajorityInName(name);
    }

}
