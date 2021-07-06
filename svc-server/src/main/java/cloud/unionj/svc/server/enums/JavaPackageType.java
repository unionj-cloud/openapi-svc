package cloud.unionj.svc.server.enums;

import lombok.Getter;

import java.util.*;

@Getter
public enum JavaPackageType {
  /**
   * 简单jar包，不含内置代码
   */
  SIMPLE_JAR(10, "simple-jar", null, ".jar", "简单jar包，不含内置代码"),
  /**
   * 简单源码Jar包，不含内置代码
   */
  SIMPLE_SOURCE_JAR(20, "simple-source-jar", null, "-sources.jar", "简单源码Jar包，不含内置代码"),
  /**
   * 含依赖的简单jar包，不含内置代码
   */
  SIMPLE_FAT_JAR(30, "simple-fat-jar", null, "-jar-with-dependencies.jar", "含依赖的简单jar包，不含内置代码"),

  /**
   * 内置代码的Jar包
   */
//  CORE_JAR(40, "core-jar", "-core", ".jar", "内置代码的Jar包"),
  /**
   * 含依赖的内置代码Jar包
   */
//  CORE_FAT_JAR(50, "core-fat-jar", "-core", "-jar-with-dependencies.jar", "含依赖的内置代码Jar包"),
  /**
   * 完整jar包，含内置代码
   */
  FULL_JAR(60, "full-jar", "-full", ".jar", "完整jar包，含内置代码"),
  /**
   * 完整源码Jar包，含内置代码
   */
  FULL_SOURCE_JAR(70, "full-source-jar", "-full", "-sources.jar", "完整源码Jar包，含内置代码"),
  /**
   * 含依赖的完整jar包，含内置代码
   */
  FULL_FAT_JAR(80, "full-fat-jar", "-full", "-jar-with-dependencies.jar", "含依赖的完整jar包，含内置代码"),
  /**
   * 原生代码压缩文件
   */
  ORIGIN_ZIP(999, "origin-zip", null, "-origin.zip", "原生代码压缩文件");

  private final int value;
  private final String name;
  private final String infix;
  private final String suffix;
  private final String desc;

  JavaPackageType(final int value, final String name, final String infix, final String suffix, final String desc) {
    this.value = value;
    this.name = name;
    this.infix = infix;
    this.suffix = suffix;
    this.desc = desc;
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
      if (javaPackageType.name == name) {
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
}
