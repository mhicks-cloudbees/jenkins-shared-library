
# Introduction

[Jenkins shared libraries](https://jenkins.io/doc/book/pipeline/shared-libraries/) allow code reuse between pipeline projects.

Custom [pipeline steps](https://jenkins.io/doc/pipeline/steps/) can be created for use in pipelines.  Shell scripts and other supporting files can be included and used by custom steps or directly by pipelines.  

This shared library defines the following custom [steps](#steps):

* [`fullArgsExample`](#fullArgsExample)
* [`helloWorld`](#helloWorld)

## Usage

Use the [`library` step](https://jenkins.io/doc/book/pipeline/shared-libraries/#dynamic-retrieval) to include a shared library in your pipeline as this keeps the most information in the Jenkinsfile under source control rather than in Jenkins configuration.

A basic `Jenkinsfile` example:

```
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
            helloWorld
        }
    }
}
```

> We recommend using a version specifier (for example, the @v1.0 in the `library` step above). This prevents your pipeline from picking up changes from new versions of the shared library that could cause errors. New versions of shared libraries can then be tested separately from standard builds. The available [releases]() are tagged.


## Steps

### `fullArgsExample`
A custom step showing how to use named parameters and a block section.

```
steps {
    fullArgsExample(namedParam: 'foo', yaNamedParam: 'bar') {
        block body text
    }
}
```


### `helloWorld`
A basic step example.

```
steps {
    helloWorld
}
```