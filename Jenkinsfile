pipeline {
    agent none
    stages {
        stage('Test') {
            agent {
                docker {
                    image 'gradle:5.6.3-jdk8'
                    args '-v /tmp/gradle-caches:/home/gradle/.gradle/caches'
                }
            }
            steps {
                sh 'gradle clean test'
            }
        }
        stage('Build') {
            agent {
                docker {
                    image 'gradle:5.6.3-jdk8'
                    args '-v /tmp/gradle-caches:/home/gradle/.gradle/caches'
                }
            }
            steps {
                sh 'gradle clean build -x test --info'
            }
        }
    }
}
