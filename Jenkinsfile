pipeline {
    agent any

    tools {
        maven 'maven'
    }

    parameters {
        string(name: 'BRANCH', defaultValue: 'main', description: 'Git branch to build')
    }

    stages {

        stage('Checkout') {
            steps {
                            checkout scmGit(branches: [[name: "*/${params.BRANCH}"]], extensions: [], userRemoteConfigs: [[url: 'https://github.com/BASU-REPOSY/E_Commerce_Application']])

            }
        }

        stage('Build Maven') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                  export PATH=$PATH:/usr/local/bin:/opt/homebrew/bin
                  docker build -t basu122/e-commerce-docker .
                '''
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([string(credentialsId: 'SECRETE', variable: 'hub')]) {
                    sh '''
                    export PATH=$PATH:/usr/local/bin:/opt/homebrew/bin
                    docker login -u basu122 -p ${hub}
                    docker push basu122/e-commerce-docker
                    '''
                }
            }
        }
    }
}
