package com.idev13.firstjobapp.job.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.idev13.firstjobapp.job.Job;
import com.idev13.firstjobapp.job.JobRepository;
import com.idev13.firstjobapp.job.JobService;

@Service
public class JobServiceImpl implements JobService {
    // private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

    private static long nextID = 1L;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextID++);
        jobRepository.save(job);

    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinsalary(updatedJob.getMinSalary());
            job.setMaxsalary(updatedJob.getMaxsalary());
            job.setLocation(updatedJob.getLocation());

            return true;
        }
        return false;

    }

}
