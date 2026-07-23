package com.example.backend;

import com.example.backend.model.Worker;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WorkerController {

    private final DataStore store;

    WorkerController(DataStore store) {
        this.store = store;
    }

    @GetMapping("/workers")
    public List<Worker> getWorkers() {
        return store.workers;
    }

    @GetMapping("/workers/{id}")
    public Worker getWorker(@PathVariable String id) {
        return store.workers.stream()
            .filter(w -> w.id().equals(id))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Worker not found: " + id));
    }
}
