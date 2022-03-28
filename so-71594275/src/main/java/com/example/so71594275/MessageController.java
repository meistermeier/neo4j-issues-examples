package com.example.so71594275;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

	private final MessageService messageService;

	@Autowired
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public Message saveMessage(@RequestBody Message message) {
		return messageService.saveNewMessage(message);
	}


	@PostMapping(path ="/alternative", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Message saveMessageAlternative(@RequestBody Message message) {
		return messageService.saveNewMessageAlternative(message);
	}

	@PostMapping(path = "/clearusers", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Message saveMessageDuplicateUsers(@RequestBody Message message) {
		return messageService.saveNewMessageEmptyUsersProperties(message);
	}

}
