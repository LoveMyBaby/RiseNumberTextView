# RiseNumberTextView
A TextView which value is rising with animation.

##Integration
Add the dependencies to your gradle file:
```javascript
dependencies {
    compile 'com.chrischeng:risenumbertextview:0.8.0'
}
```
##XML Usage
-    Use directly:
```xml
<com.chrischeng.risenumbertextview.RiseNumberTextView
        android:id="@+id/rntv
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
```
-   Current Attributes supported:
```xml
<attr name="rntv_num_start" format="float" />
<attr name="rntv_num_end" format="float" />
<attr name="rntv_decimal_place" format="integer" />
<attr name="rntv_anim_duration" format="integer" />
```
##Java Usage
```java
rnTextView = (RiseNumberTextView) findViewById(R.id.rntv);
rnTextView.setNum(0, 10000); // default (0,0)
rnTextView.setDecimalPlace(2); // default 0
rnTextView.setDuration(1000); // default 800
rnTextView.run();
```
## License
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
