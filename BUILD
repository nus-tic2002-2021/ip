load("@rules_java//java:defs.bzl", "java_binary")
load("@rules_java//java:defs.bzl", "java_test")

java_binary(
    name = "Duke",
    srcs = glob([
        "src/main/java/com/lockarhythm/**/*.java",
    ]),
    deps = ["@maven//:org_apache_commons_commons_lang3"]
)

java_test(
    name = "TestDuke",
    size = "small",
    srcs = glob([
        "src/test/java/com/lockarhythm/**/*.java",
        "src/main/java/com/lockarhythm/**/*.java",
    ]),
)

java_test(
    name = "TestEchoResponder",
    size = "small",
    srcs = glob([
        "src/test/java/com/lockarhythm/**/*.java",
        "src/main/java/com/lockarhythm/**/*.java",
    ]),
)

java_test(
    name = "TestExitResponder",
    size = "small",
    srcs = glob([
        "src/test/java/com/lockarhythm/**/*.java",
        "src/main/java/com/lockarhythm/**/*.java",
    ]),
)

java_test(
    name = "TestAddListResponder",
    size = "small",
    srcs = glob([
        "src/test/java/com/lockarhythm/**/*.java",
        "src/main/java/com/lockarhythm/**/*.java",
    ]),
)

java_test(
    name = "TestTask",
    size = "small",
    srcs = glob([
        "src/test/java/com/lockarhythm/**/*.java",
        "src/main/java/com/lockarhythm/**/*.java",
    ]),
)

java_test(
    name = "TestTaskList",
    size = "small",
    srcs = glob([
        "src/test/java/com/lockarhythm/**/*.java",
        "src/main/java/com/lockarhythm/**/*.java",
    ]),
)
