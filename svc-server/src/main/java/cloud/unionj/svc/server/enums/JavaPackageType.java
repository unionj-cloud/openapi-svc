package cloud.unionj.svc.server.enums;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;

import java.io.File;
import java.util.*;

@Getter
public enum JavaPackageType {
  /**
   * 简单jar包，不含内置代码
   */
  SIMPLE_JAR(
      10,
      "simple-jar",
      null,
      ".jar",
      "简单jar包，不含内置代码",
      "clean package -DskipTests",
      new String[]{
          "src",
          "pom.xml"
      },
      new String[]{
          "src.main.java.cloud.unionj.svc.client.java".replace(".", File.separator),
          "src.main.resources.META-INF".replace(".", File.separator)
      }
  ),
  /**
   * 简单源码Jar包，不含内置代码
   */
  SIMPLE_SOURCE_JAR(
      20,
      "simple-source-jar",
      null,
      "-sources.jar",
      "简单源码Jar包，不含内置代码",
      "source:jar",
      new String[]{
          "src",
          "pom.xml"
      },
      new String[]{
          "src.main.java.cloud.unionj.svc.client.java".replace(".", File.separator),
          "src.main.resources.META-INF".replace(".", File.separator)
      }
  ),
  /**
   * 含依赖的简单jar包，不含内置代码
   */
  SIMPLE_FAT_JAR(
      30,
      "simple-fat-jar",
      null,
      "-jar-with-dependencies.jar",
      "含依赖的简单jar包，不含内置代码",
      "clean package -DskipTests",
      new String[]{
          "src",
          "pom.xml"
      },
      new String[]{
          "src.main.java.cloud.unionj.svc.client.java".replace(".", File.separator),
          "src.main.resources.META-INF".replace(".", File.separator)
      }
  ),

  /**
   * 内置代码的Jar包
   */
  CORE_JAR(
      40,
      "core-jar",
      "-core",
      ".jar",
      "内置代码的Jar包",
      "clean package -DskipTests",
      new String[]{
          "src.main.java.cloud.unionj.svc.client.java".replace(".", File.separator),
          "src.main.resources.META-INF".replace(".", File.separator),
          "pom.xml"
      },
      true
  ),
  /**
   * 内置代码的源码Jar包
   */
  CORE_SOURCE_JAR(
      40,
      "core-source-jar",
      "-core",
      "-sources.jar",
      "内置代码的源码Jar包",
      "source:jar",
      new String[]{
          "src.main.java.cloud.unionj.svc.client.java".replace(".", File.separator),
          "src.main.resources.META-INF".replace(".", File.separator),
          "pom.xml"
      },
      true
  ),
  /**
   * 含依赖的内置代码Jar包
   */
  CORE_FAT_JAR(
      50,
      "core-fat-jar",
      "-core",
      "-jar-with-dependencies.jar",
      "含依赖的内置代码Jar包",
      "clean package -DskipTests",
      new String[]{
          "src.main.java.cloud.unionj.svc.client.java".replace(".", File.separator),
          "src.main.resources.META-INF".replace(".", File.separator),
          "pom.xml"
      },
      true),
  /**
   * 完整jar包，含内置代码
   */
  FULL_JAR(
      60,
      "full-jar",
      "-full",
      ".jar",
      "完整jar包，含内置代码",
      "clean package -DskipTests",
      new String[]{
          "src",
          "pom.xml"
      },
      true
  ),
  /**
   * 完整源码Jar包，含内置代码
   */
  FULL_SOURCE_JAR(
      70,
      "full-source-jar",
      "-full",
      "-sources.jar",
      "完整源码Jar包，含内置代码",
      "source:jar",
      new String[]{
          "src",
          "pom.xml"
      },
      true
  ),
  /**
   * 含依赖的完整jar包，含内置代码
   */
  FULL_FAT_JAR(
      80,
      "full-fat-jar",
      "-full",
      "-jar-with-dependencies.jar",
      "含依赖的完整jar包，含内置代码",
      "clean package -DskipTests",
      new String[]{
          "src",
          "pom.xml"
      },
      true
  ),
  /**
   * 原生代码压缩文件
   */
  ORIGIN_ZIP(
      999,
      "origin-zip",
      null,
      "-origin.zip",
      "原生代码压缩文件",
      new String[]{
          "src",
          "docs",
          "pom.xml"
      },
      true
  );

  private int value;
  private String name;
  private String infix;
  private String suffix;
  private String desc;
  private String mvnGoal;
  private String[] includePrefixes;
  private String[] excludePrefixes;

  JavaPackageType(int value, String name, String infix, String suffix, String desc) {
    init(value, name, infix, suffix, desc, null, null, null);
  }

  JavaPackageType(int value, String name, String infix, String suffix, String desc, String[] prefixes, boolean isIncludePrefixes) {
    init(value, name, infix, suffix, desc, null, isIncludePrefixes ? prefixes : null, !isIncludePrefixes ? prefixes : null);
  }

  JavaPackageType(int value, String name, String infix, String suffix, String desc, String[] includePrefixes, String[] excludePrefixes) {
    init(value, name, infix, suffix, desc, null, includePrefixes, excludePrefixes);
  }

  JavaPackageType(int value, String name, String infix, String suffix, String desc, String mvnGoal) {
    init(value, name, infix, suffix, desc, mvnGoal, null, null);
  }

  JavaPackageType(int value, String name, String infix, String suffix, String desc, String mvnGoal, String[] prefixes, boolean isIncludePrefixes) {
    init(value, name, infix, suffix, desc, mvnGoal, isIncludePrefixes ? prefixes : null, !isIncludePrefixes ? prefixes : null);
  }

  JavaPackageType(int value, String name, String infix, String suffix, String desc, String mvnGoal, String[] includePrefixes, String[] excludePrefixes) {
    init(value, name, infix, suffix, desc, mvnGoal, includePrefixes, excludePrefixes);
  }

  private void init(int value, String name, String infix, String suffix, String desc, String mvnGoal, String[] includePrefixes, String[] excludePrefixes) {
    this.value = value;
    this.name = name;
    this.infix = infix;
    this.suffix = suffix;
    this.desc = desc;
    this.mvnGoal = mvnGoal;
    this.includePrefixes = includePrefixes;
    this.excludePrefixes = excludePrefixes;
  }

  public static JavaPackageType findByType(int value) {
    JavaPackageType[] values = values();
    int length = values.length;
    for (int i = 0; i < length; ++i) {
      JavaPackageType javaPackageType = values[i];
      if (javaPackageType.value == value) {
        return javaPackageType;
      }
    }
    return ORIGIN_ZIP;
  }

  public static JavaPackageType findByName(String name) {
    JavaPackageType[] values = values();
    int length = values.length;
    for (int i = 0; i < length; ++i) {
      JavaPackageType javaPackageType = values[i];
      if (javaPackageType.name.equals(name)) {
        return javaPackageType;
      }
    }
    return ORIGIN_ZIP;
  }

  public static List<JavaPackageType> find(String... names) {
    return JavaPackageType.find(names, true);
  }

  private static List<JavaPackageType> find(String[] names, boolean inOrder) {
    if (names == null) {
      return null;
    }
    List<String> nameList = new ArrayList<>();
    Arrays.stream(names).forEach(name -> nameList.add(name));
    return find(nameList, inOrder);
  }

  private static List<JavaPackageType> find(List<String> names, boolean inOrder) {
    if (names == null) {
      return null;
    }
    List<JavaPackageType> list = new ArrayList<>();
    names.forEach(name -> list.add(findByName(name)));
    if (inOrder) {
      return order(list);
    }
    return list;
  }

  public static List<JavaPackageType> find(int... values) {
    return JavaPackageType.find(values, true);
  }

  public static List<JavaPackageType> find(int[] values, boolean inOrder) {
    if (values == null) {
      return null;
    }
    List<Integer> typeList = new ArrayList<>();
    Arrays.stream(values).forEach(type -> typeList.add(type));
    return find(typeList, inOrder);
  }

  public static List<JavaPackageType> find(Collection<Integer> values) {
    return find(values, true);
  }

  public static List<JavaPackageType> find(Collection<Integer> values, boolean inOrder) {
    if (values == null) {
      return null;
    }
    List<JavaPackageType> list = new ArrayList<>();
    values.forEach(type -> list.add(findByType(type)));
    if (inOrder) {
      return order(list);
    }
    return list;
  }

  public static List<JavaPackageType> order(Collection<JavaPackageType> types) {
    List<JavaPackageType> list = new ArrayList<>();
    list.addAll(types);
    //按type降序
    Collections.sort(list, (t1, t2) -> Integer.compare(t2.value, t1.value));
    return list;
  }

  public static Map<String, String> getMap() {
    Map<String, String> enumMap = new LinkedHashMap<>();
    for (JavaPackageType enumConstant : JavaPackageType.class.getEnumConstants()) {
      enumMap.put(enumConstant.getName(), enumConstant.getDesc());
    }
    return enumMap;
  }

  public boolean isMavenInvoker() {
    return StrUtil.isNotEmpty(mvnGoal);
  }
}
