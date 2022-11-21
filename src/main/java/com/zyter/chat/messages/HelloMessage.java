package com.zyter.chat.messages;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class HelloMessage {
	@NonNull
	String name;
}
