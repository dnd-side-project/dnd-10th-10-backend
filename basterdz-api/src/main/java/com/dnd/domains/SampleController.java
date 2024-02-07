package com.dnd.domains;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnd.auth.LoginMember;
import com.dnd.member.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/sample")
public class SampleController {

	@PostMapping
	public void sampleRequest(@LoginMember Member member) {
		log.info("memberId is: {} ", member.getId());
	}
}
