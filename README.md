
# Introduction

[Jenkins shared libraries](https://jenkins.io/doc/book/pipeline/shared-libraries/) allow code reuse between pipeline projects.

Custom [pipeline steps](https://jenkins.io/doc/pipeline/steps/) can be created for use in pipelines.  Shell scripts and other supporting files can be included and used by custom steps or directly by pipelines.  

This shared library defines the following custom [steps](#steps):

* [`exampleArgs`](#exampleArgs)
* [`exampleHelloWorld`](#exampleHelloWorld)

## Usage

Use the [`library` step](https://jenkins.io/doc/book/pipeline/shared-libraries/#dynamic-retrieval) to include a shared library in your pipeline as this keeps the most information in the Jenkinsfile under source control rather than in Jenkins configuration.

A basic `Jenkinsfile` example:

```groovy
library identifier: 'jenkins-shared-library@v1.0', 
    retriever: modernSCM([
        $class: 'GitSCMSource',
        remote: 'https://github.com/mhicks-cloudbees/jenkins-shared-library.git'
        // remote: 'git@github.com:mhicks-cloudbees/jenkins-shared-library.git',
        // credentialsId: 'git-key'
    ])

pipeline {
    agent any
    stages {
        stage('Hello') {
            steps {
                exampleHelloWorld()
            }
        }
    }
}
```

> We recommend using a version specifier (for example, the @v1.0 in the `library` step above). This prevents your pipeline from picking up changes from new versions of the shared library that could cause errors. New versions of shared libraries can then be tested separately from standard builds. The available [releases]() are tagged.


## Steps

### `exampleArgs`
Use named parameters with defaults and a block section. See [`vars/exampleArgs.groovy`](vars/exampleArgs.groovy) and [Steps with arguments](Step-arguments.md).

```groovy
steps {
    exampleArgs(namedArg1: 'foo', namedArg2: 'bar') {
        echo 'block steps'
    }
}
```

Example build log output:

```
[Pipeline] echo
Arguments: [namedArg1:foo, namedArg2:bar, namedArg3:table]
[Pipeline] echo
Block:org.jenkinsci.plugins.workflow.cps.CpsClosure
```

**Arguments**

| Argument name  | Type       | Purpose                                    | Default   |
|----------------|------------|--------------------------------------------|-----------|
| namedArg1      | String     | An example named argument.                 | foos      |
| namedArg2      | String     | Another example named argument.            | ball      |
| namedArg3      | String     | Yet another example named argument.        | table     |

### `exampleHelloWorld`
A trivial step example. See [`vars/exampleHelloWorld.groovy`](vars/exampleHelloWorld.groovy).

```groovy
steps {
    exampleHelloWorld()
}
```

Example build log output:

```
[Pipeline] echo
Hello, World!
```

### `exampleResourceScript`
Run a script from the shared library resources as a build step.

```
steps {
    exampleResourceScript()
}
```

Example build log output:
```
[Pipeline] libraryResource
[Pipeline] sh
+ env
+ cut -d = -f 1
+ grep JENKINS
JENKINS_NODE_COOKIE
JENKINS_HOME
JENKINS_URL
JENKINS_SERVER_COOKIE
```

## Contributing

Pull requests are welcome! When adding steps, please add appropriate entries to this document in the [Steps](#steps) section and links in the [Introduction](#introduction) list.  For major changes, please open an issue to discuss what you'd like to change.