# SpringBounceRecyclerView [![](https://jitpack.io/v/bleckshiba/SpringBounceRecyclerView.svg)](https://jitpack.io/#bleckshiba/SpringBounceRecyclerView)
Recyclerview with spring bouncing effect as close as default iOS scrolling experience

## Installation
Make sure this is added to `build.gradle`
```
allprojects {
  repositories {
    ...
    maven { url 'https//jitpack.io' }
  }
}
```
and this
```
dependencies {
  ...
  implementation 'com.github.bleckshiba:SpringBounceRecyclerView:0.1.0'
}
```

## Usage
```xml
<!-- usage is totally the same as RecyclerView -->
<com.github.bleckshiba.SpringBounceRecyclerView
  ...
/>
```
```java
 import com.github.bleckshiba.SpringBounceRecyclerView;
 
 class MainActivity extends AppCompatActivity {
   ...
   SpringBounceRecyclerView rv = findViewById(R.id.springbouncerecyclerview);
   rv.setAdapter(your_adapter);
   ...
 }
```
