/**
 * 
 */
package com.skc.ms.amqp;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sitakant
 *
 */

@RestController
public class MessageCotroller {
	
	private static final Logger LOG = Logger.getLogger(MessageCotroller.class);
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	MessageRepository repository;
	

	@PostMapping("/messages")
	public ResponseEntity<Map<String, String>> publish(@RequestBody Map<String,String> publishMessage){
		
		LOG.info("-- Got the messages --");
		
		LOG.debug("Message recieved -->"+publishMessage);
		
		repository.save(new MessageEntity(publishMessage.toString()));
		
		LOG.debug(" --- Save Store the request --- ");
		
		rabbitTemplate.convertAndSend("my_default_queue",publishMessage.toString());
		LOG.debug(" -- Sent the message to queue --");
		Map<String, String> responseMap = new HashMap<String, String>();
		responseMap.put("status", "created");
		responseMap.put("message", "The required message has been pushed to Queue");
		return new ResponseEntity<Map<String,String>>(responseMap, HttpStatus.OK);
	}
	
	@GetMapping("/services")
	public String getServiceDetails(){
		return System.getenv().get("VCAP_APPLICATION");
	}
	
	@GetMapping("/applications")
	public String getApplicationDetails(){
		return System.getenv().get("VCAP_SERVICES");
	}
	
}
