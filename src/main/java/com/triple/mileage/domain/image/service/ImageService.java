package com.triple.mileage.domain.image.service;

import com.triple.mileage.domain.image.domain.Image;
import com.triple.mileage.domain.review.domain.Review;
import com.triple.mileage.domain.image.repository.ImageRepository;
import com.triple.mileage.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {

    @Value("${reviewImageLocation}")
    private String reviewImageLocation;
    private final ImageRepository imageRepository;
    private final FileService fileService;

    // 이미지 저장
    public void addImages(Image reviewImage, MultipartFile reviewImageFile) throws Exception {
        String originalImageName = reviewImageFile.getOriginalFilename();
        String imageName = "";
        String imageUrl = "";

        if (StringUtils.hasLength(originalImageName)) {
            imageName = fileService.uploadFile(reviewImageLocation, originalImageName, reviewImageFile.getBytes());
            imageUrl = "/images/review/" + imageName;
        }

        reviewImage.updateImage(imageName, originalImageName, imageUrl);
        imageRepository.save(reviewImage);

    }


    //기존 파일 삭제
    public void deleteImages(Review review) throws Exception {
        //해당 리뷰에 저장된 이미지 가져오기
        List<Image> savedImages = imageRepository.findByReview(review);

        for (Image savedImage : savedImages) {

            //기존 이미지 파일 삭제
            if (StringUtils.hasLength(savedImage.getImageName())) {
                fileService.deleteFile(reviewImageLocation + "/" + savedImage.getImageName());
            }
            imageRepository.delete(savedImage);
        }
    }
}
