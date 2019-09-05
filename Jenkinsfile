#!groovy

pipeline {
    agent any

    tools {
        maven 'maven-3.6.1'
        jdk 'jdk8'
    }

    environment {
        BACK_NAME = "spring-boot-kubernetes-configmap"
        BACK_REPO = "https://github.com/4ebyrek/spring-boot-kubernetes-configmap-example.git"
        registry = "eldarbai/spring-boot-kubernetes-configmap"
        registryCredential = "185fc063-08bd-4d4c-9754-d369feda5eb7"
    }

    stages {
        stage('********************** Clean Workspace **********************') {
            steps {
                parallel(
                        cleanWorkSpace: {
                            sh "rm -rf $WORKSPACE/$BACK_NAME"
                        }
                )
            }
        }

        stage('********************** Extract repos from git **********************') {
            steps {
                dir("$WORKSPACE/$BACK_NAME") {
                    git branch: "develop", credentialsId: '45172769-dda8-47d2-8177-f3bf97b7958c', url: "$BACK_REPO"
                }
            }
        }

        stage('********************** clean install **********************') {
            steps {
                sh "mvn clean install -f $WORKSPACE/$BACK_NAME/pom.xml"
            }
        }

        stage('********************** build image **********************') {
            steps {
                sh "docker build -t $registry:$env.BUILD_ID $WORKSPACE/$BACK_NAME"
            }
        }

        stage('********************** push image **********************') {
            steps {
                withDockerRegistry([credentialsId: "185fc063-08bd-4d4c-9754-d369feda5eb7", url: ""]) {
                    sh "docker push $registry:$env.BUILD_ID"
                }
            }
        }
    }
}
