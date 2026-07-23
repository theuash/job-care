package com.example.backend;

import com.example.backend.model.CallVolume;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AnalyticsController {

    private final DataStore store;

    AnalyticsController(DataStore store) {
        this.store = store;
    }

    @GetMapping("/analytics/calls")
    public List<CallVolume> getCallVolumes() {
        return store.callVolumes;
    }
}
