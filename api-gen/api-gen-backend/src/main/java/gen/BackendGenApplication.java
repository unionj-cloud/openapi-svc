package gen;

import cloud.unionj.generator.backend.docparser.BackendDocParser;
import cloud.unionj.generator.backend.docparser.entity.Backend;
import cloud.unionj.generator.backend.springboot.OutputConfig;
import cloud.unionj.generator.backend.springboot.OutputType;
import cloud.unionj.generator.backend.springboot.SpringbootFolderGenerator;
import cloud.unionj.generator.openapi3.model.Openapi3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

  @Value("${designer.phone}")
  private String designerPhone;

  @Value("${designer.username}")
  private String designerUsername;

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
        .protoOutput(new OutputConfig("com.treeyee.cloud.community.proto",
            "community-proto/src/main/java/com/treeyee/cloud/community/proto"))
        .voOutput(new OutputConfig("com.treeyee.cloud.community.vo",
            "community-vo/src/main/java/com/treeyee/cloud/community/vo"))
        .pomProject(true)
        .pomParentGroupId("com.treeyee")
        .pomParentArtifactId("community")
        .pomParentVersion("1.0.0-SNAPSHOT")
        .pomProtoArtifactId("community-proto")
        .pomVoArtifactId("community-vo")
        .outputType(OutputType.OVERWRITE)
        .build();
    springbootFolderGenerator.generate();
  }
}
