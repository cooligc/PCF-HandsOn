/**
 * 
 */
package com.skc.ms.amqp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sitakant
 *
 */
@RestController
public class MessageDisplayController {
	
	private static final Logger LOG = Logger.getLogger(MessageDisplayController.class);

	@Autowired
	MessageRepository messageRepository;
	
	@GetMapping("/messages")
	public List<MessageEntity> listAllMessages(){
		LOG.debug("-- DISPLAYED all messages ---");
		return messageRepository.findAll();
	}
	
	@GetMapping("/unprocessed")
	public List<MessageEntity> listUnprocessed(){
		List<MessageEntity> messages = messageRepository.findAllByStatus("RECIVED");
		List<MessageEntity> responseList = new ArrayList<MessageEntity>();
		messages.forEach(message -> {
			responseList.add(message);
			message.setStatus("PROCESSED");
			messageRepository.save(message);
		});
		LOG.debug("-- DISPLAYED all the processed messages ---");
		return responseList;
	}
	
	@GetMapping("/envs")
	public Map<String,String> getServiceDetails(){
		return System.getenv();
	}
}
