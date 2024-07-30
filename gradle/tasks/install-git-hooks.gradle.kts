tasks.register("installGitHooks", Copy::class) {
    println("Installing git hooks")
    from(rootProject.file(".github/hooks")) {
        include("pre-commit")
        include("commit-msg")
        include("prepare-commit-msg")
    }
    into { rootProject.file(".git/hooks") }
    filePermissions {
        group {
            read = true
            write = true
            execute = true
        }
        user {
            read = true
            write = true
            execute = true
        }
        other {
            read = true
            write = false
            execute = true
        }
    }
}

tasks.withType<JavaCompile> {
    dependsOn("installGitHooks")
}
