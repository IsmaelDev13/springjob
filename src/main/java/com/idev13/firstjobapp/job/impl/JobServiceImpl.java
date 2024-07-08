package com.idev13.firstjobapp.job.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.idev13.firstjobapp.job.Job;
import com.idev13.firstjobapp.job.JobService;

@Service
public class JobServiceImpl implements JobService {
    private List<Job> jobs = new ArrayList<>();
    private static long nextID = 1L;

    @Override
    public List<Job> findAll() {
        return null;
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextID++);
        jobs.add(job);

    }

    @Override
    public Job getJobById(Long id) {
        for (Job job : jobs) {
            if (job.getId().equals(id)) {
                return job;
            }
        }
        return null;
    }

}
