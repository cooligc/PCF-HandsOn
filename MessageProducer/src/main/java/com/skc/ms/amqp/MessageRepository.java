/**
 * 
 */
package com.skc.ms.amqp;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sitakant
 *
 */
public interface MessageRepository extends JpaRepository<com.skc.ms.amqp.MessageEntity, Long> {

}
