/**
 * 
 */
package com.skc.ms.amqp;

import org.apache.log4j.Logger;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * @author sitakant
 *
 */
//@Configuration
//@RabbitListener(queues="my_default_queue")
//@Component
public class MyListener {
	private static final Logger LOG = Logger.getLogger(MyListener.class);
	
	//@Autowired
	MessageRepository repository;
	
	//@RabbitHandler
	public void onRecieve(@Payload String messages){
		LOG.debug("--------");
		LOG.debug("Recieved the message at consumer "+messages);
		
		MessageEntity entity = new MessageEntity(messages.toString());
		entity.setStatus("RECIVED");
		repository.save(entity);
		LOG.debug("Message saved safely");
	}
}
