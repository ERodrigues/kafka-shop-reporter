package com.study.kafka.shopreporter.events;

import com.study.kafka.shopreporter.dto.ShopDto;
import com.study.kafka.shopreporter.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiveKafkaMessage {
    private static final String SHOP_TOPIC_EVENT_NAME = "SHOP_TOPIC_EVENT";
    private final ReportRepository reportRepository;
    private final KafkaTemplate<String, ShopDto> kafkaTemplate;

    @Transactional
    @KafkaListener(topics = SHOP_TOPIC_EVENT_NAME, groupId = "group_report")
    public void listenerShopTopic(ShopDto shopDto){
        try{
            log.info("Compra recebida no topico: {}", shopDto.getIdentifier());
            reportRepository.incrementShopStatus(shopDto.getStatus());
        } catch (Exception e){
            log.error("Erro no processamento da compra {}", shopDto.getIdentifier());
        }
    }
}
