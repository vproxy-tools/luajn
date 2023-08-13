#!/bin/bash

set -e

./gradlew clean
for id in {2..17}
do
	./gradlew runTutorial -Did=$id
done
