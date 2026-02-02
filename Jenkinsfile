pipeline {
    agent any

    triggers {
        githubPush()
    }
    
    options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    parameters {
        choice(
            name: 'TEST_PROFILE',
            choices: ['smoke', 'sanity', 'regression', 'master'],
            description: 'Maven profile to run'
        )

        choice(
            name: 'BROWSER',
            choices: ['chrome', 'firefox'],
            description: 'Browser to execute tests'
        )
    }

    environment {
        MAVEN_OPTS = "-Xmx1024m"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn -version'
                bat 'mvn clean compile -DskipTests'
            }
        }

        stage('Test Execution') {
            steps {
                bat """
                mvn clean test ^
                -P${params.TEST_PROFILE} ^
                -Dbrowser=${params.BROWSER}
                """
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'test-results/**/*', allowEmptyArchive: true
            echo "Build finished. Reports archived."
        }

        failure {
            echo "Build failed. Check test-results."
        }

        success {
            echo "Build successful."
        }
    }
}
