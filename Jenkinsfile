library identifier: 'jenkins-shared-library@v1.0.0', 
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
        stage('Args') {
            steps {
                exampleArgs(namedArg1: 'foo', namedArg2: 'bar') {
                    echo 'block steps'
                }
            }
        }
        stage('Resource Script') {
            steps {
                exampleResourceScript()
            }
        }
    }
}