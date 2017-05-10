
# parti_i1b

[![Join the chat at https://gitter.im/Arquisoft/parti_i1b](https://badges.gitter.im/Arquisoft/parti_i1b.svg)](https://gitter.im/Arquisoft/parti_i1b?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Build Status](https://travis-ci.org/Arquisoft/parti_i1b.svg?branch=master)](https://travis-ci.org/Arquisoft/parti_i1b)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/e680327c40a44a6b8378a8171066e341)](https://www.codacy.com/app/jelabra/parti_i1b?utm_source=github.com&utm_medium=referral&utm_content=Arquisoft/parti_i1b&utm_campaign=badger)
[![codecov](https://codecov.io/gh/Arquisoft/parti_i1b/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/parti_i1b)

# Participation
## Heroku Deployment
[ParticipationSystem Heroku Deployment](https://partsystem.herokuapp.com/).
[Dashboard Heroku Deployment](https://dashboardasw.herokuapp.com/).
## Modules
### citizensLoader
### dashboard
### participationSystem
## Functionality overview
## How to run
```
```
## Building parti_i1b
Provided you have Maven and Git you just have to:
```
 git clone https://github.com/Arquisoft/parti_i1b.git
 cd parti_i1b
 mvn package
```
### Production and Develop profiles
There are two profiles called production and develop.  Develop is the
default one and it configures the application to use the local
database and kafka instances. If the production profile is chosen then
it will connect to the remote server.

The production profile may fail some tests as it expects a clean kafka
and mysql environments.
```
mvn package -P prod
mvn package -P dev
```
