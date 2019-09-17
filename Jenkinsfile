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

        DOCKER_REGISTRY = "jenkins/test-app"
        DOCKER_IMAGE_VERSION = "latest"
        DOCKER_REGISTRY_CREDENTIAL_ID = "928c8a05-3036-48ed-ab5c-0db5b702ee7a"
        DOCKER_REGISTRY_FULL_URL = "https://10.254.101.100:5000"
        DOCKER_REGISTRY_LIGHT_URL = "10.254.101.100:5000"
        KUBERNETES_DEPLOYMENTS = "test-app-deployment.yaml"
    }

    stages {

        stage('********************** Clean Workspace **********************') {
            steps {
                parallel(
                    cleanWorkSpace: {
                        sh "rm -rf $WORKSPACE/$PROJECT_NAME"
                        sh "docker system prune -f"
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
                sh "docker build -t $DOCKER_REGISTRY_LIGHT_URL/$DOCKER_REGISTRY:$DOCKER_IMAGE_VERSION $WORKSPACE/$PROJECT_NAME"
            }
        }

        stage('********************** push image **********************') {
            steps {
                withDockerRegistry([credentialsId: "$DOCKER_REGISTRY_CREDENTIAL_ID", url: "$DOCKER_REGISTRY_FULL_URL"]) {
                    sh "docker push $DOCKER_REGISTRY_LIGHT_URL/$DOCKER_REGISTRY:$DOCKER_IMAGE_VERSION"
                }
            }
        }

        stage('********************** deploy image **********************') {
            steps {
                sh "kubectl create -f $KUBERNETES_DEPLOYMENTS"
            }
        }

    }
}
