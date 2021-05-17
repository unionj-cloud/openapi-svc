package gen;

import cloud.unionj.generator.backend.docparser.BackendDocParser;
import cloud.unionj.generator.backend.docparser.entity.Backend;
import cloud.unionj.generator.backend.springboot.OutputConfig;
import cloud.unionj.generator.backend.springboot.OutputType;
import cloud.unionj.generator.backend.springboot.SpringbootFolderGenerator;
import cloud.unionj.generator.openapi3.model.Openapi3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: gen
 * @date:2020/12/23
 */
@SpringBootApplication
public class BackendGenApplication implements CommandLineRunner {

  private static Logger LOG = LoggerFactory.getLogger(BackendGenApplication.class);

  @PostConstruct
  public void setDefaultTimeZone() {
    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
  }

  public static void main(String[] args) {
    LOG.info("STARTING THE APPLICATION");
    SpringApplication.run(BackendGenApplication.class, args);
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
    Backend backend = BackendDocParser.parse(openAPI);
    SpringbootFolderGenerator springbootFolderGenerator = new SpringbootFolderGenerator.Builder(backend)
        .protoOutput(new OutputConfig("cloud.unionj.svc.proto",
            "svc-proto/src/main/java/cloud/unionj/svc/proto"))
        .voOutput(new OutputConfig("cloud.unionj.svc.vo",
            "svc-vo/src/main/java/cloud/unionj/svc/vo"))
        .pomProject(true)
        .pomParentGroupId("cloud.unionj")
        .pomParentArtifactId("openapi-svc")
        .pomParentVersion("0.0.1-SNAPSHOT")
        .pomProtoArtifactId("svc-proto")
        .pomVoArtifactId("svc-vo")
        .outputType(OutputType.OVERWRITE)
        .build();
    springbootFolderGenerator.generate();
  }
}
