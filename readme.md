# Stars Between

[![Build and Test](https://github.com/ManApart/stars_between/actions/workflows/runTests.yml/badge.svg?branch=master)](https://github.com/ManApart/stars_between/actions/workflows/runTests.yml)

## Running

```
runJvm
runJs
jsBrowserDistribution
```

## Pushing to web

```
aws s3 sync build/distributions/ s3://austinkucera.com/games/stars-between/
```


### Other
If the app complains that Swagger Config needs to be open, enable annotation processing in Intellij

View the swagger at `http://localhost:8080/swagger-ui.html`

### TODO

### Examples

![](./example/floorplan.png)
![](./example/floorplan2.png)
![](./example/planet.png)
