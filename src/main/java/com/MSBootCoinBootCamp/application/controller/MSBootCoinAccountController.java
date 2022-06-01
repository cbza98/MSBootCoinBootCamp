package com.MSBootCoinBootCamp.application.controller;
import com.MSBootCoinBootCamp.domain.beans.AvailableAmountDTO;
import com.MSBootCoinBootCamp.domain.beans.CreateMSBootCoinAccountDTO;
import com.MSBootCoinBootCamp.domain.model.MSBootCoinAccount;
import com.MSBootCoinBootCamp.infraestructure.services.MSBootCoinAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/BootCoin/Entities/BootCoin")
public class MSBootCoinAccountController {
	@Autowired
	private MSBootCoinAccountService service;
	@GetMapping
	public Mono<ResponseEntity<Flux<MSBootCoinAccount>>> findAll() {
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(service.findAll()));
	}
	@GetMapping("/{id}")
	public Mono<MSBootCoinAccount> findById(@PathVariable String id) {
		return service.findById(id);
	}
	@PostMapping
	public Mono<ResponseEntity<Map<String, Object>>> createAccount(@Valid @RequestBody Mono<CreateMSBootCoinAccountDTO> request) {

		Map<String, Object> response = new HashMap<>();

		return request.flatMap(a -> service.createMSBootCoinAccount(a).map(c -> {
			response.put("Account", c);
			response.put("Message", "Account created Successfully");
			return ResponseEntity.created(URI.create("/Accounts/Entities/Account/".concat(c.getCellphoneNumber())))
					.contentType(MediaType.APPLICATION_JSON).body(response);
		}));
	}


	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Map<String, Object> >> delete(@PathVariable String id) {
		Map<String, Object> response = new HashMap<>();

		return service.delete(id)
				.map(c -> {
					response.put("Account", c);
					response.put("Message", "Accounts deleted successfully");
					return ResponseEntity.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.location( URI.create("/Accounts/Entities/Account".concat(c.getCellphoneNumber())))
							.body(response);
				});
	}
	@GetMapping("/GetAvailableAmount/{id}")
	public Mono<AvailableAmountDTO> getAvailableAmount(@PathVariable String id) {
		return service.getAvailableAmount(id);
	}
}
