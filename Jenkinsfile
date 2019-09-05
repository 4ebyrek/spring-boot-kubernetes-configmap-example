#!groovy

pipeline {
    agent any

    tools {
        maven 'maven-3.6.1'
        jdk 'jdk8'
    }

    environment {
        PROJECT_NAME = "spring-boot-kubernetes-configmap"
        PROJECT_REPO = "https://github.com/4ebyrek/spring-boot-kubernetes-configmap-example.git"
        PROJECT_GIT_CREDENTIAL_ID = "45172769-dda8-47d2-8177-f3bf97b7958c"
        DOCKER_REGISTRY = "eldarbai/spring-boot-kubernetes-configmap"
        DOCKER_REGISTRY_CREDENTIAL_ID = "185fc063-08bd-4d4c-9754-d369feda5eb7"
    }

    stages {
        stage('********************** Clean Workspace **********************') {
            steps {
                parallel(
                        cleanWorkSpace: {
                            sh "rm -rf $WORKSPACE/$PROJECT_NAME"
                        }
                )
            }
        }

        stage('********************** Extract repos from git **********************') {
            steps {
                dir("$WORKSPACE/$PROJECT_NAME") {
                    git branch: "develop", credentialsId: "$PROJECT_GIT_CREDENTIAL_ID", url: "$PROJECT_REPO"
                }
            }
        }

        stage('********************** clean install **********************') {
            steps {
                sh "mvn clean install -f $WORKSPACE/$PROJECT_NAME/pom.xml"
            }
        }

        stage('********************** build image **********************') {
            steps {
                sh "docker build -t $DOCKER_REGISTRY:$env.BUILD_ID $WORKSPACE/$PROJECT_NAME"
            }
        }

        stage('********************** push image **********************') {
            steps {
                withDockerRegistry([credentialsId: "$DOCKER_REGISTRY_CREDENTIAL_ID", url: ""]) {
                    sh "docker push $DOCKER_REGISTRY:$env.BUILD_ID"
                }
            }
        }
    }
}
