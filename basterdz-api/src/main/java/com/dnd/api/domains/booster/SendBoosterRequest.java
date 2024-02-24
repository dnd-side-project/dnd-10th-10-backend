package com.dnd.api.domains.booster;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendBoosterRequest {

	private Long targetId;

	private String content;
}
