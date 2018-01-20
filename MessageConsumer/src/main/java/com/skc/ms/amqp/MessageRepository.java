/**
 * 
 */
package com.skc.ms.amqp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sitakant
 *
 */
public interface MessageRepository extends JpaRepository<com.skc.ms.amqp.MessageEntity, Long> {
	List<MessageEntity> findAllByStatus(String status);
}
