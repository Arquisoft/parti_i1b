
# citizensLoader0

[![Join the chat at https://gitter.im/Arquisoft/citizensLoader_i1b](https://badges.gitter.im/Arquisoft/citizensLoader_i1b.svg)](https://gitter.im/Arquisoft/citizensLoader_i1b?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Build Status](https://travis-ci.org/Arquisoft/citizensLoader_i1b.svg?branch=master)](https://travis-ci.org/Arquisoft/citizensLoader_i1b)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/e680327c40a44a6b8378a8171066e341)](https://www.codacy.com/app/jelabra/citizensLoader_i1b?utm_source=github.com&utm_medium=referral&utm_content=Arquisoft/citizensLoader0&utm_campaign=badger)
[![codecov](https://codecov.io/gh/Arquisoft/citizensLoader_i1b/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/citizensLoader_i1b)

# Citizens Loader
## Functionality overview
Citizens Loader is a CLI application that processes citizen data files, for example in excel, and inserts said data plus a password in a database. There is also a letter generation system that produces letters in multiple formats with the email and the randomly generated unhashed password.
## How to run
As Citizens Loaders is a CLI application it works with flags and arguments. An example in which 3 files are processed and the letters are generated using the pdf format:
```
mvn spring-boot:run -Drun.arguments="-f src/main/resources/ejemplo.xlsx,-f src/main/resources/ejemplo2.xlsx,-l pdf"
```
## Building Citizens Loader
Provided you have Maven and Git you just have to:
```
 git clone https://github.com/Arquisoft/citizensLoader_i1b.git
 cd citizensLoader_i1b
 mvn package
```

# Authors

Herminio García González (@herminiogg)
Jose Emilio Labra Gayo (@labra)
Ignacio Fernadez Fernandez (@scracho)
Javier Muhlach Relea (@muhlach)
Gabriel del Reguero García(@Tronerfull)
Diego Pérez López(@diegoprzl)
