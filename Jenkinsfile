pipeline {
    agent any
    stages {
        stage('build') {
            steps {
               git  sh 'mvn clean package'
            }
        }
    }
}