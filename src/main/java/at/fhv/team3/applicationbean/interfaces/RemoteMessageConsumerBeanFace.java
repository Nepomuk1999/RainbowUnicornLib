package at.fhv.team3.applicationbean.interfaces;

import at.fhv.team3.domain.dto.MessageDTO;

/**
 * Created by ClemensB on 03.12.17.
 */
public interface RemoteMessageConsumerBeanFace {

    public int getMessageCount();

    public MessageDTO pull();
}
