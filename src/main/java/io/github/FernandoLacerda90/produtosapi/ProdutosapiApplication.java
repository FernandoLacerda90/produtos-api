package io.github.FernandoLacerda90.produtosapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

@SpringBootApplication

public class ProdutosapiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProdutosapiApplication.class, args);
	}

}
