package gen;

import cloud.unionj.generator.frontend.vue.VueProjectGenerator;
import cloud.unionj.generator.openapi3.model.Openapi3;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.TimeZone;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: gen
 * @date:2020/12/23
 */
@SpringBootApplication
public class FrontendGenApplication implements CommandLineRunner {

  private static Logger LOG = LoggerFactory.getLogger(FrontendGenApplication.class);

  @PostConstruct
  public void setDefaultTimeZone() {
    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
  }

  public static void main(String[] args) {
    LOG.info("STARTING THE APPLICATION");
    SpringApplication.run(FrontendGenApplication.class, args);
    LOG.info("APPLICATION FINISHED");
    System.exit(0);
  }

  /**
   * Callback used to run the bean.
   *
   * @param args incoming main method arguments
   * @throws Exception on error
   */
  @Override
  public void run(String... args) throws Exception {
    Openapi3 openAPI = Openapi3Designer.design();
    VueProjectGenerator vueProjectGenerator = new VueProjectGenerator.Builder("openapi-svc").openAPI(openAPI).build();
    String outputFile = vueProjectGenerator.generate();
    File file = new File(outputFile);
    Assert.assertTrue(file.exists());
  }
}
