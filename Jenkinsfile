pipeline {
    agent any
    tools{
        maven 'maven_3.9.11'
    }
    stages {
        stage('Build Maven') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/BASU-REPOSY/E_Commerce_Application']])
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
        stage('pushtoDocker'){
            steps{
                withCredentials([string(credentialsId: 'SECRETE', variable: 'hub')]) {
                    sh '''
                    export PATH=$PATH:/usr/local/bin:/opt/homebrew/bin
                    docker login -u basu122 -p ${hub}
                    '''
                }
                sh '''
                    export PATH=$PATH:/usr/local/bin:/opt/homebrew/bin
                    docker push basu122/e-commerce-docker
                    '''
            }
        }
    }
}
