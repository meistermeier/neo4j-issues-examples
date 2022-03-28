package com.example.so71594275;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class MessageService {

	private final MessageRepository messageRepository;
	private final UserRepository userRepository;

	@Autowired
	public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
		this.messageRepository = messageRepository;
		this.userRepository = userRepository;
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
}
