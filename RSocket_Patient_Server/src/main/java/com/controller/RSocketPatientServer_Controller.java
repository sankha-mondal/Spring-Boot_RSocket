package com.controller;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.entity.Claim;
import com.entity.ClinicalData;
import com.entity.Patient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class RSocketPatientServer_Controller {
	Logger logger = LoggerFactory.getLogger(RSocketPatientServer_Controller.class);

	@MessageMapping("get-patient-data")
	public Mono<ClinicalData> requestResponse(@RequestBody Patient patient) {
		logger.info("Received Patient: " + patient);
		return Mono.just(new ClinicalData(90, "80/120"));

	}

	@MessageMapping("patient-checkout")
	public Mono<Void> fireAndForget(Patient patient) {
		logger.info("Patient Checking out: " + patient);
		logger.info("Billing Initiated");
		return Mono.empty().then();
	}

	@MessageMapping("claim-stream")
	public Flux<Claim> requestStream(){
		return Flux.just(new Claim(1000f, "MRI Scan"),
				new Claim(2000f, "Surgery"),
				new Claim(500f, "Food")).delayElements(Duration.ofSeconds(2));
	}

}
