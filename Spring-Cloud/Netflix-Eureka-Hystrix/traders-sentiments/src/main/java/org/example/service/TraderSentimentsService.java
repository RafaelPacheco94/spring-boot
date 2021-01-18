package org.example.service;

import org.example.model.TraderSentiments;
import org.springframework.stereotype.Service;

@Service
public interface TraderSentimentsService {
    public TraderSentiments getTraderSentiments(String id);
}
