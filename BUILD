load("@rules_java//java:defs.bzl", "java_binary")
load("@rules_java//java:defs.bzl", "java_test")

java_binary(
    name = "Duke",
    srcs = glob(["src/main/java/com/lockarhythm/cmdline/*.java"]),
)

java_test(
    name = "AllTests",
    size = "small",
    test_class = "com.lockarhythm.cmdline.TestDuke",
    srcs = glob([
        "src/main/java/com/lockarhythm/cmdline/*.java",
        "src/test/java/com/lockarhythm/cmdline/*.java",
    ]),
)
