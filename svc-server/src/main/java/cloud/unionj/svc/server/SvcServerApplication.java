package cloud.unionj.svc.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class SvcServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SvcServerApplication.class, args);
  }

}
