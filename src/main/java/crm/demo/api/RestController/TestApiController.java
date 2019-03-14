package crm.demo.api.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestApiController {

	// method for "hello" endpoint
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello API";
	}
}
