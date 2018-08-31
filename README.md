# StoneProgressBar
## 自定义进度条

可以根据需求自定义进度条指示器的文字大小、文字颜色以及进度条高度、颜色、进度条背景色

***

效果图



***


# How to（集成步骤）

### Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
### Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.stonebirds:StoneProgressBar:v1.0.0'
	}