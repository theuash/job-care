package com.example.backend;

import com.example.backend.model.Stats;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StatsController {

    private final DataStore store;

    StatsController(DataStore store) {
        this.store = store;
    }

    @GetMapping("/stats")
    public Stats getStats() {
        long activeJobs = store.jobs.stream().filter(j -> "Active".equals(j.status())).count();
        return new Stats(
            18,
            store.callVolumes.stream().mapToInt(cv -> cv.calls()).sum(),
            store.workers.size(),
            78
        );
    }
}
