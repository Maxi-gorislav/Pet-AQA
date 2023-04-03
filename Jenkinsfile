pipeline {
    environment {
        HEADLESS_MODE = 'true'
    }
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run Tests') {
            agent {
                docker {
                    image 'my-ui-tests'
                    args '-v /usr/local/bin/docker:/usr/bin/docker --group-add docker'
                }
            }
            steps {
                script {
                    try {
                        sh './gradlew clean build'
                        sh "./gradlew clean test --info --console=plain -Dtest.single=*Test* -Dorg.gradle.test.worker.maxDaemonIdleTimeMs=2000 -Dorg.gradle.test.worker.maxHeapSize=1024m -Dorg.gradle.caching=false"
                    } catch (Exception e) {
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }

        stage('Copy Allure Results') {
            steps {
                sh 'find . -type d -name "allure-results" -exec cp -r {} . \\;'
            }
        }

        stage('Allure Report') {
            steps {
                script {
                    allure([includeProperties: false, jdk: '', properties: [], reportBuildPolicy: 'ALWAYS', results: [[path: 'allure-results']]])
                }
            }
        }
    }
}