package com.santander.forex.messaging;

import com.santander.forex.domain.SpotPrice;
import com.santander.forex.service.SpotPriceCalculatorEngine;
import com.santander.forex.service.SpotPriceCalculatorEngineImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.santander.forex.util.MessageFeedConverterUtil.parseCsvLine;

@Service(LatestSpotPriceReceiverImpl.BEAN_NAME)
public class LatestSpotPriceReceiverImpl implements LatestSpotPriceReceiver {

    private static final Logger LOG = LoggerFactory.getLogger(LatestSpotPriceReceiverImpl.class);

    public static final String BEAN_NAME = "com.santander.forex.messaging.LatestSpotPriceReceiverImpl";

    @Resource(name = SpotPriceCalculatorEngineImpl.BEAN_NAME)
    private SpotPriceCalculatorEngine spotPriceCalculatorEngine;

    @Override
    public void onMessage(String message) {
        try {
            LOG.info("Receiver new Message {}", message);
            SpotPrice spotPrice = parseCsvLine(message);
            spotPriceCalculatorEngine.changeSpotPrice(spotPrice);

            //In real world - we should also update the Database / file system etc async.
            LOG.info("Processes New Message with id {}", spotPrice.getId());
        }catch(Throwable th){
            //In this case I am only logging - but in real world - This would raise an alert with the Support team
            //We should not encounter a missed message as this is causing Message to be lost
            LOG.error("An Exception was thrown processing Message {}", message, th);
        }
    }
}
