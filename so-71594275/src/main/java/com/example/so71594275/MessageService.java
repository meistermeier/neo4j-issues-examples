package com.example.so71594275;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class MessageService {

	private final MessageRepository messageRepository;
	private final UserRepository userRepository;
	private final Neo4jTemplate neo4jTemplate;

	@Autowired
	public MessageService(MessageRepository messageRepository, UserRepository userRepository, Neo4jTemplate neo4jTemplate) {
		this.messageRepository = messageRepository;
		this.userRepository = userRepository;
		this.neo4jTemplate = neo4jTemplate;
	}

	@Transactional
	public Message saveNewMessage(Message newMessage) {
		UUID recipientId = newMessage.getRecipient().getUserId();
		UUID senderId = newMessage.getSent().getUserId();

		User recipient = userRepository.findById(recipientId).orElseGet(() -> {
			User user = new User();
			user.setName("new recipient");
			return user;
		});

		User sender = userRepository.findById(senderId).orElseGet(() -> {
			User user = new User();
			user.setName("new sender");
			return user;
		});

		newMessage.setRecipient(recipient);
		newMessage.setSent(sender);

		return messageRepository.save(newMessage);
	}

	@Transactional
	public Message saveNewMessageAlternative(Message newMessage) {
		UUID recipientId = newMessage.getRecipient().getUserId();
		UUID senderId = newMessage.getSent().getUserId();

		User recipient = neo4jTemplate.findById(recipientId, User.class).orElseGet(() -> {
			User user = new User();
			user.setName("new recipient");
			return user;
		});

		User sender = neo4jTemplate.findById(senderId, User.class).orElseGet(() -> {
			User user = new User();
			user.setName("new sender");
			return user;
		});

		newMessage.setRecipient(recipient);
		newMessage.setSent(sender);

		return messageRepository.save(newMessage);
	}

	@Transactional
	public Message saveNewMessageEmptyUsersProperties(Message newMessage) {
		return messageRepository.save(newMessage);
	}
}
