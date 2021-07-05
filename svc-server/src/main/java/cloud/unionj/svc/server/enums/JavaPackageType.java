package cloud.unionj.svc.server.enums;

import java.util.*;

public enum JavaPackageType {
  /**
   * 简单jar包，不含内置代码
   */
  SIMPLE_JAR(10, "simple-jar", null, ".jar"),
  /**
   * 简单源码Jar包，不含内置代码
   */
  SIMPLE_SOURCE_JAR(20, "simple-source-jar", null, "-sources.jar"),
  /**
   * 含依赖的简单jar包，不含内置代码
   */
  SIMPLE_FAT_JAR(30, "simple-fat-jar", null, "-jar-with-dependencies.jar"),

  /**
   * 内置代码的Jar包
   */
//  CORE_JAR(40, "core-jar", "-core", ".jar"),
  /**
   * 含依赖的内置代码Jar包
   */
//  CORE_FAT_JAR(50, "core-fat-jar", "-core", "-jar-with-dependencies.jar"),
  /**
   * 完整jar包，含内置代码
   */
  FULL_JAR(60, "full-jar", "-full", ".jar"),
  /**
   * 完整源码Jar包，含内置代码
   */
  FULL_SOURCE_JAR(70, "full-source-jar", "-full", "-sources.jar"),
  /**
   * 含依赖的完整jar包，含内置代码
   */
  FULL_FAT_JAR(80, "full-fat-jar", "-full", "-jar-with-dependencies.jar"),
  /**
   * 原生代码压缩文件
   */
  ORIGIN_ZIP(999, "origin-zip", null, "-origin.zip");

  private final int value;
  private final String name;
  private final String infix;
  private final String suffix;

  JavaPackageType(final int value, final String name, final String infix, final String suffix) {
    this.value = value;
    this.name = name;
    this.infix = infix;
    this.suffix = suffix;
  }

  public int getValue() {
    return this.value;
  }

  public String getName() {
    return name;
  }

  public String getInfix() {
    return infix;
  }

  public String getSuffix() {
    return this.suffix;
  }

  public static JavaPackageType findType(int value) {
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

  public static List<JavaPackageType> findTypes(int... values) {
    return findTypes(values, true);
  }

  public static List<JavaPackageType> findTypes(int[] values, boolean inOrder) {
    if (values == null) {
      return null;
    }
    List<Integer> typeList = new ArrayList<>();
    Arrays.stream(values).forEach(type -> typeList.add(type));
    return findTypes(typeList, inOrder);
  }

  public static List<JavaPackageType> findTypes(Collection<Integer> values) {
    return findTypes(values, true);
  }

  public static List<JavaPackageType> findTypes(Collection<Integer> values, boolean inOrder) {
    if (values == null) {
      return null;
    }
    List<JavaPackageType> list = new ArrayList<>();
    values.forEach(type -> list.add(findType(type)));
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
}
