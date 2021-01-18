package org.example.service;

import org.example.model.TraderSentiments;
import org.springframework.stereotype.Service;

@Service
public class TraderSentimentsServiceImpl implements TraderSentimentsService{
    @Override
    public TraderSentiments getTraderSentiments(String id) {
        return new TraderSentiments(47, 53);
    }
}
