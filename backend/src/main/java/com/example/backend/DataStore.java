package com.example.backend;

import com.example.backend.model.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataStore {

    final List<Worker> workers = new ArrayList<>();
    final List<JobPosting> jobs = new ArrayList<>();
    final List<CallVolume> callVolumes = new ArrayList<>();

    @PostConstruct
    void init() {
        workers.addAll(List.of(
            new Worker("w1", "Ramesh Kumar", "Painter (interior)", "Whitefield, Bengaluru", "₹600–₹700/day", "Tomorrow, 8 AM", true, 97),
            new Worker("w2", "Suresh Reddy", "Mason", "Yelahanka, Bengaluru", "₹700–₹800/day", "Today, 2 PM", true, 94),
            new Worker("w3", "Dinesh Rao", "Plumber", "MG Road, Bengaluru", "₹550–₹650/day", "Next 2 days", false, 88),
            new Worker("w4", "Mahesh Naik", "Electrician", "BTM Layout, Bengaluru", "₹600–₹750/day", "Tomorrow, 7 AM", true, 91),
            new Worker("w5", "Venkatesh G", "Carpenter", "Whitefield, Bengaluru", "₹650–₹800/day", "Weekend", true, 85),
            new Worker("w6", "Ravi Shankar", "Driver (light)", "Peenya, Bengaluru", "₹500–₹600/day", "Daily", true, 79),
            new Worker("w7", "Gopi Nath", "Security Guard", "Koramangala, Bengaluru", "₹450–₹550/day", "Immediate", true, 82),
            new Worker("w8", "Prakash J", "Painter (exterior)", "Electronic City, Bengaluru", "₹600–₹700/day", "Monday", false, 73)
        ));

        jobs.addAll(List.of(
            new JobPosting("j1", "Interior Painter", "Prestige Group", "₹650/day", "Whitefield", "Painting, Putty work", "Active", "2026-07-20"),
            new JobPosting("j2", "Mason", "L&T Construction", "₹750/day", "Yelahanka", "Brickwork, Plastering", "Active", "2026-07-19"),
            new JobPosting("j3", "Plumber", "Porter Vendor", "₹600/day", "MG Road", "Pipe fitting, Repair", "Active", "2026-07-18"),
            new JobPosting("j4", "Electrician", "Swiggy Vendor", "₹700/day", "BTM Layout", "Wiring, Maintenance", "Filled", "2026-07-15"),
            new JobPosting("j5", "Carpenter", "Prestige Group", "₹750/day", "Whitefield", "Furniture, Finishing", "Active", "2026-07-22")
        ));

        callVolumes.addAll(List.of(
            new CallVolume(1, 55), new CallVolume(2, 120), new CallVolume(3, 210),
            new CallVolume(4, 310), new CallVolume(5, 480), new CallVolume(6, 650),
            new CallVolume(7, 820), new CallVolume(8, 1050)
        ));
    }
}
