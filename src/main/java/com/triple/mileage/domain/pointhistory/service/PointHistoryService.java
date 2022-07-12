package com.triple.mileage.domain.pointhistory.service;

import com.triple.mileage.domain.pointhistory.dto.PointHistoryDto;
import com.triple.mileage.domain.pointhistory.repository.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointHistoryService {
    private final PointHistoryRepository pointHistoryRepository;

    public void addPointHistory(PointHistoryDto pointHistoryDto) {
        pointHistoryRepository.save(pointHistoryDto.toEntity());
    }
}
