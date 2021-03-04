package com.silenteight.springgenderchecker;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class NameController {

    private final NameService nameService;

    @PostMapping("/first-variant-check")
    public String checkName(@RequestBody String name) {
        return nameService.extractAndCompareName(name);
    }

    @PostMapping("/second-variant-check")
    public List<String> checkMajorityInName(@RequestBody String name) {
        return nameService.compareMajorityInName(name);
    }

}
