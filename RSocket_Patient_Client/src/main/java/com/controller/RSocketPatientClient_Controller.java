package com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Claim;
import com.entity.ClinicalData;
import com.entity.Patient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class RSocketPatientClient_Controller {

	private final RSocketRequester rSocketRequester;

	Logger logger = LoggerFactory.getLogger(RSocketPatientClient_Controller.class);

	public RSocketPatientClient_Controller(@Autowired RSocketRequester.Builder builder) {
		this.rSocketRequester = builder.tcp("localhost", 7000);
	}

	// localhost:8484/request-response

	@GetMapping("/request-response")
	public Mono<ClinicalData> requestResponse(Patient patient) {
		logger.info("Sending the rsocket request for patient: " + patient);
		return rSocketRequester.route("get-patient-data").data(patient).retrieveMono(ClinicalData.class);
	}

	// localhost:8484/fire-and-forget

	@PostMapping("/fire-and-forget")
	public Mono<Void> fireAndForget(@RequestBody Patient patient) {
		logger.info("Patient Being Checked Out: " + patient);
		return rSocketRequester.route("patient-checkout").data(patient).retrieveMono(Void.class);
	}

	// localhost:8484/request-stream

	@GetMapping("/request-stream")
	public ResponseEntity<Flux<Claim>> requestStream() {
		Flux<Claim> data = rSocketRequester.route("claim-stream").retrieveFlux(Claim.class);
		return ResponseEntity.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(data);
	}

}
