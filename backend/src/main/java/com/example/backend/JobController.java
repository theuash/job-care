package com.example.backend;

import com.example.backend.model.JobPosting;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class JobController {

    private final DataStore store;

    JobController(DataStore store) {
        this.store = store;
    }

    @GetMapping("/jobs")
    public List<JobPosting> getJobs() {
        return store.jobs;
    }

    @PostMapping("/jobs")
    public JobPosting createJob(@RequestBody JobPosting job) {
        var created = new JobPosting(
            UUID.randomUUID().toString().substring(0, 8),
            job.title(), job.company(), job.wage(),
            job.location(), job.requiredSkills(),
            "Active", java.time.LocalDate.now().toString()
        );
        store.jobs.add(created);
        return created;
    }
}
