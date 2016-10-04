#!/usr/bin/env bash

./gradlew artifactoryPublish

if [-z "$TRAVIS_TAG" ]; then
	curl -X POST -u ${BINTRAY_USER}:${BINTRAY_PASSWORD} http://oss.jfrog.org/api/plugins/build/promote/snapshotsToBintray/LockableContainers/${TRAVIS_BUILD_NUMBER}
fi
