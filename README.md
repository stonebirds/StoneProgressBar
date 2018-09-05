# [https://github.com/stonebirds/StoneProgressBar](https://github.com/stonebirds/StoneProgressBar)

[ ![Download](https://api.bintray.com/packages/flybirds/StoneProgressBar/stoneprogressbar/images/download.svg?version=1.0.0) ](https://bintray.com/flybirds/StoneProgressBar/stoneprogressbar/1.0.0/link)

## 自定义进度条

可以根据需求自定义进度条指示器的文字大小、文字颜色以及进度条高度、颜色、进度条背景色

***

效果图

![效果图](https://raw.githubusercontent.com/stonebirds/StoneProgressBar/4bd5528ad44464ccef408c051cf727cd13754070/gif/1.gif)

***

# How to（集成步骤）

## Adding to project
```
<dependency>
  <groupId>com.stone</groupId>
  <artifactId>stoneprogressbar</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

### or
```   
allprojects {
    repositories {
        jcenter()
    }
}

dependencies {
        implementation 'com.stone:stoneprogressbar:1.0.0'
}

```