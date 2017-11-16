pipeline {
    agent any

    stages {
        stage('Build') { 
            steps { 
                sh 'mvn clean -DskipTests package -U' 
            }
        }
        stage('Test'){
            steps {
                sh 'echo "test"'
            }
        }
        stage('Deploy') {
            steps {
                sh 'echo "No docker, no cry ;)"'
            }
        }
    }
    post {
        always {
            archive '/target/*.jar
        }
    }
}
