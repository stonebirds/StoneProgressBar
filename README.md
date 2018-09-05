# [https://github.com/stonebirds/StoneProgressBar](https://github.com/stonebirds/StoneProgressBar)

[ ![Download](https://api.bintray.com/packages/flybirds/StoneProgressBar/stoneprogressbar/images/download.svg?version=1.0.0) ](https://bintray.com/flybirds/StoneProgressBar/stoneprogressbar/1.0.0/link)

## 自定义进度条

可以根据需求自定义进度条指示器的文字大小、文字颜色以及进度条高度、颜色、进度条背景色

***

效果图

<img src="https://raw.githubusercontent.com/stonebirds/StoneProgressBar/4bd5528ad44464ccef408c051cf727cd13754070/gif/1.gif" width="200" hegiht="400" align=center />

***

# How to（集成步骤）

****

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

***

### 字段说明

| 属性                                                      | 说明                 | 
| :---                                                      | :---                 |
| app:hpb_centerPadding="5dp"                               | 指示器与进度条的间距 |
| app:hpb_progressBarBackgroundColor="@color/colorPrimary"  | 进度条背景色         |
| app:hpb_progressBarForegroundColor="@color/colorAccent"   | 进度条前景色         |
| app:hpb_progressBarHeight="2dp"                           | 进度条高度           |
| app:hpb_textBottomPadding="5dp"                           | 指示器bottomPadding  |
| app:hpb_textLeftPadding="5dp"                             | 指示器leftPadding    |
| app:hpb_textRightPadding="5dp"                            | 指示器rightPadding   |
| app:hpb_textTopPadding="5dp"                              | 指示器topPadding     |
| app:hpb_textSize="12sp"                                   | 指示器文字大小       |
| app:hpb_textColor="#FFFFFF"                               | 指示器文字颜色       |
| app:hpb_progress="20"                                     | 进度值               |
