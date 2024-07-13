package com.idev13.firstjobapp.review.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.idev13.firstjobapp.company.Company;
import com.idev13.firstjobapp.company.CompanyService;
import com.idev13.firstjobapp.review.Review;
import com.idev13.firstjobapp.review.ReviewRepository;
import com.idev13.firstjobapp.review.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {

        List<Review> reviews = reviewRepository.findByCompanyId(companyId);

        return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);

    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if (companyService.getCompanyById(companyId) != null) {
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);

            return true;
        } else {
            return false;
        }
    }

}
